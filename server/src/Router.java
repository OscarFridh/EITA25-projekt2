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
                case "create":
                    return create(commands);
                case "read":
                    return read(commands);
                case "update":
                    return update(commands);
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
        int nurseId = Integer.parseInt(commands[2]);
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 3; i < commands.length; i++) {
            joiner.add(commands[i]);
        }
        String text = joiner.toString();
        return medicalReccordController.create(patientId, nurseId, text);
    }

    private String update(String[] commands) {
        int id = Integer.parseInt(commands[1]);
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 2; i < commands.length; i++) {
            joiner.add(commands[i]);
        }
        String newText = joiner.toString();
        return medicalReccordController.update(id, newText);
    }

    private String delete(String[] commands) {
        int id = Integer.parseInt(commands[1]);
        return medicalReccordController.delete(id);
    }

}
