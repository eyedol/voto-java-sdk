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

package com.addhen.voto.sdk.sync.test;

import com.addhen.voto.sdk.Constants;
import com.addhen.voto.sdk.model.CreateResponse;
import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.model.audio.AudioFileExtension;
import com.addhen.voto.sdk.model.audio.AudioFileFormat;
import com.addhen.voto.sdk.model.audio.DeleteAudioFileResponse;
import com.addhen.voto.sdk.model.audio.ListAudioFilesResponse;
import com.addhen.voto.sdk.model.audio.UploadAudioFileResponse;
import com.addhen.voto.sdk.model.messages.DeleteMessageResponse;
import com.addhen.voto.sdk.model.messages.ListMessagesResponse;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.Status;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.sync.SyncVotoApiClient;
import com.addhen.voto.sdk.test.BaseTestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;

import static com.addhen.voto.sdk.Constants.ErrorMessage.DESCRIPTION_REQUIRED;
import static com.addhen.voto.sdk.Constants.ErrorMessage.FILE_EXTENSION_REQUIRED;
import static com.addhen.voto.sdk.Constants.ErrorMessage.FILE_FORMAT_REQUIRED;
import static com.addhen.voto.sdk.Constants.ErrorMessage.ID_REQUIRED;
import static com.addhen.voto.sdk.Constants.ErrorMessage.PHONE_NUMBER_REQUIRED;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Henry Addo
 */
public class SyncVotoApiClientTest extends BaseTestCase {

