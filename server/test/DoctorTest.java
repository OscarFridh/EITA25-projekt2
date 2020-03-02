import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    @Test
    void canCreateMedicalReccord() {
        Doctor sut = new Doctor(1, "Division 1");
        assertTrue(sut.canCreateMedicalReccord());
    }

    @Test
    void canReadAtSameDivision() {
        //Doctor sut = new Doctor(1, "Division 1");
        //MedicalReccord medicalReccord = new MedicalReccord(1, new Patient(1), "Medical data");
    }

    @Test
    void canUpdate() {
    }

    @Test
    void cannotDelete() {
        Doctor sut = new Doctor(1, "Division 1");
        MedicalReccord medicalReccord = new MedicalReccord(1, sut, new Patient(1), "Medical data");
        assertFalse(sut.canDelete(medicalReccord));
    }
}