public class MedicalReccord {

    private int id;
    private Patient patient;
    private String text;

    public MedicalReccord(int id, Patient patient, String text) {
        this.id = id;
        this.patient = patient;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
