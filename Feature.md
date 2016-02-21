# ![Images/Testlogo.png](https://github.com/learningperl/autoTest/blob/master/WebContent/static/Images/Testlogo.png)
使用servlet，实现WebUI自动化，和接口测试自动化。


# 框架说明
    自己写的一个基于java的自动化测试框架。主要用到的技术包括java tomcat，Selenium，Mysql。测试框架原理是基于：关键字+
数据驱动的原理实现数据驱动自动化测试的一个简单框架。框架实现了WebUI和接口测试的自动化，其中接口测试用例生成的模块还没有
完成。如下图：

![all.png](https://github.com/learningperl/autoTest/blob/master/%E6%96%87%E6%A1%A3/img/all.png)


# 使用说明
### UI.jsp-UI自动化页面

![Key.png](https://github.com/learningperl/autoTest/blob/master/%E6%96%87%E6%A1%A3/img/Key.png)
如上图：

1. 添加关键字：点击图中加号按钮，会弹出添加框，填写信息后保存即可。删除和编辑类似。
2. 搜索功能：根据关键字名字和描述做模糊匹配。

### index.jsp-关键字页面

![UI.png](https://github.com/learningperl/autoTest/blob/master/%E6%96%87%E6%A1%A3/img/UI.png)
如上图：

1. 添加、删除和编辑搜索类似关键字页面。
2. 执行：用例场景有复选框，选定后点击运行按钮，即可运行选定的用例场景。
3. 调试：输入用例id，点击调试按钮，就能执行单个用例。需要注意的是，调试之前必须先执行打开浏览器的用例。调试功能可以中途
加入人工操作页面，方便操作。
4. 查看结果：用例执行后，页面会显示用例执行的状态，有PASS、FAIL、N/A三种。点击图中的小图片可以查看用例执行后的大截图

### Interface.jsp-接口测试自动化页面

![Interface.png](https://github.com/learningperl/autoTest/blob/master/%E6%96%87%E6%A1%A3/img/Interface.png)
如上图：

1. 添加、删除和编辑搜索类似关键字页面。
2. 执行：用例场景有复选框，选定后点击运行按钮，即可运行选定的用例场景。
3. 调试功能：未实现
4. 查看结果：用例执行后，页面会显示用例执行的状态，有PASS、FAIL、N/A三种。点击图中Json图片可以查看用例执行后的Json结果
 
### 其他功能为实现

###any questions : i_wanting@yeah.net
