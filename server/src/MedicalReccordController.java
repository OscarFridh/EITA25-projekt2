public class MedicalReccordController implements MedicalReccordControlling {

    private User user;
    private MedicalReccordRepository medicalReccordRepository;
    private UserRepository userRepository;
    private Logging logger;

    public MedicalReccordController(User user, MedicalReccordRepository medicalReccordRepository, UserRepository userRepository, Logging logger) {
        this.user = user;
        this.medicalReccordRepository = medicalReccordRepository;
        this.userRepository = userRepository;
        this.logger = logger;
    }

    @Override
    public String create(int patientId, int nurseId, String text) {
        if (user.canCreateMedicalReccord()) {
            Doctor doctor = (Doctor)user;
            User nurse = userRepository.get(nurseId);
            if (nurse == null || !(nurse instanceof  Nurse)) {
                logger.log("User (id: " + user.getId() + ") tried to create a new reccord for non existing nurse (id: " + nurseId + ")");
                return "No such nurse";
            }
            User patient = userRepository.get(patientId);
            if (patient == null || !(patient instanceof  Patient)) {
                logger.log("User (id: " + user.getId() + ") tried to create a new reccord for non existing patient (id: " + patientId + ")");
                return "No such patient";
            }
            int id = medicalReccordRepository.create(doctor, (Nurse) nurse, (Patient) patient, text);
            logger.log("User (id: " + user.getId() + ") created a new reccord (id: " + id + ")");
            return "Created reccord with id: " + id;
        } else {
            logger.log("Access denied - User (id: " + user.getId() + ") tried to create a new reccord");
            return "Access denied";
        }
    }

    @Override
    public String read(int id) {
        MedicalReccord result = medicalReccordRepository.get(id);
        if (result == null) {
            logger.log("User (id: " + user.getId() + ") tried to read non existing reccord (id: " + id + ")");
            return "No such reccord";
        } else if (user.canRead(result)) {
            logger.log("User (id: " + user.getId() + ") read reccord (id: " + result.getId() + ")");
            return result.getMedicalData();
        } else {
            logger.log("Access denied - User (id: " + user.getId() + ") tried to read reccord (id: " + result.getId() + ")");
            return "Access denied";
        }
    }

    @Override
    public String update(int id, String newText) {
        MedicalReccord reccord = medicalReccordRepository.get(id);
        if (reccord == null) {
            logger.log("User (id: " + user.getId() + ") tried to update a non existing reccord (id: " + id + ")");
            return "No such reccord";
        } else if(user.canUpdate(reccord)) {
            reccord.setMedicalData(newText);
            logger.log("User (id: " + user.getId() + ") updated a reccord (id: " + id + ")");
            return "Updated reccord with id: " +reccord.getId();
        } else {
            logger.log("Access denied - User (id: " + user.getId() + ") tried to update a reccord (id: " + id + ")");
            return "Access denied";
        }
    }

    @Override
    public String delete(int id) {
        MedicalReccord reccord = medicalReccordRepository.get(id);
        if (!user.canDelete(reccord)) {
            logger.log("Access denied - User (id: " + user.getId() + ") tried to delete a reccord (id: " + id + ")");
            return "Access denied";
        } else if (medicalReccordRepository.delete(id)) {
            logger.log("User (id: " + user.getId() + ") deleted a reccord (id: " + id + ")");
            return "Deleted reccord with id: " + id;
        } else {
            logger.log("User (id: " + user.getId() + ") tried to delete a non existing reccord (id: " + id + ")");
            return "No such reccord";
        }
    }
}
