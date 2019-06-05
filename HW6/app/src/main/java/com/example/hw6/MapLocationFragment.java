package com.example.hw6;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

//https://developers.google.com/maps/documentation/android-sdk/current-place-tutorial

public class MapLocationFragment extends AppCompatActivity implements OnMapReadyCallback {
    private final static String TAG = "Map Location: ";
    private GoogleMap mMap;
    private FusedLocationProviderClient mLocationClient;
    private boolean mLocationPermissionGranted = false;
    private static final String ERROR_MSG = "Play Services Missing!";

    //https://developers.google.com/maps/documentation/android-sdk/marker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);

//https://developers.google.com/maps/documentation/android-sdk/marker

        @Override
        mLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /** Called when the map is ready. */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        getLocationPermission();
        getLocation();


        Inten intent = getIntent();
        String[] cam = intent.getStringArrayExtra(MainActivity.RESULT);


        Double longitude = Double.parseDouble(cam[0]);
        Double latitude = Double.parseDouble(cam[1]);

        String id = cam[2];
        String ur1 = cam[3];

        // Add some markers to the map, and add a data object to each marker.

        LatLng camera = new Latlng(latitude, longitude);

        Marker camMarker = mMap.addMarker(new MarkerOptions()
                .position(camera)
                .title(id));

        camMarker.setTag(0);

        //Video Demo
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(camera, 10));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(camera, 10));

    }
    //Video Demo
    private void getLocationPermission(){
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            mLocationPermissionGranted = true;
        }
        else{

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    }

    //https://developer.android.com/reference/android/support/v4/app/ActivityCompat.OnRequestPermissionsResultCallback

    //Callback for the result from requesting permissions

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        mLocationPermissionGranted = false;

        switch(requestCode){

            case 1: { if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted = true;
                getLocation();

            }


            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        if(mLocationPermissionGranted) {
            Task location = mLocationClient.getLastLocation();

            location.addOnCompleteListener(new OnCompleteListener<Location>() {

                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location actualLocation = task.getResult();
                    if (actualLocation != null) {
                        String latLong = String.format("Lat: %f, Long: %f ",
                                actualLocation.getLatitude(),
                                actualLocation.getLongitude());

                        mMap.setMyLocationEnabled(true);
                        mMap.getUiSettings().setMyLocationButtonEnabled(true);

                        LatLng here = new LatLng(actualLocation.getLatitude(),
                                actualLocation.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(here).title("Current Location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here, 10));

                        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                    } else {
                        Log.e(TAG, "Can't find location!");

                    }
                }
            });
            location.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            });

        }

    }

    /** Called when the user clicks a marker.
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
     */
}
