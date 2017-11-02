package com.smn.sample;

public class SmsDemo {

    public static void main(String[] args) {
        ClientDemo clientDemo = new ClientDemo();
//        // 发送消息
//        clientDemo.smsPublish();
//
//        //查询短信的发送状态的demo
//        clientDemo.listSmsMsgReport();
//
//        // 查询短信的内容
//        clientDemo.getSmsMessage();
//
//        // 查询短信回调事件的demo
//        clientDemo.listSmsCallbackEvent();
//
//        //更新短信回调事件
//        clientDemo.updateSmsCallbackEvent();

        //查询短信签名
//        clientDemo.listSmsSigns();

        //删除签名
        clientDemo.deleteSmsSign();
    }
}
