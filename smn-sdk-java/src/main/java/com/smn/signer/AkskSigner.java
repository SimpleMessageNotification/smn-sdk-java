package com.smn.signer;

import com.cloud.sdk.DefaultRequest;
import com.cloud.sdk.Request;
import com.cloud.sdk.auth.credentials.BasicCredentials;
import com.cloud.sdk.auth.signer.SignerFactory;
import com.cloud.sdk.http.HttpMethodName;
import com.smn.common.SmnConfiguration;
import org.apache.http.HttpHeaders;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Signer {

    private SmnConfiguration smnConfiguration;
    private String serviceName;

    public Signer(SmnConfiguration smnConfiguration, String serviceName) {
        this.smnConfiguration = smnConfiguration;
        this.serviceName = serviceName;
    }

    public Map<String, String> get(URL url, HttpMethodName httpMethod) throws Exception {
        return this.getSignHeader(url, null, null, httpMethod);
    }

    public Map<String, String> getSignHeader(URL url, Map<String, String> headers,
                                             InputStream content, HttpMethodName httpMethod)
            throws Exception {

        // Make a request for signing.
        Request request = new DefaultRequest(serviceName);
        try {
            // Set the request address.
            request.setEndpoint(url.toURI());

            String urlString = url.toString();

            String parameters = null;

            if (urlString.contains("?")) {
                parameters = urlString.substring(urlString.indexOf("?") + 1);
                Map parametersmap = new HashMap<String, String>();

                if (null != parameters && !"".equals(parameters)) {
                    String[] parameterarray = parameters.split("&");

                    for (String p : parameterarray) {
                        String key = p.split("=")[0];
                        String value = p.split("=")[1];
                        parametersmap.put(key, value);
                    }
                    request.setParameters(parametersmap);
                }
            }

        } catch (URISyntaxException e) {
            // It is recommended to add logs in this place.
            e.printStackTrace();
        }
        // Set the request method.
        request.setHttpMethod(httpMethod);
        if (headers != null) {
            // Add request header information if required.
            request.setHeaders(headers);
        }
        // Configure the request content.
        request.setContent(content);

        // Select an algorithm for request signing.
        com.cloud.sdk.auth.signer.Signer signer = SignerFactory.getSigner(serviceName, smnConfiguration.getRegionId());
        // Sign the request, and the request will change after the signing.
        signer.sign(request, new BasicCredentials(smnConfiguration.getAccessKeyId(), smnConfiguration.getSecretAccessKey()));

        // Make a request that can be sent by the HTTP client.
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> requestHeaders = request.getHeaders();
        // Put the header of the signed request to the new request.
        for (String key : requestHeaders.keySet()) {
            if (key.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH.toString())) {
                continue;
            }
            map.put(key, requestHeaders.get(key));
        }
        return map;
    }

    public SmnConfiguration getSmnConfiguration() {
        return smnConfiguration;
    }

    public void setSmnConfiguration(SmnConfiguration smnConfiguration) {
        this.smnConfiguration = smnConfiguration;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
