package dev.edmt.androidcamerarecognitiontext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail,edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtEmail = (MaterialEditText) findViewById(R.id.edtEmail);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(edtEmail.equals("")||edtPassword.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        });
    }
}
