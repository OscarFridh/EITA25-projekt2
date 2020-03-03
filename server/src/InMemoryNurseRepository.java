import java.util.HashMap;

public class InMemoryNurseRepository implements NurseRepository {

    HashMap<Integer, Nurse> map;

    public InMemoryNurseRepository() {
        map = new HashMap();
    }

    @Override
    public Nurse get(int id) {
        return map.get(id);
    }

    public void add(Nurse nurse) {
        map.put(nurse.getId(), nurse);
    }

}
