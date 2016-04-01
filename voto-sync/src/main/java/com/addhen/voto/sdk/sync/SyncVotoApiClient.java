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
package com.addhen.voto.sdk.sync;

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
import retrofit2.Retrofit;
import retrofit2.http.QueryMap;

/**
 * @author Henry Addo
 */
public class SyncVotoApiClient extends BaseVotoApiClient {

    private VotoService mSyncVotoService;

    public SyncVotoApiClient(VotoService syncVotoService) {
        mSyncVotoService = syncVotoService;
    }

    public CreateSubscriberResponse createSubscriber(String phone,
            Map<String, String> optionalFields) throws IOException {
        if (StringUtils.isEmpty(phone)) {
            throw new IllegalArgumentException("phone is required and shouldn't be null or empty");
        }
        Call<CreateSubscriberResponse> createSubscriber = mSyncVotoService.createSubscriber(
                phone,
                optionalFields
        );
        return createSubscriber.execute().body();
    }

    public CreateBulkSubscribersResponse createBulkSubscribers(String phoneNumbers,
            IfPhoneNumberExists ifPhoneNumberExists,
            @QueryMap Map<String, String> optionalFields) throws IOException {
        if (StringUtils.isEmpty(phoneNumbers)) {
            throw new IllegalArgumentException("phoneNumbers is required");
        }

        Call<CreateBulkSubscribersResponse> createBulkSubscribers = mSyncVotoService
                .createBulkSubscribers(phoneNumbers, ifPhoneNumberExists, optionalFields);
        return createBulkSubscribers.execute().body();
    }

    public ListSubscribersResponse listSubscribers(int limit) throws IOException {
        if (limit > 0) {
            this.limit = limit;
        }
        Call<ListSubscribersResponse> listSubscribers = mSyncVotoService
                .listSubscribers(limit);
        return listSubscribers.execute().body();
    }

    public CreateSubscriberResponse modifySubscriberDetails(Long id,
            Map<String, String> optionalFields) throws IOException {
        Call<CreateSubscriberResponse> modifySubscriber = mSyncVotoService
                .modifySubscriberDetails(id, optionalFields);
        return modifySubscriber.execute().body();
    }

    public DeleteSubscriberResponse deleteSubscriber(Long id) throws IOException {
        Call<DeleteSubscriberResponse> deleteSubscriber = mSyncVotoService
                .deleteSubscriber(id);
        return deleteSubscriber.execute().body();
    }

    public UploadAudioFileResponse uploadAudioFileContent(String description,
            AudioFileExtension fileExtension, Map<String, String> optionalFields)
            throws IOException {
        Call<UploadAudioFileResponse> call = mSyncVotoService
                .uploadAudioFileContent(description, fileExtension, optionalFields);
        return call.execute().body();
    }

    public SubscriberDetailsResponse listSubscriberDetails(Long id) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null.");
        }

        Call<SubscriberDetailsResponse> call = mSyncVotoService.listSubscriberDetails(id);
        return call.execute().body();
    }

    public AudioFileDetailsResponse listAudioFileDetails(Long id) throws IOException {
        Call<AudioFileDetailsResponse> call = mSyncVotoService.listAudioFileDetails(id);
        return call.execute().body();
    }

    public ListAudioFilesResponse listAudioFiles() throws IOException {
        Call<ListAudioFilesResponse> call = mSyncVotoService.listAudioFiles();
        return call.execute().body();
    }

    public DeleteAudioFileResponse deleteAudioFile(Long id) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null.");
        }
        Call<DeleteAudioFileResponse> call = mSyncVotoService.deleteAudioFile(id);
        return call.execute().body();
    }

    public UploadAudioFileResponse updateAudioFileContent(Long id,
            AudioFileExtension fileExtension, Map<String, String> optionalFields)
            throws IOException {
        Call<UploadAudioFileResponse> call = mSyncVotoService
                .updateAudioFileContent(id, fileExtension, optionalFields);
        return call.execute().body();
    }

    public ResponseBody downloadAudioFile(Long id, AudioFileFormat format) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null.");
        }

        if (format == null) {
            throw new IllegalArgumentException("format is required.");
        }

        Call<ResponseBody> call = mSyncVotoService.downloadAudioFile(id, format);
        return call.execute().body();
    }

    public static class Builder extends BaseApiBuilder<Builder, SyncVotoApiClient> {

        public Builder(String apiKey) {
            super(apiKey);
        }

        @Override
        public SyncVotoApiClient build() {
            initDefaultRetrofit();
            Retrofit retrofit = mRetrofitBuilder.build();
            VotoService votoService = retrofit.create(VotoService.class);
            return new SyncVotoApiClient(votoService);
        }
    }
}
