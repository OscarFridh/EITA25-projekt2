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
}
