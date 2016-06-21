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

package com.addhen.voto.sdk.test.model;

import com.addhen.voto.sdk.model.CreateResponse;
import com.addhen.voto.sdk.test.BaseTestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * @author Henry Addo
 */
public class CreateResponseTest extends BaseTestCase {

    private static final String RESPONSE_PATH = "json/message/create_message_response.json";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldSuccessfullyDeserializeCreateResponse() throws IOException {
        final CreateResponse createResponse = mGsonDeserializer.createResponse(RESPONSE_PATH);
        assertNotNull(createResponse);
        assertNotNull(createResponse);
        assertNotNull(createResponse.data);
        assertEquals(112l, (long) createResponse.data.id);
    }

    @Test
    public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
        final CreateResponse createResponse = mGsonDeserializer.createResponse(RESPONSE_PATH);
        final String toString = createResponse.toString();
        assertEquals(
                "CreateResponse{BaseResponse{status=200, message='Message Created Successfully'}data=Data{id=112}}",
                toString
        );
    }
}
