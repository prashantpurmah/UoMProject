package dev.edmt.androidcamerarecognitiontext;

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


public class fetchData extends AsyncTask<Void,Void,Void> {

    private final static String LOG_TAG = fetchData.class.getSimpleName();

    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    String name = "";
    final String urlKey = "employees/";

    public fetchData(){}

    public fetchData(String name){
        this.name = name;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://xxx/xxx/"+urlKey+name);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);

            for(int i =0 ;i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed =  "Name:" + JO.get("name") + "\n"+
                        "EmployeeId:" + JO.get("employeeId") + "\n";

                dataParsed = dataParsed + singleParsed +"\n" ;
            }

            Log.d(LOG_TAG, dataParsed);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.tvIsConnected.setText(this.dataParsed);

    }
}
