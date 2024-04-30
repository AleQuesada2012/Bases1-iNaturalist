package tec.bases.bases1inaturalist;

public class IdentificacionTabulada {

    private int id;
    private String nombre;
    private int obsID;
    private String fechaPub;
    private String nombreObserver;
    private String rating;

    public IdentificacionTabulada(int id, String nombre, int obsID, String fechaPub, String nombreObserver, String rating) {
        this.id = id;
        this.nombre = nombre;
        this.obsID = obsID;
        this.fechaPub = fechaPub;
        this.nombreObserver = nombreObserver;
        this.rating = rating;
    }

    public String getFechaPub() {
        return fechaPub;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreObserver() {
        return nombreObserver;
    }

    public int getObsID() {
        return obsID;
    }

    public String getRating() {
        return rating;
    }
}
