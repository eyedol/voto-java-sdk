/*
 * Copyright (c) 2016. Henry Addo Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.addhen.voto.sdk.model;

/**
 * Base class for VOTO API responses. Extend this to provide specific properties for the
 * data property
 *
 * @author Henry Addo
 */
public abstract class BaseResponse {

    /** The code from the API response **/
    public Integer code;

    /** More info **/
    public String moreInfo;

    /** The to fetch the URL **/
    public String url;

    /** The pagination property **/
    public Pagination pagination;

    /** The status code of the request **/
    public Integer status;

    /** Message from the API response **/
    public String message;

    @Override
    public String toString() {
        return "BaseResponse{"
                + "status=" + status
                + ", message='" + message + '\''
                + '}';
    }
}