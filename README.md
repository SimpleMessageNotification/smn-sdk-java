# smn-sdk-java
欢迎使用SMN服务Java SDK。SMN服务Java SDK 能简化你使用SMN服务的复杂程度。这里向您介绍如何使用Java SDK 并开始调用。
smn的使用说明请查阅[SMN的SDK使用说明](https://github.com/SimpleMessageNotification/smn-sdk-java/wiki)  
  
Welcome to use the SMN Services Java SDK. SMN Services The Java SDK simplifies the complexity of your use of SMN services. Here's how to use the Java SDK and start calling. smn instructions for use, please refer to [SMN's SDK instructions](https://github.com/SimpleMessageNotification/smn-sdk-java/wiki)

# :o:重要提醒 
smn-sdk-java已全面升级到更灵活体验更好的2.0，欢迎使用[smn-sdk-java2.0](https://github.com/SimpleMessageNotification/smn-sdk-java2.0)

# 切换到smn-sdk-java2.0说明
smn-sdk-java2.0与smn-sdk-java采用不同的方式创建服务实例，发送请求。

### 1、创建服务实例的不同
在java2.0中创建服务实例如下:
```java
SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountDomainName",
                 "YourAccountPassword",
                "YourRegionName");
```

而在java1.x中创建服务实例如下:
```
// 设置用户名、密码、DomainName、访问的区域(比如cn-north-1)，
       // 请将以下参数设置为您自己的参数
        CloudAccount cloudAccount = new CloudAccount(
                "YourAccountUserName",
                "YourAccountPassword",
                "YourAccountDomainName",
                "YourRegionId");
       // CloudAccount和SmnClient只需初始化一次，单例实现即可
        SmnClient smnClient = cloudAccount.getSmnClient();
```

:zap: 注意：两者在创建服务实例存在较大区别，切换后要注意修改代码，否则会不兼容，代码运行失败

### 2、 发送请求
在java2.0中，设置请求对象后，统一使用以下方式发送请求, 不同的请求会返回不同的response，如发送短信返回SmsPublishResponse
```java
SmsPublishResponse res = smnClient.sendRequest(smnRequest);
```
