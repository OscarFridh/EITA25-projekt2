public interface MedicalReccordRepository {
    MedicalReccord fetchMedicalReccord(int id);
    int create(int patientId, String text);
}
