import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NurseTest {

    @Test
    void cannotCreateMedicalReccord() {
        Nurse sut = new Nurse(1, "Divisiont 1");
        assertFalse(sut.canCreateMedicalReccord());
    }

    @Test
    void canReadMyPatientsReccord() {
        Nurse sut = new Nurse(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(1, "Division 1"), sut, new Patient(1), "Medical data");
        assertTrue(sut.canRead(medicalReccord));
    }

    @Test
    void canReadReccordFromSameDivision() {
        Nurse sut = new Nurse(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(2, "Division 1"), new Nurse(2, "Division 1"), new Patient(1), "Medical data");
        assertTrue(sut.canRead(medicalReccord));
    }

    @Test
    void cannotReadReccordFromAnotherDivision() {
        Nurse sut = new Nurse(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(2, "Division 2"), new Nurse(2, "Division 1"), new Patient(1), "Medical data");
        assertFalse(sut.canRead(medicalReccord));
    }

    @Test
    void canUpdateMyPatientsReccord() {
        Nurse sut = new Nurse(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(2, "Division 1"), sut, new Patient(1), "Medical data");
        assertTrue(sut.canUpdate(medicalReccord));
    }

    @Test
    void cannotUpdateReccordFromSameDivision() {
        Nurse sut = new Nurse(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(2, "Division 1"), new Nurse(2, "Division 1"), new Patient(1), "Medical data");
        assertFalse(sut.canUpdate(medicalReccord));
    }

    @Test
    void cannotUpdateReccordFromAnotherDivision() {
        Nurse sut = new Nurse(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(2, "Division 2"), new Nurse(2, "Division 2"), new Patient(1), "Medical data");
        assertFalse(sut.canUpdate(medicalReccord));
    }

    @Test
    void cannotDelete() {
        Nurse sut = new Nurse(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1,  new Doctor(2, "Division 2"), sut, new Patient(1), "Medical data");
        assertFalse(sut.canDelete(medicalReccord));
    }
}