    private SyncVotoApiClient mSyncVotoApiClient;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mSyncVotoApiClient = new SyncVotoApiClient(getMockVotoService());
    }

    @Test
    public void shouldThrowExceptionWhenEmptyPhoneNumberIsSet() throws IOException {
        try {
            mSyncVotoApiClient.createSubscriber(null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(PHONE_NUMBER_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenEmptyPhoneNumbersIsSet() throws IOException {
        try {
            mSyncVotoApiClient.createBulkSubscribers(null, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(PHONE_NUMBER_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNull() throws IOException {
        try {
            mSyncVotoApiClient.deleteAudioFile(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldSuccessfullyCreateSubscriber() throws IOException {
        CreateSubscriberResponse createSubscriberResponse = mSyncVotoApiClient
                .createSubscriber("0243555333889", null);
        assertNotNull(createSubscriberResponse);
        assertNotNull(createSubscriberResponse.data);
        assertEquals(430l, (long) createSubscriberResponse.data.id);
    }

    @Test
    public void shouldSuccessfullyCreateBulkSubscribers() throws IOException {

        CreateBulkSubscribersResponse response = mSyncVotoApiClient
                .createBulkSubscribers("02345", null, null);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Subscriber(s) Created Successfully", response.message);
        assertEquals(5, response.data.size());
        assertEquals(181, (long) response.data.get(0));
    }

    @Test
    public void shouldSuccessfullyDeleteSubscriber() throws IOException {

        DeleteSubscriberResponse response = mSyncVotoApiClient.deleteSubscriber(1l);
        assertNotNull(response);
        assertEquals(200, (int) response.status);
        assertEquals("Successfully deleted subscriber", response.message);
    }

    @Test
    public void shouldSuccessfullyListSubscribers() throws IOException {

        ListSubscribersResponse response = mSyncVotoApiClient.listSubscribers(10);
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
    public void shouldSuccessfullyModifySubscriber() throws IOException {
        CreateSubscriberResponse createSubscriberResponse = mSyncVotoApiClient
                .modifySubscriberDetails(1l, null);
        assertNotNull(createSubscriberResponse);
    }

    @Test
    public void shouldSuccessfullyListSubscriberDetails() throws IOException {
        SubscriberDetailsResponse createSubscriberResponse = mSyncVotoApiClient
                .listSubscriberDetails(1l);
        assertNotNull(createSubscriberResponse);
    }

    @Test
    public void shouldSuccessfullyListAudioFileDetails() throws IOException {
        AudioFileDetailsResponse response = mSyncVotoApiClient
                .listAudioFileDetails(1l);
        assertNotNull(response);
    }

    @Test
    public void shouldSuccessfullyUploadAudioFileContent() throws IOException {
        UploadAudioFileResponse response = mSyncVotoApiClient
                .uploadAudioFileContent("description",
                        AudioFileExtension.MP3, null);
        assertNotNull(response);
    }

    @Test
    public void shouldSuccessfullyUpdateAudioFileContent() throws IOException {
        UploadAudioFileResponse response = mSyncVotoApiClient
                .updateAudioFileContent(1l, AudioFileExtension.MP3, null);
        assertNotNull(response);
    }

    @Test
    public void shouldThrowExceptionWhenApiKeyIsNull() {
        try {
            new SyncVotoApiClient.Builder(null)
                    .build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("apiKey shouldn't be null or empty.", e.getMessage());
        }
    }

    @Test
    public void shouldBuildRxJavaVotoApiClient() {
        SyncVotoApiClient syncVotoClient = new SyncVotoApiClient.Builder("api_key")
                .build();
        assertNotNull(syncVotoClient);
    }

    @Test
    public void shouldMakeSureLimitIsSet() throws IOException {
        mSyncVotoApiClient.listSubscribers(2);
        assertTrue((mSyncVotoApiClient.getLimit() < Constants.PAGINATION_LIMIT));
        assertEquals(2, mSyncVotoApiClient.getLimit());
    }

    @Test
    public void shouldMakeSureIsDefaultLimit() throws IOException {
        mSyncVotoApiClient.listSubscribers(0);
        assertEquals(Constants.PAGINATION_LIMIT, mSyncVotoApiClient.getLimit());
    }

    @Test
    public void shouldThrowExceptionWhenEmptyDescriptionIsSet() throws IOException {
        try {
            mSyncVotoApiClient.uploadAudioFileContent(null, AudioFileExtension.MP3, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(DESCRIPTION_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenEmptyFileExtensionIsSet() throws IOException {
        try {
            mSyncVotoApiClient.uploadAudioFileContent("description", null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(FILE_EXTENSION_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenDownloadFileIdIsNull() throws IOException {
        try {
            mSyncVotoApiClient.downloadAudioFile(null, AudioFileFormat.ORIGINAL);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenModifySubscriberDetailsFileIdIsNull() throws IOException {
        try {
            mSyncVotoApiClient.modifySubscriberDetails(null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenDeleteSubscriberFileIdIsNull() throws IOException {
        try {
            mSyncVotoApiClient.deleteSubscriber(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenListSubscriberDetailsFileIdIsNull() throws IOException {
        try {
            mSyncVotoApiClient.listSubscriberDetails(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenListAudioFileDetailsFileIdIsNull() throws IOException {
        try {
            mSyncVotoApiClient.listAudioFileDetails(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenUpdateAudioFileContentFileIdIsNull() throws IOException {
        try {
            mSyncVotoApiClient.updateAudioFileContent(null, AudioFileExtension.MP3, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenUpdateAudioFileContentFileExtensionIsNull()
            throws IOException {
        try {
            mSyncVotoApiClient.updateAudioFileContent(1l, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(FILE_EXTENSION_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenDownloadFileFileFormatIsNotSet() throws IOException {
        try {
            mSyncVotoApiClient.downloadAudioFile(1l, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(FILE_FORMAT_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenListSubscriberIdFieldIsNull() throws IOException {
        try {
            mSyncVotoApiClient.listSubscriberDetails(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldSuccessfullyDownloadAudioFile() throws IOException {
        ResponseBody responseBody = mSyncVotoApiClient
                .downloadAudioFile(1l, AudioFileFormat.ORIGINAL);
        assertNotNull(responseBody);
        assertEquals("AudioFile", responseBody.string());
    }

    @Test
    public void shouldSuccessfullyDeleteAudioFile() throws IOException {
        DeleteAudioFileResponse audioFileResponse = mSyncVotoApiClient.deleteAudioFile(1l);
        assertNotNull(audioFileResponse);
        assertEquals(200, (int) audioFileResponse.status);
        assertEquals("Successfully deleted audio file", audioFileResponse.message);
    }

    @Test
    public void shouldSuccessfullyListAudioFile() throws IOException {
        ListAudioFilesResponse audioFilesResponse = mSyncVotoApiClient.listAudioFiles();
        assertNotNull(audioFilesResponse);
        assertEquals(200, (int) audioFilesResponse.status);
    }

    @Test
    public void shouldSuccessfullyListMessages() throws IOException {
        ListMessagesResponse listMessagesResponse = mSyncVotoApiClient.listMessages();
        assertNotNull(listMessagesResponse);
        assertEquals(200, (int) listMessagesResponse.status);
    }

    @Test
    public void shouldSuccessfullyCreateMessage() throws IOException {
        CreateResponse createResponse = mSyncVotoApiClient
                .createMessage("title", com.addhen.voto.sdk.model.Status.NO,
                        com.addhen.voto.sdk.model.Status.YES, null);
        assertNotNull(createResponse);
        assertEquals(200, (int) createResponse.status);
    }

    @Test
    public void shouldSuccessfullyUpdateMessage() throws IOException {
        CreateResponse createResponse = mSyncVotoApiClient.updateMessage(1l, null);
        assertNotNull(createResponse);
        assertEquals(200, (int) createResponse.status);
    }

    @Test
    public void shouldSuccessfullyDeleteMessage() throws IOException {
        DeleteMessageResponse deleteMessageResponse = mSyncVotoApiClient.deleteMessage(1l);
        assertNotNull(deleteMessageResponse);
        assertEquals(200, (int) deleteMessageResponse.status);
        assertEquals("Successfully deleted message", deleteMessageResponse.message);
    }
}
