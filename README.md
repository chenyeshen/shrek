# shrek
springcloud 全家桶 架构学习中

之前看了一些资料，现在准备自己弄

# shrek-service   注册中心
# shrek-geteway   spring 网关
# shrek-auth      认证中心
# shrek-upms      用户权限管理（未实现）

# 部署执行步骤
1，下载代码，执行SQL文件
2，修改shrek-auth的数据链接和REDis配置
3，分别启动 shrek-service ,shrek-geteway ,shrek-auth


浏览器访问 http://localhost:9999/auth/oauth/token?username=demoUser1&password=123456&grant_type=password&client_id=frontend&client_secret=frontend

或者用postman测试POST
