public interface MedicalReccordControlling {
    String create(int patientId, String text);
    String read(int id);
    String update(int id, String newText);
    String delete(int id);
}
