module "ec2" {
    source  = "./MODULES/ec2"
    ec2_count = "${var.ec2_count}"
}

module "cloudwatch" {
    source = "./MODULES/cloudwatch"
}


variable "ec2_count" {
    default = 5
}