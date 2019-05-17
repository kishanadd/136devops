provider "aws" {}

terraform {
  backend "s3" {
    bucket = "terraform-batch36"
    key    = "06-modules/terraform.state"
    region = "us-east-2"
  }
}