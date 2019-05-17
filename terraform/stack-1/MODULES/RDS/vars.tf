variable "DBNAME" {}
variable "DBUSERNAME" {}
variable "DBPASSWORD" {}
variable "VPCID" {}
variable "PRIVATE_SUBNETS" {
    type = "list"
}

variable "env"  {}
variable "VPCCIDR" {}

variable "MGMT_CIDR" {}