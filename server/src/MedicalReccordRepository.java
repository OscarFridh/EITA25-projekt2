public interface MedicalReccordRepository {
    MedicalReccord get(int id);
    int create(Patient patient, String text);
    boolean delete(int id);
}
