package dev.edmt.androidcamerarecognitiontext;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import dev.edmt.androidcamerarecognitiontext.entities.Employee;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class fetchData extends AsyncTask<Void,Void,Void> {

    private static final String LOG_DATA = fetchData.class.getSimpleName();

    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    String name = "";
    final String urlKey = "employees/";
    List<Employee> employees;
    Context context;

    public fetchData(){}

    public fetchData(String name, Context context){
        this.name = name;
        this.context = context;
    }

    public void getEmployeeByName(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.186.53.164:90/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Employee>> response = service.getEmployeeByName(name);

        try {
            this.employees = response.execute().body();
        } catch (IOException e) {
            Log.d(LOG_DATA, e.getMessage());
        }

    }

    @Override
    protected Void doInBackground(Void... voids) {
        getEmployeeByName();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.tvIsConnected.setText(this.dataParsed);
        Intent validateEmployee = new Intent(context,ValidateEmployee.class);
        validateEmployee.putExtra("employees",(ArrayList<Employee>)employees);
        context.startActivity(validateEmployee);
    }
}
