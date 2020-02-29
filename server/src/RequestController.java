public class RequestController {

    private MedicalReccordControlling medicalReccordController;

    public RequestController(MedicalReccordControlling medicalReccordController) {
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
        }
        if(clientMsg.equals("Medical")) {
            return "Medical Records";
        }
        else {
            return new StringBuilder(clientMsg).reverse().toString();
        }
    }
}
