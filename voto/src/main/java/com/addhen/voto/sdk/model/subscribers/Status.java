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

/**
 * The Active status of the subscriber
 *
 * @author Henry Addo
 */
public enum Status {
  /** 1 indicates active **/
  @SerializedName("1")
  ACTIVE, /** 0 indicates inactive **/
  @SerializedName("0")
  INACTIVE;
}
