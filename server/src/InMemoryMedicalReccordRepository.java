import java.util.HashMap;

public class InMemoryMedicalReccordRepository implements MedicalReccordRepository {

    private HashMap<Integer, MedicalReccord> medicalReccords;

    public InMemoryMedicalReccordRepository() {
        this.medicalReccords = new HashMap();
    }

    @Override
    public MedicalReccord fetchMedicalReccord(int id) {
        return medicalReccords.get(id);
    }

    public void add(MedicalReccord reccord) {
        medicalReccords.put(reccord.getId(), reccord);
    }


}
