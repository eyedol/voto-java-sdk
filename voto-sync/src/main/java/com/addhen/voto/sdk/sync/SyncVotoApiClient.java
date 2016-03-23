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
package com.addhen.voto.sdk.sync;

import com.addhen.voto.sdk.BaseApiBuilder;
import com.addhen.voto.sdk.BaseVotoApiClient;
import com.addhen.voto.sdk.util.StringUtils;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.IfPhoneNumberExists;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.service.VotoService;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.QueryMap;

/**
 * @author Henry Addo
 */
public class SyncVotoApiClient extends BaseVotoApiClient {

    private VotoService mSyncSubscribersService;

    public SyncVotoApiClient(VotoService syncSubscribersService) {
        mSyncSubscribersService = syncSubscribersService;
    }

    public CreateSubscriberResponse createSubscriber(String phone,
            Map<String, String> optionalFields) throws IOException {
        if (StringUtils.isEmpty(phone)) {
            throw new IllegalArgumentException("phone is required and shouldn't be null or empty");
        }
        Call<CreateSubscriberResponse> createSubscriber = mSyncSubscribersService.createSubscriber(
                phone,
                optionalFields
        );
        return createSubscriber.execute().body();
    }

    public CreateBulkSubscribersResponse createBulkSubscribers(String phoneNumbers,
            IfPhoneNumberExists ifPhoneNumberExists,
            @QueryMap Map<String, String> optionalFields) throws IOException {
        if (StringUtils.isEmpty(phoneNumbers)) {
            throw new IllegalArgumentException("phoneNumbers is required");
        }

        Call<CreateBulkSubscribersResponse> createBulkSubscribers = mSyncSubscribersService
                .createBulkSubscribers(phoneNumbers, ifPhoneNumberExists, optionalFields);
        return createBulkSubscribers.execute().body();
    }

    public ListSubscribersResponse listSubscribers(int limit) throws IOException {
        if (limit > 0) {
            this.limit = limit;
        }
        Call<ListSubscribersResponse> listSubscribers = mSyncSubscribersService
                .listSubscribers(limit);
        return listSubscribers.execute().body();
    }

    public SubscriberDetailsResponse modifySubscriberDetails(Long id,
            Map<String, String> optionalFields) throws IOException {
        Call<SubscriberDetailsResponse> modifySubscriber = mSyncSubscribersService
                .modifySubscriberDetails(id, optionalFields);
        return modifySubscriber.execute().body();
    }

    public DeleteSubscriberResponse deleteSubscriber(Long id) throws IOException {
        Call<DeleteSubscriberResponse> deleteSubscriber = mSyncSubscribersService
                .deleteSubscriber(id);
        return deleteSubscriber.execute().body();
    }

    public static class Builder extends BaseApiBuilder<Builder, SyncVotoApiClient> {

        public Builder(String apiKey) {
            super(apiKey);
        }

        @Override
        public SyncVotoApiClient build() {
            initDefaultRetrofit();
            Retrofit retrofit = mRetrofitBuilder.build();
            VotoService votoService = retrofit.create(VotoService.class);
            return new SyncVotoApiClient(votoService);
        }
    }
}
