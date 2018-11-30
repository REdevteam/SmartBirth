package com.futech.smartbirth;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        imageView = v.findViewById(R.id.imageViewAddress);
        imageView.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(getActivity().getApplicationContext(), MapsActivity.class);
        getActivity().getApplicationContext().startActivity(i);
    }
}
