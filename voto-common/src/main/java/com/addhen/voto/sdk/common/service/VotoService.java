package com.addhen.voto.sdk.common.service;

import com.addhen.voto.sdk.Constants;
import com.addhen.voto.sdk.model.subscribers.CreateBulkSubscribersResponse;
import com.addhen.voto.sdk.model.subscribers.CreateSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.DeleteSubscriberResponse;
import com.addhen.voto.sdk.model.subscribers.IfPhoneNumberExists;
import com.addhen.voto.sdk.model.subscribers.ListSubscribersResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author Henry Addo
 */
public interface VotoService {

    /** Subscribers **/
    @FormUrlEncoded
    @POST(Constants.VotoEndpoints.SUBSCRIBERS)
    Call<CreateSubscriberResponse> createSubscriber(
            @Field("phone") String phone,
            @QueryMap Map<String, String> optionalFields
    );

    @FormUrlEncoded
    @POST(Constants.VotoEndpoints.SUBSCRIBERS)
    Call<CreateBulkSubscribersResponse> createBulkSubscribers(
            @Field("phone_numbers") String phone,
            @Field("if_phone_number_exists") IfPhoneNumberExists ifPhoneNumberExists,
            @QueryMap Map<String, String> optionalFields
    );

    @GET(Constants.VotoEndpoints.SUBSCRIBERS)
    Call<ListSubscribersResponse> listSubscribers(@Query("limit") int limit);

    @PUT(Constants.VotoEndpoints.SUBSCRIBERS + "/{id}")
    Call<CreateSubscriberResponse> modifySubscriberDetails(@Path("id") Long id,
            @QueryMap Map<String, String> optionalFields);

    @DELETE(Constants.VotoEndpoints.SUBSCRIBERS + "/{id}")
    Call<DeleteSubscriberResponse> deleteSubscriber(@Path("id") Long id);
}
