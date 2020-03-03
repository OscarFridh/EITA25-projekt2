import java.util.HashMap;

public class InMemoryPatientRepository implements PatientRepository {

    HashMap<Integer, Patient> map;

    public InMemoryPatientRepository() {
        map = new HashMap();
    }

    @Override
    public Patient get(int id) {
        return map.get(id);
    }

    public void add(Patient nurse) {
        map.put(nurse.getId(), nurse);
    }
}
