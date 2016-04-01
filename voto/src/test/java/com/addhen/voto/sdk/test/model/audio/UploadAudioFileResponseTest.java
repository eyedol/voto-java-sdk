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

package com.addhen.voto.sdk.test.model.audio;

import com.addhen.voto.sdk.model.audio.UploadAudioFileResponse;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class UploadAudioFileResponseTest extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldSuccessfullyDeserializeUploadAudioFileResponse() throws IOException {
        final UploadAudioFileResponse uploadAudioFileResponse = mGsonDeserializer
                .uploadAudioFileContent();
        assertNotNull(uploadAudioFileResponse);
        assertEquals(200, (int) uploadAudioFileResponse.status);
        assertEquals("Audio File Uploaded", uploadAudioFileResponse.message);
        assertNotNull(uploadAudioFileResponse.data);
        assertEquals(122, (long) uploadAudioFileResponse.data.id);
    }

    @Test
    public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
        final UploadAudioFileResponse uploadAudioFileResponse = mGsonDeserializer
                .uploadAudioFileContent();
        final String toString = uploadAudioFileResponse.toString();
        assertFalse(StringUtils.isEmpty(toString));
    }

}
