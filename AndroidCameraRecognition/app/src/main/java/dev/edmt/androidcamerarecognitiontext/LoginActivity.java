package dev.edmt.androidcamerarecognitiontext;

import android.content.Intent;
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
    String[] userData;
    String URL = "";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtEmail = (MaterialEditText) findViewById(R.id.edtEmail);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        progressBar = (ProgressBar) findViewById(R.id.login_progressBar);

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

                    ValidateUser validateUser = new ValidateUser(data);
                    validateUser.delegate = LoginActivity.this;
                    validateUser.execute();
                    Log.d(LOG_DATA, "0"+data[0]+data[1]);

                    Log.d(LOG_DATA, validateUser.getStatus().name());
                    Log.d(LOG_DATA, loginResponse);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Log.d(LOG_DATA, e.getMessage());
                    }
                    Log.d(LOG_DATA, validateUser.getStatus().name());
                    Log.d(LOG_DATA, loginResponse);

                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });


    }
    @Override
    public void processFinish(String output) {
        this.loginResponse = output;
    }
}
