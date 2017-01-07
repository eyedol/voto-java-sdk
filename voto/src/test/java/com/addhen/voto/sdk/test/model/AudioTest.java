package com.addhen.voto.sdk.test.model;

import com.addhen.voto.sdk.model.Audio;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class AudioTest extends BaseTestCase {

  @Before public void setUp() throws Exception {
    super.setUp();
  }

  @Test public void shouldCreateAudioObject() {
    final Audio audio = initAudio();
    assertNotNull(audio);
    assertEquals(1, (long) audio.languageId);
    assertEquals("Frafra", audio.languageName);
    assertEquals(1, (long) audio.audioFileId);
    assertEquals("Foo", audio.audioFileDescription);
  }

  @Test public void shouldTestSubscriberToString() {
    final Audio response = initAudio();
    final String toString = response.toString();
    assertFalse(StringUtils.isEmpty(response.toString()));
  }

  private Audio initAudio() {
    return new Audio(1L, "Frafra", 1L, "Foo");
  }
}
