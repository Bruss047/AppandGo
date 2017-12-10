package mapas.locaciones;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static mapas.locaciones.DetailActivity.EXTRA_POSITION;
import static mapas.locaciones.R.array.posicionamientos_latitud1;
import static mapas.locaciones.R.styleable.View;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitud;
    double longitud;
    Button xj;
    int position;
   int valor;
    boolean ejecutado = true;

    String cafe;


    //String[] lugares = resources.getStringArray(R.array.cafe);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        xj = (Button) findViewById(R.id.xj);
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        } else {

        }


        xj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        position = getIntent().getIntExtra("posicion", 0);





        //cafe= (lugares[position % lugares.length]);
        /*public void onClick (View v){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }*/
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);


        if (!((LocationManager) this.getSystemService(Context.LOCATION_SERVICE))
                .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //prompt user to enable g
            // ps
            Toast.makeText(MapsActivity.this, "GPS Desactivado: Activalo si deseas una mejor experiencia de navegación.", Toast.LENGTH_LONG).show();
            Intent gpsOptionsIntent = new Intent(
                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(gpsOptionsIntent);
        } else {
            //gps is enabled
        }


        try {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]
                            {
                                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.INTERNET,
                                    Manifest.permission.ACCESS_NETWORK_STATE
                            }, 10);


                }


            }
            googleMap.setMyLocationEnabled(true);


        } catch (Exception e) {
            e.printStackTrace();

        }

        Ubicacion();


    }


    public void Ubicacion() {


        Resources resources = getResources();
        String[] cordenadas = resources.getStringArray(posicionamientos_latitud1);
        String[] cordenadastwo = resources.getStringArray(R.array.posicionamientos_longitud1);

        // Add a marker in Sydney and move the camera

        if (position == 0) {
            for (int h = 0; h < cordenadas.length; h++) {

                latitud = Double.parseDouble(cordenadas[h]);
                longitud = Double.parseDouble(cordenadastwo[h]);

                LatLng casa = new LatLng(latitud, longitud);
                mMap.addMarker(new MarkerOptions().position(casa).title("s").snippet("Este es boror").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                float zoomlevel = 12;
                mMap.moveCamera(CameraUpdateFactory.newLatLng(casa));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(casa, zoomlevel));

            }


        }
        if (position == 1) {
            LatLng casa = new LatLng(latitud, longitud);
            mMap.addMarker(new MarkerOptions().position(casa).title("s").snippet("Este es boror").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }


    }


}
