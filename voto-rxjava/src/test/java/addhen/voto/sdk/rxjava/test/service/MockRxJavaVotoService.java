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

import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.IfPhoneNumberExists;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;
import com.addhen.voto.sdk.rxjava.service.RxJavaVotoService;
import com.addhen.voto.sdk.test.GsonDeserializer;

import java.util.Map;

import retrofit2.http.Field;
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
    public Observable<CreateSubscriberResponse> createSubscriber(@Field("phone") String phone,
            @QueryMap Map<String, String> optionalFields) {
        CreateSubscriberResponse createSubscriberResponse = mGsonDeserializer
                .deserializeCreateSubscriberResponse();
        return Observable.just(createSubscriberResponse);
    }

    @Override
    public Observable<CreateBulkSubscribersResponse> createBulkSubscribers(
            @Field("phone_numbers") String phone,
            @Field("if_phone_number_exists") IfPhoneNumberExists ifPhoneNumberExists,
            @QueryMap Map<String, String> optionalFields) {
        CreateBulkSubscribersResponse createBulkSubscribersResponse = mGsonDeserializer
                .deserializeCreateBulkSubscriberResponse();

        return Observable.just(createBulkSubscribersResponse);
    }

    @Override
    public Observable<ListSubscribersResponse> listSubscribers(@Query("limit") int limit) {
        ListSubscribersResponse listSubscribersResponse = mGsonDeserializer.listSubscribers();
        return Observable.just(listSubscribersResponse);
    }

    @Override
    public Observable<CreateSubscriberResponse> modifySubscriberDetails(@Path("id") Long id,
            @QueryMap Map<String, String> optionalFields) {
        CreateSubscriberResponse createSubscriberResponse = mGsonDeserializer
                .modifySubscriberDetails();
        return Observable.just(createSubscriberResponse);
    }

    @Override
    public Observable<DeleteSubscriberResponse> deleteSubscriber(@Path("id") Long id) {
        DeleteSubscriberResponse deleteSubscriberResponse = mGsonDeserializer.deleteSubscriber();
        return Observable.just(deleteSubscriberResponse);
    }
}
