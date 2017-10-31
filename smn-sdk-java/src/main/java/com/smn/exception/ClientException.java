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
package com.smn.exception;

import com.smn.common.ErrorCode;

/**
 * 尝试访问华为云smn服务时返回的异常信息
 * <p>
 * <p>
 * 用于表示客户端无法处理的消息, 该请求无法被继续处理
 * 比如: 参数为空
 * </p>
 *
 * @author zhangyx
 * @version 0.9
 */
public class ClientException extends RuntimeException {

    private String errorCode = ErrorCode.UNKNOWN;

    /**
     * 构造方法
     */
    public ClientException() {
        super();
    }

    /**
     * 给定异常信息，构造新的实例
     *
     * @param message 异常信息
     */
    public ClientException(String message) {
        super(message);
    }

    /**
     * 给定异常，构造新的实例
     *
     * @param cause 异常原因
     */
    public ClientException(Throwable cause) {
        super(cause);
    }

    /**
     * 给定错误码和异常信息构造实例
     *
     * @param errorCode 错误码
     * @param message   异常信息
     */
    public ClientException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 获取异常错误码
     *
     * @return 异常错误码
     */
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "[error code]:" + errorCode + "," +
                "[message]:" + getMessage();
    }
}
