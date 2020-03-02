public class Nurse extends User {

    private String division;

    public Nurse(int id, String division) {
        super(id);
        this.division = division;
    }

    public String getDivision() {
        return division;
    }
}
