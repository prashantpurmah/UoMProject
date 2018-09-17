package dev.edmt.androidcamerarecognitiontext.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import dev.edmt.androidcamerarecognitiontext.MainActivity;
import dev.edmt.androidcamerarecognitiontext.MainMenuActivity;
import dev.edmt.androidcamerarecognitiontext.R;
import dev.edmt.androidcamerarecognitiontext.imageclassifier.ClassifierActivity;
import dev.edmt.androidcamerarecognitiontext.imageclassifier.TensorTestActivity;

public class TabFragmentAsset extends Fragment {

    private Button assignButton;
    private Button returnButton,viewButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.tab_fragment_asset, container, false);
        assignButton = (Button) view.findViewById(R.id.assign_button);
        returnButton = (Button) view.findViewById(R.id.return_button);
        viewButton = (Button) view.findViewById(R.id.view_button);

        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent assetIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(assetIntent);
            }
        };

        assignButton.setOnClickListener(onClickListener);

        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(getActivity(), ClassifierActivity.class);
                startActivity(activityChangeIntent);
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent activityChangeIntent = new Intent(getActivity(), TensorTestActivity.class);
                startActivity(activityChangeIntent);
            }
        });

        return view;
    }

}
