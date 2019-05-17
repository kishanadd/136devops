resource "aws_subnet" "public-subnets" {
    count                       = "${length(var.AZ)}"
    vpc_id                      = "${aws_vpc.main.id}"
    availability_zone           = "${element(var.AZ, count.index)}"
    cidr_block                  = "${cidrsubnet(var.vpc_cidr, 8, count.index)}"
    map_public_ip_on_launch     = true
    tags                        = {
        Name                    = "StudentApp-${var.env}-Public-Subnet-${count.index+1}"
        env                     = "${var.env}"
        CREATED_WITH            = "TERRAFORM"
    }
}

resource "aws_subnet" "private-subnets" {
    count                       = "${length(var.AZ)}"
    vpc_id                      = "${aws_vpc.main.id}"
    availability_zone           = "${element(var.AZ, count.index)}"
    cidr_block                  = "${cidrsubnet(var.vpc_cidr, 8, count.index+3)}"
    map_public_ip_on_launch     = false
    tags                        = {
        Name                    = "StudentApp-${var.env}-Private-Subnet-${count.index+1}"
        env                     = "${var.env}"
        CREATED_WITH            = "TERRAFORM"
    }
}

resource "aws_route_table" "r-igw" {
    vpc_id                      = "${aws_vpc.main.id}"
    tags                        = {
        Name                    = "StudentApp-${var.env}-Public-R-Table"
        env                     = "${var.env}"
        CREATED_WITH            = "TERRAFORM"
  }
}

resource "aws_route" "r-igw-1" {
  route_table_id                = "${aws_route_table.r-igw.id}"
  destination_cidr_block        = "0.0.0.0/0"
  gateway_id                    = "${aws_internet_gateway.igw.id}"
}

resource "aws_route" "r-igw-2" {
  route_table_id                = "${aws_route_table.r-igw.id}"
  destination_cidr_block        = "${var.MGMT_CIDR}"
  vpc_peering_connection_id     = "${aws_vpc_peering_connection.mgmt-vpc-to-app-vpc.id}"
}

resource "aws_route_table" "r-private" {
    vpc_id                      = "${aws_vpc.main.id}"
    tags                        = {
        Name                    = "StudentApp-${var.env}-Private-R-Table"
        env                     = "${var.env}"
        CREATED_WITH            = "TERRAFORM"
  }
}

resource "aws_route" "r-private-1" {
  route_table_id                = "${aws_route_table.r-private.id}"
  destination_cidr_block        = "${var.MGMT_CIDR}"
  vpc_peering_connection_id     = "${aws_vpc_peering_connection.mgmt-vpc-to-app-vpc.id}"
}


resource "aws_route_table_association" "a" {
    count                       = "${length(var.AZ)}"
    subnet_id                   = "${element(aws_subnet.public-subnets.*.id, count.index)}"
    route_table_id              = "${aws_route_table.r-igw.id}"
}

resource "aws_route_table_association" "private-associate" {
    count                       = "${length(var.AZ)}"
    subnet_id                   = "${element(aws_subnet.private-subnets.*.id, count.index)}"
    route_table_id              = "${aws_route_table.r-private.id}"
}

resource "aws_route" "route" {
  route_table_id                = "${var.MGMT_RT}"
  destination_cidr_block        = "${var.vpc_cidr}"
  vpc_peering_connection_id     = "${aws_vpc_peering_connection.mgmt-vpc-to-app-vpc.id}"
}