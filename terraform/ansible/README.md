# ansible

1. Take two servers, Use one as ansible server and other one as node to manage using ansible.

2. On first node create `devops.pem` file, and copy the conetent from your local system.  Create file in location `/home/centos/devops.pem`. ALso set the permissions properly.

```shell
$ chmod go-rwx /home/centos/devops.pem
```

Note: Ensure the pem is working by connecting with this pem file to second node.

```shell
$ ssh -i devops.pem -l centos <PRIVATE-IP-OF-SECOND-SERVER>
```

3. Install `ansible` on first server.

```shell
$ sudo yum install ansible -y 
```

4. Now we can use ansible to connect to the remote nodes. SO list of nodes to be kept in a file `/home/centos/hosts`. (Second server needs to kept under hosts file)

5. Use ansible to connect to the remote nodes.

```shell
$ ansible -i hosts all -u centos --private-key=devops.pem -m ping
```

![image](/uploads/403f6ee3654343090e747a2b31369c07/image.png)


## Ensure the above one is working.

Now we can get rid of all those options in ansible command line , we can specify those defaults in ansible configuration file.

```shell
$ cd
$ mkdir ansible 
$ cd ansible
$ cp /etc/ansible/ansible.cfg .
$ ansible --version
```

Note: `ansible --version` should show the configuration file as `/home/centos/ansible/ansible.cfg`


## We can add few options to ansible.cfg to avoid giving those options in command line

1. Ansible inventory 

 `inventory      = /home/centos/ansible/hosts`

2. Remote ssh user

`remote_user = centos`

3. Private key file 

`private_key_file = /home/centos/devops.pem`

4. Host key check disable 

`host_key_checking = False` 


