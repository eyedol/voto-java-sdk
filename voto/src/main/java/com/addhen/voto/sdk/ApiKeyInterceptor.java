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

import com.addhen.voto.sdk.util.StringUtils;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Henry Addo
 */
public class ApiKeyInterceptor implements Interceptor {

  private final String apiKey;

  public ApiKeyInterceptor(String apiKey) {
    this.apiKey = apiKey;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Request.Builder builder = chain.request().newBuilder();
    builder.header("Accept", "application/json");
    if (!StringUtils.isEmpty(apiKey)) {
      builder.header("api_key", apiKey);
    }
    return chain.proceed(builder.build());
  }
}
