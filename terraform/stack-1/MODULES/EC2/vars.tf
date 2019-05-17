variable "env" {}
variable "PUBLIC_SUBNETS" {
    type = "list"
}
variable "VPCID" {}
variable "VPCCIDR" {}
variable "MGMT_CIDR" {}
variable "DBNAME" {}
variable "DBUSERNAME" {}
variable "DBPASSWORD" {}
variable "DBHOST" {}
variable "AMIID" {}
variable "INSTANCE_TYPE" {}

variable "INSTANCE_COUNT" {}
variable "WAR_URL" {}
variable "API_URL" {}