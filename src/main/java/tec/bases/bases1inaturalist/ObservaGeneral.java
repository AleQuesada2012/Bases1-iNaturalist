package tec.bases.bases1inaturalist;

public class ObservaGeneral {

    private String reino;
    private String especie;
    private String fecha;
    private String latitud;
    private String longitud;
    private String nombreUsuario;
    private String URL;

    public ObservaGeneral(String reino, String especie, String fecha, String latitud, String longitud, String nombreUsuario, String URL) {
        this.reino = reino;
        this.especie = especie;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombreUsuario = nombreUsuario;
        this.URL = URL;
    }

    public String getEspecie() {
        return especie;
    }

    public String getFecha() {
        return fecha;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getReino() {
        return reino;
    }

    public String getURL() {
        return URL;
    }
}
