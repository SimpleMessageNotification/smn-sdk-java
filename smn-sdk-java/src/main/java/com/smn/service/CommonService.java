/*
 * Copyright (C) 2017. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
 */
package com.smn.service;

import com.smn.common.SmnConfiguration;

/**
 * common service interface
 * 
 * @author huangqiong
 * @version 0.1
 */
public interface CommonService {

    /**
     * configure runtime environment
     * 
     * @param smnConfiguration
     *            configure runtime parameters
     */
    void setSmnConfiguration(SmnConfiguration smnConfiguration);
}
