variable "name" {
    default = "cloudwatch"
}

resource "null_resource" "cloudwatch" {
    provisioner "local-exec" {
    command = "echo Hello from cloudwatch module "
  }
}

output "name" {
  value = "${var.name}"
}
