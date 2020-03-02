public interface MedicalReccordControlling {
    String read(int id);
    String create(int patientId, String text);
    String delete(int id);
}
