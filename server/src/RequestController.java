public class RequestController {

    private static String mRecord = "Medical Records";

    public String handleRequest(String clientMsg) {
        if(clientMsg.equals("Medical")) {
            return getmRecord();
        }
        else {
            return new StringBuilder(clientMsg).reverse().toString();
        }
    }

    public static String getmRecord() {
        return mRecord;
    }

}
