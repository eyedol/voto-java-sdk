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

package com.addhen.voto.sdk.common.test.service;

import com.addhen.voto.sdk.common.service.VotoService;
import com.addhen.voto.sdk.common.test.BaseTestCase;
import com.addhen.voto.sdk.common.test.service.mock.BehaviorDelegate;
import com.addhen.voto.sdk.common.test.service.mock.MockRetrofit;
import com.addhen.voto.sdk.common.test.service.mock.MockVotoService;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit.mock.NetworkBehavior;
import retrofit2.Call;
import retrofit2.Retrofit;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * @author Henry Addo
 */
public class VotoServiceTest extends BaseTestCase {

    private MockVotoService mMockVotoService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        // Create a very simple Retrofit adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.demo.com")
                .build();

        // Create a MockRetrofit object with a NetworkBehavior which manages the fake behavior of calls.
        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();

        BehaviorDelegate<VotoService> votoServiceBehaviorDelegate = mockRetrofit
                .create(VotoService.class);
        mMockVotoService = new MockVotoService(votoServiceBehaviorDelegate);
    }

    @Test
    public void shouldSuccessfullyCreateSubscriber() throws IOException {
        assertNotNull(mMockVotoService);
        Call<CreateSubscriberResponse> call = mMockVotoService
                .createSubscriber("", null);
        CreateSubscriberResponse createSubscriberResponse = call.execute().body();
        assertNotNull(createSubscriberResponse);
        assertNotNull(createSubscriberResponse.data);
        assertEquals(430l, (long) createSubscriberResponse.data.id);
    }

    @Test
    public void shouldSuccessfullyCreateBulkSubscribers() throws IOException {
        assertNotNull(mMockVotoService);
        Call<CreateBulkSubscribersResponse> call = mMockVotoService
                .createBulkSubscribers("", null, null);
        CreateBulkSubscribersResponse response = call.execute().body();
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Subscriber(s) Created Successfully", response.message);
        assertEquals(5, response.data.size());
        assertEquals(181, (long) response.data.get(0));
    }
}
