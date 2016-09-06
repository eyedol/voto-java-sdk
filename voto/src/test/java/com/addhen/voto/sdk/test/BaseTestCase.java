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

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.addhen.voto.sdk.DateDeserializer;
import com.addhen.voto.sdk.service.VotoService;
import com.addhen.voto.sdk.test.service.MockVotoService;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * All unit test cases have to inherit from this. This is to make it easier to
 * annotate every class with RunWith annotation
 *
 * @author Henry Addo
 */
@RunWith(JUnit4.class)
public abstract class BaseTestCase {

    protected Gson mGson;

    protected GsonDeserializer mGsonDeserializer;

    @Before
    public void setUp() throws Exception {
        mGson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        mGsonDeserializer = new GsonDeserializer(mGson);
    }

    protected static String formatDate(String pattern, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    protected static String formatShowingDate(Date date) {
        return formatDate("yyyy-MM-dd", date);
    }

    protected MockVotoService getMockVotoService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.demo.com")
                .build();

        // Create a MockRetrofit object with a NetworkBehavior which manages the fake behavior of calls.
        NetworkBehavior behavior = NetworkBehavior.create(new Random(2847));
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .backgroundExecutor(Executors.newSingleThreadExecutor())
                .networkBehavior(behavior)
                .build();

        GsonDeserializer gsonDeserializer = new GsonDeserializer(mGson);

        BehaviorDelegate<VotoService> votoServiceBehaviorDelegate = mockRetrofit
                .create(VotoService.class);
        return new MockVotoService(votoServiceBehaviorDelegate, gsonDeserializer);
    }
}