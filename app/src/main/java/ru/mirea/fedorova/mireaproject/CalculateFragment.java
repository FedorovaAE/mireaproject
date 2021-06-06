package ru.mirea.fedorova.mireaproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculateFragment newInstance(String param1, String param2) {
        CalculateFragment fragment = new CalculateFragment();
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
        return inflater.inflate(R.layout.fragment_calculate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText etNum1 = view.findViewById(R.id.editTextNum1);
        EditText etNum2 = view.findViewById(R.id.editTextNum2);
        Button btnAdd = view.findViewById(R.id.buttonAdd);
        Button btnSub = view.findViewById(R.id.buttonSub);
        Button btnMult = view.findViewById(R.id.buttonMult);
        Button btnDiv = view.findViewById(R.id.buttonDiv);
        TextView tvResult = view.findViewById(R.id.textView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNum1.getText().toString())
                        || TextUtils.isEmpty(etNum2.getText().toString())) return;
                float num1 = Float.parseFloat(etNum1.getText().toString());
                float num2 = Float.parseFloat(etNum2.getText().toString());
                float result = num1 + num2;
                tvResult.setText(num1 + " + " + num2 + " = " + result);
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNum1.getText().toString())
                        || TextUtils.isEmpty(etNum2.getText().toString())) return;
                float num1 = Float.parseFloat(etNum1.getText().toString());
                float num2 = Float.parseFloat(etNum2.getText().toString());
                float result = num1 - num2;
                tvResult.setText(num1 + " - " + num2 + " = " + result);
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNum1.getText().toString())
                        || TextUtils.isEmpty(etNum2.getText().toString())) return;
                float num1 = Float.parseFloat(etNum1.getText().toString());
                float num2 = Float.parseFloat(etNum2.getText().toString());
                float result = num1 * num2;
                tvResult.setText(num1 + " * " + num2 + " = " + result);
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNum1.getText().toString())
                        || TextUtils.isEmpty(etNum2.getText().toString())) return;
                float num1 = Float.parseFloat(etNum1.getText().toString());
                float num2 = Float.parseFloat(etNum2.getText().toString());
                if (num2 != 0) {
                    float result = num1 / num2;
                    tvResult.setText(num1 + " / " + num2 + " = " + result);
                } else tvResult.setText("Нельзя делить на 0!");
            }
        });
    }
}