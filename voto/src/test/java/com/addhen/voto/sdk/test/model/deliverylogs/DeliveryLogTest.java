package com.addhen.voto.sdk.test.model.deliverylogs;

import com.addhen.voto.sdk.model.deliverylogs.DeliveryLog;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class DeliveryLogTest extends BaseTestCase {

  private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

  @Before public void setUp() throws Exception {
    super.setUp();
  }

  @Test public void shouldCreateDeliveryLogObject() {
    final DeliveryLog deliveryLog = initDeliveryLog();
    assertNotNull(deliveryLog);
    assertEquals(1L, (long) deliveryLog.messageId);
    assertEquals(DeliveryLog.DeliveryStatus.Queued, deliveryLog.filterDeliveryStatus);
    String created = formatDate(DATE_FORMAT, deliveryLog.filterBeforeDate);
    assertEquals("2015-08-24 05:28:48", created);
    String modified = formatDate(DATE_FORMAT, deliveryLog.filterAfterDate);
    assertEquals("2015-10-30 09:22:15", modified);
  }

  @Test public void shouldTestMessageToString() {
    final DeliveryLog deliveryLog = initDeliveryLog();
    final String toString = deliveryLog.toString();
    assertFalse(StringUtils.isEmpty(deliveryLog.toString()));
  }

  private DeliveryLog initDeliveryLog() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
    Date created = null;
    try {
      created = simpleDateFormat.parse("2015-08-24 05:28:48");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    Date modified = null;
    try {
      modified = simpleDateFormat.parse("2015-10-30 09:22:15");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new DeliveryLog(1L, DeliveryLog.DeliveryStatus.Queued, modified, created);
  }
}
