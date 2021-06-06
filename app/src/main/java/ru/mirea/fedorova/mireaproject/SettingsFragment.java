package ru.mirea.fedorova.mireaproject;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    private EditText editTextName;
    private EditText editTextAge;
    private EditText etR;
    private EditText etG;
    private EditText etB;
    private TextView tv;
    private View bgElement;
    private SharedPreferences preferencesName;
    final String SAVED_NAME = "saved_Name";
    private SharedPreferences preferencesAge;
    final String SAVED_AGE = "saved_Age";
    private SharedPreferences preferencesR;
    final String SAVED_R = "saved_R";
    private SharedPreferences preferencesG;
    final String SAVED_G = "saved_G";
    private SharedPreferences preferencesB;
    final String SAVED_B = "saved_B";
    private Button buttonSave;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextName = view.findViewById (R.id.editTextName);
        editTextAge = view.findViewById (R.id.editTextAge);
        etR = view.findViewById (R.id.editTextR);
        etG = view.findViewById (R.id.editTextG);
        etB = view.findViewById (R.id.editTextB);
        preferencesName = getActivity().getPreferences(getContext().MODE_PRIVATE);
        preferencesAge = getActivity().getPreferences(getContext().MODE_PRIVATE);
        preferencesR = getActivity().getPreferences(getContext().MODE_PRIVATE);
        preferencesG = getActivity().getPreferences(getContext().MODE_PRIVATE);
        preferencesB = getActivity().getPreferences(getContext().MODE_PRIVATE);
        bgElement = view.findViewById(R.id.fl);
        buttonSave = view.findViewById (R.id.buttonSave);
        String settingsName = preferencesName.getString(SAVED_NAME, "Имя");
        editTextName.setText(settingsName);
        String settingsAge = preferencesName.getString(SAVED_AGE, "0");
        editTextAge.setText(settingsAge);
        String colorR = preferencesR.getString(SAVED_R, "255");
        etR.setText(colorR);
        String colorG = preferencesG.getString(SAVED_G, "255");
        etG.setText(colorG);
        String colorB = preferencesB.getString(SAVED_B, "255");
        etB.setText(colorB);
        int r = Integer.parseInt(colorR);
        int g = Integer.parseInt(colorG);
        int b = Integer.parseInt(colorB);
        if (r > 255) r = 255;
        if (g > 255) g = 255;
        if (b > 255) b = 255;
        bgElement.setBackgroundColor(Color.rgb( r, g, b));
        Toast.makeText(getActivity(), "Загружено", Toast.LENGTH_SHORT).show();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editorName = preferencesName.edit();
                editorName.putString(SAVED_NAME, editTextName.getText().toString());
                editorName.apply();
                SharedPreferences.Editor editorAge = preferencesAge.edit();
                editorAge.putString(SAVED_AGE, editTextAge.getText().toString());
                editorAge.apply();
                SharedPreferences.Editor editorR = preferencesR.edit();
                editorR.putString(SAVED_R, etR.getText().toString());
                editorR.apply();
                SharedPreferences.Editor editorG = preferencesR.edit();
                editorG.putString(SAVED_G, etG.getText().toString());
                editorG.apply();
                SharedPreferences.Editor editorB = preferencesR.edit();
                editorB.putString(SAVED_B, etB.getText().toString());
                editorB.apply();
                int r = Integer.parseInt(etR.getText().toString());
                int g = Integer.parseInt(etG.getText().toString());
                int b = Integer.parseInt(etB.getText().toString());
                if (r > 255) r = 255;
                if (g > 255) g = 255;
                if (b > 255) b = 255;
                bgElement.setBackgroundColor(Color.rgb( r, g, b));
                Toast.makeText(getActivity(), "Сохранено", Toast.LENGTH_SHORT).show();
            }
        });
    }
}