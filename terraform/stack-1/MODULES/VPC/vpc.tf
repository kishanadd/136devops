resource "aws_vpc" "main" {
    cidr_block          = "${var.vpc_cidr}"
    tags                = {
        Name            = "StudentApp-${var.env}-VPC"
        env             = "${var.env}"
        CREATED_WITH    = "TERRAFORM"
    }
}

resource "aws_internet_gateway" "igw" {
  vpc_id                = "${aws_vpc.main.id}"
  tags                  = {
        Name            = "StudentApp-${var.env}-IGW"
        env             = "${var.env}"
        CREATED_WITH    = "TERRAFORM"
  }
}



resource "aws_vpc_peering_connection" "mgmt-vpc-to-app-vpc" {
  peer_owner_id         = "${var.ACCOUNT_ID}"
  peer_vpc_id           = "${aws_vpc.main.id}"
  vpc_id                = "${var.MGMT_VPC}"
  auto_accept           = true
  tags                  = {
        Name            = "mgmt-vpc-to-app-vpc"
        env             = "${var.env}"
        CREATED_WITH    = "TERRAFORM"
  }
}