import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalReccordControllerTest {

    @Test
    void readMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock(new MedicalReccord("Medical reccord 1"));
        MedicalReccordController sut = new MedicalReccordController(repositoryMock);

        String response = sut.read(1);

        assertEquals("Medical reccord 1", response);
        assertEquals(1, repositoryMock.lastFetch);
    }

    @Test
    void readAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock(new MedicalReccord("Medical reccord 2"));
        MedicalReccordController sut = new MedicalReccordController(repositoryMock);

        String response = sut.read(2);

        assertEquals("Medical reccord 2", response);
        assertEquals(2, repositoryMock.lastFetch);
    }


    class MedicalReccordRepositoryMock implements MedicalReccordRepository {

        private MedicalReccord fetchResult;
        private Integer lastFetch;

        public MedicalReccordRepositoryMock(MedicalReccord fetchResult) {
            this.fetchResult = fetchResult;
        }

        @Override
        public MedicalReccord fetchMedicalReccord(int id) {
            lastFetch = id;
            return fetchResult;
        }
    }
}