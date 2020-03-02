import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

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

    @Test
    void createMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setCreateResult(1);
        MedicalReccordController sut = new MedicalReccordController(repositoryMock);

        String response = sut.create(2, "Text");

        assertEquals("Created reccord with id: 1", response);
        assertEquals(2, repositoryMock.getLastCreatePatientId());
        assertEquals("Text", repositoryMock.getLastCreateText());
    }

    @Test
    void createAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setCreateResult(2);
        MedicalReccordController sut = new MedicalReccordController(repositoryMock);

        String response = sut.create(3, "Text 2");

        assertEquals("Created reccord with id: 2", response);
        assertEquals(3, repositoryMock.getLastCreatePatientId());
        assertEquals("Text 2", repositoryMock.getLastCreateText());
    }


    class MedicalReccordRepositoryMock implements MedicalReccordRepository {

        private MedicalReccord fetchResult;
        private Integer lastFetch;

        private Integer createResult;
        private Integer lastCreatePatientId;
        private String lastCreateText;

        public Integer getLastFetch() {
            return lastFetch;
        }
        public Integer getLastCreatePatientId() {
            return lastCreatePatientId;
        }
        public String getLastCreateText() {
            return lastCreateText;
        }

        public void setFetchResult(MedicalReccord fetchResult) {
            this.fetchResult = fetchResult;
        }
        public void setCreateResult(Integer createResult) {
            this.createResult = createResult;
        }

        @Override
        public MedicalReccord fetchMedicalReccord(int id) {
            lastFetch = id;
            return fetchResult;
        }

        @Override
        public int create(int patientId, String text) {
            lastCreatePatientId = patientId;
            lastCreateText = text;
            return createResult;
        }
    }
}