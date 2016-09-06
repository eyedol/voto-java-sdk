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

package com.addhen.voto.sdk.async.test;

import com.addhen.voto.sdk.Constants;
import com.addhen.voto.sdk.async.AsyncVotoApiClient;
import com.addhen.voto.sdk.model.CreateResponse;
import com.addhen.voto.sdk.model.audio.AudioFileDetailsResponse;
import com.addhen.voto.sdk.model.audio.AudioFileExtension;
import com.addhen.voto.sdk.model.audio.AudioFileFormat;
import com.addhen.voto.sdk.model.audio.DeleteAudioFileResponse;
import com.addhen.voto.sdk.model.audio.ListAudioFilesResponse;
import com.addhen.voto.sdk.model.audio.UploadAudioFileResponse;
import com.addhen.voto.sdk.model.messages.ListMessagesResponse;
import com.addhen.voto.sdk.model.messages.MessageDeliveryLogResponse;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.Status;
import com.addhen.voto.sdk.model.subscribers.SubscriberDetailsResponse;
import com.addhen.voto.sdk.test.BaseTestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
public class AsyncVotoApiClientTest extends BaseTestCase {

    private AsyncVotoApiClient mAsyncVotoApiClient;

    private CountDownLatch mCountDownLatch = new CountDownLatch(1);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mAsyncVotoApiClient = new AsyncVotoApiClient(getMockVotoService());
    }

    @Test
    public void shouldThrowExceptionWhenEmptyPhoneNumberIsSet() throws IOException {
        try {
            mAsyncVotoApiClient.createSubscriber(null, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(PHONE_NUMBER_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenEmptyPhoneNumbersIsSet() throws IOException {
        try {
            mAsyncVotoApiClient.createBulkSubscribers(null, null, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(PHONE_NUMBER_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNull() throws IOException {
        try {
            mAsyncVotoApiClient.deleteAudioFile(null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldSuccessfullyCreateSubscriber() throws IOException, InterruptedException {
        mAsyncVotoApiClient
                .createSubscriber("0243555333889", null, new Callback<CreateSubscriberResponse>() {
                    @Override
                    public void onResponse(Call<CreateSubscriberResponse> call,
                            Response<CreateSubscriberResponse> response) {
                        CreateSubscriberResponse createSubscriberResponse = response.body();
                        assertNotNull(createSubscriberResponse);
                        assertNotNull(createSubscriberResponse.data);
                        assertEquals(11, (long) createSubscriberResponse.data.id);
                        mCountDownLatch.countDown();
                    }

                    @Override
                    public void onFailure(Call<CreateSubscriberResponse> call, Throwable t) {
                        mCountDownLatch.countDown();

                    }
                });

        mCountDownLatch.await(30, TimeUnit.SECONDS);
    }

    @Test
    public void shouldSuccessfullyCreateBulkSubscribers() throws IOException, InterruptedException {

        mAsyncVotoApiClient.createBulkSubscribers("02345", null, null,
                new Callback<CreateBulkSubscribersResponse>() {
                    @Override
                    public void onResponse(Call<CreateBulkSubscribersResponse> call,
                            Response<CreateBulkSubscribersResponse> resp) {
                        CreateBulkSubscribersResponse response = resp.body();
                        assertNotNull(response);
                        assertEquals(200, (int) response.status);
                        assertEquals("Subscriber(s) Created Successfully..",
                                response.message);
                        assertEquals(5, response.data.size());
                        assertEquals(181, (long) response.data.get(0));

                        //Release latch
                        mCountDownLatch.countDown();
                    }

                    @Override
                    public void onFailure(Call<CreateBulkSubscribersResponse> call,
                            Throwable t) {

                    }
                });

        mCountDownLatch.await(40, TimeUnit.SECONDS);
    }

    @Test
    public void shouldSuccessfullyDeleteSubscriber() throws IOException, InterruptedException {

        mAsyncVotoApiClient.deleteSubscriber(1l,
                new Callback<DeleteSubscriberResponse>() {
                    @Override
                    public void onResponse(Call<DeleteSubscriberResponse> call,
                            Response<DeleteSubscriberResponse> resp) {
                        DeleteSubscriberResponse response = resp.body();
                        assertNotNull(response);
                        assertEquals(200, (int) response.status);
                        assertEquals("Successfully deleted subscriber", response.message);
                        mCountDownLatch.countDown();
                    }

                    @Override
                    public void onFailure(Call<DeleteSubscriberResponse> call, Throwable t) {
                        mCountDownLatch.countDown();
                    }
                });
        mCountDownLatch.await(40, TimeUnit.SECONDS);
    }

    @Test
    public void shouldSuccessfullyListSubscribers() throws IOException, InterruptedException {

        mAsyncVotoApiClient.listSubscribers(10,
                new Callback<ListSubscribersResponse>() {
                    @Override
                    public void onResponse(Call<ListSubscribersResponse> call,
                            Response<ListSubscribersResponse> resp) {
                        ListSubscribersResponse response = resp.body();
                        assertNotNull(response);
                        assertEquals(200, (int) response.status);
                        assertEquals(1001, (int) response.code);
                        assertEquals("Subscribers fetched successfully", response.message);
                        assertEquals("", response.moreInfo);
                        assertNotNull(response.message);
                        assertEquals("https://go.votomobile.org/api/v1/subscribers?limit=2",
                                response.url);
                        assertEquals(
                                "https://go.votomobile.org/api/v1/subscribers?limit=2&page_after=376089",
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
                        String date = BaseTestCase
                                .formatShowingDate(response.data.subscribers.get(0).startDate);
                        assertEquals("2014-09-10", date);
                        assertEquals(200247l, (long) response.data.subscribers.get(0).languageId);
                        assertEquals("0", response.data.subscribers.get(0).isTestSubscriber);
                        assertEquals(
                                "201031, 201409, 204128, 204129, 204130, 204131, 204132, 204133, 204134, 204135, 204136, 204137",
                                response.data.subscribers.get(0).groupIds);
                        assertNotNull(response.data.subscribers.get(0).properties);
                        assertEquals("Kodjo Antwi",
                                response.data.subscribers.get(0).properties.name);
                        assertEquals("Ejisu", response.data.subscribers.get(0).properties.location);
                        assertEquals("Out flying kites",
                                response.data.subscribers.get(0).properties.comments);
                        mCountDownLatch.countDown();
                    }

                    @Override
                    public void onFailure(Call<ListSubscribersResponse> call, Throwable t) {
                        mCountDownLatch.countDown();
                    }
                });
        mCountDownLatch.await(40, TimeUnit.SECONDS);
    }

    @Test
    public void shouldSuccessfullyModifySubscriber() throws IOException, InterruptedException {
        mAsyncVotoApiClient
                .modifySubscriberDetails(1l, null, new Callback<CreateSubscriberResponse>() {
                    @Override
                    public void onResponse(Call<CreateSubscriberResponse> call,
                            Response<CreateSubscriberResponse> resp) {
                        CreateSubscriberResponse createSubscriberResponse = resp.body();
                        assertNotNull(createSubscriberResponse);
                        mCountDownLatch.countDown();
                    }

                    @Override
                    public void onFailure(Call<CreateSubscriberResponse> call, Throwable t) {
                        // Do nothing
                        mCountDownLatch.countDown();
                    }
                });
        mCountDownLatch.await(40, TimeUnit.SECONDS);
    }

    @Test
    public void shouldSuccessfullyListSubscriberDetails() throws IOException, InterruptedException {
        mAsyncVotoApiClient
                .listSubscriberDetails(1l, new Callback<SubscriberDetailsResponse>() {
                    @Override
                    public void onResponse(Call<SubscriberDetailsResponse> call,
                            Response<SubscriberDetailsResponse> resp) {
                        SubscriberDetailsResponse createSubscriberResponse = resp.body();
                        assertNotNull(createSubscriberResponse);
                        mCountDownLatch.countDown();
                    }

                    @Override
                    public void onFailure(Call<SubscriberDetailsResponse> call, Throwable t) {
                        // Do nothing
                        mCountDownLatch.countDown();
                    }
                });
        mCountDownLatch.await(40, TimeUnit.SECONDS);
    }

    @Test
    public void shouldSuccessfullyListAudioFileDetails() throws IOException {
        mAsyncVotoApiClient
                .listAudioFileDetails(1l, new Callback<AudioFileDetailsResponse>() {
                    @Override
                    public void onResponse(Call<AudioFileDetailsResponse> call,
                            Response<AudioFileDetailsResponse> resp) {
                        AudioFileDetailsResponse response = resp.body();
                        assertNotNull(response);
                    }

                    @Override
                    public void onFailure(Call<AudioFileDetailsResponse> call, Throwable t) {
                        // Do nothing
                    }
                });
    }

    @Test
    public void shouldSuccessfullyUploadAudioFileContent() throws IOException {
        mAsyncVotoApiClient
                .uploadAudioFileContent("description",
                        AudioFileExtension.MP3, null, new Callback<UploadAudioFileResponse>() {
                            @Override
                            public void onResponse(Call<UploadAudioFileResponse> call,
                                    Response<UploadAudioFileResponse> resp) {
                                UploadAudioFileResponse response = resp.body();
                                assertNotNull(response);
                            }

                            @Override
                            public void onFailure(Call<UploadAudioFileResponse> call, Throwable t) {
                                //Do nothing
                            }
                        });
    }

    @Test
    public void shouldSuccessfullyUpdateAudioFileContent() throws IOException {
        mAsyncVotoApiClient
                .updateAudioFileContent(1l, AudioFileExtension.MP3, null,
                        new Callback<UploadAudioFileResponse>() {
                            @Override
                            public void onResponse(Call<UploadAudioFileResponse> call,
                                    Response<UploadAudioFileResponse> resp) {
                                UploadAudioFileResponse response = resp.body();
                                assertNotNull(response);
                            }

                            @Override
                            public void onFailure(Call<UploadAudioFileResponse> call, Throwable t) {
                                //Do nothing
                            }
                        });
    }

    @Test
    public void shouldThrowExceptionWhenApiKeyIsNull() {
        try {
            new AsyncVotoApiClient.Builder(null).build();
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("apiKey shouldn't be null or empty.", e.getMessage());
        }
    }

    @Test
    public void shouldBuildRxJavaVotoApiClient() {
        AsyncVotoApiClient syncVotoClient = new AsyncVotoApiClient.Builder("api_key").build();
        assertNotNull(syncVotoClient);
    }

    @Test
    public void shouldMakeSureLimitIsSet() throws IOException {
        mAsyncVotoApiClient.listSubscribers(2, new Callback<ListSubscribersResponse>() {
            @Override
            public void onResponse(Call<ListSubscribersResponse> call,
                    Response<ListSubscribersResponse> response) {
                // Do nothing
            }

            @Override
            public void onFailure(Call<ListSubscribersResponse> call, Throwable t) {

            }
        });
        assertTrue((mAsyncVotoApiClient.getLimit() < Constants.PAGINATION_LIMIT));
        assertEquals(2, mAsyncVotoApiClient.getLimit());
    }

    @Test
    public void shouldMakeSureIsDefaultLimit() throws IOException {
        mAsyncVotoApiClient.listSubscribers(0, new Callback<ListSubscribersResponse>() {
            @Override
            public void onResponse(Call<ListSubscribersResponse> call,
                    Response<ListSubscribersResponse> response) {
                // Do nothing
            }

            @Override
            public void onFailure(Call<ListSubscribersResponse> call, Throwable t) {

            }
        });
        assertEquals(Constants.PAGINATION_LIMIT, mAsyncVotoApiClient.getLimit());
    }

    @Test
    public void shouldThrowExceptionWhenEmptyDescriptionIsSet() throws IOException {
        try {
            mAsyncVotoApiClient.uploadAudioFileContent(null, AudioFileExtension.MP3, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(DESCRIPTION_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenDeleteSubscriberFileIdIsNull() throws IOException {
        try {
            mAsyncVotoApiClient.deleteSubscriber(null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenEmptyFileExtensionIsSet() throws IOException {
        try {
            mAsyncVotoApiClient.uploadAudioFileContent("description", null, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(FILE_EXTENSION_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenListAudioFileDetailsFileIdIsNull() throws IOException {
        try {
            mAsyncVotoApiClient.listAudioFileDetails(null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenUpdateAudioFileIdIsNull() throws IOException {
        try {
            mAsyncVotoApiClient.updateAudioFileContent(null, AudioFileExtension.MP3, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenUpdateAudioFileFileExtensionIsNull() throws IOException {
        try {
            mAsyncVotoApiClient.updateAudioFileContent(1l, null, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(FILE_EXTENSION_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenDownloadFileIdIsNull() throws IOException {
        try {
            mAsyncVotoApiClient.downloadAudioFile(null, AudioFileFormat.ORIGINAL, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenDownloadFileFileFormatIsNotSet() throws IOException {
        try {
            mAsyncVotoApiClient.downloadAudioFile(1l, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(FILE_FORMAT_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenListSubscriberIdFieldIsNull() throws IOException {
        try {
            mAsyncVotoApiClient.listSubscriberDetails(null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenModifySubscriberDetailsFileIdIsNull() throws IOException {
        try {
            mAsyncVotoApiClient.modifySubscriberDetails(null, null, null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(ID_REQUIRED, e.getMessage());
        }
    }

    @Test
    public void shouldSuccessfullyDownloadAudioFile() throws IOException {
        mAsyncVotoApiClient
                .downloadAudioFile(1l, AudioFileFormat.ORIGINAL, new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                            Response<ResponseBody> resp) {
                        ResponseBody responseBody = resp.body();
                        assertNotNull(responseBody);
                        try {
                            assertEquals("AudioFile", responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    @Test
    public void shouldSuccessfullyDeleteAudioFile() throws IOException {
        mAsyncVotoApiClient.deleteAudioFile(1l,
                new Callback<DeleteAudioFileResponse>() {
                    @Override
                    public void onResponse(Call<DeleteAudioFileResponse> call,
                            Response<DeleteAudioFileResponse> resp) {
                        DeleteAudioFileResponse audioFileResponse = resp.body();
                        assertNotNull(audioFileResponse);
                        assertEquals(200, (int) audioFileResponse.status);
                        assertEquals("Successfully deleted audio file", audioFileResponse.message);
                    }

                    @Override
                    public void onFailure(Call<DeleteAudioFileResponse> call, Throwable t) {
                        // Do nothing
                    }
                });
    }

    @Test
    public void shouldSuccessfullyListAudioFile() throws IOException {
        mAsyncVotoApiClient.listAudioFiles(
                new Callback<ListAudioFilesResponse>() {
                    @Override
                    public void onResponse(Call<ListAudioFilesResponse> call,
                            Response<ListAudioFilesResponse> resp) {
                        ListAudioFilesResponse audioFilesResponse = resp.body();
                        assertNotNull(audioFilesResponse);
                        assertEquals(200, (int) audioFilesResponse.status);
                    }

                    @Override
                    public void onFailure(Call<ListAudioFilesResponse> call, Throwable t) {
                        // Do nothing
                    }
                });
    }

    @Test
    public void shouldSuccessfullyListMessages() throws IOException {
        mAsyncVotoApiClient.listMessages(new Callback<ListMessagesResponse>() {
            @Override
            public void onResponse(Call<ListMessagesResponse> call,
                    Response<ListMessagesResponse> response) {
                ListMessagesResponse listMessagesResponse = response.body();
                assertNotNull(listMessagesResponse);
                assertEquals(200, (int) listMessagesResponse.status);
            }

            @Override
            public void onFailure(Call<ListMessagesResponse> call, Throwable t) {
                // Do nothing
            }
        });
    }

    @Test
    public void shouldSuccessfullyCreateMessages() throws IOException {
        mAsyncVotoApiClient.createMessage("title", com.addhen.voto.sdk.model.Status.NO,
                com.addhen.voto.sdk.model.Status.YES, null, new Callback<CreateResponse>() {
                    @Override
                    public void onResponse(Call<CreateResponse> call,
                            Response<CreateResponse> response) {
                        CreateResponse createResponse = response.body();
                        assertNotNull(createResponse);
                        assertEquals(200, (int) createResponse.status);
                    }

                    @Override
                    public void onFailure(Call<CreateResponse> call, Throwable t) {
                        // Do nothing
                    }
                });
    }

    @Test
    public void shouldSuccessfullyUpdateMessage() throws IOException {
        mAsyncVotoApiClient.updateMessage(1l, null, new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                CreateResponse createResponse = response.body();
                assertNotNull(createResponse);
                assertEquals(200, (int) createResponse.status);
            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                // Do nothing
            }
        });
    }

    @Test
    public void shouldSuccessfullyGetMessageDeliveryLogCount() throws IOException {
        mAsyncVotoApiClient.getMessageDeliveryLog(1l, null,
                new Callback<MessageDeliveryLogResponse>() {
                    @Override
                    public void onResponse(Call<MessageDeliveryLogResponse> call,
                            Response<MessageDeliveryLogResponse> response) {
                        MessageDeliveryLogResponse messageDeliveryLogResponse = response.body();
                        assertNotNull(messageDeliveryLogResponse);
                        assertNotNull(messageDeliveryLogResponse);
                        assertEquals(200, (int) messageDeliveryLogResponse.status);
                        assertEquals(1000, (int) messageDeliveryLogResponse.code);
                        assertNotNull(messageDeliveryLogResponse.data);
                        assertEquals(201712, (long) messageDeliveryLogResponse.data.messageId);
                        assertEquals(2, (int) messageDeliveryLogResponse.data.count);
                        assertNull(messageDeliveryLogResponse.data.filterAfterDate);
                        assertNull(messageDeliveryLogResponse.data.filterBeforeDate);
                        assertNull(messageDeliveryLogResponse.data.filterDeliveryStatus);
                    }

                    @Override
                    public void onFailure(Call<MessageDeliveryLogResponse> call, Throwable t) {
                        // Do nothing
                    }
                });
    }
}
