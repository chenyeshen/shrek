# 模块介绍

shrek-service 注册中心

shrek-gateway 网关服务，目前只做了到auth服务的网关

shrek-auth     认证服务 ，  标准springsecurityoauth 2.0认证，实现了密码模式认证，

shrek-upms    用户权限管理模块 ，提供用户权限管理功能，目前只提供通过用户返回用户信息的接口

shrek-codegen   自动生成代码模块 ，功能已实现，模块还需修改



1,先执行 auth.sql 脚本 <br>

2，修改配置文件的数据库链接和redis参数 <br>

3，按上面的顺序启动 redis服务，shrek-service，shrek-gateway，shrek-auth，shrek-upms  <br>

4，启动完成 浏览器访问  http://localhost:9999/auth/oauth/token?username=admin&password=123456&grant_type=password&client_id=frontend&client_secret=frontend <br>

用户名，密码，client_id,client_secret在数据库配置 <br>



#  整体流程    <br>

1，请求到shrek-gateway,根据auth转到 shrek-auth, <br>

2,  shrek-auth 的 springsecurityathos 处理 oauth/token，根据参数判断是密码模式，这里会判断client_id=frontend&client_secret=frontend  2个参数是否和下表一样 <br>

3，shrek-auth 通过feigh调用shrek-upms的通过用户名获取用户信息接口并返回给springsecurityoauth <br>

