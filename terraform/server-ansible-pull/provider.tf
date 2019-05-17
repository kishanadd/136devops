provider "aws" {}

terraform {
  backend "s3" {
    bucket = "terraform-batch36"
    key    = "server-ansible-pull/terraform.state"
    region = "us-east-2"
  }
}