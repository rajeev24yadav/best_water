package water.retorfit;


import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import water.model.CartItemModel;
import water.model.LoginResponseModel;

import water.model.MyAccountResponse;
import water.model.PickUpResponse;
import water.model.SignUpResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import water.utils.Constants;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/User/login")
    Single<LoginResponseModel> login(@Header("Unique-key") String value, @Field(Constants.PHONE_NUMBER) String phone_number);

    @FormUrlEncoded
    @POST("api/User/verify_otp")
    Single<LoginResponseModel> verify(@Header("Unique-key") String value, @Field("phone_number") String mobile_number
            , @Field("otp") String otp
            , @Field("type") String type);

    @FormUrlEncoded
    @POST("api/User/registration")
    Single<LoginResponseModel> signUp(@Header("Unique-key") String value, @Field("dob") int dob
            , @Field("phone_number") String phone_number
            , @Field("name") String name
            , @Field("email") String email);

    @FormUrlEncoded
    @POST("api/Water/product_list")
    Single<PickUpResponse> category(@Header("Unique-key") String value,@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("api/Water/product_details")
    Single<PickUpResponse> product(@Header("Unique-key") String value,@Field("product_id") String product_id);

    @FormUrlEncoded
    @POST("api/Water/banner_list")
    Single<PickUpResponse> banner(@Header("Unique-key") String value,@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("api/User/user_details")
    Single<MyAccountResponse> user_details(@Header("Unique-key") String value, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("api/User/user_update")
    Single<MyAccountResponse> user_update(@Header("Unique-key") String value, @Field("name") String name, @Field("date_of_birth") String dob,@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("api/User/cart_item_list")
    Single<CartItemModel> cart_item_list(@Header("Unique-key") String value, @Field("user_id") String user_id);
}
