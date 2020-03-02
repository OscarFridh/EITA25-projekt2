import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            }
        } catch (NumberFormatException e) {
            return "Invalid command";
        } catch (IndexOutOfBoundsException e) {
            return "Invalid command";
        }

        if(clientMsg.equals("Medical")) {
            return "Medical Records";
        }
        else {
            return new StringBuilder(clientMsg).reverse().toString();
        }
    }
}
