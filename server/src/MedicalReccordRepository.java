public interface MedicalReccordRepository {
    MedicalReccord get(int id);
    int create(int patientId, String text);
    boolean delete(int id);
}
