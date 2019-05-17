provider "aws" {}

terraform {
  backend "s3" {
    bucket = "terraform-batch36"
    key    = "03-variables/terraform.state"
    region = "us-east-2"
  }
}