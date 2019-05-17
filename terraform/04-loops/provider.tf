provider "aws" {}

terraform {
  backend "s3" {
    bucket = "terraform-batch36"
    key    = "04-loops/terraform.state"
    region = "us-east-2"
  }
}