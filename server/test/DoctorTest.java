import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    @Test
    void canCreateMedicalReccord() {
        Doctor sut = new Doctor(1, "Division 1");
        assertTrue(sut.canCreateMedicalReccord());
    }

    @Test
    void canReadMyPatientsReccord() {
        Doctor sut = new Doctor(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, sut, new Nurse(1, "Division 1"), new Patient(1), "Medical data");
        assertTrue(sut.canRead(medicalReccord));
    }

    @Test
    void canReadReccordFromSameDivision() {
        Doctor sut = new Doctor(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(2, "Division 1"), new Nurse(1, "Division 1"), new Patient(1), "Medical data");
        assertTrue(sut.canRead(medicalReccord));
    }

    @Test
    void canReadReccordFromAnotherDivision() {
        Doctor sut = new Doctor(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(2, "Division 2"), new Nurse(1, "Division 1"), new Patient(1), "Medical data");
        assertFalse(sut.canRead(medicalReccord));
    }

    @Test
    void canUpdateMyPatientsReccord() {
        Doctor sut = new Doctor(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, sut, new Nurse(1, "Division 1"), new Patient(1), "Medical data");
        assertTrue(sut.canUpdate(medicalReccord));
    }

    @Test
    void cannotUpdateReccordFromSameDivision() {
        Doctor sut = new Doctor(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(2, "Division 1"), new Nurse(1, "Division 1"), new Patient(1), "Medical data");
        assertFalse(sut.canUpdate(medicalReccord));
    }

    @Test
    void cannotUpdateReccordFromAnotherDivision() {
        Doctor sut = new Doctor(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(2, "Division 2"), new Nurse(1, "Division 1"), new Patient(1), "Medical data");
        assertFalse(sut.canUpdate(medicalReccord));
    }

    @Test
    void cannotDelete() {
        Doctor sut = new Doctor(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, sut, new Nurse(1, "Division 1"), new Patient(1), "Medical data");
        assertFalse(sut.canDelete(medicalReccord));
    }
}