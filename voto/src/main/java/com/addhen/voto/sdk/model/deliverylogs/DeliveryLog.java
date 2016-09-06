package com.addhen.voto.sdk.model.deliverylogs;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Henry Addo
 */
public class DeliveryLog {

    public Long messageId;

    public DeliveryStatus filterDeliveryStatus;

    public Date filterAfterDate;

    public Date filterBeforeDate;

    public Integer count;

    @Override
    public String toString() {
        return "DeliveryLog{"
                + "messageId=" + messageId
                + ", filterDeliveryStatus=" + filterDeliveryStatus
                + ", filterAfterDate=" + filterAfterDate
                + ", filterBeforeDate=" + filterBeforeDate
                + ", count=" + count
                + '}';
    }

    public enum DeliveryStatus {

        @SerializedName("1")
        Queued,

        @SerializedName("2")
        Ringing,

        @SerializedName("3")
        IN_PROGRESS,

        @SerializedName("4")
        WAITING_TO_TRY,

        @SerializedName("5")
        FAILED_NO_ANSWER,

        @SerializedName("6")
        FINISHED_COMPLETE,

        @SerializedName("7")
        FINISHED_INCOMPLETE,

        @SerializedName("8")
        FAILED_NO_VOTO_CREDIT,

        @SerializedName("9")
        FAILED_NO_NETWORK,

        @SerializedName("10")
        FAILED_CANCELLED,

        @SerializedName("11")
        SENT,

        @SerializedName("12")
        FINISHED_VOICEMAIL,

        @SerializedName("13")
        FAILED_VOICEMAIL,

        @SerializedName("14")
        FAILED_VOICE;
    }
}
