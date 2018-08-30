package dev.edmt.androidcamerarecognitiontext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AssetDashboardActivity extends AppCompatActivity {

    private Button visitorButton;
    private Button assetButton;
    private Button optionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_dashboard);

        visitorButton = (Button) findViewById(R.id.visitor_button);
        assetButton = (Button) findViewById(R.id.asset_button);
        optionButton = (Button) findViewById(R.id.options_button);
    }

    public void onClickAsset(View view){
        Intent assetIntent = new Intent(this, MainMenuActivity.class);
        this.startActivity(assetIntent);
    }
}
