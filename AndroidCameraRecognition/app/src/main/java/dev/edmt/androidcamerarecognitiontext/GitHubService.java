package dev.edmt.androidcamerarecognitiontext;

import java.util.List;

import dev.edmt.androidcamerarecognitiontext.entities.AssetModel;
import dev.edmt.androidcamerarecognitiontext.entities.Employee;
import dev.edmt.androidcamerarecognitiontext.entities.EmployeeAssetModel;
import dev.edmt.androidcamerarecognitiontext.entities.LoginEntity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GitHubService {

    @POST("/users")
    Call<ResponseBody> validateUser(@Body LoginEntity loginEntity);

    @GET("assets/{id}/")
    Call<ResponseBody> getAssetById(@Path("id") String id);

    @GET("employees/{name}/")
    Call<List<Employee>> getEmployeeByName(@Path("name") String name);

    @POST("/employee-asset")
    Call<ResponseBody> assignAsset(@Body EmployeeAssetModel employeeAssetModel);

    @PUT("/employee-asset/{assetId}/")
    Call<ResponseBody> returnAsset(@Path("assetId") String assetId);

    @GET("/employee-asset")
    Call<List<EmployeeAssetModel>> viewTransactions();
}
