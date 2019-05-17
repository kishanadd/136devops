resource "aws_instance" "web" {
  ami                       = "${var.images["${data.aws_region.current.name}"]}"
  instance_type             = "t2.micro"
  subnet_id                 = "subnet-29d59f52"
  vpc_security_group_ids    = ["sg-0b8dd39383c49e3e2"]
  key_name                  = "devops"

  tags = {
        Name                = "HelloWorld"
        Value               = "${var.demo["b"]}"
  }
}

resource "aws_instance" "web1" {
  ami                       = "${var.image}"
  instance_type             = "t2.micro"
  subnet_id                 = "subnet-29d59f52"
  vpc_security_group_ids    = ["sg-0b8dd39383c49e3e2"]
  key_name                  = "devops"

  tags = {
        Name                = "HelloWorld"
        ENV                 = "${var.environment}"
  }
}