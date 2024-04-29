package tec.bases.bases1inaturalist;

public class Observation {

    private int observationId;
    private String taxonomyPath;

    public Observation(int observationId, String taxonomyPath) {
        this.observationId = observationId;
        this.taxonomyPath = taxonomyPath;
    }

    public int getObservationId() {
        return observationId;
    }

    public String getTaxonomyPath() {
        return taxonomyPath;
    }
}