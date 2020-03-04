public class AuditLogger implements Logging {
    @Override
    public void log(String event) {
        System.out.println("[AUDIT LOG] " + event);
    }
}
