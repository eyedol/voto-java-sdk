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

import com.addhen.voto.sdk.model.Audio;
import com.addhen.voto.sdk.model.SmsContent;
import com.addhen.voto.sdk.model.Status;
import com.addhen.voto.sdk.model.messages.Message;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class MessageTest extends BaseTestCase {

  private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

  @Before public void setUp() throws Exception {
    super.setUp();
  }

  @Test public void shouldCreateMessageObject() {
    final Message message = initMessage();
    assertNotNull(message);
    assertEquals(203244L, (long) message.id);
    assertEquals("New message just in case.", message.title);
    assertEquals(Status.YES, message.hasVoice);
    assertEquals(Status.NO, message.hasSms);
    String created = formatDate(DATE_FORMAT, message.created);
    assertEquals("2015-08-24 05:28:48", created);
    String modified = formatDate(DATE_FORMAT, message.modified);
    assertEquals("2015-10-30 09:22:15", modified);
    assertNotNull(message.smsContents);
    SmsContent smsContent = message.smsContents.get(0);
    assertNotNull(smsContent);
    assertEquals(1, (long) smsContent.languageId);
    assertEquals("Akan", smsContent.languageName);
    assertEquals("Test release 15 Sep 2014", smsContent.smsContent);
    assertNotNull(message.audioFiles);
    Audio audio = message.audioFiles.get(0);
    assertNotNull(audio);
    assertEquals(1, (long) audio.languageId);
    assertEquals("Frafra", audio.languageName);
    assertEquals(1, (long) audio.audioFileId);
    assertEquals("Foo", audio.audioFileDescription);
  }

  @Test public void shouldTestMessageToString() {
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
    return new Message(203244L, "New message just in case.", Status.YES, Status.NO, created,
        modified, Arrays.asList(initSmsContent()), Arrays.asList(initAudio()));
  }

  private SmsContent initSmsContent() {
    return new SmsContent(1L, "Akan", "Test release 15 Sep 2014");
  }

  private Audio initAudio() {
    return new Audio(1L, "Frafra", 1L, "Foo");
  }
}
