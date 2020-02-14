package com.digitalhouse.a0819cpmoacn01arce_01.view.fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalhouse.a0819cpmoacn01arce_01.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticiasFragment extends Fragment {

    private MapView mapView;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private String latitude;
    private String longitude;

    private int locationRequestCode = 1000;
    private Double wayLatitude;
    private Double wayLongitude;


    public NoticiasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        location();


        SupportMapFragment supportMapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapsFragment);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.clear();
                googleMap.setMyLocationEnabled(true);

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(-34.6343603, -58.414678))
                        .zoom(12)
                        .bearing(0)
                        .tilt(45)
                        .build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-34.6343603, -58.414678))
                        .title("vendedor"));

            }
        });
        return view;
    }


    public interface notificadorNoticias {
        public void enviarNotificacionNoticias();
    }


    private void location() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    locationRequestCode);
        } else {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {

                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapsFragment);
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                googleMap.clear();
                                googleMap.setMyLocationEnabled(true);

                                CameraPosition googlePlex = CameraPosition.builder()
                                        .target(new LatLng(wayLatitude, wayLongitude))
                                        .zoom(12)
                                        .bearing(0)
                                        .tilt(45)
                                        .build();

                                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.5891340, -58.3937930))
                                        .title("Cinepolis Recoleta")
                                        .snippet("Vicente Lopez 2050, Recoleta Urban Mall")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.6032590, -58.4108300))
                                        .title("Cinemark Hoyts Abasto")
                                        .snippet("Av. Corrientes 3247, Abasto Shopping")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.5865220, -58.4103580))
                                        .title("Cinemark Palermo")
                                        .snippet("Beruti 3399, Palermo")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.6161600, -58.4288710))
                                        .title("Cinemark Hoyts Caballito")
                                        .snippet("Av. La Plata 96, Caballito")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.6186100, -58.4375080))
                                        .title("Cinepolis Caballito")
                                        .snippet("Av. Rivadavia 5071, Caballito")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.6293770, -58.4624630))
                                        .title("Atlas Flores")
                                        .snippet("Rivera Indarte 44, Flores")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.6398110, -58.5269060))
                                        .title("Plaza Liniers Shopping")
                                        .snippet("Av. Rivadavia 11526, Liniers")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.6115370, -58.5170510))
                                        .title("Cinema Devoto")
                                        .snippet("Quevedo 3365, Devoto")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.5469690, -58.4876330))
                                        .title("Hoyts Dot")
                                        .snippet("Vedia 3626, Dot Baires Shopping")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.5605260, -58.4563820))
                                        .title("Belgrano Multiplex")
                                        .snippet("Vuelta de Obligado 2199, Belgrano")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.5569190, -58.4615300))
                                        .title("Cinema City General Paz")
                                        .snippet("Av. Cabildo 2702, Belgrano")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.5567750, -58.4610740))
                                        .title("Arte Multiplex")
                                        .snippet("Av. Cabildo 2829, Belgrano")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.5537440, -58.4532790))
                                        .title("Showcase Belgrano")
                                        .snippet("Av. Monroe 1655, Belgrano")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.6210600, -58.3650670))
                                        .title("Cinemark Puerto Madero")
                                        .snippet("Av. Alicia Moreau de Justo 1920, Puerto Madero")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.6023600, -58.3774880))
                                        .title("Monumental Lavalle")
                                        .snippet("Lavalle 780, San Nicolas")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(-34.6090120, -58.3896120))
                                        .title("Cine Gaumont")
                                        .snippet("Av. Rivadavia 1635, Congreso")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2)));
                            }
                        });


                    } else {
                        Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {

                                wayLatitude = location.getLatitude();
                                wayLongitude = location.getLongitude();

                            } else {
                                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                }
                break;
            }
        }
    }
}
