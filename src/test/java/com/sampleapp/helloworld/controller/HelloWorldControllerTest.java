package com.sampleapp.helloworld.controller;

import com.sampleapp.helloworld.service.HelloWorldService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HelloWorldControllerTest {

    private HelloWorldController helloWorldController;
    private HelloWorldService mockHelloWorldService;
    private UserDetails userDetails;
    private LocalDate currentLocalDate = LocalDate.now(ZoneId.of("UTC"));
    private LocalDate oneDayOldLocalDate = currentLocalDate.minusDays(1);
    ;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        mockHelloWorldService = mock(HelloWorldService.class);
        helloWorldController = new HelloWorldController(mockHelloWorldService);
        userDetails = new UserDetails();
    }

    @Test
    void updateUserDetails_shouldReturn204NoContent() throws ParseException {
        userDetails.setDateOfBirth(oneDayOldLocalDate);

        ResponseEntity actualResponse = helloWorldController.updateUserDetails("abhishek", userDetails);

        assertEquals(HttpStatus.NO_CONTENT, actualResponse.getStatusCode());
    }

    @Test
    void updateUserDetails_shouldThrowBadRequestException_whenNonAlphabeticUserNameIsPassed() {
        String expectedErrorMessage = "400 UserName must contain only letters";
        String actualErrorMessage = null;
        userDetails.setDateOfBirth(oneDayOldLocalDate);

        try {
            helloWorldController.updateUserDetails("12345abhishek", userDetails);
        } catch (Exception exception) {
            actualErrorMessage = exception.getMessage();
        }

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    void updateUserDetails_shouldThrowBadRequestException_whenCurrentDateIsPassed() {
        String expectedErrorMessage = "400 Date of birth must be a date before the today date";
        String actualErrorMessage = null;
        userDetails.setDateOfBirth(currentLocalDate);

        try {
            helloWorldController.updateUserDetails("abhishek", userDetails);
        } catch (Exception exception) {
            actualErrorMessage = exception.getMessage();
        }

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    void updateUserDetails_shouldThrowBadRequestException_whenFutureDateIsPassed() {
        String expectedErrorMessage = "400 Date of birth must be a date before the today date";
        String actualErrorMessage = null;
        userDetails.setDateOfBirth(currentLocalDate.plusDays(1));

        try {
            helloWorldController.updateUserDetails("abhishek", userDetails);
        } catch (Exception exception) {
            actualErrorMessage = exception.getMessage();
        }

        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    void updateUserDetails_shouldInvokeService_whenValidInputDataIsPassed() {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setUserName("abhishek");
        userDetailsDTO.setDateOfBirth(oneDayOldLocalDate);
        userDetails.setDateOfBirth(oneDayOldLocalDate);

        helloWorldController.updateUserDetails("abhishek", userDetails);

        verify(mockHelloWorldService, times(1)).updateUserDetails(userDetailsDTO);
    }
}