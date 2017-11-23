package com.smn.model.request.iam;

import com.smn.common.SmnConstants;
import com.smn.model.AbstractSmnRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProjectIdsRequest extends AbstractSmnRequest {
    private static Logger LOGGER = LoggerFactory.getLogger(GetProjectIdsRequest.class);

    /**
     * region name
     */
    private String name;

    /**
     * build and get request url
     */
    @Override
    public String getRequestUri() {
        if (StringUtils.isBlank(name)) {
            LOGGER.error("Get projectId request name is null.");
            throw new RuntimeException("Get projectId request name is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(SmnConstants.IAM_PROJECT_URL);

        // set the request parameter
        String params = getRequestParamString();
        if (!StringUtils.isEmpty(params)) {
            sb.append("?").append(params);
        }
        LOGGER.info("Request url is {}. ", sb.toString());

        return sb.toString();
    }

    /**
     * build and get request parameters
     */
    @Override
    public Map<String, Object> getRequestParameterMap() {
        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        return requestParameterMap;
    }

    /**
     * obtain the get request param
     *
     * @return the param string
     */
    private String getRequestParamString() {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (!StringUtils.isBlank(name)) {
            nameValuePairs.add(new BasicNameValuePair(SmnConstants.NAME, name));
        }

        String param = "";
        if (!nameValuePairs.isEmpty()) {
            try {
                param = EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, Charset.forName("UTF-8")));
            } catch (IOException e) {
                throw new RuntimeException("get request param error");
            }
        }
        return param;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GetProjectIdsRequest [name=").append(name).append("]");
        return builder.toString();
    }
}
