package dev.edmt.androidcamerarecognitiontext;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ValidateUser extends AsyncTask<Void, Void, String> {

    private static final String LOG_DATA = ValidateUser.class.getSimpleName();

    private final String urlToUse = "http://26cea77e.ngrok.io";
    private String[] data;
    private String result;
    public AsyncResponse delegate = null;

    public ValidateUser(String[] data){
        this.data = data;
    }

    public String post()
    {
        URL url = null;
        try {
            url = new URL(urlToUse);
        } catch (MalformedURLException e) {
            Log.d(LOG_DATA, e.getLocalizedMessage());
        }
        HttpURLConnection client = null;

        try
        {
            client = (HttpURLConnection) url.openConnection();

            client.setRequestMethod("POST");
            client.setRequestProperty("employeeId", data[0]);
            client.setRequestProperty("password", data[1]);
            client.setDoOutput(true);

            OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
            result = outputPost.toString();
            outputPost.flush();
            outputPost.close();
        }
        catch(SocketTimeoutException error) {
            Log.d(LOG_DATA, error.getMessage());
        }
        catch(IOException e)
        {
            Log.d(LOG_DATA, e.getMessage());
            e.printStackTrace();

        }
        finally {
            if(client != null) // Make sure the connection is not null.
                client.disconnect();
        }

        return result;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return post();
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);

    }
}
