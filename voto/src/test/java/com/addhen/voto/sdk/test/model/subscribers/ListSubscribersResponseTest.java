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

package com.addhen.voto.sdk.test.model.subscribers;

import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.Status;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;


/**
 * @author Henry Addo
 */
public class ListSubscribersResponseTest extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldSuccessfullyDeserializeListSubscribersResponse() throws IOException {
        final ListSubscribersResponse response = mGsonDeserializer.listSubscribers();
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
        String date = formatShowingDate(response.data.subscribers.get(0).startDate);
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

    @Test
    public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
        final ListSubscribersResponse response = mGsonDeserializer.listSubscribers();
        final String toString = response.toString();
        assertFalse(StringUtils.isEmpty(response.toString()));
    }
}
