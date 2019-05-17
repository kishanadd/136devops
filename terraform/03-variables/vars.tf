variable "name" {
    default = "Hello"
}


variable "images" {
  type = "map"

  default = {
    us-east-1 = "ami-4bf3d731"
    us-east-2 = "ami-e1496384"
  }
}



variable "demo" {
    type = "map" 

    default = {
        a = 10
        b = 20
    }
}

variable "image" {}

variable "environment" {}