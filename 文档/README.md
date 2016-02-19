# autoTest
使用servlet，实现WebUI自动化，和接口测试自动化。

# 功能描述


# 项目部署
### eclipse中的部署
1. eclipse和java环境：eclipse+jre7+tomcat7
2. 下载git项目后，在eclipse中直接导入项目
3. 项目依赖的jar包和驱动程序下载后放到lib目录下。下载地址 http://pan.baidu.com/s/1eQQw7Gu。将selenium-server-standalone-2.42.2.jar放到tomcat，lib目录下。如果tomcat，lib目录下没有servlet-api.jar，也将它放进去。
4. 修改配置文件com.test.tools下面Test.properties文件中的配置项
5. 更改eclipse中jar包依赖
6. 创建数据库，运行项目下的mysql.txt创建表。将数据配置写到配置文件中
7. 部署完成后，尝试运行。你还有可能遇到一些问题......请联系i_wanting@yeah.net
8. 如果UI测试结果的图出不来，请在项目运行的tomcat server.xml中增加一个静态资源发布的context，访问根路径请保持与项目一致。
 
# 
### tomcat中的war包部署
1. 下载(http://pan.baidu.com/s/1eQQw7Gu)或者eclipse导出jar包
2. 将selenium-server-standalone-2.42.2.jar放到tomcat，lib目录下。如果tomcat，lib目录下没有servlet-api.jar，也将它放进去。
3. 将war包放到tomcat，webapps目录下
4. 配置tomcat/conf目录下的server.xml。添加：两个context。一个是war包对的context，另一个是静态图片的context。静态图片对的context目录需要和配置文件中的imgPath一致
5. 配置好后启动tomcat。访问地址与context中配置的访问路径有关。如果启动报错，请到tomcat/webapps目录下的war文件名目录下找到Test.properties文件，修改配置。
6. 运行tomcat时，不能使用管理员模式，或者是管理员模式运行net start tomcat。这两种情况会导致用例执行时，看不到浏览器界面。

###any questions : i_wanting@yeah.net
