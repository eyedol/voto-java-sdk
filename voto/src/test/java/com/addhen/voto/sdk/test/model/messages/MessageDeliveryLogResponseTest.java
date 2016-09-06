package com.addhen.voto.sdk.test.model.messages;

import com.addhen.voto.sdk.model.messages.MessageDeliveryLogResponse;
import com.addhen.voto.sdk.test.BaseTestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class MessageDeliveryLogResponseTest extends BaseTestCase {

    private MessageDeliveryLogResponse mMessageDeliveryLogResponse;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mMessageDeliveryLogResponse = mGsonDeserializer.getMessageDeliveryLogCount();
        assertNotNull(mMessageDeliveryLogResponse);
    }

    @Test
    public void shouldSuccessfullyDeserializeDeleteMessageResponseTest() throws IOException {
        assertMessageDeliveryLogCountResponse(mMessageDeliveryLogResponse);
    }

    @Test
    public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
        final String toString = mMessageDeliveryLogResponse.toString();
        assertNotNull(toString);
    }
}
