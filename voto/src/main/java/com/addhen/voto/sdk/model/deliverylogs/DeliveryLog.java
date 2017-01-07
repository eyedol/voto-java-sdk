package com.addhen.voto.sdk.model.deliverylogs;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * @author Henry Addo
 */
public class DeliveryLog {

  /** The message id */
  public Long messageId;

  /** Status of the delivery report */
  public DeliveryStatus filterDeliveryStatus;

  /** The filter after date */
  public Date filterAfterDate;

  /** The filter by before date */
  public Date filterBeforeDate;

  /** Number of delivery reports */
  public Integer count;

  public DeliveryLog(Long messageId, DeliveryStatus filterDeliveryStatus, Date filterAfterDate,
      Date filterBeforeDate) {
    this.messageId = messageId;
    this.filterDeliveryStatus = filterDeliveryStatus;
    this.filterAfterDate = filterAfterDate;
    this.filterBeforeDate = filterBeforeDate;
  }

  @Override public String toString() {
    return "DeliveryLog{"
        + "messageId="
        + messageId
        + ", filterDeliveryStatus="
        + filterDeliveryStatus
        + ", filterAfterDate="
        + filterAfterDate
        + ", filterBeforeDate="
        + filterBeforeDate
        + ", count="
        + count
        + '}';
  }

  @SuppressWarnings("Lint.Missing a Javadoc comment") public enum DeliveryStatus {
    /** Queued **/
    @SerializedName("1")
    Queued, /** Ringing **/
    @SerializedName("2")
    Ringing,

    /** In progress **/
    @SerializedName("3")
    IN_PROGRESS,

    /** Waiting to try again **/
    @SerializedName("4")
    WAITING_TO_TRY,

    /** Failed with no answer **/
    @SerializedName("5")
    FAILED_NO_ANSWER,

    /** Completely finished **/
    @SerializedName("6")
    FINISHED_COMPLETE,

    /** Incompletely finished **/
    @SerializedName("7")
    FINISHED_INCOMPLETE,

    /** Failed with no voto credit **/
    @SerializedName("8")
    FAILED_NO_VOTO_CREDIT,

    /** Failed with no network **/
    @SerializedName("9")
    FAILED_NO_NETWORK,

    /** cancelled **/
    @SerializedName("10")
    FAILED_CANCELLED,

    /** Sent **/
    @SerializedName("11")
    SENT,

    /** Voicemail finished **/
    @SerializedName("12")
    FINISHED_VOICEMAIL,

    /** Voicemail failed **/
    @SerializedName("13")
    FAILED_VOICEMAIL,

    /** Voicemail voice **/
    @SerializedName("14")
    FAILED_VOICE;
  }
}
