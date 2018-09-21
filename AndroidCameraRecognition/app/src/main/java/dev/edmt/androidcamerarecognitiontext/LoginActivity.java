package dev.edmt.androidcamerarecognitiontext;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity implements AsyncResponse {

    private static final String LOG_DATA = LoginActivity.class.getSimpleName();

    private String loginResponse = "nothing yet";

    EditText edtEmail,edtPassword;
    Button btnSignIn;
    ProgressBar progressBar;
    AsyncResponse delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtEmail = (MaterialEditText) findViewById(R.id.edtEmail);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        progressBar = (ProgressBar) findViewById(R.id.login_progressBar);
        delegate = this;

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                if(edtEmail.getText().toString().equals("0")||edtPassword.getText().toString().equals("0")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    String[] data = {edtEmail.getText().toString(), edtPassword.getText().toString()};

                    ValidateUser validateUser = new ValidateUser(data, delegate);
                    validateUser.execute();
                    if (validateUser.getStatus() == AsyncTask.Status.RUNNING){
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(loginResponse.equals("202")){
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
                        startActivity(homeIntent);
                        finish();
                    } else if(loginResponse.equals("401")){
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                    } else if(loginResponse.equals("404")){
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), "Username not found", Toast.LENGTH_SHORT).show();
                    } else{
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), "Error logging in", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });


    }
    @Override
    public void processFinish(String output) {
        Log.d(LOG_DATA, output);
        this.loginResponse = output;
    }
}
