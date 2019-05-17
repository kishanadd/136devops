provider "aws" {}

terraform {
  backend "s3" {
    bucket = "terraform-batch36"
    key    = "server-1/terraform.state"
    region = "us-east-2"
  }
}