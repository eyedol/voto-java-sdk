package com.addhen.voto.sdk.model.messages;

import com.addhen.voto.sdk.model.BaseResponse;

/**
 * @author Henry Addo
 */
public class MessageDetailsResponse extends BaseResponse {

  /** The returned message **/
  public Data data;

  public static class Data {

    /** List of messages **/
    public Message message;

    @Override public String toString() {
      return "Data{" + "message=" + message + '}';
    }
  }
}
