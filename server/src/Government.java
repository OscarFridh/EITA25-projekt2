public class Government extends User {

    public Government(int id) {
        super(id);
    }

    @Override
    public boolean canRead(MedicalReccord medicalReccord) {
        return true;
    }

    @Override
    public boolean canDelete(MedicalReccord medicalReccord) {
        return true;
    }
}
