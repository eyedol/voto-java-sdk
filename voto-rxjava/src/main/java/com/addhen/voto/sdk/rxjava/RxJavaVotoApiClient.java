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
package com.addhen.voto.sdk.rxjava;

import com.addhen.voto.sdk.BaseApiBuilder;
import com.addhen.voto.sdk.BaseVotoApiClient;
import com.addhen.voto.sdk.model.CreateResponse;
import com.addhen.voto.sdk.model.Status;
import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.model.audio.AudioFileExtension;
import com.addhen.voto.sdk.model.audio.AudioFileFormat;
import com.addhen.voto.sdk.model.audio.DeleteAudioFileResponse;
import com.addhen.voto.sdk.model.audio.ListAudioFilesResponse;
import com.addhen.voto.sdk.model.audio.UploadAudioFileResponse;
import com.addhen.voto.sdk.model.messages.DeleteMessageResponse;
import com.addhen.voto.sdk.model.messages.ListMessagesResponse;
import com.addhen.voto.sdk.model.messages.MessageDeliveryLogResponse;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.IfPhoneNumberExists;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.rxjava.service.RxJavaVotoService;
import com.addhen.voto.sdk.util.StringUtils;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.addhen.voto.sdk.Constants.ErrorMessage.DESCRIPTION_REQUIRED;
import static com.addhen.voto.sdk.Constants.ErrorMessage.FILE_EXTENSION_REQUIRED;
import static com.addhen.voto.sdk.Constants.ErrorMessage.FILE_FORMAT_REQUIRED;
import static com.addhen.voto.sdk.Constants.ErrorMessage.ID_REQUIRED;
import static com.addhen.voto.sdk.Constants.ErrorMessage.PHONE_NUMBER_REQUIRED;

/**
 * @author Henry Addo
 */
public class RxJavaVotoApiClient extends BaseVotoApiClient {

    private RxJavaVotoService mRxJavaVotoService;

    public RxJavaVotoApiClient(RxJavaVotoService rxJavaVotoService) {
        mRxJavaVotoService = rxJavaVotoService;
    }

    public Observable<CreateSubscriberResponse> createSubscriber(String phone,
            Map<String, String> optionalFields) {
        if (StringUtils.isEmpty(phone)) {
            throw new IllegalArgumentException(PHONE_NUMBER_REQUIRED);
        }
        Observable<CreateSubscriberResponse> createSubscriber = mRxJavaVotoService.createSubscriber(
                phone,
                optionalFields
        );
        return createSubscriber;
    }

    public Observable<CreateBulkSubscribersResponse> createBulkSubscribers(String phoneNumbers,
            IfPhoneNumberExists ifPhoneNumberExists,
            @QueryMap Map<String, String> optionalFields) {
        if (StringUtils.isEmpty(phoneNumbers)) {
            throw new IllegalArgumentException(PHONE_NUMBER_REQUIRED);
        }

        Observable<CreateBulkSubscribersResponse> createBulkSubscribers = mRxJavaVotoService
                .createBulkSubscribers(phoneNumbers, ifPhoneNumberExists, optionalFields);
        return createBulkSubscribers;
    }

    public Observable<ListSubscribersResponse> listSubscribers(int limit) {
        if (limit > 0) {
            this.limit = limit;
        }
        Observable<ListSubscribersResponse> listSubscribers = mRxJavaVotoService
                .listSubscribers(this.limit);
        return listSubscribers;
    }

    public Observable<CreateSubscriberResponse> modifySubscriberDetails(Long id,
            Map<String, String> optionalFields) {
        if (id == null) {
            throw new IllegalArgumentException(ID_REQUIRED);
        }

        Observable<CreateSubscriberResponse> modifySubscriber = mRxJavaVotoService
                .modifySubscriberDetails(id, optionalFields);
        return modifySubscriber;
    }

