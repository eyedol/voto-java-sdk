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

package com.addhen.voto.sdk.sample;

import com.addhen.voto.sdk.async.AsyncVotoApiClient;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.rxjava.RxJavaVotoApiClient;
import com.addhen.voto.sdk.sync.SyncVotoApiClient;

import java.io.IOException;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

/**
 * @author Henry Addo
 */
public class Main {

    public static void main(String args[]) {
        syncClient();
        asyncClient();
        rxJavaClient();
    }

    private static void syncClient() {
        SyncVotoApiClient syncVotoApiClient = new SyncVotoApiClient.Builder("api_key")
                .withLogLevel(HttpLoggingInterceptor.Level.BODY)
                .build();
        ListSubscribersResponse listSubscribersResponse = null;
        try {
            listSubscribersResponse = syncVotoApiClient.listSubscribers(10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(listSubscribersResponse);
    }

    private static void asyncClient() {
        AsyncVotoApiClient asyncVotoApiClient = new AsyncVotoApiClient.Builder("api_key")
                .withLogLevel(HttpLoggingInterceptor.Level.BODY)
                .build();
        asyncVotoApiClient.listSubscribers(10, new Callback<ListSubscribersResponse>() {
            @Override
            public void onResponse(Call<ListSubscribersResponse> call,
                    Response<ListSubscribersResponse> response) {
                ListSubscribersResponse listSubscribersResponse = response.body();
                System.out.println(listSubscribersResponse);
            }

            @Override
            public void onFailure(Call<ListSubscribersResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private static void rxJavaClient() {
        RxJavaVotoApiClient rxJavaVotoApiClient = new RxJavaVotoApiClient.Builder("api_key")
                .withLogLevel(HttpLoggingInterceptor.Level.BODY)
                .build();
        rxJavaVotoApiClient.listSubscribers(10).subscribe(new Action1<ListSubscribersResponse>() {
            @Override
            public void call(ListSubscribersResponse listSubscribersResponse) {
                System.out.println(listSubscribersResponse);
            }
        });
    }
}
