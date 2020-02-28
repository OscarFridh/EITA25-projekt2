public class RequestController {

    public String handleRequest(String clientMsg) {
        if(clientMsg.equals("Medical")) {
            return "Medical Records";
        }
        else {
            return new StringBuilder(clientMsg).reverse().toString();
        }
    }
}
