import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalReccordControllerTest {

    @Test
    void readMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(new MedicalReccord(1, "Medical reccord 1"));
        MedicalReccordController sut = new MedicalReccordController(repositoryMock);

        String response = sut.read(1);

        assertEquals("Medical reccord 1", response);
        assertEquals(1, repositoryMock.lastFetch);
    }

    @Test
    void readAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(new MedicalReccord(2, "Medical reccord 2"));
        MedicalReccordController sut = new MedicalReccordController(repositoryMock);

        String response = sut.read(2);

        assertEquals("Medical reccord 2", response);
        assertEquals(2, repositoryMock.lastFetch);
    }

    @Test
    void readNonExistingMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(null);
        MedicalReccordController sut = new MedicalReccordController(repositoryMock);

        String response = sut.read(3);

        assertEquals("No such reccord", response);
        assertEquals(3, repositoryMock.lastFetch);
    }


    class MedicalReccordRepositoryMock implements MedicalReccordRepository {

        private MedicalReccord fetchResult;
        private Integer lastFetch;

        @Override
        public MedicalReccord fetchMedicalReccord(int id) {
            lastFetch = id;
            return fetchResult;
        }

        public Integer getLastFetch() {
            return lastFetch;
        }

        public void setFetchResult(MedicalReccord fetchResult) {
            this.fetchResult = fetchResult;
        }
    }
}