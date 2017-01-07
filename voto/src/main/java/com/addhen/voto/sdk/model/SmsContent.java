package com.addhen.voto.sdk.model;

/**
 * @author Henry Addo
 */
public class SmsContent {

  /** The language id of the SMS */
  public Long languageId;

  /** The language name of the SMS */
  public String languageName;

  /** The actual SMS content */
  public String smsContent;

  public SmsContent(Long languageId, String languageName, String smsContent) {
    this.languageId = languageId;
    this.languageName = languageName;
    this.smsContent = smsContent;
  }
}
