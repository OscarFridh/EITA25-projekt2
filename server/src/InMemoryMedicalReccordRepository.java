import java.util.HashMap;

public class InMemoryMedicalReccordRepository implements MedicalReccordRepository {

    private HashMap<Integer, MedicalReccord> medicalReccords;

    public InMemoryMedicalReccordRepository() {
        this.medicalReccords = new HashMap();
    }

    @Override
    public MedicalReccord get(int id) {
        return medicalReccords.get(id);
    }

    @Override
    public int create(Doctor doctor, Patient patient, String text) {
        MedicalReccord reccord = new MedicalReccord(Helper.generateUniqueId(), doctor, patient, text);
        medicalReccords.put(reccord.getId(), reccord);
        return reccord.getId();
    }

    @Override
    public boolean delete(int id) {
        return (medicalReccords.remove(id) != null);
    }
}
