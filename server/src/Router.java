import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {

    private MedicalReccordControlling medicalReccordController;

    public Router(MedicalReccordControlling medicalReccordController) {
        this.medicalReccordController = medicalReccordController;
    }

    public String handleRequest(String clientMsg) {
        if (clientMsg.startsWith("read")) {
            String[] commands = clientMsg.split(" ");
            try {
                int id = Integer.parseInt(commands[1]);
                return medicalReccordController.read(id);
            } catch (NumberFormatException e) {
                return "Invalid command";
            } catch (IndexOutOfBoundsException e) {
                return "Invalid command";
            }
        } else if (clientMsg.startsWith("create")) {
            try {
                String[] commands = clientMsg.split(" ");
                int patientId = Integer.parseInt(commands[1]);
                StringJoiner joiner = new StringJoiner(" ");
                for (int i = 2; i < commands.length; i++) {
                    joiner.add(commands[i]);
                }
                String text = joiner.toString();
                return medicalReccordController.create(patientId, text);
            } catch (NumberFormatException e) {
                return "Invalid command";
            } catch (IndexOutOfBoundsException e) {
                return "Invalid command";
            }
        }
        if(clientMsg.equals("Medical")) {
            return "Medical Records";
        }
        else {
            return new StringBuilder(clientMsg).reverse().toString();
        }
    }
}
