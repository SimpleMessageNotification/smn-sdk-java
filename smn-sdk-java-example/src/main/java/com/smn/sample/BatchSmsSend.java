package com.smn.sample;

import com.smn.account.CloudAccount;
import com.smn.client.SmnClient;
import com.smn.common.HttpResponse;
import com.smn.model.request.sms.SmsPublishRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 批量发送短信
 */
public class BatchSmsSend {
    private static Logger logger = Logger.getLogger(BatchSmsSend.class);

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户domain name
     */
    private String domainName;

    /**
     * 需要访问的region
     */
    private String regionId;

    /**
     * 发送短信需要的签名ID
     */
    private String signId;

    /**
     * 每封邮件间隔时间，单位毫秒，默认600(即1分钟100条),
     * 相同内容的短信发送不能过于频繁
     */
    private int sleepTime = 600;

    /**
     * 短信内容,短信内容中不要出现【】或者[]
     */
    private String content;

    /**
     * 发送列表
     */
    private List<String> phoneList;

    /**
     * 单例 smnclient
     */
    private SmnClient smnClient;

    public BatchSmsSend() {
        load("config/configuration.properties");
        CloudAccount cloudAccount = new CloudAccount(userName, password, domainName, regionId);
        smnClient = cloudAccount.getSmnClient();
    }

    /**
     * 直接发送
     */
    public void send() {
        for (int i = 0; i < phoneList.size(); i++) {
            String phoneNum = phoneList.get(i);

            System.out.println("正在发送第" + (i+1) + "个手机, phone=" + phoneNum);
            logger.info("正在发送第" + (i+1) + "个手机, phone=" + phoneNum);

            SmsPublishRequest request = getRequest(phoneNum);
            HttpResponse response = smnClient.smsPublish(request);

            System.out.println("第" + (i+1) + "个手机发送完成, phone=" + phoneNum + ", result= " + response);
            logger.info("第" + (i+1) + "个手机发送完成, phone[" + phoneNum + "], result[" + response + "]");

            // 发送延时
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取请求
     *
     * @param phone 需要发送的手机号码
     * @return
     */
    private SmsPublishRequest getRequest(String phone) {
        // 构造请求对象
        SmsPublishRequest smsPublishRequest = new SmsPublishRequest();
        // 设置手机号码
        smsPublishRequest.setEndpoint(phone);
        // 设置短信内容，短信内容中不要出现【】或者[]
        smsPublishRequest.setMessage(content);
        // 设置短信签名
        smsPublishRequest.setSignId(signId);
        return smsPublishRequest;
    }

    /**
     * 读取配置文件，并获取联系人和内容
     *
     * @param filePath
     */
    public void load(String filePath) {
        Properties properties = new Properties();

        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
        } catch (IOException e) {
            logger.error("fail to read configuration file.", e);
            throw new RuntimeException(e);
        }

        userName = properties.getProperty("user.name");
        if (StringUtils.isEmpty(userName)) {
            throw new RuntimeException("user.name is null");
        }

        password = properties.getProperty("user.password");
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("user.password is null");
        }

        domainName = properties.getProperty("domain.name");
        if (StringUtils.isEmpty(domainName)) {
            throw new RuntimeException("domain.name is null");
        }

        regionId = properties.getProperty("region.id");
        if (StringUtils.isEmpty(regionId)) {
            throw new RuntimeException("region.id is null");
        }

        signId = properties.getProperty("sign.id");
        if (StringUtils.isEmpty(signId)) {
            throw new RuntimeException("sign.id is null");
        }

        String sleepStr = properties.getProperty("sleep.time");
        if (!StringUtils.isEmpty(sleepStr)) {
            sleepTime = Integer.parseInt(sleepStr);
        }

        // 读取文件内容
        String contentFilePath = properties.getProperty("content.file");
        contentFilePath = StringUtils.isEmpty(contentFilePath) ? "config/content.txt" : contentFilePath;
        readContent(contentFilePath);
        if (StringUtils.isEmpty(content)) {
            throw new RuntimeException("content is null");
        }

        // 读取联系人列表
        String phoneFilePath = properties.getProperty("phone.file");
        phoneFilePath = StringUtils.isEmpty(contentFilePath) ? "config/phone.txt" : phoneFilePath;
        readPhoneList(phoneFilePath);
        if (phoneList == null || phoneList.size() == 0) {
            throw new RuntimeException("phone list is empty");
        }
    }

    /**
     * 读取文件内容，发生
     *
     * @param filePath
     * @return
     */
    public void readPhoneList(String filePath) {
        phoneList = new ArrayList<String>();
        FileInputStream fis = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream(new File(filePath));
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            String s = null;
            while ((s = br.readLine()) != null) {
                if (!StringUtils.isBlank(s)) {
                    phoneList.add(s.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("fail to read phone list file.", e);
        } finally {
            try {
                if (br != null) br.close();
                if (isr != null) isr.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("fail to close phone list file.", e);
            }
        }
    }

    /**
     * 读取文件内容，发生
     *
     * @param filePath
     * @return
     */
    public void readContent(String filePath) {
        StringBuilder result = new StringBuilder();
        FileInputStream fis = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream(new File(filePath));
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(s);
            }
            content = result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("fail to read content file.", e);
        } finally {
            try {
                if (br != null) br.close();
                if (isr != null) isr.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("fail to close content file.", e);
            }

        }
    }
}
