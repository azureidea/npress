npress
======

六特尔原创的java开源blog程序，基于六特尔原创开源的NLF框架构建。
<br />
默认使用csv数据库，下载即可部署使用。
<br />
默认支持csv和mysql数据库，在源代码src/db/（部署后是classes/db/）下有两个数据库配置文件1csvdb和2mysqldb，框架自动选取文件名升序排序的第一个作为默认数据库，因此可以直接将文件名的数字调换来切换数据库。如果确定不需要另外一种数据库，直接删除其配置文件即可。
<br />
后台登录地址：/login.jsp，默认管理员用户名：admin，密码：admin。登录后可修改用户名和密码。
<br />
默认未开启html缓存功能，登录后台后，进系统设置，将是否缓存改为true，保存即可开启缓存。
<br />
默认未开启javascript本地缓存，以便本地测试，部署后请将js/icore.js中debug改为false。
<br />
更多资料请访问http://6tail.cn
