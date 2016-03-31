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
package com.addhen.voto.sdk.async;

import com.addhen.voto.sdk.BaseApiBuilder;
import com.addhen.voto.sdk.BaseVotoApiClient;
import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.model.audio.AudioFileExtension;
import com.addhen.voto.sdk.model.audio.AudioFileFormat;
import com.addhen.voto.sdk.model.audio.DeleteAudioFileResponse;
import com.addhen.voto.sdk.model.audio.ListAudioFilesResponse;
import com.addhen.voto.sdk.model.audio.UploadAudioFileResponse;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.IfPhoneNumberExists;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.service.VotoService;
import com.addhen.voto.sdk.util.StringUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.QueryMap;

/**
 * @author Henry Addo
 */
public class AsyncVotoApiClient extends BaseVotoApiClient {

    private VotoService mAsyncVotoService;

    public AsyncVotoApiClient(VotoService aSyncVotoService) {
        mAsyncVotoService = aSyncVotoService;
    }

    public void createSubscriber(String phone,
            Map<String, String> optionalFields, Callback<CreateSubscriberResponse> callback)
            throws IOException {
        if (StringUtils.isEmpty(phone)) {
            throw new IllegalArgumentException("phone is required and shouldn't be null or empty");
        }
        Call<CreateSubscriberResponse> createSubscriber = mAsyncVotoService.createSubscriber(
                phone,
                optionalFields
        );
        createSubscriber.enqueue(callback);
    }

    public void createBulkSubscribers(String phoneNumbers,
            IfPhoneNumberExists ifPhoneNumberExists,
            @QueryMap Map<String, String> optionalFields,
            Callback<CreateBulkSubscribersResponse> callback) {
        if (StringUtils.isEmpty(phoneNumbers)) {
            throw new IllegalArgumentException("phoneNumbers is required");
        }

        Call<CreateBulkSubscribersResponse> createBulkSubscribers = mAsyncVotoService
                .createBulkSubscribers(phoneNumbers, ifPhoneNumberExists, optionalFields);
        createBulkSubscribers.enqueue(callback);
    }

    public void listSubscribers(int limit, Callback<ListSubscribersResponse> callback) {
        if (limit > 0) {
            this.limit = limit;
        }
        Call<ListSubscribersResponse> listSubscribers = mAsyncVotoService
                .listSubscribers(limit);
        listSubscribers.enqueue(callback);
    }

    public void modifySubscriberDetails(Long id, Map<String, String> optionalFields,
            Callback<CreateSubscriberResponse> callback) {
        Call<CreateSubscriberResponse> modifySubscriber = mAsyncVotoService
                .modifySubscriberDetails(id, optionalFields);
        modifySubscriber.enqueue(callback);
    }

    public void listSubscriberDetails(Long id, Callback<SubscriberDetailsResponse> callback) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null.");
        }

        Call<SubscriberDetailsResponse> response = mAsyncVotoService.listSubscriberDetails(id);
        response.enqueue(callback);
    }

    public void deleteSubscriber(Long id, Callback<DeleteSubscriberResponse> callback) {
        Call<DeleteSubscriberResponse> deleteSubscriber = mAsyncVotoService
                .deleteSubscriber(id);
        deleteSubscriber.enqueue(callback);
    }

    public void listAudioFiles(Callback<ListAudioFilesResponse> callback) {
        Call<ListAudioFilesResponse> call = mAsyncVotoService.listAudioFiles();
        call.enqueue(callback);
    }

    public void uploadAudioFileContent(String description,
            AudioFileExtension fileExtension, Map<String, String> optionalFields,
            Callback<UploadAudioFileResponse> callback)
            throws IOException {

        if (StringUtils.isEmpty(description)) {
            throw new IllegalArgumentException("description is required.");
        }

        if ((fileExtension == null) || (StringUtils.isEmpty(fileExtension.name()))) {
            throw new IllegalArgumentException("fileExtension is required.");
        }

        Call<UploadAudioFileResponse> call = mAsyncVotoService
                .uploadAudioFileContent(description, fileExtension, optionalFields);
        call.enqueue(callback);
    }

    public void listAudioFileDetails(Long id,
            Callback<AudioFileDetailsResponse> callback) throws IOException {
        Call<AudioFileDetailsResponse> call = mAsyncVotoService.listAudioFileDetails(id);
        call.enqueue(callback);
    }

    public void deleteAudioFile(Long id, Callback<DeleteAudioFileResponse> callback) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null.");
        }
        Call<DeleteAudioFileResponse> call = mAsyncVotoService.deleteAudioFile(id);
        call.enqueue(callback);
    }

    public void updateAudioFileContent(Long id,
            AudioFileExtension fileExtension, Map<String, String> optionalFields,
            Callback<UploadAudioFileResponse> callback) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null.");
        }

        if ((fileExtension == null) || (StringUtils.isEmpty(fileExtension.name()))) {
            throw new IllegalArgumentException("fileExtension is required.");
        }

        Call<UploadAudioFileResponse> call = mAsyncVotoService
                .updateAudioFileContent(id, fileExtension, optionalFields);
        call.enqueue(callback);
    }

    public void downloadAudioFile(Long id, AudioFileFormat format,
            Callback<ResponseBody> callback) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null.");
        }

        if ((format == null) || (StringUtils.isEmpty(format.name()))) {
            throw new IllegalArgumentException("format is required.");
        }

        Call<ResponseBody> call = mAsyncVotoService.downloadAudioFile(id, format);
        call.enqueue(callback);
    }

    public static class Builder extends BaseApiBuilder<Builder, AsyncVotoApiClient> {

        public Builder(String apiKey) {
            super(apiKey);
        }

        @Override
        public AsyncVotoApiClient build() {
            initDefaultRetrofit();
            Retrofit retrofit = mRetrofitBuilder.build();
            VotoService syncVotoService = retrofit.create(VotoService.class);
            return new AsyncVotoApiClient(syncVotoService);
        }
    }
}
