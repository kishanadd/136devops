output "app-elb" {
  value = "${aws_elb.app.dns_name}"
}

