package mapas.locaciones;

/**
 * Created by Javi047 on 29/11/2017.
 */

public class Posicion {
    private static double Latitud;
    private static double Longitud;



    public Posicion (double Latitud, double Longitud){

        this.Latitud=Latitud;
        this.Longitud=Longitud;

    }

    public static double getLatitud() {
        return Latitud;
    }

    public static void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public static double getLongitud() {
        return Longitud;
    }


}
