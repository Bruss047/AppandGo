package mapas.locaciones;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static mapas.locaciones.DetailActivity.EXTRA_POSITION;
import static mapas.locaciones.R.array.posicionamientos_latitud1;
import static mapas.locaciones.R.styleable.View;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private Map<Marker, Class> allMarkersMap = new HashMap<Marker, Class>();
    private GoogleMap mMap;
    private GoogleMap mMap1;
    private double latitud;
    private double longitud;
    private String direcciones;
    private String titulo_sitios;
    private String snipet_sitios;
    private MediaPlayer mp;
    private Dialog dialog;
    private Button xj;
    private Button pop;
    private PopupWindow popupWindow;
    private Marker marker;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    private int position;


    String cafe;


    //String[] lugares = resources.getStringArray(R.array.cafe);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mp = MediaPlayer.create(this, R.raw.wololo);
        mp.start();
        xj = (Button) findViewById(R.id.xj);


        relativeLayout = (RelativeLayout) findViewById(R.id.rela);
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        } else {

        }
        position = getIntent().getIntExtra("posicion", 0);


        xj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });


    }


    //cafe= (lugares[position % lugares.length]);
        /*public void onClick (View v){

        }*/


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
            Toast.makeText(MapsActivity.this, "GPS Desactivado: Opcional: Activalo si deseas una mejor experiencia de navegación. ", Toast.LENGTH_LONG).show();
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
        String[] cordenadas_desayuno = resources.getStringArray(posicionamientos_latitud1);
        String[] cordenadas_desayuno2 = resources.getStringArray(R.array.posicionamientos_longitud1);
        String[] cordenadas_almuerzo = resources.getStringArray(R.array.posicionamientos_latitud2);
        String[] cordenadas_almuerzo2 = resources.getStringArray(R.array.posicionamientos_longitud2);
      //  String[] enlaces = resources.getStringArray((R.array.urls));
        String[] titulositios_desayuno = resources.getStringArray((R.array.nombre_sitios));
        String[] snipetsitios_desayuno = resources.getStringArray((R.array.snipet));
        String[] titulositios_almuerzo = resources.getStringArray((R.array.nombre_sitios_almuerzo));
        String[] snipetsitios_almuerzo = resources.getStringArray((R.array.snipet2));
      //  ArrayList<Marker> marcadores = new ArrayList<Marker>();
        //Map<Integer, GoogleMap> nombreMap = new HashMap<Integer, GoogleMap>();        // Add a marker in Sydney and move the camera

        if (position == 0) {


            for (int h = 0; h < cordenadas_desayuno.length; h++) {


                latitud = Double.parseDouble(cordenadas_desayuno[h]);
                longitud = Double.parseDouble(cordenadas_desayuno2[h]);
                titulo_sitios = titulositios_desayuno[h];
                snipet_sitios = snipetsitios_desayuno[h];

                LatLng casa = new LatLng(latitud, longitud);


                mMap.addMarker(new MarkerOptions().position(casa).title(titulo_sitios).snippet(snipet_sitios).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                float zoomlevel = 15;
                mMap.moveCamera(CameraUpdateFactory.newLatLng(casa));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(casa, zoomlevel));


            }


        }
        if (position == 1) { for (int h = 0; h < cordenadas_almuerzo.length; h++) {


            latitud = Double.parseDouble(cordenadas_almuerzo[h]);
            longitud = Double.parseDouble(cordenadas_almuerzo2[h]);
            titulo_sitios = titulositios_almuerzo[h];
            snipet_sitios = snipetsitios_almuerzo[h];

            LatLng casa = new LatLng(latitud, longitud);


            mMap.addMarker(new MarkerOptions().position(casa).title(titulo_sitios).snippet(snipet_sitios).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            float zoomlevel = 15;
            mMap.moveCamera(CameraUpdateFactory.newLatLng(casa));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(casa, zoomlevel));


        }

        }


    }

}

