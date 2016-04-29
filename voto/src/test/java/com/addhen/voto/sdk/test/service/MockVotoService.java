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

package com.addhen.voto.sdk.test.service;

import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.model.audio.AudioFileExtension;
import com.addhen.voto.sdk.model.audio.AudioFileFormat;
import com.addhen.voto.sdk.model.audio.DeleteAudioFileResponse;
import com.addhen.voto.sdk.model.audio.ListAudioFilesResponse;
import com.addhen.voto.sdk.model.audio.UploadAudioFileResponse;
import com.addhen.voto.sdk.model.messages.ListMessagesResponse;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.IfPhoneNumberExists;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.service.VotoService;
import com.addhen.voto.sdk.test.GsonDeserializer;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.mock.BehaviorDelegate;


/**
 * Mocked {@link VotoService} responses for testing
 *
 * @author Henry Addo
 */
public class MockVotoService implements VotoService {

    private final BehaviorDelegate<VotoService> mDelegate;

    private GsonDeserializer mGsonDeserializer;

    public MockVotoService(BehaviorDelegate<VotoService> delegate,
            GsonDeserializer gsonDeserializer) {
        mDelegate = delegate;
        mGsonDeserializer = gsonDeserializer;
    }

    @Override
    public Call<CreateSubscriberResponse> createSubscriber(@Field("phone") String phone,
            @QueryMap Map<String, String> optionalFields) {
        CreateSubscriberResponse createSubscriberResponse = mGsonDeserializer
                .deserializeCreateSubscriberResponse();
        return mDelegate.returningResponse(createSubscriberResponse)
                .createSubscriber(phone, optionalFields);
    }

    @Override
    public Call<CreateBulkSubscribersResponse> createBulkSubscribers(
            @Field("phone_numbers") String phone,
            @Field("if_phone_number_exists") IfPhoneNumberExists ifPhoneNumberExists,
            @QueryMap Map<String, String> optionalFields) {

        final CreateBulkSubscribersResponse createBulkSubscribersResponse = mGsonDeserializer
                .deserializeCreateBulkSubscriberResponse();
        return mDelegate.returningResponse(createBulkSubscribersResponse)
                .createBulkSubscribers(phone, ifPhoneNumberExists, optionalFields);
    }

    @Override
    public Call<ListSubscribersResponse> listSubscribers(@Query("limit") int limit) {
        final ListSubscribersResponse listSubscribersResponse = mGsonDeserializer.listSubscribers();
        return mDelegate.returningResponse(listSubscribersResponse).listSubscribers(limit);
    }

    @Override
    public Call<CreateSubscriberResponse> modifySubscriberDetails(@Path("id") Long id,
            @QueryMap Map<String, String> optionalFields) {
        final CreateSubscriberResponse createSubscriberResponse = mGsonDeserializer
                .modifySubscriberDetails();
        return mDelegate.returningResponse(createSubscriberResponse)
                .modifySubscriberDetails(id, optionalFields);
    }

    @Override
    public Call<DeleteSubscriberResponse> deleteSubscriber(@Path("id") Long id) {
        final DeleteSubscriberResponse deleteSubscriberResponse = mGsonDeserializer
                .deleteSubscriber();
        return mDelegate.returningResponse(deleteSubscriberResponse).deleteSubscriber(1l);
    }

    @Override
    public Call<SubscriberDetailsResponse> listSubscriberDetails(@Path("id") Long id) {
        final SubscriberDetailsResponse response = mGsonDeserializer.listSubscriberDetails();
        return mDelegate.returningResponse(response).listSubscriberDetails(id);
    }

    @Override
    public Call<ListAudioFilesResponse> listAudioFiles() {
        final ListAudioFilesResponse listAudioFilesResponse = mGsonDeserializer
                .listAudioFiles();
        return mDelegate.returningResponse(listAudioFilesResponse).listAudioFiles();
    }

    @Override
    public Call<DeleteAudioFileResponse> deleteAudioFile(@Path("id") Long id) {
        final DeleteAudioFileResponse deleteAudioFileResponse = mGsonDeserializer.deleteAudioFile();
        return mDelegate.returningResponse(deleteAudioFileResponse).deleteAudioFile(1l);
    }

    @Override
    public Call<AudioFileDetailsResponse> listAudioFileDetails(@Path("id") Long id) {
        final AudioFileDetailsResponse audioFileDetailsResponse = mGsonDeserializer
                .listAudioFileDetails();
        return mDelegate.returningResponse(audioFileDetailsResponse).listAudioFileDetails(1l);
    }

    @Override
    public Call<UploadAudioFileResponse> uploadAudioFileContent(
            @Query("description") String description,
            @Query("file_extension") AudioFileExtension format,
            @QueryMap Map<String, String> optionalFields) {
        final UploadAudioFileResponse audioFileResponse = mGsonDeserializer
                .uploadAudioFileContent();
        return mDelegate.returningResponse(audioFileResponse)
                .uploadAudioFileContent("description", AudioFileExtension.MP3, null);
    }

    @Override
    public Call<UploadAudioFileResponse> updateAudioFileContent(@Path("id") Long id,
            @Query("file_extension") AudioFileExtension format,
            @QueryMap Map<String, String> optionalFields) {
        final UploadAudioFileResponse audioFileResponse = mGsonDeserializer
                .updateAudioFileContent();
        return mDelegate.returningResponse(audioFileResponse)
                .updateAudioFileContent(id, format, optionalFields);
    }

    @Override
    public Call<ResponseBody> downloadAudioFile(@Path("id") Long id, AudioFileFormat format) {
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("text/plain"), "AudioFile");
        return mDelegate.returningResponse(responseBody).downloadAudioFile(id, format);
    }

    @Override
    public Call<ListMessagesResponse> listMessages() {
        final ListMessagesResponse listMessagesResponse = mGsonDeserializer.listMessages();
        return mDelegate.returningResponse(listMessagesResponse).listMessages();
    }
}
