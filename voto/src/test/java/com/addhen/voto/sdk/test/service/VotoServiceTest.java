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

import com.addhen.voto.sdk.model.audio.AudioFile;
import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.model.audio.AudioFileFormat;
import com.addhen.voto.sdk.model.audio.DeleteAudioFileResponse;
import com.addhen.voto.sdk.model.audio.ListAudioFilesResponse;
import com.addhen.voto.sdk.model.messages.ListMessagesResponse;
import com.addhen.voto.sdk.model.messages.MessageDeliveryLogResponse;
import com.addhen.voto.sdk.model.messages.MessageDetailsResponse;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.Status;
import com.addhen.voto.sdk.test.BaseTestCase;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * @author Henry Addo
 */
public class VotoServiceTest extends BaseTestCase {

    private MockVotoService mMockVotoService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mMockVotoService = getMockVotoService();
        assertNotNull(mMockVotoService);
    }

    @Test
    public void shouldSuccessfullyCreateSubscriber() throws IOException {
        Call<CreateSubscriberResponse> call = mMockVotoService
                .createSubscriber("", null);
        CreateSubscriberResponse createSubscriberResponse = call.execute().body();
        assertNotNull(createSubscriberResponse);
        assertNotNull(createSubscriberResponse.data);
        assertEquals(430L, (long) createSubscriberResponse.data.id);
    }

    @Test
    public void shouldSuccessfullyCreateBulkSubscribers() throws IOException {
        Call<CreateBulkSubscribersResponse> call = mMockVotoService
                .createBulkSubscribers("", null, null);
        CreateBulkSubscribersResponse response = call.execute().body();
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Subscriber(s) Created Successfully", response.message);
        assertEquals(5, response.data.size());
        assertEquals(181, (long) response.data.get(0));
    }

    @Test
    public void shouldSuccessfullyDeleteSubscriber() throws IOException {
        Call<DeleteSubscriberResponse> call = mMockVotoService.deleteSubscriber(1l);
        DeleteSubscriberResponse response = call.execute().body();
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Successfully deleted subscriber", response.message);
    }

    @Test
    public void shouldSuccessfullyListSubscribers() throws IOException {
        Call<ListSubscribersResponse> call = mMockVotoService.listSubscribers(10);
        ListSubscribersResponse response = call.execute().body();
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
        Call<ListAudioFilesResponse> call = mMockVotoService.listAudioFiles();
        ListAudioFilesResponse listAudioFilesResponse = call.execute().body();

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
    public void shouldSuccessfullyDeleteAudioFIle() throws IOException {
        Call<DeleteAudioFileResponse> call = mMockVotoService.deleteAudioFile(1L);
        DeleteAudioFileResponse response = call.execute().body();
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Successfully deleted audio file", response.message);
    }

    @Test
    public void shouldSuccessfullyListAudioFileDetails() throws IOException {
        Call<AudioFileDetailsResponse> call = mMockVotoService.listAudioFileDetails(1L);
        AudioFileDetailsResponse response = call.execute().body();
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
        Call<ResponseBody> call = mMockVotoService.downloadAudioFile(1L, AudioFileFormat.ORIGINAL);
        ResponseBody responseBody = call.execute().body();
        assertNotNull(responseBody);
        assertEquals("AudioFile", responseBody.string());
    }

    @Test
    public void shouldSuccessfullyFetchMessageDeliveryCount() throws IOException {
        Call<MessageDeliveryLogResponse> call = mMockVotoService.getMessageDeliveryLog(1L, null);
        MessageDeliveryLogResponse messageDeliveryLogResponse = call.execute().body();
        assertMessageDeliveryLogCountResponse(messageDeliveryLogResponse);
    }

    @Test
    public void shouldSuccessfullyListMessages() throws IOException {
        Call<ListMessagesResponse> call = mMockVotoService.listMessages();
        ListMessagesResponse listMessagesResponse = call.execute().body();
        assertListMessages(listMessagesResponse);
    }

    @Test
    public void shouldSuccessfullyGetMessageDetails() throws IOException {
        Call<MessageDetailsResponse> call = mMockVotoService.getMessageDetails(1L);
        MessageDetailsResponse messageDetailsResponse = call.execute().body();
        assertMessageDetailsResponse(messageDetailsResponse);
    }
}
