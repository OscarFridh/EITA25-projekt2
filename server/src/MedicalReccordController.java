public class MedicalReccordController implements MedicalReccordControlling {

    private User user;
    private MedicalReccordRepository repository;

    public MedicalReccordController(User user, MedicalReccordRepository repository) {
        this.user = user;
        this.repository = repository;
    }

    @Override
    public String create(int patientId, String text) {
        if (user.canCreateMedicalReccord()) {
            Doctor doctor = (Doctor)user;
            Patient patient = new Patient(patientId);
            int id = repository.create(doctor, patient, text);
            return "Created reccord with id: " + id;
        } else {
            return "Access denied";
        }
    }

    @Override
    public String read(int id) {
        MedicalReccord result = repository.get(id);
        if (result == null) {
            return "No such reccord";
        } else if (user.canRead(result)) {
            return result.getMedicalData();
        } else {
            return "Access denied";
        }
    }

    @Override
    public String update(int id, String newText) {
        MedicalReccord reccord = repository.get(id);
        if (reccord == null) {
            return "No such reccord";
        } else if(user.canUpdate(reccord)) {
            reccord.setMedicalData(newText);
            return "Updated reccord with id: " +reccord.getId();
        } else {
            return "Access denied";
        }
    }

    @Override
    public String delete(int id) {
        MedicalReccord reccord = repository.get(id);
        if (!user.canDelete(reccord)) {
            return "Access denied";
        } else if (repository.delete(id)) {
            return "Deleted reccord with id: " + id;
        } else {
            return "Could not delete reccord with id: " + id;
        }
    }
}
