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

import com.addhen.voto.sdk.model.audio.AudioFile;
import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Henry Addo
 */
public class AudioFileDetailsResponseTest extends BaseTestCase {

  @Before public void setUp() throws Exception {
    super.setUp();
  }

  @Test public void shouldSuccessfullyDeserializeAudioFileDetails() throws IOException {
    final AudioFileDetailsResponse response = mGsonDeserializer.listAudioFileDetails();
    assertNotNull(response);
    assertEquals("Audio File", response.message);
    assertEquals(200, (int) response.status);
    assertNotNull(response.data);
    final AudioFile audioFile = response.data.audioFile;
    assertEquals("Audio Filename A", audioFile.description);
    assertEquals(3, (long) audioFile.languageId);
    assertEquals(47, (int) audioFile.lengthSeconds);
    String created = formatDate("yyyy-MM-dd h:m", audioFile.created);
    assertEquals("2013-04-09 12:57", created);
    String modified = formatDate("yyyy-MM-dd h:m", audioFile.modified);
    assertEquals("2013-04-09 12:57", modified);
  }

  @Test public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
    final AudioFileDetailsResponse response = mGsonDeserializer.listAudioFileDetails();
    final String toString = response.toString();
    assertFalse(StringUtils.isEmpty(response.toString()));
  }
}
