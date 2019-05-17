resource "aws_security_group" "for_ec2" {
  name                                  = "For-Ec2"
  description                           = "Allow required ports to server"
  vpc_id                                = "${var.VPCID}"
  ingress {
    from_port                           = 22
    to_port                             = 22
    protocol                            = "TCP"
    cidr_blocks                         = ["${var.VPCCIDR}","${var.MGMT_CIDR}" ]
  }
  ingress {
    from_port                           = 80
    to_port                             = 80
    protocol                            = "TCP"
    cidr_blocks                         = ["${var.VPCCIDR}" ]
  }
  egress {
    from_port                           = 0
    to_port                             = 0
    protocol                            = "-1"
    cidr_blocks                         = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "for_elb" {
  name                                  = "For-ELB"
  description                           = "Allow required ports to ELB"
  vpc_id                                = "${var.VPCID}"
  ingress {
    from_port                           = 80
    to_port                             = 80
    protocol                            = "TCP"
    cidr_blocks                         = ["0.0.0.0/0" ]
  }
  egress {
    from_port                           = 0
    to_port                             = 0
    protocol                            = "-1"
    cidr_blocks                         = ["0.0.0.0/0"]
  }
}