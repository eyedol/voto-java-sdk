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

import com.addhen.voto.sdk.model.subscribers.Status;
import com.addhen.voto.sdk.model.subscribers.Subscriber;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Henry Addo
 */
public class SubscriberDetailsResponseTest extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldSuccessfullyDeserializeSubscriberDetails() throws IOException {
        final SubscriberDetailsResponse response = mGsonDeserializer.listSubscriberDetails();
        assertNotNull(response);
        assertEquals("Subscriber Details", response.message);
        assertEquals(200, (int) response.status);
        assertNotNull(response.data);
        final Subscriber subscriber = response.data.subscriber;
        assertNotNull(subscriber);
        assertEquals(33105, (long) subscriber.id);
        assertEquals("1", subscriber.receiveSms);
        assertEquals("1", subscriber.receiveVoice);
        assertEquals("0", subscriber.receiveData);
        assertEquals("0", subscriber.receiveUssd);
        assertEquals("255754280903", subscriber.phone);
        assertEquals(Status.ACTIVE, subscriber.status);
        String date = formatShowingDate(subscriber.startDate);
        assertEquals("2015-11-13", date);
        assertEquals(200715, (long) subscriber.languageId);
        assertEquals("0", subscriber.isTestSubscriber);
        assertEquals("31,32", subscriber.groupIds);
        assertNotNull(subscriber.properties);
        assertEquals("Firstname Othernames", subscriber.properties.name);
        assertEquals("Someplace", subscriber.properties.location);
        assertEquals("This is a comment", subscriber.properties.comments);
    }

    @Test
    public void shouldTestToStringToMakeSureIsNotEmpty() throws Exception {
        final SubscriberDetailsResponse response = mGsonDeserializer.listSubscriberDetails();
        final String toString = response.toString();
        assertFalse(StringUtils.isEmpty(response.toString()));
    }

    @Test
    public void shouldMakeSureDataToStringIsNotEmpty() throws Exception {
        final SubscriberDetailsResponse response = mGsonDeserializer.listSubscriberDetails();
        assertNotNull(response.data);
        final String toString = response.data.toString();
        assertFalse(StringUtils.isEmpty(response.toString()));
    }
}