    public Observable<SubscriberDetailsResponse> listSubscriberDetails(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_REQUIRED);
        }

        Observable<SubscriberDetailsResponse> response = mRxJavaVotoService
                .listSubscriberDetails(id);
        return response;
    }

    public Observable<DeleteSubscriberResponse> deleteSubscriber(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_REQUIRED);
        }

        Observable<DeleteSubscriberResponse> deleteSubscriber = mRxJavaVotoService
                .deleteSubscriber(id);
        return deleteSubscriber;
    }

    public Observable<ListAudioFilesResponse> listAudioFiles() {
        Observable<ListAudioFilesResponse> observable = mRxJavaVotoService.listAudioFiles();
        return observable;
    }

    public Observable<DeleteAudioFileResponse> deleteAudioFile(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_REQUIRED);
        }

        Observable<DeleteAudioFileResponse> response = mRxJavaVotoService.deleteAudioFile(id);
        return response;
    }

    public Observable<UploadAudioFileResponse> uploadAudioFileContent(String description,
            AudioFileExtension fileExtension, Map<String, String> optionalFields) {
        if (StringUtils.isEmpty(description)) {
            throw new IllegalArgumentException(DESCRIPTION_REQUIRED);
        }

        if (fileExtension == null) {
            throw new IllegalArgumentException(FILE_EXTENSION_REQUIRED);
        }

        Observable<UploadAudioFileResponse> observable = mRxJavaVotoService
                .uploadAudioFileContent(description, fileExtension, optionalFields);
        return observable;
    }

    public Observable<AudioFileDetailsResponse> listAudioFileDetails(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_REQUIRED);
        }

        Observable<AudioFileDetailsResponse> observable = mRxJavaVotoService
                .listAudioFileDetails(id);
        return observable;
    }

    public Observable<UploadAudioFileResponse> updateAudioFileContent(Long id,
            AudioFileExtension fileExtension, Map<String, String> optionalFields) {
        if (id == null) {
            throw new IllegalArgumentException(ID_REQUIRED);
        }

        if (fileExtension == null) {
            throw new IllegalArgumentException(FILE_EXTENSION_REQUIRED);
        }

        Observable<UploadAudioFileResponse> observable = mRxJavaVotoService
                .updateAudioFileContent(id, fileExtension, optionalFields);
        return observable;
    }

    public Observable<ResponseBody> downloadAudioFile(Long id, AudioFileFormat format) {
        if (id == null) {
            throw new IllegalArgumentException(ID_REQUIRED);
        }

        if (format == null) {
            throw new IllegalArgumentException(FILE_FORMAT_REQUIRED);
        }

        Observable<ResponseBody> observable = mRxJavaVotoService.downloadAudioFile(id, format);
        return observable;
    }

    // Messages
    public Observable<ListMessagesResponse> listMessages() {
        Observable<ListMessagesResponse> observable = mRxJavaVotoService.listMessages();
        return observable;
    }

    public Observable<CreateResponse> createMessage(String title, Status hasSms, Status hasVoice,
            Map<String, String> optionalFields) {
        return mRxJavaVotoService.createMessage(title, hasSms, hasVoice, optionalFields);

    }

    public Observable<CreateResponse> updateMessage(Long id, Map<String, String> optionalFields) {
        return mRxJavaVotoService.updateMessage(id, optionalFields);
    }

    public Observable<DeleteMessageResponse> deleteMessage(Long id) {
        return mRxJavaVotoService.deleteMessage(id);
    }

    public Observable<MessageDeliveryLogResponse> getMessageDeliveryLog(Long id,
            Map<String, String> optionalFields) {
        return mRxJavaVotoService.getMessageDeliveryLog(id, optionalFields);
    }

    public static class Builder extends BaseApiBuilder<Builder, RxJavaVotoApiClient> {

        public Builder(String apiKey) {
            super(apiKey);
        }

        @Override
        public RxJavaVotoApiClient build() {
            mRetrofitBuilder.client(mOkHttpClientBuilder.build());
            mRetrofitBuilder.baseUrl(getBaseUrl());
            mRetrofitBuilder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            Retrofit retrofit = mRetrofitBuilder.build();
            RxJavaVotoService rxJavaVotoService = retrofit.create(RxJavaVotoService.class);
            return new RxJavaVotoApiClient(rxJavaVotoService);
        }
    }
}
