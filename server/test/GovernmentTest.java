import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GovernmentTest {


    @Test
    void cannotCreateMedicalReccord() {
        Government sut = new Government(1);
        assertFalse(sut.canCreateMedicalReccord());
    }

    @Test
    void canReadReccord() {
        Government sut = new Government(1);
        MedicalReccord medicalReccord = new MedicalReccord(1, new Patient(1),"Text");
        assertTrue(sut.canRead(medicalReccord));
    }

    @Test
    void cannotUpdateReccord() {
        Government sut = new Government(1);
        MedicalReccord medicalReccord = new MedicalReccord(1, new Patient(1),"Text");
        assertFalse(sut.canUpdate(medicalReccord));
    }

    @Test
    void canDeleteReccord() {
        Government sut = new Government(1);
        MedicalReccord medicalReccord = new MedicalReccord(1, new Patient(1),"Text");
        assertTrue(sut.canDelete(medicalReccord));
    }
}