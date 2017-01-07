package com.addhen.voto.sdk.test.model.messages;

import com.addhen.voto.sdk.model.messages.MessageDetailsResponse;
import com.addhen.voto.sdk.test.BaseTestCase;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class MessageDetailsResponseTest extends BaseTestCase {

  private MessageDetailsResponse mMessageDetailsResponse;

  @Before public void setUp() throws Exception {
    super.setUp();
    mMessageDetailsResponse = mGsonDeserializer.getMessageDetails();
    assertNotNull(mMessageDetailsResponse);
  }

  @Test public void shouldSuccessfullyDeserializeDeleteMessageResponseTest() throws IOException {
    assertMessageDetailsResponse(mMessageDetailsResponse);
  }

  @Test public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
    final String toString = mMessageDetailsResponse.toString();
    assertNotNull(toString);
  }
}
