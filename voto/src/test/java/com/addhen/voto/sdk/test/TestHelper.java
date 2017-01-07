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

package com.addhen.voto.sdk.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Helper class for reading files from the resources folder
 *
 * @author Henry Addo
 */
public class TestHelper {

  public static String getResource(String resourceName) throws IOException {
    InputStream inputStream = TestHelper.class.getResourceAsStream("/" + resourceName);
    assert (inputStream != null);
    int n;
    byte[] buffer = new byte[81992];
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    while ((n = inputStream.read(buffer)) != -1) {
      bos.write(buffer, 0, n);
    }
    return new String(bos.toByteArray());
  }
}
