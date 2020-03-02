public class Nurse extends User {

    private String division;

    public Nurse(int id, String division) {
        super(id);
        this.division = division;
    }

    public String getDivision() {
        return division;
    }

    @Override
    public boolean canRead(MedicalReccord medicalReccord) {
        return medicalReccord.getDoctor().getDivision().equals(getDivision());
    }

    @Override
    public boolean canUpdate(MedicalReccord medicalReccord) {
        return (medicalReccord.getNurse().getId() == getId());
    }
}
