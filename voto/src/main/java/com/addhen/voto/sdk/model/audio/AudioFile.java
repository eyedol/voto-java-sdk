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

package com.addhen.voto.sdk.model.audio;

import com.addhen.voto.sdk.model.Model;

import java.util.Date;

/**
 * @author Henry Addo
 */
public class AudioFile extends Model {

    /** The description of the audio **/
    public String description;

    /** The language id of audio belongs to **/
    public Long languageId;

    /** The length of the audio in seconds **/
    public Integer lengthSeconds;

    /** The date the audio file was created **/
    public Date created;

    /** The date the audio file was modified **/
    public Date modified;

    public AudioFile(Long id, String description, Long languageId, Integer lengthSeconds,
            Date created, Date modified) {
        this.id = id;
        this.description = description;
        this.languageId = languageId;
        this.lengthSeconds = lengthSeconds;
        this.created = created;
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "AudioFile{"
                + "description='" + description + '\''
                + ", languageId=" + languageId
                + ", lengthSeconds=" + lengthSeconds
                + ", created=" + created
                + ", modified=" + modified
                + '}';
    }
}
