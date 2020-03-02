public class Patient extends User {

    public Patient(int id) {
        super(id);
    }

    @Override
    public boolean canRead(MedicalReccord medicalReccord) {
        return medicalReccord.getPatient().getId() == getId();
    }
}
