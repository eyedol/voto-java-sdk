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
import com.addhen.voto.sdk.model.audio.AudioFile;
import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.model.audio.AudioFileFormat;
import com.addhen.voto.sdk.model.audio.DeleteAudioFileResponse;
import com.addhen.voto.sdk.model.audio.ListAudioFilesResponse;
import com.addhen.voto.sdk.model.messages.DeleteMessageResponse;
import com.addhen.voto.sdk.model.messages.ListMessagesResponse;
import com.addhen.voto.sdk.model.messages.Message;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.Status;
import com.addhen.voto.sdk.model.subscribers.Subscriber;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.test.GsonDeserializer;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * @author Henry Addo
 */
public class RxJavaVotoServiceTest extends BaseTestCase {

    private MockRxJavaVotoService mMockRxJavaVotoService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GsonDeserializer gsonDeserializer = new GsonDeserializer(mGson);
        mMockRxJavaVotoService = new MockRxJavaVotoService(gsonDeserializer);
    }

    @Test
    public void shouldSuccessfullyCreateSubscriber() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<CreateSubscriberResponse> observable = mMockRxJavaVotoService
                .createSubscriber("", null);
        TestSubscriber<CreateSubscriberResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        CreateSubscriberResponse createSubscriberResponse = result.getOnNextEvents().get(0);
        assertNotNull(createSubscriberResponse);
        assertNotNull(createSubscriberResponse.data);
        assertEquals(430l, (long) createSubscriberResponse.data.id);
    }

    @Test
    public void shouldSuccessfullyCreateBulkSubscribers() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<CreateBulkSubscribersResponse> observable = mMockRxJavaVotoService
                .createBulkSubscribers("", null, null);
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
        assertNotNull(mMockRxJavaVotoService);
        Observable<DeleteSubscriberResponse> observable = mMockRxJavaVotoService
                .deleteSubscriber(1l);
        TestSubscriber<DeleteSubscriberResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        DeleteSubscriberResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Successfully deleted subscriber", response.message);
    }

    @Test
    public void shouldSuccessfullyModifySubscriber() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<CreateSubscriberResponse> observable = mMockRxJavaVotoService
                .modifySubscriberDetails(1l, null);
        TestSubscriber<CreateSubscriberResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        CreateSubscriberResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Subscriber Modified", response.message);
        assertEquals(2, (long) response.data.id);
    }

    @Test
    public void shouldSuccessfullyListSubscribersDetails() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<SubscriberDetailsResponse> observable = mMockRxJavaVotoService
                .listSubscriberDetails(1l);
        TestSubscriber<SubscriberDetailsResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        SubscriberDetailsResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Subscriber Details", response.message);
        assertNotNull(response.data);
        final Subscriber subscriber = response.data.subscriber;
        assertNotNull(subscriber);
        assertEquals(33105, (long) subscriber.id);
        assertEquals("1", subscriber.receiveSms);
        assertEquals("1", subscriber.receiveVoice);
        assertEquals("0", subscriber.receiveData);
        assertEquals("0", subscriber.receiveUssd);
        assertEquals("255754280903", subscriber.phone);
        assertEquals(Status.ACTIVE, subscriber.status);
        String date = BaseTestCase.formatShowingDate(subscriber.startDate);
        assertEquals("2015-11-13", date);
        assertEquals(200715, (long) subscriber.languageId);
        assertEquals("0", subscriber.isTestSubscriber);
        assertEquals("31,32", subscriber.groupIds);
        assertNotNull(subscriber.properties);
        assertEquals("Firstname Othernames", subscriber.properties.name);
        assertEquals("Someplace", subscriber.properties.location);
        assertEquals("This is a comment", subscriber.properties.comments);
    }

    @Test
    public void shouldSuccessfullyListSubscribers() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<ListSubscribersResponse> observable = mMockRxJavaVotoService.listSubscribers(10);
        TestSubscriber<ListSubscribersResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        ListSubscribersResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals(1001, (int) response.code);
        assertEquals("Subscribers fetched successfully", response.message);
        assertEquals("", response.moreInfo);
        assertNotNull(response.message);
        assertEquals("https://go.votomobile.org/api/v1/subscribers?limit=2", response.url);
        assertEquals("https://go.votomobile.org/api/v1/subscribers?limit=2&page_after=376089",
                response.pagination.nextURL);
        assertNull(response.pagination.previousURL);
        assertNotNull(response.data.subscribers);
        assertEquals(2, response.data.subscribers.size());
        assertNotNull(response.data.subscribers.get(0));
        assertEquals(373648l, (long) response.data.subscribers.get(0).id);
        assertEquals("0", response.data.subscribers.get(0).receiveSms);
        assertEquals("1", response.data.subscribers.get(0).receiveVoice);
        assertEquals("0", response.data.subscribers.get(0).receiveData);
        assertEquals("0", response.data.subscribers.get(0).receiveUssd);
        assertEquals("233264164182", response.data.subscribers.get(0).phone);
        assertEquals(Status.ACTIVE, response.data.subscribers.get(0).status);
        String date = BaseTestCase.formatShowingDate(response.data.subscribers.get(0).startDate);
        assertEquals("2014-09-10", date);
        assertEquals(200247l, (long) response.data.subscribers.get(0).languageId);
        assertEquals("0", response.data.subscribers.get(0).isTestSubscriber);
        assertEquals(
                "201031, 201409, 204128, 204129, 204130, 204131, 204132, 204133, 204134, 204135, 204136, 204137",
                response.data.subscribers.get(0).groupIds);
        assertNotNull(response.data.subscribers.get(0).properties);
        assertEquals("Kodjo Antwi", response.data.subscribers.get(0).properties.name);
        assertEquals("Ejisu", response.data.subscribers.get(0).properties.location);
        assertEquals("Out flying kites", response.data.subscribers.get(0).properties.comments);
    }

    @Test
    public void shouldSuccessfullyListAudioFiles() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<ListAudioFilesResponse> observable = mMockRxJavaVotoService.listAudioFiles();
        TestSubscriber<ListAudioFilesResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        ListAudioFilesResponse listAudioFilesResponse = result.getOnNextEvents().get(0);

        TestCase.assertNotNull(listAudioFilesResponse);
        assertEquals(200, (int) listAudioFilesResponse.status);
        assertEquals("Audio Files", listAudioFilesResponse.message);
        TestCase.assertNotNull(listAudioFilesResponse.data);
        TestCase.assertNotNull(listAudioFilesResponse.data.audioFiles);
        assertEquals(2, listAudioFilesResponse.data.audioFiles.size());
        final AudioFile audioFile = listAudioFilesResponse.data.audioFiles.get(0);
        assertEquals("Audio Filename A", audioFile.description);
        assertEquals(3, (long) audioFile.languageId);
        assertEquals(47, (int) audioFile.lengthSeconds);
        String created = formatDate("yyyy-MM-dd h:m", audioFile.created);
        assertEquals("2013-04-09 12:57", created);
        String modified = formatDate("yyyy-MM-dd h:m", audioFile.modified);
        assertEquals("2013-04-09 12:57", modified);
    }

    @Test
    public void shouldSuccessfullyDeleteAudioFile() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<DeleteAudioFileResponse> observable = mMockRxJavaVotoService
                .deleteAudioFile(1l);
        TestSubscriber<DeleteAudioFileResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        final DeleteAudioFileResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Successfully deleted audio file", response.message);
    }

    @Test
    public void shouldSuccessfullyListAudioFileDetails() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<AudioFileDetailsResponse> observable = mMockRxJavaVotoService
                .listAudioFileDetails(1l);
        TestSubscriber<AudioFileDetailsResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        final AudioFileDetailsResponse response = result.getOnNextEvents().get(0);
        assertNotNull(response);
        assertEquals("Audio File", response.message);
        assertEquals(200, (int) response.status);
        assertNotNull(response.data);
        final AudioFile audioFile = response.data.audioFile;
        assertEquals("Audio Filename A", audioFile.description);
        assertEquals(3, (long) audioFile.languageId);
        assertEquals(47, (int) audioFile.lengthSeconds);
        String created = formatDate("yyyy-MM-dd h:m", audioFile.created);
        assertEquals("2013-04-09 12:57", created);
        String modified = formatDate("yyyy-MM-dd h:m", audioFile.modified);
        assertEquals("2013-04-09 12:57", modified);
    }

    @Test
    public void shouldSuccessfullyDownloadAudioFile() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<ResponseBody> observable = mMockRxJavaVotoService
                .downloadAudioFile(1l, AudioFileFormat.ORIGINAL);
        TestSubscriber<ResponseBody> result = new TestSubscriber<>();
        observable.subscribe(result);
        ResponseBody responseBody = result.getOnNextEvents().get(0);
        assertNotNull(responseBody);
        assertEquals("AudioFile", responseBody.string());
    }

    @Test
    public void shouldSuccessfullyCreateMessage() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<CreateResponse> observable = mMockRxJavaVotoService.createMessage("title",
                com.addhen.voto.sdk.model.Status.NO, com.addhen.voto.sdk.model.Status.YES, null);
        TestSubscriber<CreateResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        CreateResponse createResponse = result.getOnNextEvents().get(0);
        assertNotNull(createResponse);
        assertEquals(200, (int) createResponse.status);
        assertEquals(112l, (long) createResponse.data.id);
        assertEquals("Message Created Successfully", createResponse.message);
    }

    @Test
    public void shouldSuccessfullyListMessages() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<ListMessagesResponse> observable = mMockRxJavaVotoService.listMessages();
        TestSubscriber<ListMessagesResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        ListMessagesResponse listMessagesResponse = result.getOnNextEvents().get(0);
        assertNotNull(listMessagesResponse);
        assertEquals(200, (int) listMessagesResponse.status);
        Message message = listMessagesResponse.data.messages.get(0);
        assertNotNull(message);
        assertEquals(201712, (long) message.id);
        assertEquals("Test release 15 Sep 2014 ", message.title);
        assertEquals(com.addhen.voto.sdk.model.Status.YES, message.hasSms);
        assertEquals(com.addhen.voto.sdk.model.Status.YES, message.hasVoice);
        System.out.println("Date: " + message);
        String created = formatDate("yyyy-MM-dd H:mm:ss", message.created);
        System.out.println("Created: " + created);
        assertEquals("2014-09-15 16:20:48", created);
        String modified = formatDate("yyyy-MM-dd H:mm:ss", message.modified);
        assertEquals("2014-09-15 16:20:48", modified);
    }

    @Test
    public void shouldSuccessfullyUpdateMessages() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<CreateResponse> observable = mMockRxJavaVotoService.updateMessage(112l, null);
        TestSubscriber<CreateResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        CreateResponse createResponse = result.getOnNextEvents().get(0);
        assertNotNull(createResponse);
        assertEquals(200, (int) createResponse.status);
        assertEquals(112l, (long) createResponse.data.id);
        assertEquals("Message Created Successfully", createResponse.message);
    }

    @Test
    public void shouldSuccessfullyDeleteMessage() throws IOException {
        assertNotNull(mMockRxJavaVotoService);
        Observable<DeleteMessageResponse> observable = mMockRxJavaVotoService.deleteMessage(1l);
        TestSubscriber<DeleteMessageResponse> result = new TestSubscriber<>();
        observable.subscribe(result);
        DeleteMessageResponse deleteMessageResponse = result.getOnNextEvents().get(0);
        assertNotNull(deleteMessageResponse);
        assertEquals(200, (int) deleteMessageResponse.status);
        assertEquals("Succesfully deleted message", deleteMessageResponse.message);
    }
}
