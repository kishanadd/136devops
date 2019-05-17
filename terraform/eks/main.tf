module "my-cluster" {
  source          = "terraform-aws-modules/eks/aws"
  cluster_name    = "sample"
  subnets         = ["subnet-dcf4e1b5", "subnet-904adcdd", "subnet-29d59f52"]
  vpc_id          = "vpc-4271752b"
  cluster_version = "1.12"

  worker_groups = [
    {
      instance_type = "t2.small"
      asg_max_size  = 3
    }
  ]

  tags = {
    environment = "sample"
  }
}