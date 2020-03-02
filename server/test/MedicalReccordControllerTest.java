import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class MedicalReccordControllerTest {

    @Test
    void readMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(new MedicalReccord(1, "Medical reccord 1"));
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanRead(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.read(1);

        assertEquals("Medical reccord 1", response);
        assertEquals(1, repositoryMock.lastFetch);
    }

    @Test
    void readAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(new MedicalReccord(2, "Medical reccord 2"));
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanRead(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.read(2);

        assertEquals("Medical reccord 2", response);
        assertEquals(2, repositoryMock.lastFetch);
    }

    @Test
    void readNonExistingMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(null);
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanRead(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.read(3);

        assertEquals("No such reccord", response);
        assertEquals(3, repositoryMock.lastFetch);
    }

    @Test
    void createMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setCreateResult(1);
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanCreate(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.create(2, "Text");

        assertEquals("Created reccord with id: 1", response);
        assertEquals(2, repositoryMock.getLastCreatePatientId());
        assertEquals("Text", repositoryMock.getLastCreateText());
    }

    @Test
    void createAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setCreateResult(2);
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanCreate(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.create(3, "Text 2");

        assertEquals("Created reccord with id: 2", response);
        assertEquals(3, repositoryMock.getLastCreatePatientId());
        assertEquals("Text 2", repositoryMock.getLastCreateText());
    }

    @Test
    void deleteMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setDeleteResult(true);
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanDelete(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.delete(3);

        assertEquals("Deleted reccord with id: 3", response);
        assertEquals(3, repositoryMock.getLastDelete());
        assertTrue(repositoryMock.getLastDeleteResult());
    }

    @Test
    void deleteAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setDeleteResult(true);
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanDelete(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.delete(4);

        assertEquals("Deleted reccord with id: 4", response);
        assertEquals(4, repositoryMock.getLastDelete());
        assertTrue(repositoryMock.getLastDeleteResult());
    }

    @Test
    void deleteNotExistingMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setDeleteResult(false);
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanDelete(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.delete(5);

        assertEquals("Could not delete reccord with id: 5", response);
        assertEquals(5, repositoryMock.getLastDelete());
        assertFalse(repositoryMock.getLastDeleteResult());
    }

    @Test
    void updateMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        MedicalReccord reccord = new MedicalReccord(1, "Old text");
        repositoryMock.setFetchResult(reccord);
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanUpdate(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.update(1, "New text");

        assertEquals("Updated reccord with id: 1", response);
        assertEquals("New text", reccord.getText());
    }

    @Test
    void updateAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        MedicalReccord reccord = new MedicalReccord(2, "Old text");
        repositoryMock.setFetchResult(reccord);
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanUpdate(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.update(2, "New text 2");

        assertEquals("Updated reccord with id: 2", response);
        assertEquals("New text 2", reccord.getText());
    }

    @Test
    void updateNonExistingMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        UserMock userMock = new UserMock(1, repositoryMock);
        userMock.setCanUpdate(true);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock);

        String response = sut.update(2, "New text 2");

        assertEquals("No such reccord", response);
    }


    class MedicalReccordRepositoryMock implements MedicalReccordRepository {

        private MedicalReccord fetchResult;
        private Integer lastFetch;

        private Integer createResult;
        private Integer lastCreatePatientId;
        private String lastCreateText;

        private Integer lastDelete;
        private boolean deleteResult;

        public Integer getLastFetch() {
            return lastFetch;
        }
        public Integer getLastCreatePatientId() {
            return lastCreatePatientId;
        }
        public String getLastCreateText() {
            return lastCreateText;
        }
        public Integer getLastDelete() {
            return lastDelete;
        }
        public boolean getLastDeleteResult() {
            return deleteResult;
        }

        public void setFetchResult(MedicalReccord fetchResult) {
            this.fetchResult = fetchResult;
        }
        public void setCreateResult(Integer createResult) {
            this.createResult = createResult;
        }
        public void setDeleteResult(boolean deleteResult) {
            this.deleteResult = deleteResult;
        }

        @Override
        public MedicalReccord get(int id) {
            lastFetch = id;
            return fetchResult;
        }

        @Override
        public int create(int patientId, String text) {
            lastCreatePatientId = patientId;
            lastCreateText = text;
            return createResult;
        }

        @Override
        public boolean delete(int id) {
            lastDelete = id;
            return deleteResult;
        }
    }

    class UserMock extends User {

        private boolean canCreate = false;
        private boolean canRead = false;
        private boolean canUpdate = false;
        private boolean canDelete = false;

        public UserMock(int id, MedicalReccordRepository medicalReccordRepository) {
            super(id, medicalReccordRepository);
        }

        public void setCanCreate(boolean canCreate) {
            this.canCreate = canCreate;
        }

        public void setCanRead(boolean canRead) {
            this.canRead = canRead;
        }

        public void setCanUpdate(boolean canUpdate) {
            this.canUpdate = canUpdate;
        }

        public void setCanDelete(boolean canDelete) {
            this.canDelete = canDelete;
        }

        @Override
        public boolean canCreateMedicalReccord() {
            return canCreate;
        }

        @Override
        public boolean canRead(MedicalReccord medicalReccord) {
            return canRead;
        }

        @Override
        public boolean canUpdate(MedicalReccord medicalReccord) {
            return canUpdate;
        }

        @Override
        public boolean canDelete(MedicalReccord medicalReccord) {
            return canDelete;
        }
    }
}