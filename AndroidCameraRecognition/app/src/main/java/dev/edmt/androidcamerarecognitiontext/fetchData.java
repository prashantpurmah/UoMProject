package dev.edmt.androidcamerarecognitiontext;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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


public class fetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    String name = "";
    final String urlKey = "employees/";
    List<Employee> employees = new ArrayList<>();
    Context context;

    public fetchData(){}

    public fetchData(String name, Context context){
        this.name = name;
        this.context = context;
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

                Employee employee = new Employee(JO.get("employeeId").toString(),JO.get("name").toString());
                employees.add(employee);

                singleParsed =  "Name:" + JO.get("name") + "\n"+
                        "EmployeeId:" + JO.get("employeeId") + "\n";

                dataParsed = dataParsed + singleParsed +"\n" ;
            }

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
        Intent validateEmployee = new Intent(context,ValidateEmployee.class);
        validateEmployee.putExtra("employees",(ArrayList<Employee>)employees);
        context.startActivity(validateEmployee);
    }
}
