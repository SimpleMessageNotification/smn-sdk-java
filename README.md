# smn-sdk-java
Welcome to Simple Message Notification(SMN)    
The java sdk client for Simple Message Notification

update configuration.properties for user info

```
#socket connector timeout(ms)
socket.connector.timeout=10000
#socket read timeout(ms)
socket.read.timeout=10000

# user name from iam
iam.user.name=liuqiangqiang
# domain name from iam
iam.domain.name=liuqiangqiang
# user passwd from iam
iam.user.password=******
# iam.url 
iam.token.url=https://iam.cn-north-1.myhwclouds.com/v3/auth/tokens
# regionId 
region.id=cn-north-1
# smn.endpoint
smn.endpoint=https://smn.cn-north-1.myhwclouds.com

```

run com.smn.client.SmnClientTest to send a message   

需要注意 由于API网关升级SSL安全协议，只接受TLSV1.1和TLSV1.2的协议    
老版本的JDK低版本无法支持TLS1.1，连接服务端指定TLS协议
