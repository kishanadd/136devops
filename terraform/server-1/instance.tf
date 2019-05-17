resource "aws_instance" "web1" {
  ami                       = "${data.aws_ami.mycentos.id}"
  instance_type             = "t2.micro"
  subnet_id                 = "subnet-29d59f52"
  vpc_security_group_ids    = ["sg-0b8dd39383c49e3e2"]
  key_name                  = "devops"
  // user_data                 = "${file("userdata-terraform.sh")}"

  tags = {
        Name                = "${upper("student-proj-webapp")}"
        CreatedBy           = "Terraform"
        Project             = "STUDENT"
        ENVIRONMENT         = "TEST"
  }
  
  provisioner "remote-exec" {
    connection {
        type     = "ssh"
        user     = "centos"
        private_key = "${file("/home/centos/devops.pem")}"
    }

    inline = [
      "sudo echo Hello World >/opt/sample"
    ]
  }
}