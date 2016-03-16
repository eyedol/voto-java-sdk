package com.addhen.voto.sdk.common.service;

import com.addhen.voto.sdk.model.CreateSubscriberResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author Henry Addo
 */
public interface VotoService {

    /** Subscribers **/
    @FormUrlEncoded
    @POST("/user/{userId}/rooms/{roomId}/unreadItems")
    Call<CreateSubscriberResponse> createSubscriber(
            @Field("phone") String phone,
            @QueryMap Map<String, String> optionalFields
    );
}
