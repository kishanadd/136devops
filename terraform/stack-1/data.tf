data "aws_availability_zones" "available" {}

data "aws_caller_identity" "current" {}

data "aws_route_table" "default" {
  vpc_id = "${var.MGMT_VPC}"
}
