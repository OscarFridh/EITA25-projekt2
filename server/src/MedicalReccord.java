public class MedicalReccord {

    private int id;
    private Doctor doctor;
    private Patient patient;
    private String medicalData;

    public MedicalReccord(int id, Doctor doctor, Patient patient, String medicalData) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medicalData = medicalData;
    }

    public int getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
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
