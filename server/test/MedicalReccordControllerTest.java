import org.junit.jupiter.api.Test;

import javax.print.Doc;

import static org.junit.jupiter.api.Assertions.*;

class MedicalReccordControllerTest {

    @Test
    void readMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(new MedicalReccord(1, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(1), "Medical reccord 1"));
        MedicalReccordController sut = new MedicalReccordController(new UserMock(), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.read(1);

        assertEquals("Medical reccord 1", response);
        assertEquals(1, repositoryMock.lastFetch);
    }

    @Test
    void readAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(new MedicalReccord(2, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(1), "Medical reccord 2"));
        MedicalReccordController sut = new MedicalReccordController(new UserMock(), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.read(2);

        assertEquals("Medical reccord 2", response);
        assertEquals(2, repositoryMock.lastFetch);
    }

    @Test
    void readNonExistingMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(null);
        MedicalReccordController sut = new MedicalReccordController(new UserMock(), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.read(3);

        assertEquals("No such reccord", response);
        assertEquals(3, repositoryMock.lastFetch);
    }

    @Test
    void readMedicalReccordWithoutPermission() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setFetchResult(new MedicalReccord(1, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(1), "Medical reccord 1"));
        UserMock userMock = new UserMock();
        userMock.setCanRead(false);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.read(1);

        assertEquals("Access denied", response);
        assertEquals(1, repositoryMock.lastFetch);
    }


    @Test
    void createMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setCreateResult(1);
        MedicalReccordController sut = new MedicalReccordController(new Doctor(1, "Division"), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.create(2, 3, "Text");

        assertEquals("Created reccord with id: 1", response);
        assertEquals(1, repositoryMock.getLastCreateDoctorId());
        assertEquals(2, repositoryMock.getLastCreatePatientId());
        assertEquals(3, repositoryMock.getLastCreatedNurseId());
        assertEquals("Text", repositoryMock.getLastCreateText());
    }

    @Test
    void createAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setCreateResult(2);
        MedicalReccordController sut = new MedicalReccordController(new Doctor(2, "Division"), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.create(3, 4, "Text 2");

        assertEquals("Created reccord with id: 2", response);
        assertEquals(2, repositoryMock.getLastCreateDoctorId());
        assertEquals(3, repositoryMock.getLastCreatePatientId());
        assertEquals(4, repositoryMock.getLastCreatedNurseId());
        assertEquals("Text 2", repositoryMock.getLastCreateText());
    }

    @Test
    void createMedicalReccordWithoutPermission() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setCreateResult(1);
        UserMock userMock = new UserMock();
        userMock.setCanCreate(false);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.create(2, 1, "Text");

        assertEquals("Access denied", response);
        assertNull(repositoryMock.getLastCreatePatientId());
        assertNull(repositoryMock.getLastCreateDoctorId());
        assertNull(repositoryMock.getLastCreatedNurseId());
        assertNull(repositoryMock.getLastCreateText());
    }

    @Test
    void deleteMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setDeleteResult(true);
        MedicalReccordController sut = new MedicalReccordController(new UserMock(), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.delete(3);

        assertEquals("Deleted reccord with id: 3", response);
        assertEquals(3, repositoryMock.getLastDelete());
        assertTrue(repositoryMock.getLastDeleteResult());
    }

    @Test
    void deleteAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setDeleteResult(true);
        MedicalReccordController sut = new MedicalReccordController(new UserMock(), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.delete(4);

        assertEquals("Deleted reccord with id: 4", response);
        assertEquals(4, repositoryMock.getLastDelete());
        assertTrue(repositoryMock.getLastDeleteResult());
    }

    @Test
    void deleteNotExistingMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setDeleteResult(false);
        MedicalReccordController sut = new MedicalReccordController(new UserMock(), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.delete(5);

        assertEquals("Could not delete reccord with id: 5", response);
        assertEquals(5, repositoryMock.getLastDelete());
        assertFalse(repositoryMock.getLastDeleteResult());
    }

    @Test
    void deleteMedicalReccordWithoutPermission() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        repositoryMock.setDeleteResult(true);
        UserMock userMock = new UserMock();
        userMock.setCanDelete(false);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.delete(3);

        assertEquals("Access denied", response);
        assertNull(repositoryMock.getLastDelete());
    }

    @Test
    void updateMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        MedicalReccord reccord = new MedicalReccord(1, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(1), "Old text");
        repositoryMock.setFetchResult(reccord);
        MedicalReccordController sut = new MedicalReccordController(new UserMock(), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.update(1, "New text");

        assertEquals("Updated reccord with id: 1", response);
        assertEquals("New text", reccord.getMedicalData());
    }

    @Test
    void updateAnotherMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        MedicalReccord reccord = new MedicalReccord(2, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(1), "Old text");
        repositoryMock.setFetchResult(reccord);
        MedicalReccordController sut = new MedicalReccordController(new UserMock(), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.update(2, "New text 2");

        assertEquals("Updated reccord with id: 2", response);
        assertEquals("New text 2", reccord.getMedicalData());
    }

    @Test
    void updateNonExistingMedicalReccord() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        MedicalReccordController sut = new MedicalReccordController(new UserMock(), repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.update(2, "New text 2");

        assertEquals("No such reccord", response);
    }

    @Test
    void updateMedicalReccordWithoutPermission() {
        MedicalReccordRepositoryMock repositoryMock = new MedicalReccordRepositoryMock();
        MedicalReccord reccord = new MedicalReccord(1, new Doctor(1, "Division"), new Nurse(1, "Division"), new Patient(1), "Old text");
        repositoryMock.setFetchResult(reccord);
        UserMock userMock = new UserMock();
        userMock.setCanUpdate(false);
        MedicalReccordController sut = new MedicalReccordController(userMock, repositoryMock, new NurseRepositoryMock(new Nurse(1, "Division")));

        String response = sut.update(1, "New text");

        assertEquals("Access denied", response);
        assertEquals("Old text", reccord.getMedicalData());
    }

    class MedicalReccordRepositoryMock implements MedicalReccordRepository {

        private MedicalReccord fetchResult;
        private Integer lastFetch;

        private Integer createResult;
        private Integer lastCreatePatientId;
        private Integer lastCreatedDoctorId;
        private Integer lastCreatedNurseId;
        private String lastCreateText;

        private Integer lastDelete;
        private boolean deleteResult;

        public Integer getLastFetch() {
            return lastFetch;
        }

        public Integer getLastCreatePatientId() {
            return lastCreatePatientId;
        }

        public Integer getLastCreatedNurseId() {
            return lastCreatedNurseId;
        }

        public Integer getLastCreateDoctorId() {
            return lastCreatedDoctorId;
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
        public int create(Doctor doctor, Nurse nurse, Patient patient, String text) {
            lastCreatePatientId = patient.getId();
            lastCreatedDoctorId = doctor.getId();
            lastCreatedNurseId = nurse.getId();
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

        private boolean canCreate = true;
        private boolean canRead = true;
        private boolean canUpdate = true;
        private boolean canDelete = true;

        public UserMock() {
            super(1);
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

    class NurseRepositoryMock implements NurseRepository {

        private Nurse nurse;

        public NurseRepositoryMock(Nurse nurse) {
            this.nurse = nurse;
        }

        @Override
        public Nurse get(int id) {
            return nurse;
        }
    }
}