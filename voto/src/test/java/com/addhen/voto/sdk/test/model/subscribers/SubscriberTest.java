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
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Henry Addo
 */
public class SubscriberTest extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldCreateSubscriberObject() {
        final Subscriber subscriber = initSubscriber();
        assertNotNull(subscriber);
        assertEquals(373648l, (long) subscriber.id);
        assertEquals("0", subscriber.receiveSms);
        assertEquals("1", subscriber.receiveVoice);
        assertEquals("0", subscriber.receiveData);
        assertEquals("0", subscriber.receiveUssd);
        assertEquals("233264164182", subscriber.phone);
        assertEquals(Status.ACTIVE, subscriber.status);
        assertNotNull(subscriber.startDate);
        String date = formatShowingDate(subscriber.startDate);
        assertEquals("2015-11-13", date);
        assertEquals(200247l, (long) subscriber.languageId);
        assertEquals("0", subscriber.isTestSubscriber);
        assertEquals("31,32", subscriber.groupIds);
        assertNotNull(subscriber.properties);
        assertEquals("Kodjo Antwi", subscriber.properties.name);
        assertEquals("Ejisu", subscriber.properties.location);
        assertEquals("Out flying kites", subscriber.properties.comments);
    }

    @Test
    public void shouldTestSubscriberToString() {
        final Subscriber response = initSubscriber();
        final String toString = response.toString();
        assertFalse(StringUtils.isEmpty(response.toString()));
    }

    private Subscriber initSubscriber() {
        Subscriber.Properties properties = new Subscriber.Properties();
        properties.comments = "Out flying kites";
        properties.location = "Ejisu";
        properties.name = "Kodjo Antwi";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date d = null;
        try {
            d = simpleDateFormat.parse("2015-11-13");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Subscriber subscriber = new Subscriber(
                373648l,
                "0",
                "1",
                "0",
                "0",
                "233264164182",
                d,
                "0",
                "31,32",
                Status.ACTIVE,
                200247l,
                properties
        );
        return subscriber;
    }
}

