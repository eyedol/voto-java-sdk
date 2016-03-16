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

package com.addhen.voto.sdk.rxjava.service;

import com.addhen.voto.sdk.model.CreateSubscriberResponse;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author Henry Addo
 */
public interface RxJavaVotoService {

    /** Subscribers **/
    @FormUrlEncoded
    @POST("/user/{userId}/rooms/{roomId}/unreadItems")
    Observable<CreateSubscriberResponse> createSubscriber(
            @Field("phone") String phone,
            @QueryMap Map<String, String> optionalFields
    );
}
