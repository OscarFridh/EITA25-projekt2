public class MedicalReccordController implements MedicalReccordControlling {

    private User user;
    private MedicalReccordRepository medicalReccordRepository;
    private NurseRepository nurseRepository;

    public MedicalReccordController(User user, MedicalReccordRepository medicalReccordRepository, NurseRepository nurseRepository) {
        this.user = user;
        this.medicalReccordRepository = medicalReccordRepository;
        this.nurseRepository = nurseRepository;
    }

    @Override
    public String create(int patientId, int nurseId, String text) {
        if (user.canCreateMedicalReccord()) {
            Doctor doctor = (Doctor)user;
            Nurse nurse = nurseRepository.get(nurseId);
            if (nurse == null) {
                return "No such nurse";
            }
            Patient patient = new Patient(patientId); // TODO: Fix
            int id = medicalReccordRepository.create(doctor, nurse, patient, text);
            return "Created reccord with id: " + id;
        } else {
            return "Access denied";
        }
    }

    @Override
    public String read(int id) {
        MedicalReccord result = medicalReccordRepository.get(id);
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
        MedicalReccord reccord = medicalReccordRepository.get(id);
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
        MedicalReccord reccord = medicalReccordRepository.get(id);
        if (!user.canDelete(reccord)) {
            return "Access denied";
        } else if (medicalReccordRepository.delete(id)) {
            return "Deleted reccord with id: " + id;
        } else {
            return "Could not delete reccord with id: " + id;
        }
    }
}
