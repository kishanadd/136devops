module "vpc" {
  source            = "./MODULES/VPC"
  vpc_cidr          = "${var.vpc_cidr}"
  env               = "${var.env}"
  #subnet_cidr       = "${var.subnet_cidr}"
  AZ                = "${data.aws_availability_zones.available.names}"
  MGMT_VPC          = "${var.MGMT_VPC}"
  MGMT_CIDR         = "${var.MGMT_CIDR}"
  ACCOUNT_ID        = "${data.aws_caller_identity.current.account_id}"
  MGMT_RT           = "${data.aws_route_table.default.id}"
}


module "rds" {
  source            = "./MODULES/RDS"
  env               = "${var.env}"
  DBNAME            = "${var.DBNAME}"
  DBUSERNAME        = "${var.DBUSERNAME}"
  DBPASSWORD        = "${var.DBPASSWORD}"
  VPCID             = "${module.vpc.vpc-id}"
  PRIVATE_SUBNETS   = "${module.vpc.private-subnets}"
  VPCCIDR           = "${var.vpc_cidr}"
  MGMT_CIDR         = "${var.MGMT_CIDR}"
}

module "ec2" {
  source            = "./MODULES/EC2"
  env               = "${var.env}"
  PUBLIC_SUBNETS    = "${module.vpc.public-subnets}"
  VPCID             = "${module.vpc.vpc-id}"
  VPCCIDR           = "${var.vpc_cidr}"
  MGMT_CIDR         = "${var.MGMT_CIDR}"
  DBNAME            = "${var.DBNAME}"
  DBUSERNAME        = "${var.DBUSERNAME}"
  DBPASSWORD        = "${var.DBPASSWORD}"
  DBHOST            = "${module.rds.dbaddress}"
  AMIID             = "${var.AMI_ID}"
  INSTANCE_TYPE     = "${var.INSTANCE_TYPE}"
  INSTANCE_COUNT    = "${var.INSTANCE_COUNT}"
  WAR_URL           = "${var.WAR_URL}"
  API_URL           = "${var.API_URL}"
}

module "route53" {
  source            = "./MODULES/ROUTE53"
  APP_ELB           = "${module.ec2.app-elb}"
}