public class Helper {

    private static int idCounter = 1;

    static int generateUniqueId() {
        return idCounter++;
    }

}
