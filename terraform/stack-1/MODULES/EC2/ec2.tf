resource "aws_instance" "web" {
    count                   = "${var.INSTANCE_COUNT}"
    ami                     = "${var.AMIID}"
    instance_type           = "${var.INSTANCE_TYPE}"
    key_name                = "devops"
    subnet_id               = "${element(var.PUBLIC_SUBNETS, count.index)}"
    vpc_security_group_ids  = ["${aws_security_group.for_ec2.id}"]

    tags = {
        Name                = "StudentApp-${var.env}-App-Node-${count.index+1}"
        env                 = "${var.env}"
        CREATED_BY          = "TERRAFORM"
    }
}

resource "null_resource" "webapp" {
    count                       = "${var.INSTANCE_COUNT}"
    provisioner "remote-exec" {
        connection {
            type                = "ssh"
            user                = "centos"
            private_key         = "${file("../centos.pem")}"
            host                = "${element(aws_instance.web.*.private_ip, count.index)}"
        }
        inline = [
        "sudo yum install git ansible -y",
        "ansible-pull -U https://raghudevops36:devops321@gitlab.com/batch36/stack-ansible.git stack.yml -e DBUSER=${var.DBUSERNAME} -e DBPASS=${var.DBPASSWORD} -e DBHOST=${var.DBHOST} -e DBNAME=${var.DBNAME} -e API_URL=${var.API_URL} -e WAR_URL=${var.WAR_URL}",
        ]
    }
}

