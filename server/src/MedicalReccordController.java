public class MedicalReccordController implements MedicalReccordControlling {

    private MedicalReccordRepository repository;

    public MedicalReccordController(MedicalReccordRepository repository) {
        this.repository = repository;
    }

    @Override
    public String create(int patientId, String text) {
        int id = repository.create(patientId, text);
        return "Created reccord with id: " + id;
    }

    @Override
    public String read(int id) {
        MedicalReccord result = repository.get(id);
        if (result == null) {
            return "No such reccord";
        } else {
            return result.getText();
        }
    }

    @Override
    public String update(int id, String newText) {
        MedicalReccord reccord = repository.get(id);
        if (reccord == null) {
            return "No such reccord";
        } else {
            reccord.setText(newText);
            return "Updated reccord with id: " +reccord.getId();
        }
    }

    @Override
    public String delete(int id) {
        if (repository.delete(id)) {
            return "Deleted reccord with id: " + id;
        } else {
            return "Could not delete reccord with id: " + id;
        }
    }
}
