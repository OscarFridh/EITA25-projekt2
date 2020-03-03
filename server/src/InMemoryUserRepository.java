import java.util.HashMap;

public class InMemoryUserRepository implements UserRepository {

    HashMap<Integer, User> map;

    public InMemoryUserRepository() {
        map = new HashMap();
    }

    @Override
    public User get(int id) {
        return map.get(id);
    }

    public void add(User user) {
        map.put(user.getId(), user);
    }

}
