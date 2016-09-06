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

package com.addhen.voto.sdk.model.subscribers;

import com.addhen.voto.sdk.model.BaseResponse;

import java.util.List;

/**
 * Lists subscribers
 *
 * @author Henry Addo
 */
public class ListSubscribersResponse extends BaseResponse {

    /** The returned subscribers list **/
    public Data data;

    @Override
    public String toString() {
        return "ListSubscribersResponse{"
                + super.toString()
                + "moreInfo='" + moreInfo + '\''
                + ", url='" + url + '\''
                + ", pagination=" + pagination
                + ", data=" + data
                + '}';
    }

    public static class Data {

        /** List of subscribers **/
        public List<Subscriber> subscribers;

        @Override
        public String toString() {
            return "Data{"
                    + "subscribers=" + subscribers
                    + '}';
        }
    }
}
