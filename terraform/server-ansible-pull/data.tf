data "aws_ami" "mycentos" {
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