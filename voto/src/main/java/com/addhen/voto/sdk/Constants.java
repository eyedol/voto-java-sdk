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

package com.addhen.voto.sdk;

/**
 * @author Henry Addo
 */
public final class Constants {

    /** The default date format. It's yyyy-MM-dd **/
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /** Default pagination limit value **/
    public static final int PAGINATION_LIMIT = 10;

    private Constants() {
        // No instantiation is allowed
    }

    public static class VotoEndpoints {

        /** The default voto api endpoint **/
        public static final String VOTO_API_ENDPOINT = " https://go.votomobile.org/api/";

        /** The version number of the API endpoint **/
        public static final String VOTO_API_ENDPOINT_VERSION = "v1";

        /** The subscribers endpoint **/
        public static final String SUBSCRIBERS = "subscribers";
    }
}
