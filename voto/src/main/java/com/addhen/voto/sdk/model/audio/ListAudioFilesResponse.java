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

import com.addhen.voto.sdk.model.BaseResponse;
import java.util.List;

/**
 * @author Henry Addo
 */
public class ListAudioFilesResponse extends BaseResponse {

  /** The list of audio files */
  public Data data;

  public static class Data {

    /** List of audio files **/
    public List<AudioFile> audioFiles;

    @Override public String toString() {
      return "Data{" + "audio=" + audioFiles + '}';
    }
  }
}
