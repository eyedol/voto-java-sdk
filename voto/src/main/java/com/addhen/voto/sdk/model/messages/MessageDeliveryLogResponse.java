package com.addhen.voto.sdk.model.messages;

import com.addhen.voto.sdk.model.BaseResponse;
import com.addhen.voto.sdk.model.deliverylogs.DeliveryLog;

/**
 * @author Henry Addo
 */
public class MessageDeliveryLogResponse extends BaseResponse {

    /** The returned subscribers list **/
    public DeliveryLog data;
}
