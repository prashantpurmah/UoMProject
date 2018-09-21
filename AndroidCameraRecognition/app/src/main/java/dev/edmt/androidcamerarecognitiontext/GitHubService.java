package dev.edmt.androidcamerarecognitiontext;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GitHubService {

    @POST("/users")
    Call<ResponseBody> validateUser(@Body LoginEntity loginEntity);

    @GET("/employees")
    @FormUrlEncoded
    Call<String> employeeHome();
}
