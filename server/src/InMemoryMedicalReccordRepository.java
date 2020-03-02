import java.util.HashMap;

public class InMemoryMedicalReccordRepository implements MedicalReccordRepository {

    private HashMap<Integer, MedicalReccord> medicalReccords;
    private static int idCounter = 1;

    public InMemoryMedicalReccordRepository() {
        this.medicalReccords = new HashMap();
    }

    @Override
    public MedicalReccord get(int id) {
        return medicalReccords.get(id);
    }

    public void add(MedicalReccord reccord) {
        medicalReccords.put(reccord.getId(), reccord);
    }

    @Override
    public int create(int patientId, String text) {
        MedicalReccord reccord = new MedicalReccord(nextId(), text);
        add(reccord);
        return reccord.getId();
    }

    private static int nextId() {
        return idCounter++;
    }

    @Override
    public boolean delete(int id) {
        return (medicalReccords.remove(id) != null);
    }
}
