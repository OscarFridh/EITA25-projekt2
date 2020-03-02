import java.util.StringJoiner;

public class Router {

    private MedicalReccordControlling medicalReccordController;

    public Router(MedicalReccordControlling medicalReccordController) {
        this.medicalReccordController = medicalReccordController;
    }

    public String handleRequest(String clientMsg) {
        try {
            String[] commands = clientMsg.split(" ");

            if (clientMsg.startsWith("read")) {

                int id = Integer.parseInt(commands[1]);
                return medicalReccordController.read(id);

            } else if (clientMsg.startsWith("create")) {

                int patientId = Integer.parseInt(commands[1]);
                StringJoiner joiner = new StringJoiner(" ");
                for (int i = 2; i < commands.length; i++) {
                    joiner.add(commands[i]);
                }
                String text = joiner.toString();
                return medicalReccordController.create(patientId, text);

            } else {
                return "Unknown command";
            }
        } catch (NumberFormatException e) {
            return "Invalid command";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid command";
        }
    }
}
