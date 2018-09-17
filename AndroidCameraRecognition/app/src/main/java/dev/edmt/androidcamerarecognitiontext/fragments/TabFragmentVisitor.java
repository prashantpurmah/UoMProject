package dev.edmt.androidcamerarecognitiontext.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.edmt.androidcamerarecognitiontext.R;

public class TabFragmentVisitor extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.tab_fragment_visitor, container, false);
    }
}