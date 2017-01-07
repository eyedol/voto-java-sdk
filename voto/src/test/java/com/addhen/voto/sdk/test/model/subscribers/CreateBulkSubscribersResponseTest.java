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

import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.test.BaseTestCase;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * @author Henry Addo
 */
public class CreateBulkSubscribersResponseTest extends BaseTestCase {

  @Before public void setUp() throws Exception {
    super.setUp();
  }

  @Test public void shouldSuccessfullyDeserializeCreateSubscriberResponse() throws IOException {
    final CreateBulkSubscribersResponse response =
        mGsonDeserializer.deserializeCreateBulkSubscriberResponse();
    assertNotNull(response);
    assertEquals(200, (int) response.status);
    assertEquals("Subscriber(s) Created Successfully", response.message);
    assertEquals(5, response.data.size());
    assertEquals(181, (long) response.data.get(0));
  }

  @Test public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
    final CreateBulkSubscribersResponse response =
        mGsonDeserializer.deserializeCreateBulkSubscriberResponse();
    final String toString = response.toString();
    assertEquals("CreateBulkSubscribersResponse{BaseResponse{status=200, message='Subscriber(s) "
        + "Created Successfully'}data=[181, 182, 183, 184, 185]}", toString);
  }
}
