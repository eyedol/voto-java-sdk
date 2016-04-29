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

package com.addhen.voto.sdk.test.model.messages;

import com.addhen.voto.sdk.model.Status;
import com.addhen.voto.sdk.model.messages.Message;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class MessageTest extends BaseTestCase {

    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldCreateMessageObject() {
        final Message message = initMessage();
        assertNotNull(message);
        assertEquals(203244l, (long) message.id);
        assertEquals("New message just in case.", message.title);
        assertEquals(Status.YES, message.hasVoice);
        assertEquals(Status.NO, message.hasSms);
        String created = formatDate(DATE_FORMAT, message.created);
        assertEquals("2015-08-24 05:28:48", created);
        String modified = formatDate(DATE_FORMAT, message.modified);
        assertEquals("2015-10-30 09:22:15", modified);
    }

    @Test
    public void shouldTestMessageToString() {
        final Message response = initMessage();
        final String toString = response.toString();
        assertFalse(StringUtils.isEmpty(response.toString()));
    }

    private Message initMessage() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date created = null;
        try {
            created = simpleDateFormat.parse("2015-08-24 05:28:48");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date modified = null;
        try {
            modified = simpleDateFormat.parse("2015-10-30 09:22:15");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Message(203244l, "New message just in case.", Status.YES, Status.NO, created,
                modified);
    }
}