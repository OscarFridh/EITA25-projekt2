public abstract class User {

    private int id;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
