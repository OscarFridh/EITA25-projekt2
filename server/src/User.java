public abstract class User {

    private int id;
    private MedicalReccordRepository medicalReccordRepository;

    public User(int id, MedicalReccordRepository medicalReccordRepository) {
        this.id = id;
        this.medicalReccordRepository = medicalReccordRepository;
    }

    protected MedicalReccordRepository getMedicalReccordRepository() {
        return medicalReccordRepository;
    }

    public boolean canCreateMedicalReccord() {
        return false;
    }

    public boolean canRead(MedicalReccord medicalReccord) {
        return false;
    }

    public boolean canUpdate(MedicalReccord medicalReccord) {
        return false;
    }

    public boolean canDelete(MedicalReccord medicalReccord) {
        return false;
    }
}
