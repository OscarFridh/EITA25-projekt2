import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMedicalReccordRepositoryTest {

    @Test
    void fetchAddedMedicalReccord() {

        // Given
        InMemoryMedicalReccordRepository sut = new InMemoryMedicalReccordRepository();

        // When
        sut.add(new MedicalReccord(1, "Medical reccord 1"));
        sut.add(new MedicalReccord(2, "Medical reccord 2"));
        MedicalReccord fetch1 = sut.get(1);
        MedicalReccord fetch2 = sut.get(2);
        MedicalReccord fetch3 = sut.get(3);

        // Then
        assertEquals(1, fetch1.getId());
        assertEquals("Medical reccord 1", fetch1.getText());

        assertEquals(2, fetch2.getId());
        assertEquals("Medical reccord 2", fetch2.getText());

        assertNull(fetch3);
    }

    @Test
    void testDelete() {

        // Given
        InMemoryMedicalReccordRepository sut = new InMemoryMedicalReccordRepository();

        // When
        sut.add(new MedicalReccord(1, "Medical reccord 1"));
        assertTrue(sut.delete(1));
        assertFalse(sut.delete(2));

        // Then
        assertNull(sut.get(1));
    }
}