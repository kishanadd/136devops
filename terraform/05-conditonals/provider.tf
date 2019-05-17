provider "aws" {}

terraform {
  backend "s3" {
    bucket = "terraform-batch36"
    key    = "05-conditionals/terraform.state"
    region = "us-east-2"
  }
}