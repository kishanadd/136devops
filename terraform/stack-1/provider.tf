provider "aws" {}

terraform {
  backend "s3" {
    bucket = "terraform-batch36"
    key    = "stack-1/terraform.state"
    region = "us-east-2"
  }
}