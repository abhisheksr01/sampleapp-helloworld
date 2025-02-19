owner            = "abhishek"
environment_name = "dev"
description      = "Backend for springbootjpainfra project"
aws_rds_db = {
  name                                  = "springbootjpadb",
  region                                = "eu-west-2",
  region2                               = "eu-west-1",
  engine                                = "postgres"
  engine_version                        = "15",
  family                                = "postgres14",
  major_engine_version                  = "14"
  publicly_accessible                   = false
  instance_class                        = "db.t4g.large",
  allocated_storage                     = 20
  max_allocated_storage                 = 100
  port                                  = 5432
  multi_az                              = true
  maintenance_window                    = "Mon:00:00-Mon:03:00"
  backup_window                         = "03:00-06:00"
  enabled_cloudwatch_logs_exports       = ["postgresql", "upgrade"]
  create_cloudwatch_log_group           = true
  backup_retention_period               = 1
  skip_final_snapshot                   = true
  deletion_protection                   = false
  performance_insights_enabled          = true
  performance_insights_retention_period = 7
  create_monitoring_role                = true
  monitoring_interval                   = 60
  monitoring_role_name                  = "springbootjpa-monitoring-role-name"
  monitoring_role_use_name_prefix       = true
  monitoring_role_description           = "Description for monitoring role"
}

vpc = {
  cidr                                   = "10.99.0.0/18"
  public_subnets                         = ["10.99.0.0/24", "10.99.1.0/24", "10.99.2.0/24"]
  private_subnets                        = ["10.99.3.0/24", "10.99.4.0/24", "10.99.5.0/24"]
  database_subnets                       = ["10.99.7.0/24", "10.99.8.0/24", "10.99.9.0/24"]
  create_database_subnet_group           = false
  create_database_subnet_route_table     = false
  create_database_internet_gateway_route = false
  enable_dns_hostnames                   = false
  enable_dns_support                     = false
}
