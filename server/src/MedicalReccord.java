public class MedicalReccord {

    private int id;
    private Patient patient;
    private String medicalData;

    public MedicalReccord(int id, Patient patient, String medicalData) {
        this.id = id;
        this.patient = patient;
        this.medicalData = medicalData;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getMedicalData() {
        return medicalData;
    }

    public void setMedicalData(String medicalData) {
        this.medicalData = medicalData;
    }
}
