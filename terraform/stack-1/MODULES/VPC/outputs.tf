output "vpc-id" {
  value = "${aws_vpc.main.id}"
}

output "private-subnets" {
    value = "${aws_subnet.private-subnets.*.id}"
}

output "public-subnets" {
    value = "${aws_subnet.public-subnets.*.id}"
}
