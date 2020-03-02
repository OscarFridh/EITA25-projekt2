public class Doctor extends User {

    private String division;

    public Doctor(int id, String division) {
        super(id);
        this.division = division;
    }

    public String getDivision() {
        return division;
    }

    @Override
    public boolean canCreateMedicalReccord() {
        return true;
    }

    @Override
    public boolean canRead(MedicalReccord medicalReccord) {
        return medicalReccord.getDoctor().getDivision().equals(getDivision());
    }

    @Override
    public boolean canUpdate(MedicalReccord medicalReccord) {
        return (medicalReccord.getDoctor().getId() == getId());
    }
}
