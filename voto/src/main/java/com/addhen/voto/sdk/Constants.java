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

package com.addhen.voto.sdk;

/**
 * @author Henry Addo
 */
public final class Constants {

  /** Default pagination limit value **/
  public static final int PAGINATION_LIMIT = 10;

  private Constants() {
    // No instantiation is allowed
  }

  public static class ErrorMessage {

    /** Error message for ID **/
    public static final String ID_REQUIRED = "id cannot be null.";

    /** Error message for file extension **/
    public static final String FILE_EXTENSION_REQUIRED = "fileExtension is required.";

    /** Error message for file format **/
    public static final String FILE_FORMAT_REQUIRED = "format is required.";

    /** Error message for description **/
    public static final String DESCRIPTION_REQUIRED = "description is required.";

    /** Error message for phone number **/
    public static final String PHONE_NUMBER_REQUIRED =
        "phone is required and shouldn't be null or empty.";
  }

  public static class VotoEndpoints {

    /** The default voto api endpoint **/
    public static final String VOTO_API_ENDPOINT = " https://go.votomobile.org/api/";

    /** The version number of the API endpoint **/
    public static final String VOTO_API_ENDPOINT_VERSION = "v1";

    /** The subscribers endpoint **/
    public static final String SUBSCRIBERS = "subscribers";

    /** Audio files endpoint **/
    public static final String AUDIO_FILES = "audio_files";

    /** Messages endpoint **/
    public static final String MESSAGES = "messages";
  }
}
