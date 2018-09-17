package dev.edmt.androidcamerarecognitiontext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import dev.edmt.androidcamerarecognitiontext.imageclassifier.ClassifierActivity;
import dev.edmt.androidcamerarecognitiontext.imageclassifier.Logger;
import dev.edmt.androidcamerarecognitiontext.imageclassifier.ResultsView;

public class ShowPopUp extends AppCompatActivity {
    private static final Logger LOGGER = new Logger();
    /** Called when the activity is first created. */

    String assetTag,assetName;
    String employeeName = "";
    TextView tag,name,employee;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_connection_fragment);

        assetName = getIntent().getStringExtra("assetName");
        assetTag = getIntent().getStringExtra("assetTag");
        employeeName=getIntent().getStringExtra("employeeName");
        LOGGER.e("02TEST "+assetName," Test", "Result: ");
        LOGGER.e("02TEST "+assetTag," Test", "Result: ");

        final Button btnOpenPopup = (Button)findViewById(R.id.openpopup);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup_window, null);
                tag = (TextView) popupView.findViewById(R.id.Tag);
                name = (TextView) popupView.findViewById(R.id.Name);
                employee = (TextView) popupView.findViewById(R.id.Employee);
                tag.setText("TAG: " +assetTag.toString());
                name.setText("NAME: " +assetName.toString());
                employee.setText("EMPLOYEE: "+employee.toString());

                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Intent activityChangeIntent = new Intent(ShowPopUp.this, ClassifierActivity.class);
                        startActivity(activityChangeIntent);
                        finish();
                    }});

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

            }});
    }
}
