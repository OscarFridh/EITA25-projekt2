import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void cannotCreateMedicalReccord() {
        Patient sut = new Patient(1);
        assertFalse(sut.canCreateMedicalReccord());
    }

    @Test
    void canReadMyReccord() {
        Patient sut = new Patient(1);
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(1),"Text");
        assertTrue(sut.canRead(medicalReccord));
    }

    @Test
    void cannotReadAnotherPatientsReccord() {
        Patient sut = new Patient(1);
        MedicalReccord medicalReccord = new MedicalReccord(2, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(2),"Text");
        assertFalse(sut.canRead(medicalReccord));
    }

    @Test
    void cannotUpdateMyReccord() {
        Patient sut = new Patient(1);
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(1),"Text");
        assertFalse(sut.canUpdate(medicalReccord));
    }

    @Test
    void cannotUpdateAnotherPatientsReccord() {
        Patient sut = new Patient(1);
        MedicalReccord medicalReccord = new MedicalReccord(2, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(2),"Text");
        assertFalse(sut.canUpdate(medicalReccord));
    }

    @Test
    void cannotDeleteMyReccord() {
        Patient sut = new Patient(1);
        MedicalReccord medicalReccord = new MedicalReccord(1, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(1),"Text");
        assertFalse(sut.canDelete(medicalReccord));
    }

    @Test
    void cannotDeleteAnotherPatientsReccord() {
        Patient sut = new Patient(1);
        MedicalReccord medicalReccord = new MedicalReccord(2, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(2),"Text");
        assertFalse(sut.canDelete(medicalReccord));
    }

}