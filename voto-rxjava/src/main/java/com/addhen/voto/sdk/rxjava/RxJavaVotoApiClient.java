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
package com.addhen.voto.sdk.rxjava;

import com.addhen.voto.sdk.BaseApiBuilder;
import com.addhen.voto.sdk.Util.StringUtils;
import com.addhen.voto.sdk.model.CreateSubscriberResponse;
import com.addhen.voto.sdk.rxjava.service.RxJavaVotoService;

import java.io.IOException;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * @author Henry Addo
 */
public class RxJavaVotoApiClient {

    private RxJavaVotoService mRxJavaVotoService;

    public RxJavaVotoApiClient(RxJavaVotoService rxJavaVotoService) {
        mRxJavaVotoService = rxJavaVotoService;
    }

    public Observable<CreateSubscriberResponse> createSubscriber(String phone,
            Map<String, String> optionalFields) throws IOException {
        if (StringUtils.isEmpty(phone)) {
            throw new IllegalArgumentException("phone is required and shouldn't be null or empty");
        }
        Observable<CreateSubscriberResponse> createSubscriber = mRxJavaVotoService.createSubscriber(
                phone,
                optionalFields
        );
        return createSubscriber;
    }

    public static class Builder extends BaseApiBuilder<Builder, RxJavaVotoApiClient> {

        public Builder(String apiKey) {
            super(apiKey);
        }

        @Override
        public RxJavaVotoApiClient build() {
            mRetrofitBuilder.client(mOkHttpClientBuilder.build());
            mRetrofitBuilder.baseUrl(getBaseUrl());
            mRetrofitBuilder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            Retrofit retrofit = mRetrofitBuilder.build();
            RxJavaVotoService rxJavaVotoService = retrofit.create(RxJavaVotoService.class);
            return new RxJavaVotoApiClient(rxJavaVotoService);
        }
    }
}
