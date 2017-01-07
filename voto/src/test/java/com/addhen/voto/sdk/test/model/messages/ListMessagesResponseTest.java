package com.addhen.voto.sdk.test.model.messages;

import com.addhen.voto.sdk.model.messages.ListMessagesResponse;
import com.addhen.voto.sdk.test.BaseTestCase;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class ListMessagesResponseTest extends BaseTestCase {

  private ListMessagesResponse mListMessagesResponse;

  @Before public void setUp() throws Exception {
    super.setUp();
    mListMessagesResponse = mGsonDeserializer.listMessages();
  }

  @Test public void shouldSuccessfullyDeserializeDeleteMessageResponseTest() throws IOException {
    assertListMessages(mListMessagesResponse);
  }

  @Test public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
    final String toString = mListMessagesResponse.toString();
    assertNotNull(toString);
  }
}
