#!/bin/bash

echo "----安装依赖插件----"
yum -y install epel-release wget
mkdir -p /etc/yum.repos.d/bak
mv /etc/yum.repos.d/* /etc/yum.repos.d/bak
wget http://mirrors.aliyun.com/repo/Centos-7.repo -P /etc/yum.repos.d/
wget http://mirrors.aliyun.com/repo/epel-7.repo -P /etc/yum.repos.d/
yum -y install wget vim ntp unzip zip net-tools traceroute htop yum-utils device-mapper-persistent-data lvm2 git

sleep 1
echo "----安装docker----"
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum -y install docker-ce
systemctl start docker
systemctl enable docker   开机启动
docker version

sleep 1
echo "----修改时区----"
timedatectl set-timezone Asia/Shanghai
ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

echo "----优化tcp连接数----"
sleep 1
echo "----用户可用的最大进程数量----"
cat >> /etc/security/limits.conf << EOF
* soft nproc 65536
* hard nproc 65536
* soft nofile 65536
* hard nofile 65536
EOF

sleep 1
echo "----Linux最大进程数最大进程数量----"
cat >> /etc/security/limits.d/20-nproc.conf << EOF
* soft nproc unlimited
* hard nproc unlimited
EOF

sleep 1
#echo "----设置用户登录记录----"
echo '#!/bin/bash
loginFile="/var/log/sshd/sshlogin.log"
user=$USER
ip=${SSH_CLIENT%% *}
#if [ "$user" != "root" ] || [ "$ip" != "192.168.31.88" ]
 #then
echo "LoginUser:"$user"--IP:"$ip"--LoginTime:"`date "+%Y-%m-%d %H:%M:%S"` >> "$loginFile";
#fi' >> /etc/ssh/sshrc
mkdir /var/log/sshd
touch /var/log/sshd/sshlogin.log
chmod -R 777 /var/log/sshd
chmod +x /etc/ssh/sshrc

sleep 1
#echo "----查看历史操作记录，并加时间戳----"
echo 'export HISTTIMEFORMAT="%F %T `whoami` "' >> /etc/profile
source /etc/profile