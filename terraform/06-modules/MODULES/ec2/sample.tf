variable "name" {
    default = "ec2"
}

variable "ec2_count" {}

resource "null_resource" "ec2" {
    provisioner "local-exec" {
    command = "echo Hello from ec2 module "
  }
}

output "name" {
  value = "${var.name}-${var.ec2_count}"
}
