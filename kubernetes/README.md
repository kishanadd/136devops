# kubernetes

## Install with terraform (Code is already available in git repo of ours in terraform repository).. You need to change the  values of network of your account
## Install kubectl 

```
$ curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
$ chmod +x kubectl 
$ mv kubectl /bin
```

## Install aws-iam-authenticator

```
$ curl -o aws-iam-authenticator https://amazon-eks.s3-us-west-2.amazonaws.com/1.12.7/2019-03-27/bin/linux/amd64/aws-iam-authenticator
$ chmod +x aws-iam-authenticator
$ sudo mv aws-iam-authenticator /bin
```

## Terraform apply 

## Generate kubectl config 

```
$ aws eks --region us-east-2 update-kubeconfig --name sample
```