public class MedicalReccordController implements MedicalReccordControlling {

    private MedicalReccordRepository repository;

    public MedicalReccordController(MedicalReccordRepository repository) {
        this.repository = repository;
    }

    @Override
    public String read(int id) {
        MedicalReccord result = repository.fetchMedicalReccord(id);
        if (result == null) {
            return "No such reccord";
        } else {
            return result.getText();
        }
    }

    @Override
    public String create(int patientId, String text) {
        return "";
    }
}
