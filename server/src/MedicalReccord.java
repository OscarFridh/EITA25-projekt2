public class MedicalReccord {

    private int id;
    private Doctor doctor;
    private Nurse nurse;
    private Patient patient;
    private String medicalData;

    public MedicalReccord(int id, Doctor doctor, Nurse nurse, Patient patient, String medicalData) {
        this.id = id;
        this.doctor = doctor;
        this.nurse = nurse;
        this.patient = patient;
        this.medicalData = medicalData;
    }

    public int getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Nurse getNurse() {
        return nurse;
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
