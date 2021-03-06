provider "aws" {}

terraform {
  backend "s3" {
    bucket = "terraform-batch36"
    key    = "02-terraform-state/terraform.state"
    region = "us-east-2"
  }
}

data "aws_ami" "example" {
  most_recent      = true
  name_regex       = "MyCentOS-7"
  owners           = ["self"]

  filter {
    name = "name"
    values = ["MyCentOS-7"]
  }

  filter {
    name = "root-device-type"
    values = ["ebs"]
  }

  filter {
    name = "virtualization-type"
    values = ["hvm"]
  }
}

resource "aws_instance" "web" {
  ami                       = "${data.aws_ami.example.image_id}"
  instance_type             = "t2.micro"
  subnet_id                 = "subnet-29d59f52"
  vpc_security_group_ids    = ["sg-0b8dd39383c49e3e2"]
  key_name                  = "devops"

  tags = {
        Name                = "HelloWorld"
  }
}