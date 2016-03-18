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

/**
 * Response returned from the API as a results of creating a new subscriber
 *
 * @author Henry Addo
 */
public class CreateSubscriberResponse extends BaseResponse {

    /** The data returned as a result of creating a subscriber */
    public Data data;

    @Override
    public String toString() {
        return "CreateSubscriberResponse{"
                + super.toString()
                + "data=" + data
                + '}';
    }

    /** The data response */
    public class Data {

        /** The id of the newly created subscriber **/
        public Long id;

        @Override
        public String toString() {
            return "Data{"
                    + "id=" + id
                    + '}';
        }
    }
}
