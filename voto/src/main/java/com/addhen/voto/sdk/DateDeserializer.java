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

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * @author Henry Addo
 */
public class DateDeserializer implements JsonDeserializer<Date> {

  private static final String[] DATE_FORMATS = new String[] { "yyyy-MM-dd", "yyyy-MM-dd h:mm:ss" };

  @Override
  public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    // Determine whether date has time as well
    String[] dateTime = json.getAsString().trim().split(" ");
    try {
      if (dateTime.length > 1) {
        return new SimpleDateFormat(DATE_FORMATS[1], Locale.US).parse(json.getAsString());
      }
      return new SimpleDateFormat(DATE_FORMATS[0], Locale.US).parse(json.getAsString());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    throw new JsonParseException(
        "Unparseable date: \"" + json.getAsString() + "\". Supported formats: " + Arrays.toString(
            DATE_FORMATS));
  }
}
