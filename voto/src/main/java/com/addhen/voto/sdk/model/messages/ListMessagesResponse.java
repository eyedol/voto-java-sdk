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

package com.addhen.voto.sdk.model.messages;

import com.addhen.voto.sdk.model.BaseResponse;
import com.addhen.voto.sdk.model.Pagination;

import java.util.List;

/**
 * @author Henry Addo
 */
public class ListMessagesResponse extends BaseResponse {

    /** The code from the API response **/
    public Integer code;

    /** More info **/
    public String moreInfo;

    /** The to fetch the URL **/
    public String url;

    /** The pagination property **/
    public Pagination pagination;

    /** The returned subscribers list **/
    public Data data;

    public static class Data {

        /** List of subscribers **/
        public List<Message> messages;

        @Override
        public String toString() {
            return "Data{"
                    + "messages=" + messages
                    + '}';
        }
    }
}