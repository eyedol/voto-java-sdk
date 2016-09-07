package com.addhen.voto.sdk.test.model;

import com.addhen.voto.sdk.model.SmsContent;
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
public class SmsContentTest extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldCreateSmsContentObject() {
        final SmsContent smsContent = initSmsContent();
        assertNotNull(smsContent);
        assertEquals(1, (long) smsContent.languageId);
        assertEquals("Akan", smsContent.languageName);
        assertEquals("Test release 15 Sep 2014", smsContent.smsContent);
    }

    @Test
    public void shouldTestSubscriberToString() {
        final SmsContent response = initSmsContent();
        final String toString = response.toString();
        assertFalse(StringUtils.isEmpty(response.toString()));
    }

    private SmsContent initSmsContent() {
        return new SmsContent(1l, "Akan", "Test release 15 Sep 2014");
    }
}
