/*
 * Copyright (c) 2016. Henry Addo Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.addhen.voto.sdk.model.subscribers;

import com.google.gson.annotations.SerializedName;

import com.addhen.voto.sdk.model.Model;

import java.util.Date;


/**
 * A subscriber model
 *
 * @author Henry Addo
 */
public class Subscriber extends Model {

    /** Whether the subscriber received the SMS. 1 for success, 0 for failure **/
    public String receiveSms;

    /** Whether the subscriber received the voice. 1 for success, 0 for fialure **/
    public String receiveVoice;

    /** The received data **/
    public String receiveData;

    /** The received Ussd **/
    public String receiveUssd;

    /** The phone number of the subscriber **/
    public String phone;

    /** The subscribers start date **/
    public Date startDate;

    /** Determines if it's a test subscriber **/
    public String isTestSubscriber;

    /** The group IDs of the subscribers. This is CSV **/
    public String groupIds;

    /** The active statue of the subscriber **/
    @SerializedName("active")
    public Status status;

    /** The language id the subscriber speaks or interested in **/
    public Long languageId;

    /** The custom properties of the subscriber **/
    public Properties properties;

    public Subscriber(String receiveSMS, String receiveVoice, String receiveData, String phone,
            Date startDate, String isTestSubscriber, String groupIds, Status status,
            Long languageId,
            Properties properties) {
        this.receiveSms = receiveSMS;
        this.receiveVoice = receiveVoice;
        this.receiveData = receiveData;
        this.phone = phone;
        this.startDate = startDate;
        this.isTestSubscriber = isTestSubscriber;
        this.groupIds = groupIds;
        this.status = status;
        this.languageId = languageId;
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Subscriber{"
                + "id=" + super.id
                + ", receiveSms='" + receiveSms + '\''
                + ", receiveVoice='" + receiveVoice + '\''
                + ", receiveData='" + receiveData + '\''
                + ", receiveUssd='" + receiveUssd + '\''
                + ", phone='" + phone + '\''
                + ", startDate=" + startDate
                + ", isTestSubscriber='" + isTestSubscriber + '\''
                + ", groupIds='" + groupIds + '\''
                + ", status=" + status
                + ", languageId=" + languageId
                + ", properties=" + properties
                + '}';
    }

    public class Properties {

        /** The name property **/
        public String name;

        /** The location property **/
        public String location;

        /** The comment property **/
        public String comments;

        @Override
        public String toString() {
            return "Properties{"
                    + "name='" + name + '\''
                    + ", location='" + location + '\''
                    + ", comments='" + comments + '\''
                    + '}';
        }
    }
}
