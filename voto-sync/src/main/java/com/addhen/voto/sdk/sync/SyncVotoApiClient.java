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
import com.addhen.voto.sdk.Util.StringUtils;
import com.addhen.voto.sdk.common.service.VotoService;
import com.addhen.voto.sdk.model.CreateSubscriberResponse;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @author Henry Addo
 */
public class SyncVotoApiClient {

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
