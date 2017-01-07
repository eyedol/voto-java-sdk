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
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Base class for building API clients
 *
 * @author Henry Addo
 */
public abstract class BaseApiBuilder<B, A> {

  protected Retrofit.Builder mRetrofitBuilder;

  protected OkHttpClient.Builder mOkHttpClientBuilder;

  protected String mApiKey;

  protected String mApiVersion = Constants.VotoEndpoints.VOTO_API_ENDPOINT_VERSION;

  protected Gson mGson;

  public BaseApiBuilder(String apiKey) {
    if (StringUtils.isEmpty(apiKey)) {
      throw new IllegalArgumentException("apiKey shouldn't be null or empty.");
    }
    mApiKey = apiKey;
    mOkHttpClientBuilder = new OkHttpClient.Builder();
    initializeGson();
    initDefaultOkHttpClient();
    mRetrofitBuilder = new Retrofit.Builder();
    mRetrofitBuilder.addConverterFactory(GsonConverterFactory.create(mGson));
  }

  public abstract A build();

  @SuppressWarnings("unchecked") public B withApiVersion(String apiVersion) {
    if (StringUtils.isEmpty(apiVersion)) {
      throw new IllegalArgumentException("apiVersion shouldn't be null or empty.");
    }

    mApiVersion = apiVersion;
    return (B) this;
  }

  @SuppressWarnings("unchecked")
  public B withOwnOkHttpClient(OkHttpClient.Builder okHttpClientBuilder) {
    mOkHttpClientBuilder = okHttpClientBuilder;
    return (B) this;
  }

  @SuppressWarnings("unchecked") public B withCache(Cache cache) {
    mOkHttpClientBuilder.cache(cache);
    return (B) this;
  }

  @SuppressWarnings("unchecked") public B withLogLevel(HttpLoggingInterceptor.Level logLevel) {
    final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
    logInterceptor.setLevel(logLevel);
    mOkHttpClientBuilder.addInterceptor(logInterceptor);
    return (B) this;
  }

  protected String getBaseUrl() {
    return Constants.VotoEndpoints.VOTO_API_ENDPOINT + mApiVersion + "/";
  }

  protected void initDefaultRetrofit() {
    mRetrofitBuilder.client(mOkHttpClientBuilder.build());
    mRetrofitBuilder.baseUrl(getBaseUrl());
  }

  private void initDefaultOkHttpClient() {
    mOkHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
    mOkHttpClientBuilder.writeTimeout(10, TimeUnit.SECONDS);
    mOkHttpClientBuilder.readTimeout(10, TimeUnit.SECONDS);
    mOkHttpClientBuilder.cookieJar(CookieJar.NO_COOKIES);
    mOkHttpClientBuilder.addInterceptor(new ApiKeyInterceptor(mApiKey));
  }

  private void initializeGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    builder.registerTypeAdapter(Date.class, new DateDeserializer());
    mGson = builder.create();
  }
}
