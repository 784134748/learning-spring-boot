/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yalonglee.learning.account.utils.snowflake;

/**
 * UidGenerateException
 *
 * @author yutianbao
 */
public class SnowflakeException extends RuntimeException {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -27048199131316992L;

    /**
     * Constructor with message
     *
     * @param message
     */
    public SnowflakeException(String message) {
        super(message);
    }

    /**
     * Constructor with message format
     *
     * @param msgFormat
     * @param args
     */
    public SnowflakeException(String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
    }

}
