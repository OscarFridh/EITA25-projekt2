import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalReccordControllerTest {

    @Test
    void readMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock("Medical reccord 1");
        MedicalReccordController sut = new MedicalReccordController(repositoryMock);

        String response = sut.read(1);

        assertEquals("Medical reccord 1", response);
        assertEquals(1, repositoryMock.lastFetch);
    }

    @Test
    void readAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock("Medical reccord 2");
        MedicalReccordController sut = new MedicalReccordController(repositoryMock);

        String response = sut.read(2);

        assertEquals("Medical reccord 2", response);
        assertEquals(2, repositoryMock.lastFetch);
    }




    class MedicalReccordRepositoryMock implements MedicalReccordRepository {

        private String fetchResult;
        private Integer lastFetch;

        public MedicalReccordRepositoryMock(String fetchResult) {
            this.fetchResult = fetchResult;
        }

        @Override
        public String fetchMedicalReccord(int id) {
            lastFetch = id;
            return fetchResult;
        }
    }
}