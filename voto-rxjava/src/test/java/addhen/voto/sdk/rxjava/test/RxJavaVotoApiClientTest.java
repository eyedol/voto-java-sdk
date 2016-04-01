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

package addhen.voto.sdk.rxjava.test;

import com.addhen.voto.sdk.Constants;
import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.model.audio.AudioFileExtension;
import com.addhen.voto.sdk.model.audio.AudioFileFormat;
import com.addhen.voto.sdk.model.audio.DeleteAudioFileResponse;
import com.addhen.voto.sdk.model.audio.UploadAudioFileResponse;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.rxjava.RxJavaVotoApiClient;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.test.GsonDeserializer;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import addhen.voto.sdk.rxjava.test.service.MockRxJavaVotoService;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Henry Addo
 */
public class RxJavaVotoApiClientTest extends BaseTestCase {

    private RxJavaVotoApiClient mRxJavaVotoApiClient;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GsonDeserializer gsonDeserializer = new GsonDeserializer(mGson);
        MockRxJavaVotoService mockRxJavaVotoService = new MockRxJavaVotoService(gsonDeserializer);
        mRxJavaVotoApiClient = new RxJavaVotoApiClient(mockRxJavaVotoService);
    }

    @Test
    public void shouldThrowExceptionWhenEmptyPhoneNumberIsSet() {
        try {
            mRxJavaVotoApiClient.createSubscriber(null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("phone is required and shouldn't be null or empty.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenEmptyPhoneNumbersIsSet() {
        try {
            mRxJavaVotoApiClient.createBulkSubscribers(null, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("phoneNumbers is required.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNull() {
        try {
            mRxJavaVotoApiClient.deleteAudioFile(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("id cannot be null.", e.getMessage());
        }
    }

    @Test
    public void shouldSuccessfullyCreateSubscriber() throws IOException {
        Observable<CreateSubscriberResponse> observable = mRxJavaVotoApiClient
                .createSubscriber("0243555333889", null);
        TestSubscriber<CreateSubscriberResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        CreateSubscriberResponse createSubscriberResponse = result.getOnNextEvents().get(0);
        assertNotNull(createSubscriberResponse);
    }

    @Test
    public void shouldSuccessfullyCreateBulkSubscribers() throws IOException {

        Observable<CreateBulkSubscribersResponse> observable = mRxJavaVotoApiClient
                .createBulkSubscribers("0243555333889", null, null);
        TestSubscriber<CreateBulkSubscribersResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        CreateBulkSubscribersResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Subscriber(s) Created Successfully", response.message);
        assertEquals(5, response.data.size());
        assertEquals(181, (long) response.data.get(0));
    }

    @Test
    public void shouldSuccessfullyDeleteSubscriber() throws IOException {
        Observable<DeleteSubscriberResponse> observable = mRxJavaVotoApiClient
                .deleteSubscriber(1l);
        TestSubscriber<DeleteSubscriberResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        DeleteSubscriberResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
    }

    @Test
    public void shouldSuccessfullyListSubscribers() throws IOException {
        Observable<ListSubscribersResponse> observable = mRxJavaVotoApiClient.listSubscribers(10);
        TestSubscriber<ListSubscribersResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        ListSubscribersResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
    }

    @Test
    public void shouldSuccessfullyModifySubscriber() throws IOException {
        Observable<CreateSubscriberResponse> observable = mRxJavaVotoApiClient
                .modifySubscriberDetails(1l, null);
        TestSubscriber<CreateSubscriberResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        CreateSubscriberResponse createSubscriberResponse = result.getOnNextEvents().get(0);
        assertNotNull(createSubscriberResponse);
    }

    @Test
    public void shouldSuccessfullyListSubscriberDetails() throws IOException {
        Observable<SubscriberDetailsResponse> observable = mRxJavaVotoApiClient
                .listSubscriberDetails(1l);
        TestSubscriber<SubscriberDetailsResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        SubscriberDetailsResponse createSubscriberResponse = result.getOnNextEvents().get(0);
        assertNotNull(createSubscriberResponse);
    }

    @Test
    public void shouldSuccessfullyListAudioFileDetails() throws IOException {
        Observable<AudioFileDetailsResponse> observable = mRxJavaVotoApiClient
                .listAudioFileDetails(1l);
        TestSubscriber<AudioFileDetailsResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        AudioFileDetailsResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
    }

    @Test
    public void shouldSuccessfullyUploadAudioFileContent() throws IOException {
        Observable<UploadAudioFileResponse> observable = mRxJavaVotoApiClient
                .uploadAudioFileContent("description",
                        AudioFileExtension.MP3, null);
        TestSubscriber<UploadAudioFileResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        UploadAudioFileResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
    }

    @Test
    public void shouldSuccessfullyUpdateAudioFileContent() throws IOException {
        Observable<UploadAudioFileResponse> observable = mRxJavaVotoApiClient
                .updateAudioFileContent(1l, AudioFileExtension.MP3, null);
        TestSubscriber<UploadAudioFileResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        UploadAudioFileResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
    }

    @Test
    public void shouldThrowExceptionWhenApiKeyIsNull() {
        try {
            new RxJavaVotoApiClient.Builder(null)
                    .build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("apiKey shouldn't be null or empty.", e.getMessage());
        }
    }

    @Test
    public void shouldBuildRxJavaVotoApiClient() {
        RxJavaVotoApiClient rxVotoClient = new RxJavaVotoApiClient.Builder("api_key")
                .build();
        assertNotNull(rxVotoClient);
    }

    @Test
    public void shouldMakeSureLimitIsSet() {
        mRxJavaVotoApiClient.listSubscribers(2);
        assertTrue((mRxJavaVotoApiClient.getLimit() < Constants.PAGINATION_LIMIT));
        assertEquals(2, mRxJavaVotoApiClient.getLimit());
    }

    @Test
    public void shouldMakeSureIsDefaultLimit() {
        mRxJavaVotoApiClient.listSubscribers(0);
        assertEquals(Constants.PAGINATION_LIMIT, mRxJavaVotoApiClient.getLimit());
    }

    @Test
    public void shouldThrowExceptionWhenEmptyDescriptionIsSet() {
        try {
            mRxJavaVotoApiClient.uploadAudioFileContent(null, AudioFileExtension.MP3, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("description is required.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenEmptyFileExtensionIsSet() {
        try {
            mRxJavaVotoApiClient.uploadAudioFileContent("description", null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("fileExtension is required.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenDownloadFileIdIsNull() {
        try {
            mRxJavaVotoApiClient.downloadAudioFile(null, AudioFileFormat.ORIGINAL);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("id cannot be null.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenDownloadFileFileFormatIsNotSet() {
        try {
            mRxJavaVotoApiClient.downloadAudioFile(1l, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("format is required.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenListSubscriberIdFieldIsNull() {
        try {
            mRxJavaVotoApiClient.listSubscriberDetails(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("id cannot be null.", e.getMessage());
        }
    }

    @Test
    public void shouldSuccessfullyDownloadAudioFile() throws IOException {
        Observable<ResponseBody> observable = mRxJavaVotoApiClient
                .downloadAudioFile(1l, AudioFileFormat.ORIGINAL);
        TestSubscriber<ResponseBody> result = new TestSubscriber<>();
        observable.subscribe(result);
        ResponseBody responseBody = result.getOnNextEvents().get(0);
        assertNotNull(responseBody);
        assertEquals("AudioFile", responseBody.string());
    }

    @Test
    public void shouldSuccessfullyDeleteAudioFile() throws IOException {
        Observable<DeleteAudioFileResponse> observable = mRxJavaVotoApiClient.deleteAudioFile(1l);
        TestSubscriber<DeleteAudioFileResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        DeleteAudioFileResponse audioFileResponse = result.getOnNextEvents().get(0);
        assertNotNull(audioFileResponse);
        assertEquals(200, (int) audioFileResponse.status);
        assertEquals("Successfully deleted audio file", audioFileResponse.message);
    }
}
