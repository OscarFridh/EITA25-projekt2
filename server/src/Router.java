import java.util.StringJoiner;

public class Router {

    private MedicalReccordControlling medicalReccordController;

    public Router(MedicalReccordControlling medicalReccordController) {
        this.medicalReccordController = medicalReccordController;
    }

    public String handleRequest(String clientMsg) {
        try {
            String[] commands = clientMsg.split(" ");

            switch (commands[0]) {
                case "read":
                    return read(commands);
                case "create":
                    return create(commands);
                case "delete":
                    return delete(commands);
                default:
                    return "Unknown command";
            }
        } catch (NumberFormatException e) {
            return "Invalid command";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid command";
        }
    }

    private String read(String[] commands) {
        int id = Integer.parseInt(commands[1]);
        return medicalReccordController.read(id);
    }

    private String create(String[] commands) {
        int patientId = Integer.parseInt(commands[1]);
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 2; i < commands.length; i++) {
            joiner.add(commands[i]);
        }
        String text = joiner.toString();
        return medicalReccordController.create(patientId, text);
    }

    private String delete(String[] commands) {
        int id = Integer.parseInt(commands[1]);
        return medicalReccordController.delete(id);
    }

}
