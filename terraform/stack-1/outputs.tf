resource "local_file" "ec2-elb" {
    content     = "${module.ec2.app-elb}"
    filename = "/tmp/ec2-elb"
}