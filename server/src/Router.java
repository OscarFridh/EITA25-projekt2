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
        String text = parseMedicalReccordText(commands);
        return medicalReccordController.create(patientId, 1, text);
    }

    private String parseMedicalReccordText(String[] commands) {
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 2; i < commands.length; i++) {
            joiner.add(commands[i]);
        }
        return joiner.toString();
    }

    private String update(String[] commands) {
        int id = Integer.parseInt(commands[1]);
        String newText = parseMedicalReccordText(commands);
        return medicalReccordController.update(id, newText);
    }

    private String delete(String[] commands) {
        int id = Integer.parseInt(commands[1]);
        return medicalReccordController.delete(id);
    }

}
