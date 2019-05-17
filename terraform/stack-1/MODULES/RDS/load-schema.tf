resource "null_resource" "load-schema" {
    #count = 0
  provisioner "local-exec" {
    command = <<EOT
      mysql -h ${aws_db_instance.default.address} -u ${var.DBUSERNAME} -p${var.DBPASSWORD} <MODULES/RDS/schema.sql
   EOT
  }
}