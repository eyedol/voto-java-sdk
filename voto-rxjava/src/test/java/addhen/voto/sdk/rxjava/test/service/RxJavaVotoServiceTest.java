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

package addhen.voto.sdk.rxjava.test.service;

import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.Status;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.test.GsonDeserializer;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * @author Henry Addo
 */
public class RxJavaVotoServiceTest extends BaseTestCase {

    private MockRxJavaVotoService mMockRxJavaVotoService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GsonDeserializer gsonDeserializer = new GsonDeserializer(mGson);
        mMockRxJavaVotoService = new MockRxJavaVotoService(gsonDeserializer);
    }

    @Test
    public void shouldSuccessfullyCreateSubscriber() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<CreateSubscriberResponse> observable = mMockRxJavaVotoService
                .createSubscriber("", null);
        TestSubscriber<CreateSubscriberResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        CreateSubscriberResponse createSubscriberResponse = result.getOnNextEvents().get(0);
        assertNotNull(createSubscriberResponse);
        assertNotNull(createSubscriberResponse.data);
        assertEquals(430l, (long) createSubscriberResponse.data.id);
    }

    @Test
    public void shouldSuccessfullyCreateBulkSubscribers() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<CreateBulkSubscribersResponse> observable = mMockRxJavaVotoService
                .createBulkSubscribers("", null, null);
        TestSubscriber<CreateBulkSubscribersResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        CreateBulkSubscribersResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Subscriber(s) Created Successfully", response.message);
        assertEquals(5, response.data.size());
        assertEquals(181, (long) response.data.get(0));
    }

    @Test
    public void shouldSuccessfullyDeleteSubscriber() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<DeleteSubscriberResponse> observable = mMockRxJavaVotoService
                .deleteSubscriber(1l);
        TestSubscriber<DeleteSubscriberResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        DeleteSubscriberResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Successfully deleted subscriber", response.message);
    }

    @Test
    public void shouldSuccessfullyListSubscribers() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<ListSubscribersResponse> observable = mMockRxJavaVotoService.listSubscribers(10);
        TestSubscriber<ListSubscribersResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        ListSubscribersResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals(1001, (int) response.code);
        assertEquals("Subscribers fetched successfully", response.message);
        assertEquals("", response.moreInfo);
        assertNotNull(response.message);
        assertEquals("https://go.votomobile.org/api/v1/subscribers?limit=2", response.url);
        assertEquals("https://go.votomobile.org/api/v1/subscribers?limit=2&page_after=376089",
                response.pagination.nextURL);
        assertNull(response.pagination.previousURL);
        assertNotNull(response.data.subscribers);
        assertEquals(2, response.data.subscribers.size());
        assertNotNull(response.data.subscribers.get(0));
        assertEquals(373648l, (long) response.data.subscribers.get(0).id);
        assertEquals("0", response.data.subscribers.get(0).receiveSms);
        assertEquals("1", response.data.subscribers.get(0).receiveVoice);
        assertEquals("0", response.data.subscribers.get(0).receiveData);
        assertEquals("0", response.data.subscribers.get(0).receiveUssd);
        assertEquals("233264164182", response.data.subscribers.get(0).phone);
        assertEquals(Status.ACTIVE, response.data.subscribers.get(0).status);
        String date = BaseTestCase.formatShowingDate(response.data.subscribers.get(0).startDate);
        assertEquals("2014-09-10", date);
        assertEquals(200247l, (long) response.data.subscribers.get(0).languageId);
        assertEquals("0", response.data.subscribers.get(0).isTestSubscriber);
        assertEquals(
                "201031, 201409, 204128, 204129, 204130, 204131, 204132, 204133, 204134, 204135, 204136, 204137",
                response.data.subscribers.get(0).groupIds);
        assertNotNull(response.data.subscribers.get(0).properties);
        assertEquals("Kodjo Antwi", response.data.subscribers.get(0).properties.name);
        assertEquals("Ejisu", response.data.subscribers.get(0).properties.location);
        assertEquals("Out flying kites", response.data.subscribers.get(0).properties.comments);
    }
}
