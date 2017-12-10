package mapas.locaciones;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static mapas.locaciones.DetailActivity.EXTRA_POSITION;

public class Prueba extends AppCompatActivity {

    TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

       // double doble = Double.parseDouble(cadena);

        prueba = (TextView) findViewById(R.id.prueba);

        int postion = getIntent().getIntExtra("pruebax", 0);
        Resources resources = getResources();
         String[] cordenadas = resources.getStringArray(R.array.posicionamientos_latitud1);
        prueba = (TextView) findViewById(R.id.prueba);





    }
}
