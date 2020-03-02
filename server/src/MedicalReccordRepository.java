public interface MedicalReccordRepository {
    MedicalReccord get(int id);
    int create(Doctor doctor, Nurse nurse, Patient patient, String text);
    boolean delete(int id);
}
