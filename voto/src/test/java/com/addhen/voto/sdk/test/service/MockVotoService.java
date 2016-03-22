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

package com.addhen.voto.sdk.test.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.addhen.voto.sdk.Constants;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.IfPhoneNumberExists;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.service.VotoService;
import com.addhen.voto.sdk.test.GsonDeserializer;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.mock.BehaviorDelegate;

import static com.addhen.voto.sdk.test.TestHelper.getResource;


/**
 * Mocked {@link VotoService} responses for testing
 *
 * @author Henry Addo
 */
public class MockVotoService implements VotoService {

    private final BehaviorDelegate<VotoService> mDelegate;

    private Gson mGson;

    private GsonDeserializer mGsonDeserializer;

    public MockVotoService(BehaviorDelegate<VotoService> delegate,
            GsonDeserializer gsonDeserializer) {
        mDelegate = delegate;
        mGsonDeserializer = gsonDeserializer;
        mGson = new GsonBuilder()
                .setDateFormat(Constants.DATE_FORMAT)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Override
    public Call<CreateSubscriberResponse> createSubscriber(@Field("phone") String phone,
            @QueryMap Map<String, String> optionalFields) {
        String createResponseJson = null;
        try {
            createResponseJson = getResource(
                    "json/subscriber/create_subscriber_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateSubscriberResponse createSubscriberResponse = mGsonDeserializer
                .deserializeCreateSubscriberResponse();
        return mDelegate.returningResponse(createSubscriberResponse)
                .createSubscriber("", null);
    }

    @Override
    public Call<CreateBulkSubscribersResponse> createBulkSubscribers(
            @Field("phone_numbers") String phone,
            @Field("if_phone_number_exists") IfPhoneNumberExists ifPhoneNumberExists,
            @QueryMap Map<String, String> optionalFields) {

        String createResponseJson = null;
        try {
            createResponseJson = getResource(
                    "json/subscriber/create_bulk_subscribers_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateBulkSubscribersResponse createBulkSubscribersResponse = mGson
                .fromJson(createResponseJson, CreateBulkSubscribersResponse.class);
        return mDelegate.returningResponse(createBulkSubscribersResponse)
                .createBulkSubscribers("", null, null);
    }

    @Override
    public Call<ListSubscribersResponse> listSubscribers(@Query("limit") int limit) {
        String responseJson = null;
        try {
            responseJson = getResource("json/subscriber/list_subscribers_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListSubscribersResponse listSubscribersResponse = mGson
                .fromJson(responseJson, ListSubscribersResponse.class);
        return mDelegate.returningResponse(listSubscribersResponse).listSubscribers(10);
    }

    @Override
    public Call<CreateSubscriberResponse> modifySubscriberDetails(@Path("id") Long id,
            @QueryMap Map<String, String> optionalFields) {
        String responseJson = null;
        try {
            responseJson = getResource("json/subscriber/modify_subscriber_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateSubscriberResponse createSubscriberResponse = mGson
                .fromJson(responseJson, CreateSubscriberResponse.class);
        return mDelegate.returningResponse(createSubscriberResponse)
                .createSubscriber("", null);
    }

    @Override
    public Call<DeleteSubscriberResponse> deleteSubscriber(@Path("id") Long id) {
        String responseJson = null;
        try {
            responseJson = getResource("json/subscriber/delete_subscriber_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DeleteSubscriberResponse deleteSubscriberResponse = mGson
                .fromJson(responseJson, DeleteSubscriberResponse.class);
        return mDelegate.returningResponse(deleteSubscriberResponse).deleteSubscriber(1l);
    }
}
