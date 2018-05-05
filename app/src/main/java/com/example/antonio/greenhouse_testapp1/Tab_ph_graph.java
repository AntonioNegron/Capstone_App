package com.example.antonio.greenhouse_testapp1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Tab_ph_graph extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity_ph_graph, container, false);


        GraphView graphData = (GraphView) rootView.findViewById(R.id.ph_graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(11, 70),
                new DataPoint(12, 72),
                new DataPoint(13, 68),
                new DataPoint(14, 70),
                new DataPoint(15, 70)

        });
        graphData.addSeries(series);

        return rootView;
    }
}
