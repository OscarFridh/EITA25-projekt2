import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMedicalReccordRepositoryTest {

    @Test
    void fetchAddedMedicalReccord() {

        // Given
        InMemoryMedicalReccordRepository sut = new InMemoryMedicalReccordRepository();

        // When
        int firstId = sut.create(new Patient(1), "Medical reccord 1");
        int secondId = sut.create(new Patient(1), "Medical reccord 2");

        MedicalReccord fetch1 = sut.get(firstId);
        MedicalReccord fetch2 = sut.get(secondId);
        MedicalReccord fetch3 = sut.get(secondId + 1);

        // Then
        assertEquals(firstId, fetch1.getId());
        assertEquals("Medical reccord 1", fetch1.getMedicalData());

        assertEquals(secondId, fetch2.getId());
        assertEquals("Medical reccord 2", fetch2.getMedicalData());

        assertNull(fetch3);
    }

    @Test
    void testDelete() {

        // Given
        InMemoryMedicalReccordRepository sut = new InMemoryMedicalReccordRepository();

        // When
        int id = sut.create(new Patient(1), "Medical reccord 1");
        assertFalse(sut.delete(id+1));
        assertTrue(sut.delete(id));

        // Then
        assertNull(sut.get(1));
    }
}