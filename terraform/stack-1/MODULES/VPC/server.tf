resource "aws_instance" "web" {
    count           = 0
  ami               = "ami-020cf2da882fb59e9"
  subnet_id         = "${element(aws_subnet.public-subnets.*.id, count.index)}"
  instance_type     = "t2.micro"


  tags = {
    Name = "HelloWorld"
  }
}
