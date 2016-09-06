package com.addhen.voto.sdk.test.model.messages;

import com.addhen.voto.sdk.model.messages.DeleteMessageResponse;
import com.addhen.voto.sdk.test.BaseTestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * @author Henry Addo
 */
public class DeleteMessageResponseTest extends BaseTestCase {

    private DeleteMessageResponse mDeleteMessageResponse;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mDeleteMessageResponse = mGsonDeserializer.deleteMessage();
    }

    @Test
    public void shouldSuccessfullyDeserializeDeleteMessageResponseTest() throws IOException {
        assertNotNull(mDeleteMessageResponse);
        assertEquals(200, (int) mDeleteMessageResponse.status);
        assertEquals("Successfully deleted message", mDeleteMessageResponse.message);
    }

    @Test
    public void shouldTestToStringToMakeSureItsNotEmpty() throws Exception {
        final String toString = mDeleteMessageResponse.toString();
        System.out.print(toString);
        assertEquals(
                "BaseResponse{status=200, message='Successfully deleted message'}",
                toString
        );
    }
}
