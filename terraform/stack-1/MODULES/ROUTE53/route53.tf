resource "aws_route53_record" "app-elb" {
  zone_id = "ZVIUSPP5LVUDJ"
  name    = "studentapp-test.devopsproj.com"
  type    = "CNAME"
  ttl     = "300"
  records = ["${var.APP_ELB}"]
}