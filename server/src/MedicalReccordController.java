public class MedicalReccordController implements MedicalReccordControlling {

    private MedicalReccordRepository repository;

    public MedicalReccordController(MedicalReccordRepository repository) {
        this.repository = repository;
    }

    @Override
    public String read(int id) {
        return repository.fetchMedicalReccord(id).getText();
    }
}
