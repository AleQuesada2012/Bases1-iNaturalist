package tec.bases.bases1inaturalist;

public class FilaReporteExtra {
    private int id_usuario;
    private int id_person;
    private int id_identification;
    private String personName;
    private String countryName;

    public FilaReporteExtra(int id_usuario, int id_person, int id_identification, String personName, String countryName) {
        this.id_usuario = id_usuario;
        this.id_person = id_person;
        this.id_identification = id_identification;
        this.personName = personName;
        this.countryName = countryName;
    }

    public int getId_identification() {
        return id_identification;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getId_person() {
        return id_person;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getPersonName() {
        return personName;
    }
}
