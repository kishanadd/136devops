resource "aws_instance" "web1" {
  count                     = "${var.environment ? 1 : 0 }"
  ami                       = "ami-e1496384"
  instance_type             = "t2.micro"
  subnet_id                 = "subnet-29d59f52"
  vpc_security_group_ids    = ["sg-0b8dd39383c49e3e2"]
  key_name                  = "devops"

  tags = {
        Name                = "${upper("helloworld")}"
  }
}

variable "environment" {}
