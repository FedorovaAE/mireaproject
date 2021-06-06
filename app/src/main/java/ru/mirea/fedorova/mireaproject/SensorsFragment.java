package ru.mirea.fedorova.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorsFragment extends Fragment implements SensorEventListener {
    private TextView XTextView;
    private TextView YTextView;
    private TextView ZTextView;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;

    private TextView X2TextView;
    private TextView Y2TextView;
    private TextView Z2TextView;
    private SensorManager sensorManager2;
    private Sensor mfSensor;

    private TextView X3TextView;
    private TextView Y3TextView;
    private TextView Z3TextView;
    private SensorManager sensorManager3;
    private Sensor accelerometerSensor;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SensorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorsFragment newInstance(String param1, String param2) {
        SensorsFragment fragment = new SensorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        XTextView = view.findViewById(R.id.textViewX);
        YTextView = view.findViewById(R.id.textViewY);
        ZTextView = view.findViewById(R.id.textViewZ);

        sensorManager2 = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mfSensor = sensorManager2.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        X2TextView = view.findViewById(R.id.textViewX2);
        Y2TextView = view.findViewById(R.id.textViewY2);
        Z2TextView = view.findViewById(R.id.textViewZ2);

        sensorManager3 = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager3.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        X3TextView = view.findViewById(R.id.textViewX3);
        Y3TextView = view.findViewById(R.id.textViewY3);
        Z3TextView = view.findViewById(R.id.textViewZ3);
    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        sensorManager2.unregisterListener(this);
        sensorManager3.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gyroscopeSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager2.registerListener(this, mfSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager3.registerListener(this, accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float valueX = event.values[0];
            float valueY = event.values[1];
            float valueZ = event.values[2];
            XTextView.setText("X: " + valueX);
            YTextView.setText("Y: " + valueY);
            ZTextView.setText("Z: " + valueZ);
        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float valueX2 = event.values[0];
            float valueY2 = event.values[1];
            float valueZ2 = event.values[2];
            X2TextView.setText("X: " + valueX2);
            Y2TextView.setText("Y: " + valueY2);
            Z2TextView.setText("Z: " + valueZ2);
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float valueX3 = event.values[0];
            float valueY3 = event.values[1];
            float valueZ3 = event.values[2];
            X3TextView.setText("X: " + valueX3);
            Y3TextView.setText("Y: " + valueY3);
            Z3TextView.setText("Z: " + valueZ3);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d("Sensor", "Changed");
    }
}