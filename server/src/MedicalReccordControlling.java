public interface MedicalReccordControlling {
    String create(int patientId, int nurseId, String text);
    String read(int id);
    String update(int id, String newText);
    String delete(int id);
}
