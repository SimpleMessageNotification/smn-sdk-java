/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http: //www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.smn.service;

import com.smn.common.HttpResponse;
import com.smn.model.request.publish.PublishMsgRequest;

/**
 * Message publish service
 * 
 * @author huangqiong
 *
 * @date 2017年9月9日
 *
 * @version 0.6
 */
public interface PublishService extends CommonService {

    /**
	 * publish message
	 * <p>
	 * success，return<CODE>HttpResponse</CODE>
	 * <p>
	 * failed，return request_id and status
	 * 
	 * @param publishMsgRequest
	 *            {@link PublishMsgRequest} request
	 * @return {@link HttpResponse}
	 *         <p>
	 *         {@code httpCode}
	 *         <p>
	 *         {@code body}Map&lt;String,String%gt;
	 * @throws RuntimeException
	 *             connect error,fail to get iam token ,throw exception
	 */
	HttpResponse publish(PublishMsgRequest publishMsgRequest) throws RuntimeException;

}
