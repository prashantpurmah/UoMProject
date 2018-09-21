package dev.edmt.androidcamerarecognitiontext;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ValidateUser extends AsyncTask<Void, Void, String> {

    private static final String LOG_DATA = ValidateUser.class.getSimpleName();

    private final String urlToUse = "http://10.186.53.164:90/users/";
    private String[] data;
    private String result = "Initialized";
    public AsyncResponse delegate = null;

    public ValidateUser(String[] data, AsyncResponse delegate){
        this.delegate = delegate;
        this.data = data;
    }

    public String checkLogin(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlToUse)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        LoginEntity loginEntity = new LoginEntity(data[0], data[1]);

        Call<ResponseBody> response = service.validateUser(loginEntity);
        Log.d(LOG_DATA, data[0] + "" + data[1]);

        Response<ResponseBody> actualResponse = null;
        try {
             actualResponse = response.execute();
             return String.valueOf(actualResponse.code());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    @Override
    protected String doInBackground(Void... voids) {
        return checkLogin();
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(LOG_DATA, result);
        delegate.processFinish(result);

    }
}
