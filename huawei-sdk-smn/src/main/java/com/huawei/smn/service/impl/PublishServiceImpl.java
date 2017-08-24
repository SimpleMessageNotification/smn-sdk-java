/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.huawei.smn.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import com.huawei.smn.common.SmnConfiguration;
import com.huawei.smn.common.utils.ConstantsUtil;
import com.huawei.smn.common.utils.JsonUtil;
import com.huawei.smn.common.utils.ValidationUtil;
import com.huawei.smn.model.SmnRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.smn.common.utils.HttpUtil;
import com.huawei.smn.model.request.publish.PublishMsgRequest;
import com.huawei.smn.service.AbstractCommonService;
import com.huawei.smn.service.PublishService;

import javax.xml.bind.annotation.XmlType;

import static com.huawei.smn.common.utils.ConstantsUtil.DEFAULT_MESSAGE;
import static com.huawei.smn.common.utils.ConstantsUtil.TAGS;

/**
 * Publish service implemented
 * 
 * @author yangyanping
 *
 * @date 2017年8月24日
 *
 * @version 0.1
 */
public class PublishServiceImpl extends AbstractCommonService implements PublishService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishServiceImpl.class);

    /**
     * smn host url
     */
    private String smnEndpoint;

    /**
     * project id
     */
    private String projectId;

    /**
     * message publish
     * 
     * @param smnRequest
     * @return
     * @throws RuntimeException
     */
    public Map<String, Object> publish(PublishMsgRequest smnRequest) throws RuntimeException, UnsupportedEncodingException {
        LOGGER.info("Start to publish message.");

        //check topic urn
        if(!ValidationUtil.validateTopicUrn(smnRequest.getTopicUrn())){
            throw new RuntimeException("topic urn is illegal");
        }

        //check subject
        if(!checkSubject(smnRequest.getSubject())){
            throw new RuntimeException("subject is illegal");
        }

        //check message
        if(smnRequest.getMessageStructure()!=null){
            if(!checkMessageStruct(smnRequest.getMessageStructure())){
               // throw new RuntimeException("messageStructure is illegal");
            }
        }else if(smnRequest.getMessageTemplateName()!=null){
            if(!checkMessageTemplate(smnRequest.getRequestParameterMap())){
                System.out.println("++++++++++++");
                throw new RuntimeException("messageTemplate is illegal");
            }
        }else  {
            //check
            if (!checkMessage(smnRequest.getMessage())) {
                throw new RuntimeException("message is illegal");
            }
        }


        try {
            Map<String, String> requestHeader = smnRequest.getRequestHeaderMap();
            Map<String, Object> requestParam = smnRequest.getRequestParameterMap();
            projectId = getIAMService().getAuthentication().getProjectId();
            smnEndpoint = smnConfiguration.getSmnEndpoint();
            smnRequest.setSmnEndpoint(smnEndpoint);
            smnRequest.setProjectId(projectId);
            String url = buildRequestUrl(smnRequest.getRequestUri());
            buildRequestHeader(requestHeader);
            Map<String, Object> responseMap = HttpUtil.post(requestHeader, requestParam, url);
            return responseMap;
        } catch (Exception e) {
            LOGGER.error("Failed to publish message.", e);
            throw new RuntimeException("Failed to publish message.", e);
        }
    }

    /**
     * 检查subject是否符合规范，<code>true</> 表示符合规范，否则不符合规范
     * @param subject
     * @return boolean  <code>true</> 表示符合规范，否则不符合规范
     */
    private boolean checkSubject(String subject){
        if (subject == null){
            LOGGER.debug("subject is null");
            return  true;
        }
        if(!ValidationUtil.validateSubject(subject)){
            LOGGER.error("Parameter: Subject is invalid. ");
            return false;
        }
        try {
            byte[] b = subject.getBytes("utf-8");
            SmnConfiguration smnConfiguration = new SmnConfiguration();
            //判断消息的主题长度小于512byte
            if(b.length > smnConfiguration.getMaxSubjectLength()){
                LOGGER.error("Parameter: Subject is invalid. . The Length of Subject is {}.",b.length);
                return  false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 检查message是否符合规范 ，<code>true</> 表示符合规范，否则不符合规范
     * @param message
     * @return boolean  <code>true</> 表示符合规范，否则不符合规范
     */
    private boolean checkMessage(String message){
        if (message == null){
            return  false;
        }
        try {
            byte[] b = message.getBytes("utf-8");
            //判断消息的长度小于512byte
            SmnConfiguration smnConfiguration = new SmnConfiguration();
            if(b.length>smnConfiguration.getMaxMessageLength()){
                LOGGER.error("Parameter: message is invalid. . The Length of message is {}.",b.length);
                return  false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkMessageStruct(String messageStruct){
        if (messageStruct==null){
            LOGGER.error("Parameter:MessageStruct is invalid");
            return false;
        }
        try {
            SmnConfiguration smnConfiguration = new SmnConfiguration();
            byte[] b = messageStruct.getBytes(ConstantsUtil.URL_ENCODING);
            if(b.length > smnConfiguration.getMaxMessageLength()){
                LOGGER.error("Parameter:MessageStruct is invalid,it is too long");
                return  false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Object messageObject = JsonUtil.parseJsonMessage(messageStruct);
        if(!(messageObject instanceof Map<?,?>)){
            LOGGER.error("Parameter:MessageStruct is invalid, it is null");
            return false;
        }

        //解析JSON格式
        Map<String,Object> messageMap = (Map<String,Object>) messageObject;
        //消息不是json格式，返回异常
        if(messageMap.size()==0){
            LOGGER.error("Parameter:MessageStruct is invalid. Failed to parse MessageStructure.");
            return false;
        }
        //校验default message string 类型
        if(!(messageMap.get(DEFAULT_MESSAGE) instanceof  String)){
            LOGGER.error("Parameter:MessageStruct is invalid. Default message isn't String.");
            return  false;
        }
        return true;
    }

    private boolean checkMessageTemplate(Map<String,Object> parmMap) throws UnsupportedEncodingException {
        if(parmMap.get(TAGS)!=null){
            if(!(parmMap.get(TAGS) instanceof Map<?,?>)){
                LOGGER.error("Tag is error.");
                return  false;
            }
            //检查每一个tag不能超过1kb
            Map<?,?> tagsmap = (Map<?,?>) parmMap.get(TAGS);
            Iterator<Object> tagValues = (Iterator<Object>) tagsmap.values().iterator();
            Object obj = null;
            byte[] b = null;
            while (tagValues.hasNext()){
                obj = tagValues.next();
                //测试json值为空
                b = obj.toString().getBytes(ConstantsUtil.URL_ENCODING);
                SmnConfiguration smnConfiguration = new SmnConfiguration();
                if (b.length> smnConfiguration.getMaxTagLength()){
                    LOGGER.error("Tag is erro . The tag length is {}.",b.length);
                    return  false;
                }
            }
        }else{
            LOGGER.info("EmptyTag.");
            return  false;
        }
        return true;
    }



}
