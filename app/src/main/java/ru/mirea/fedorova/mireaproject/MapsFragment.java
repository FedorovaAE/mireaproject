package ru.mirea.fedorova.mireaproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng mirea = new LatLng(55.66999, 37.48046);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(mirea).zoom(12)
                    .build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.addMarker(new MarkerOptions().title("МИРЭА г. Москва").snippet("Основной" +
                    " кампус на Проспекте Вернадского, 78; 1980-е; 55.66999, 37.48046")
                    .position(mirea));
            LatLng mirea1 = new LatLng(45.061796, 41.920961);
            googleMap.addMarker(new MarkerOptions().title("МИРЭА Филиал в г. Ставрополе")
                    .snippet("ул. Кулакова, д. 18; 1996 г.; 45.061796, 41.920961").position(mirea1));
            LatLng mirea2 = new LatLng(55.965662, 38.048818);
            googleMap.addMarker(new MarkerOptions().title("МИРЭА Филиал в г. Фрязино")
                    .snippet("ул. Вокзальная, д.2а; 1962 г.; 55.965662, 38.048818").position(mirea2));

            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(latLng).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    LayoutInflater li = LayoutInflater.from(getActivity());
                    View dialogView = li.inflate(R.layout.dialog_maps, null);
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                    dialogBuilder.setView(dialogView);
                    final EditText userTitle = dialogView.findViewById(R.id.editTextTitle);
                    final EditText userSnippet = dialogView.findViewById(R.id.editTextSnippet);
                    dialogBuilder.setCancelable(false).setPositiveButton("Добавить маркер",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    googleMap.addMarker(new MarkerOptions().title(
                                            userTitle.getText().toString())
                                            .snippet(userSnippet.getText().toString())
                                            .position(latLng).draggable(true));
                                    Toast.makeText(getActivity(), "Добавлено, маркер можно " +
                                            "перемещать", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}