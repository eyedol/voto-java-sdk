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

package com.addhen.voto.sdk.model.messages;

import com.google.gson.annotations.SerializedName;

import com.addhen.voto.sdk.model.Audio;
import com.addhen.voto.sdk.model.Model;
import com.addhen.voto.sdk.model.SmsContent;
import com.addhen.voto.sdk.model.Status;

import java.util.Date;
import java.util.List;

/**
 * @author Henry Addo
 */
public class Message extends Model {

    /** The title of the message **/
    public String title;

    /** Indicates whether the message has voice or not **/
    public Status hasVoice;

    /** Indicate whether the message has sms or not **/
    public Status hasSms;

    /** The date the audio file was created **/
    @SerializedName("created_at")
    public Date created;

    /** The date the audio file was modified **/
    @SerializedName("updated_at")
    public Date modified;

    /** The list of SMS content */
    @SerializedName("sms_content")
    public List<SmsContent> smsContents;

    /** List audio files */
    public List<Audio> audioFiles;

    public Message(Long id, String title, Status hasVoice, Status hasSms, Date created,
            Date modified, List<SmsContent> mSmsContents, List<Audio> audioFiles) {
        this.id = id;
        this.title = title;
        this.hasSms = hasSms;
        this.hasVoice = hasVoice;
        this.created = created;
        this.modified = modified;
        this.smsContents = mSmsContents;
        this.audioFiles = audioFiles;
    }

    @Override
    public String toString() {
        return "Message{"
                + "title='" + title + '\''
                + ", hasVoice=" + hasVoice
                + ", hasSms=" + hasSms
                + ", created=" + created
                + ", modified=" + modified
                + ", smsContents=" + smsContents
                + ", audioFiles=" + audioFiles
                + '}';
    }
}
