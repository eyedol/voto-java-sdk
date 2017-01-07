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

import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.test.BaseTestCase;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * @author Henry Addo
 */
public class DeleteSubscriberResponseTest extends BaseTestCase {

  @Before public void setUp() throws Exception {
    super.setUp();
  }

  @Test public void shouldSuccessfullyDeleteSubscriber() throws IOException {
    final DeleteSubscriberResponse response = mGsonDeserializer.deleteSubscriber();
    assertNotNull(response);
    assertEquals(200, (int) response.status);
    assertEquals("Successfully deleted subscriber", response.message);
  }

  @Test public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
    final DeleteSubscriberResponse response = mGsonDeserializer.deleteSubscriber();
    final String toString = response.toString();
    assertEquals("BaseResponse{status=200, message='Successfully deleted subscriber'}", toString);
  }
}
