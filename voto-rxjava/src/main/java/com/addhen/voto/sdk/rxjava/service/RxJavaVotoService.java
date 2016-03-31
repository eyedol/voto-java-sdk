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

package com.addhen.voto.sdk.rxjava.service;

import com.addhen.voto.sdk.Constants;
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

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import rx.Observable;

import static com.addhen.voto.sdk.Constants.VotoEndpoints;

/**
 * @author Henry Addo
 */
public interface RxJavaVotoService {

    /** Subscribers **/
    @FormUrlEncoded
    @POST(VotoEndpoints.SUBSCRIBERS)
    Observable<CreateSubscriberResponse> createSubscriber(
            @Field("phone") String phone,
            @FieldMap Map<String, String> optionalFields
    );

    @FormUrlEncoded
    @POST(VotoEndpoints.SUBSCRIBERS)
    Observable<CreateBulkSubscribersResponse> createBulkSubscribers(
            @Field("phone_numbers") String phone,
            @Field("if_phone_number_exists") IfPhoneNumberExists ifPhoneNumberExists,
            @FieldMap Map<String, String> optionalFields
    );

    @GET(VotoEndpoints.SUBSCRIBERS)
    Observable<ListSubscribersResponse> listSubscribers(
            @Query("limit") int limit
    );

    @PUT(VotoEndpoints.SUBSCRIBERS + "/{id}")
    Observable<SubscriberDetailsResponse> modifySubscriberDetails(
            @Path("id") Long id,
            @FieldMap Map<String, String> optionalFields
    );

    @DELETE(Constants.VotoEndpoints.SUBSCRIBERS + "/{id}")
    Observable<DeleteSubscriberResponse> deleteSubscriber(@Path("id") Long id);

    // Audio files
    @GET(Constants.VotoEndpoints.AUDIO_FILES)
    Observable<ListAudioFilesResponse> listAudioFiles();

    @DELETE(VotoEndpoints.AUDIO_FILES + "/{id}")
    Observable<DeleteAudioFileResponse> deleteAudioFile(@Path("id") Long id);

    @GET(Constants.VotoEndpoints.AUDIO_FILES + "/{id}")
    Observable<AudioFileDetailsResponse> listAudioFileDetails(@Path("id") Long id);

    @POST(Constants.VotoEndpoints.AUDIO_FILES)
    Observable<UploadAudioFileResponse> uploadAudioFileContent(
            @Query("description") String description,
            @Query("file_extension") AudioFileExtension fileExtension,
            @QueryMap Map<String, String> optionalFields);

    @PUT(Constants.VotoEndpoints.AUDIO_FILES + "/{id}")
    Observable<UploadAudioFileResponse> updateAudioFileContent(@Path("id") Long id,
            @Query("file_extension") AudioFileExtension format,
            @QueryMap Map<String, String> optionalFields);

    @GET(Constants.VotoEndpoints.AUDIO_FILES + "/{id}")
    @Streaming
    Observable<ResponseBody> downloadAudioFile(@Path("id") Long id, AudioFileFormat format);
}
