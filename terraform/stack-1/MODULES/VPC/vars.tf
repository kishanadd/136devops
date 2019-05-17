variable "vpc_cidr" {}
variable "env" {}

#variable "subnet_cidr" {
#    type = "list"
#}
variable "AZ" {
    type = "list"
}

variable "MGMT_VPC" {}
variable "MGMT_CIDR" {}
variable "ACCOUNT_ID" {}
variable "MGMT_RT" {}