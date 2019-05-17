resource "aws_db_instance" "default" {
  count = 1
  allocated_storage                     = 20
  storage_type                          = "gp2"
  engine                                = "mysql"
  engine_version                        = "5.7"
  instance_class                        = "db.t2.micro"
  identifier                            = "studentapp-dev-mysql"
  name                                  = "${var.DBNAME}"
  username                              = "${var.DBUSERNAME}"
  password                              = "${var.DBPASSWORD}"
  parameter_group_name                  = "default.mysql5.7"
  db_subnet_group_name                  = "${aws_db_subnet_group.default.id}"
  vpc_security_group_ids                = ["${aws_security_group.allow_mysql_to_rds.id}"]
  skip_final_snapshot                   = true
}

resource "aws_db_subnet_group" "default" {
  name                                  = "default-all-private"
  subnet_ids                            = ["${var.PRIVATE_SUBNETS}"]
  tags                                  = {
        Name                            = "StudentApp-${var.env}-Subnet-Group"
        env                             = "${var.env}"
        CREATED_WITH                    = "TERRAFORM"
  }
}

resource "aws_security_group" "allow_mysql_to_rds" {
  name                                  = "allow_mysql_to_rds"
  description                           = "Allow Mysql Access to RDS"
  vpc_id                                = "${var.VPCID}"
  ingress {
    from_port                           = 3306
    to_port                             = 3306
    protocol                            = "TCP"
    cidr_blocks                         = ["${var.VPCCIDR}",["${var.MGMT_CIDR}"] ]
  }
  egress {
    from_port                           = 0
    to_port                             = 0
    protocol                            = "-1"
    cidr_blocks                         = ["0.0.0.0/0"]
  }
}