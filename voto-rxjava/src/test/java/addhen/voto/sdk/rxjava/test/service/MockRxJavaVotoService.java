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

package addhen.voto.sdk.rxjava.test.service;

import com.addhen.voto.sdk.model.CreateResponse;
import com.addhen.voto.sdk.model.Status;
import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.model.audio.AudioFileExtension;
import com.addhen.voto.sdk.model.audio.AudioFileFormat;
import com.addhen.voto.sdk.model.audio.DeleteAudioFileResponse;
import com.addhen.voto.sdk.model.audio.ListAudioFilesResponse;
import com.addhen.voto.sdk.model.audio.UploadAudioFileResponse;
import com.addhen.voto.sdk.model.messages.ListMessagesResponse;
import com.addhen.voto.sdk.model.messages.MessageResponse;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.IfPhoneNumberExists;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.rxjava.service.RxJavaVotoService;
import com.addhen.voto.sdk.test.GsonDeserializer;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author Henry Addo
 */
public class MockRxJavaVotoService implements RxJavaVotoService {

    private GsonDeserializer mGsonDeserializer;

    public MockRxJavaVotoService(GsonDeserializer gsonDeserializer) {
        mGsonDeserializer = gsonDeserializer;
    }

    @Override
    public Observable<CreateSubscriberResponse> createSubscriber(String phone,
            @QueryMap Map<String, String> optionalFields) {
        final CreateSubscriberResponse createSubscriberResponse = mGsonDeserializer
                .deserializeCreateSubscriberResponse();
        return Observable.just(createSubscriberResponse);
    }

    @Override
    public Observable<CreateBulkSubscribersResponse> createBulkSubscribers(
            String phone, IfPhoneNumberExists ifPhoneNumberExists,
            Map<String, String> optionalFields) {
        final CreateBulkSubscribersResponse createBulkSubscribersResponse = mGsonDeserializer
                .deserializeCreateBulkSubscriberResponse();
        return Observable.just(createBulkSubscribersResponse);
    }

    @Override
    public Observable<ListSubscribersResponse> listSubscribers(int limit) {
        final ListSubscribersResponse listSubscribersResponse = mGsonDeserializer.listSubscribers();
        return Observable.just(listSubscribersResponse);
    }

    @Override
    public Observable<CreateSubscriberResponse> modifySubscriberDetails(Long id,
            Map<String, String> optionalFields) {
        final CreateSubscriberResponse createSubscriberResponse = mGsonDeserializer
                .modifySubscriberDetails();
        return Observable.just(createSubscriberResponse);
    }

    @Override
    public Observable<DeleteSubscriberResponse> deleteSubscriber(Long id) {
        final DeleteSubscriberResponse deleteSubscriberResponse = mGsonDeserializer
                .deleteSubscriber();
        return Observable.just(deleteSubscriberResponse);
    }

    @Override
    public Observable<SubscriberDetailsResponse> listSubscriberDetails(Long id) {
        final SubscriberDetailsResponse response = mGsonDeserializer.listSubscriberDetails();
        return Observable.just(response);
    }

    @Override
    public Observable<ListAudioFilesResponse> listAudioFiles() {
        final ListAudioFilesResponse listAudioFilesResponse = mGsonDeserializer.listAudioFiles();
        return Observable.just(listAudioFilesResponse);
    }

    @Override
    public Observable<DeleteAudioFileResponse> deleteAudioFile(Long id) {
        final DeleteAudioFileResponse deleteAudioFileResponse = mGsonDeserializer.deleteAudioFile();
        return Observable.just(deleteAudioFileResponse);
    }

    @Override
    public Observable<AudioFileDetailsResponse> listAudioFileDetails(Long id) {
        final AudioFileDetailsResponse audioFileResponse = mGsonDeserializer.listAudioFileDetails();
        return Observable.just(audioFileResponse);
    }

    @Override
    public Observable<UploadAudioFileResponse> uploadAudioFileContent(
            @Query("description") String description,
            @Query("file_extension") AudioFileExtension format,
            @QueryMap Map<String, String> optionalFields) {
        final UploadAudioFileResponse uploadAudioFileResponse = mGsonDeserializer
                .uploadAudioFileContent();
        return Observable.just(uploadAudioFileResponse);
    }

    @Override
    public Observable<UploadAudioFileResponse> updateAudioFileContent(Long id,
            AudioFileExtension format, Map<String, String> optionalFields) {

        final UploadAudioFileResponse uploadAudioFileResponse = mGsonDeserializer
                .updateAudioFileContent();
        return Observable.just(uploadAudioFileResponse);
    }

    @Override
    public Observable<ResponseBody> downloadAudioFile(Long id, AudioFileFormat format) {
        // Return a plain text file
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("text/plain"), "AudioFile");
        return Observable.just(responseBody);
    }

    @Override
    public Observable<ListMessagesResponse> listMessages() {
        final ListMessagesResponse listMessagesResponse = mGsonDeserializer.listMessages();
        return Observable.just(listMessagesResponse);
    }

    @Override
    public Observable<CreateResponse> createMessage(String title,
            Status hasSms, Status hasVoice, Map<String, String> optionalFields) {
        final CreateResponse createResponse = mGsonDeserializer.createMessage();
        return Observable.just(createResponse);
    }

    @Override
    public Observable<CreateResponse> updateMessage(Long id, Map<String, String> optionalFields) {
        final CreateResponse createResponse = mGsonDeserializer.updateMessage();
        return Observable.just(createResponse);
    }

    @Override
    public Observable<MessageResponse> deleteMessage(@Path("id") Long id) {
        // TODO: Implement message deletion
        return null;
    }
}
