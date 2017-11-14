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
package com.smn.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date utils
 * 
 * @author huangqiong
 * @version 0.1
 */
public class DateUtil {
    /**
     * Date format for IAM service
     */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");

    /**
     * Parse the date format string to date
     * <p>
     * Non thread safe, but not affected
     * 
     * @param strDate
     *            Date format string
     * @return {@code Date}
     * @throws ParseException
     *             When the date is parsed, an exception is thrown
     */
    public static Date parseDate(String strDate) throws ParseException {
        return SIMPLE_DATE_FORMAT.parse(strDate);
    }

}
