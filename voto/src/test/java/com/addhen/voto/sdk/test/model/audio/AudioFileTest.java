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
public class AudioFileTest extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldCreateSubscriberObject() {
        final AudioFile audioFile = initAudioFile();
        assertNotNull(audioFile);
        assertEquals(111, (long) audioFile.id);
        assertEquals("Audio Filename A", audioFile.description);
        assertEquals(3, (long) audioFile.languageId);
        assertEquals(47, (int) audioFile.lengthSeconds);
        String created = formatDate("yyyy-MM-dd h:mm", audioFile.created);
        assertEquals("2013-04-09 12:57", created);
        String modified = formatDate("yyyy-MM-dd h:mm", audioFile.modified);
        assertEquals("2013-04-09 12:57", modified);
    }

    @Test
    public void shouldTestSubscriberToString() {
        final AudioFile response = initAudioFile();
        final String toString = response.toString();
        assertFalse(StringUtils.isEmpty(response.toString()));
    }

    private AudioFile initAudioFile() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd h:mm");
        Date created = null;
        try {
            created = simpleDateFormat.parse("2013-04-09 12:57");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date modified = null;
        try {
            modified = simpleDateFormat.parse("2013-04-09 12:57");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new AudioFile(111l, "Audio Filename A", 3l, 47, created, modified);
    }
}
