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

package com.addhen.voto.sdk.test;

import com.google.gson.Gson;

import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;

import java.io.IOException;

import static com.addhen.voto.sdk.test.TestHelper.getResource;

/**
 * @author Henry Addo
 */
public final class GsonDeserializer {

    private Gson mGson;

    public GsonDeserializer(Gson gson) {
        mGson = gson;
    }

    public CreateSubscriberResponse deserializeCreateSubscriberResponse() {
        String createResponseJson = null;
        try {
            createResponseJson = getResource(
                    "json/subscriber/create_subscriber_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mGson.fromJson(createResponseJson, CreateSubscriberResponse.class);
    }


    public CreateBulkSubscribersResponse deserializeCreateBulkSubscriberResponse() {
        String createBulkSubscribersResponse = null;
        try {
            createBulkSubscribersResponse = getResource(
                    "json/subscriber/create_bulk_subscribers_response.json");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mGson.fromJson(createBulkSubscribersResponse, CreateBulkSubscribersResponse.class);
    }

    public ListSubscribersResponse listSubscribers() {
        String responseJson = null;
        try {
            responseJson = getResource("json/subscriber/list_subscribers_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mGson.fromJson(responseJson, ListSubscribersResponse.class);
    }

    public CreateSubscriberResponse modifySubscriberDetails() {
        String responseJson = null;
        try {
            responseJson = getResource("json/subscriber/modify_subscriber_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mGson.fromJson(responseJson, CreateSubscriberResponse.class);
    }

    public DeleteSubscriberResponse deleteSubscriber() {
        String responseJson = null;
        try {
            responseJson = getResource("json/subscriber/delete_subscriber_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mGson.fromJson(responseJson, DeleteSubscriberResponse.class);
    }
}
