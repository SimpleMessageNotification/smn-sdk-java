package com.smn.sample;

public class TopicDemo {

    public static void main(String[] args) {
        ClientDemo clientDemo = new ClientDemo();
        // 创建topic的demo
        clientDemo.createTopic();

        // 更新topic的demo
        clientDemo.updateTopic();

        // 删除topic的demo
        clientDemo.deleteTopic();

        // 查询topic 列表的demo
        clientDemo.listTopics();

        // 查询topic详情的demo
        clientDemo.queryTopicDetail();

        // 查询topic属性的demo
        clientDemo.listTopicAttributes();

        // 更新topic属性的demo
        clientDemo.updateTopicAttribute();

        // 删除指定名称的topic属性的demo
        clientDemo.updateTopicAttribute();

        // 删除所有的topic属性的demo
        clientDemo.deleteTopicAttributes();
    }
}
