resource "aws_elb" "app" {
  name                          = "studentapp-dev-elb"
  subnets                       = ["${var.PUBLIC_SUBNETS}" ]
  security_groups               = ["${aws_security_group.for_elb.id}"]
  listener {
    instance_port               = 80
    instance_protocol           = "http"
    lb_port                     = 80
    lb_protocol                 = "http"
  }
  health_check {
    healthy_threshold           = 2
    unhealthy_threshold         = 2
    timeout                     = 3
    target                      = "TCP:80"
    interval                    = 30
  }
  instances                     = ["${aws_instance.web.*.id}"]
  cross_zone_load_balancing     = true
  idle_timeout                  = 400
  connection_draining           = true
  connection_draining_timeout   = 400
  tags                          = {
    Name                        = "StudentApp-${var.env}-elb"
    env                         = "${var.env}"
    CREATED_BY                  = "TERRAFORM"
  }
}