import com.smn.common.HttpResponse;
import com.smn.common.SmnConfiguration;
import com.smn.common.utils.JsonUtil;
import com.smn.model.request.topic.*;
import com.smn.service.TopicService;
import com.smn.service.impl.TopicServiceImpl;

import java.util.*;

/**
 * topic操作相关的demo
 *
 * @author zhangyx
 * @version 0.7
 */
public class TopicServiceExample {
    public static void main(String[] args) {
        deleteTopicAttributes();
    }

    /**
     * 创建topic的demo
     */
    public static void createTopic() {
        // 创建topic操作的服务类
        TopicService topicService = new TopicServiceImpl();

        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        topicService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        CreateTopicRequest createTopicRequest = new CreateTopicRequest();

        // 设置创建topic的名字
        createTopicRequest.setName("firsttopic_v1");
        // 设置topic的描述信息
        createTopicRequest.setDisplayName("thefirstcreatetopic");

        // 创建topic
        HttpResponse res = topicService.createTopic(createTopicRequest);
        System.out.println(res);
    }

    /**
     * 更新topic的demo
     */
    public static void updateTopic() {
        // 创建topic操作的服务类
        TopicService topicService = new TopicServiceImpl();

        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        topicService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        UpdateTopicRequest updateTopicRequest = new UpdateTopicRequest();

        // 设置topic的唯一资源标示
        updateTopicRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:firsttopic_v1");
        // 设置topic的描述信息
        updateTopicRequest.setDisplayName("thefirstcreatetopic_v2");

        // 更新topic
        HttpResponse res = topicService.updateTopic(updateTopicRequest);
        System.out.println(res);
    }

    /**
     * 删除topic的demo
     */
    public static void deleteTopic() {
        // 创建topic操作的服务类
        TopicService topicService = new TopicServiceImpl();

        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        topicService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest();

        // 设置topic的唯一资源标示
        deleteTopicRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:firsttopic_v1");

        // 删除topic
        HttpResponse res = topicService.deleteTopic(deleteTopicRequest);
        System.out.println(res);
    }

    /**
     * 查询topic 列表的demo
     */
    public static void listTopics() {
        // 创建topic操作的服务类
        TopicService topicService = new TopicServiceImpl();

        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        topicService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        ListTopicsRequest listTopicsRequest = new ListTopicsRequest();

        // 设置每页的最大条目数
        listTopicsRequest.setLimit(100);

        // 设置分页列表的起始页
        listTopicsRequest.setOffset(0);

        // 查询topic列表
        HttpResponse res = topicService.listTopics(listTopicsRequest);
        System.out.println(res);
    }

    /**
     * 查询topic详情的demo
     */
    public static void queryTopicDetail() {
        // 创建topic操作的服务类
        TopicService topicService = new TopicServiceImpl();

        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        topicService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        QueryTopicDetailRequest queryTopicDetailRequest = new QueryTopicDetailRequest();

        // 设置topic的唯一资源标示
        queryTopicDetailRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 查询topic详情
        HttpResponse res = topicService.queryTopicDetail(queryTopicDetailRequest);
        System.out.println(res);
    }

    /**
     * 查询topic属性的demo
     */
    public static void listTopicAttributes() {
        // 创建topic操作的服务类
        TopicService topicService = new TopicServiceImpl();

        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        topicService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        ListTopicAttributesRequest listTopicAttributesRequest = new ListTopicAttributesRequest();

        // 设置topic的唯一资源标示
        listTopicAttributesRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置属性名称
        listTopicAttributesRequest.setAttributesName("access_policy");

        // 查询topic属性列表
        HttpResponse res = topicService.listTopicAttributes(listTopicAttributesRequest);
        System.out.println(res);
    }

    /**
     * 更新topic属性的demo
     */
    public static void updateTopicAttribute() {
        // 创建topic操作的服务类
        TopicService topicService = new TopicServiceImpl();

        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        topicService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        UpdateTopicAttributeRequest updateTopicAttributeRequest = new UpdateTopicAttributeRequest();

        // 设置topic的唯一资源标示
        updateTopicAttributeRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置属性名称
        updateTopicAttributeRequest.setAttributesName("access_policy");

        // 设置属性值
        LinkedHashMap<String, Object> attributeValue = new LinkedHashMap<>();
        attributeValue.put("Version", "2016-09-07");
        attributeValue.put("Id", "__default_policy_ID");

        List<LinkedHashMap<String, Object>> statements= new ArrayList<LinkedHashMap<String, Object>>();
        LinkedHashMap<String, Object> statement1 = new LinkedHashMap<String, Object>();
        statement1.put("Sid", "__user_pub_0");
        statement1.put("Effect", "Allow");

        LinkedHashMap<String, Object> csp1 = new LinkedHashMap<String, Object>();
        csp1.put("CSP", Arrays.asList("urn:csp:iam::1040774eae344b78b14f2939863d4ede:root"));
        statement1.put("Principal", csp1);
        statement1.put("Action", Arrays.asList("SMN:Publish", "SMN:QueryTopicDetail"));
        statement1.put("Resource", "urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");
        statements.add(statement1);

        attributeValue.put("Statement", statements);
        updateTopicAttributeRequest.setAttributeValue(attributeValue);

        // 更新topic属性
        HttpResponse res = topicService.updateTopicAttribute(updateTopicAttributeRequest);
        System.out.println(res);
    }

    /**
     * 删除指定名称的topic属性的demo
     */
    public static void deleteTopicAttributeByName() {
        // 创建topic操作的服务类
        TopicService topicService = new TopicServiceImpl();

        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        topicService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        DeleteTopicAttributeByNameRequest deleteTopicAttributeByNameRequest = new DeleteTopicAttributeByNameRequest();

        // 设置topic的唯一资源标示
        deleteTopicAttributeByNameRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 设置属性名称
        deleteTopicAttributeByNameRequest.setAttributesName("access_policy");

        // 删除topic属性
        HttpResponse res = topicService.deleteTopicAttributeByName(deleteTopicAttributeByNameRequest);
        System.out.println(res);
    }

    /**
     * 删除所有的topic属性的demo
     */
    public static void deleteTopicAttributes() {
        // 创建topic操作的服务类
        TopicService topicService = new TopicServiceImpl();

        // 设置必要请求参数
        SmnConfiguration smnConfiguration = new SmnConfiguration();
        // 设置DomainName/设置用户名/设置密码
        smnConfiguration.setDomainName("XXX");
        smnConfiguration.setUserName("XXXX");
        smnConfiguration.setPassword("XXXXX");

        // 设置访问的地狱
        // 华为云华北区 cn-north-1
        // 华为云华南区 cn-sourth-1
        // 华为华东区 cn-east-2
        smnConfiguration.setRegionId("cn-north-1");
        // 设置配置信息
        topicService.setSmnConfiguration(smnConfiguration);

        // 构造请求对象
        DeleteTopicAttributesRequest deleteTopicAttributesRequest = new DeleteTopicAttributesRequest();

        // 设置topic的唯一资源标示
        deleteTopicAttributesRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:SmnApi");

        // 删除topic属性
        HttpResponse res = topicService.deleteTopicAttributes(deleteTopicAttributesRequest);
        System.out.println(res);
    }
}
