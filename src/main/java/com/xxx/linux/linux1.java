/**
 * 1. xshell和xftp
 *      Xshell 是Windows下的远程访问工具；xftp是xshell配套组件，用于向服务器上传/下载文件；
 *
 * 2。 当前路径：pwd
 *      清屏：clear
 *      打开目录：cd
 *      ls
 *      ll /home 列出目录下文件详情
 *      mkdir -p 创建多级目录; -v 查询创建详情
 *      mkdir --help
 *      cp -r ./src /dest
 *      mv ./src ./dest 移动或重命名
 *      rm -rf 强制迭代删除
 *      find ./src -name *.exe  查询/src目录下名字以.exe结尾的文件
 *      where tree  #查看tree应用位置
 *
 *
 * 3。 vim文本编辑
 *      delete或x  删除单个字符
 *      dd  删除整行
 *      /str   全文查找str字符串，n下一个，N前一个
 *      :% s/old/new/g    替换文件内所有old字符串为new
 *      u  撤销最近一次操作
 *      :wq 或 :wq!   退出并保存
 *      :q! 强制退出，放弃保存
 *
 * 4。 echo "str"
 *      echo "str" > hello.txt  将输出的str字符串写入hello.txt文件
 *      echo "str" >> hello.txt   将输出的str字符串追加到hello.txt文件
 *
 * 5。 cat
 *      cat -n hello.txt   查看文件内容并显示行号
 *      cat -nE hello.txt  查看包含空行内容，空行为单个$
 *      cat a.txt b.txt >> full.txt  将a和b两个文件合并写入到full.txt文件中
 *      cat > test.txt << EOF    产生一个输入流，当写入EOF时结束输入
 *
 *  6。 tail
 *      tail -n 2 test.txt  显示最后两行文本
 *      tail -f test.txt    动态监控日志
 *
 *  7。 grep
 *      grep search test.txt  对test.txt文件中指定的search字符串的行进行搜索
 *      grep s....h test.txt  正则匹配s开头h结尾的字符串的行
 *      grep -v search test.txt  排除包含search字符的行
 *      ll | grep test.txt
 *      ll | grep -E "log[0-9]{1,5}.txt"  正则搜索
 *
 * 8。打包和压缩
 *      tar -zcvf test.tar.gz ./test  压缩
 *      tar -zxvf test.tar.gz -C ./test  解压，-C指定释放路径
 *
 * 9。按照应用程序：yum与rpm
 *      rpm安装时自己解决依赖
 *      yum自动安装依赖
 *      yum search appName  #在仓库中搜索
 *      yum install -y appName  #全自动安装应用和依赖
 *      yum info appName #查看应用信息
 *      yum list installed appName  #查看已安装的应用程序
 *      rpm -ql appName  #查看安装后输出的文件清单
 *      yum remove -y appName #自动卸载指定应用
 *
 *      rpm localinstall -y 本地.rpm文件  #rpm安装本地文件
 *
 * 10。 make编译安装
 *      通过 tar -zcvf *.tar.gz 解压文件；
 *      进入目录，使用make命令，
 *      缺少gcc，使用yun install -y gcc；
 *      每次编译失败之后，需要退回父级目录，删除项目文件然后重新解压应用文件；
 *      然后编译成功，编译后的应用文件在当前项目目录；
 *
 *  11。 应用命令
 *      ifconfig 获取ip地址
 *      netstat -ano 或者 netstat -tulpn #查看网络端口号
 *          t  #显示tcp传输协议的连接状况
 *          u  #显示udp传输协议的连接状况
 *          l  #显示处于监听状态的网络连接
 *          p  #显示应用PID和程序名称
 *          n  #显示ip地址
 *          a  #显示所有连接
 *          o  #显示计时器
 *
 *      ps -ef #查看进程
 *      kill -9 PID  #杀掉进程
 *      ps -ef | grep vim
 *
 * 12. 应用服务化
 *      systemctl
 *          start
 *          stop
 *          restart
 *          enable   #设置开机启动
 *          disable  #禁止开机启动
 *          status   #查看服务状态
 *          daemon-reload    #重载服务配置文件
 *          list-unit-files  #列出所有服务
 *
 *          find / -name *.pid  #查找出应用运行时的pid文件， // /run/redis_6379.pid
 *          ps -ef | grep redis
 *          cd /usr/lib/systemd/system  #应用服务化目录
 *          在服务化目录下创建redis.service文件，内容如下：
 *              [Unit]
 *              Description=Redis
 *              After=syslog.target network.target remote-fs.target nss-lookup.target
 *
 *              [Service]
 *              Type=forking
 *              PIDFile=/run/redis_6397.pid
 *              ExecStart=/usr/local/redis-4.0.14/src/redis-server /usr/local/redis-4.0.14/redis.conf
 *              ExecStop=/bin/kill -s QUIT $MAINPID
 *              PrivateTmp=true
 *
 *              [Install]
 *              WantedBy=multi-user.target
 *
 *
 *      systemctl daemon-reload  #执行该命令，重载上面编写的配置文件
 *      kill -s QUIT PID
 *      systemctl start redis
 *      systemctl status redis  #查看状态
 *      systemctl stop redis
 *      systemctl restart redis
 *      systemctl enable redis  #运行开机启动
 *      systemctl disable redis
 *      systemctl list-unit-files  #查看所有服务
 *
 * 13。用户权限
 *      useradd #创建新用户
 *      passwd #修改密码
 *      usermod #修改用户信息/分配组
 *      groupadd #创建新的用户组
 *      chown  #更改文件属主或组
 *      chmod #更改文件访问权限
 *      newgrp #切换用户当前组
 *
 *          useradd t1
 *          passwd t1
 *          groupadd testor
 *          usermod -g testor t1  #将t1加入testor分组
 *          usermod -G testor,t2 t1  #将t1同时加入testor和t2分组
 *          mkdir ./test
 *          chown t1:testor ./test  #将test目录的组改为testor，属主改为t1
 *          chmod 750 ./test  #将test目录权限调整为750
 *
 * 14。 sudo授权
 *      visudo 编辑配置文件，约100行，添加如下：
 *          t1(用户名)  ALL=(ALL)  NOPASSWD:ALL
 *      visudo -c  #格式检查
 *
 * 15。防火墙
 *      firewall-cmd --state  #防火墙状态
 *      firewall-cmd --list-ports #防火墙放行端口
 *      firewall-cmd --zone=public --permanent --add-port=8080/tcp  #添加端口8080/tcp防火墙永久放行
 *      firewall-cmd --reload  #防火墙配置重载
 *      firewall-cmd --zone=public --permanent --remove-port=8080/tcp  #移除防火墙放行端口
 *      firewall-cmd --reload
 *
 *      firewall-cmd --zone=public --permanent --add-port=8000-9000/tcp  #范围段端口放行
 *
 *  16。 shell
 *      新建deploy_tomcat.sh，编辑内容如下：
 *          echo "准备下载tomcat9："
 *          wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.59/bin/apache-tomcat-9.0.59.tar.gz
 *          echo "正在解压tomcat9："
 *          tar zxf apache-tomcat-9.0.59.tar.gz
 *          echo "防火墙开放8080端口："
 *          firewall-cmd --zone=public --permanent --add-port=8080/tcp
 *          firewall-cmd --reload
 *          echo "启动Tomcat"
 *          cd ./apache-tomcat-9.0.59/bin
 *          ./startup.sh
 *
 *  17。 新安装mysql8
 *      安装之后，使用 vi /var/log/mysqld.log 查看自动生成的密码；
 *      mysql -uroot -p    #登陆
 *      alter user 'root'@'localhost' identified with mysql_native_password by 'new_password';  #修改root用户密码
 *      use mysql;
 *      update user set host='%' where user='root';   #更改root用户可登陆设备
 *      flush privileges; #清空mysql缓存
 *      firewall-cmd --zone=public --permanent --add-port=3306/tcp  #放行防火墙3306端口
 *      firewall-cmd --reload
 *
 *  18。安装tomcat
 *      yum install -y java-1.8.0-openjdk   #安装jdk1.8
 *      which java
 *
 *      tar zxf apache-tomcat-9.0.34.tar.gz
 *      tar zxf oa.war  #解压oa项目文件
 *      mv ./oa ./apache-tomcat-9.0.34/webapps/
 *      yum install -y vim-common
 *      yum install -y vim-enhanced
 *      cd apache-tomcat-9.0.34/webapps/
 *      vim ./oa/WEB_INF/classes/mybatis-config.xml  #编辑数据库连接配置
 *      vim ./config/server.xml  #编辑tomcat配置文件，修改端口号并指定上下文路径
 *      <Context path="/" docBase="oa" />     #将oa上下文路径映射到根路径
 *      ./bin/startup.sh   # 启动tomcat服务
 *      firewall-cmd --zone=public --permanent --add-port=80/tcp
 *      firewall-cmd --reload
 *
 *      mysql服务风险：
 *      firewall-cmd --zone=public --permanent --remove-port=3306/tcp  #先关闭上面设置的防火墙放行3306端口
 *      #设置防火墙特定访问ip的特定端口：只允许oa服务部署机器的ip，访问数据库服务的3306端口，其他访问都不允许
 *      firewall-cmd --permanent --zone=public --add-rich-rule="rule family="ipv4" source address="oa服务部署机器的ip地址" port protocol="tcp" port="3306" accept "
 *      firewall-cmd --reload
 *
 *  19。安装redis
 *      redis下载路径：https://redis.io/download
 *      wget https://download.redis.io/releases/redis-6.2.6.tar.gz
 *      tar xzf redis-6.2.6.tar.gz
 *      cd redis-6.2.6
 *      make   #编译redis
 *      ./src/redis-server redis.config   #启动redis服务，并引用配置文件
 *      ./src/redis-cli   #使用redis客户端
 *      ./src/redis-cli -p 6379
 *      ./src/redis-cli shutdown  #关闭redis服务
 *
 *  20。redis常用基本配置
 *      daemonize yes  #是否启用后台运行（守护进程），默认no
 *      port 6379       #设置端口号，默认6379
 *      logfile 日志文件   #设置日志文件
 *      database 255     #设置redis数据库总量
 *      dir 数据文件目录   #设置数据文件存储目录
 *      requirepass 12345  #设置使用密码
 *
 *      protected-mode no #关闭保护模式，允许其他主机访问redis
 *      bind 0.0.0.0  #允许所有IP的主机访问 TODO 生产环境禁止这样配置
 *
 *      redis 命令：
 *      select 1 #使用1号数据库
 *      auth 12345  #使用密码鉴权登陆
 *      set key value  #设置key值value
 *      get key   #获取key的值
 *      keys he*   #根据Pattern表达式查询符合条件的key
 *      dbsize     #返回key的总数
 *      exists a   #检查key=a是否存在
 *      del a      #删除key=a的数据
 *      expire a 20    #设置key=a的过期时间为20秒
 *      ttl a      #查看key=a的过期时间
 *      clear      #清屏
 *
 *      String：最大512M，建议单个kv不超过100K
 *          mset name1 v1 name2 v2
 *          mget name1 name2
 *
 *      Hash：哈希
 *          hset
 *          hget
 *          hgetall
 *          hmset key name1 v1 name2 v2 name3 v3
 *          hdel key name1
 *          hlen
 *          hexists
 *
 *      List:列表
 *          rpush listKey c b a   #右侧插入cba
 *          lpush listKey f e d   #左侧插入fed
 *          rpop listKey   #右侧弹出
 *          lpop listKey   #左侧弹出
 *          lrange listKey 0 -1  #取出所有元素
 *
 *      Set和Zset：
 *          set无序，元素唯一
 *          zset有序，元素唯一
 *
 *          sadd key value
 *          smembers key
 *          sinter set1 set2   #获取交集
 *          sunion set1 set2   #并集
 *          sdiff  set1 set2   #差集
 *
 *          zadd key1 100 a
 *          zadd key1 101 b
 *          zadd key1 102 c
 *          zrange key1 0 -1 [withscores]
 *
 *
 *
 *
 *
 */