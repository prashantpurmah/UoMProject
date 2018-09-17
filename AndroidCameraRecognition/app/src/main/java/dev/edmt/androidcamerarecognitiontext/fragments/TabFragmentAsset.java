package dev.edmt.androidcamerarecognitiontext.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import dev.edmt.androidcamerarecognitiontext.R;

public class TabFragmentAsset extends Fragment {

    private static final String LOG_TAG = TabFragmentAsset.class.getSimpleName();

    private Button assignButton;
    private Button returnButton;
    private Button viewButton;

    private int assignButtonWidth;
    private int viewButtonWidth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.tab_fragment_asset, container, false);
        assignButton = (Button) view.findViewById(R.id.assign_button);
        returnButton = (Button) view.findViewById(R.id.return_button);
        viewButton = (Button) view.findViewById(R.id.view_button);

        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent assetIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(assetIntent);*/
            }
        };

        assignButton.setOnClickListener(onClickListener);

        return view;
    }



}
