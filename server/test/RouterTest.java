import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {

    @Test
    void readMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setReadResult("Read result");
        Router sut = new Router(medicalReccordControllerMock);

        String response = sut.handleRequest("read 1");

        assertEquals("Read result", response);
        assertEquals(1, medicalReccordControllerMock.getLastRead());
    }

    @Test
    void readAnotherMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setReadResult("Another read result");
        Router sut = new Router(medicalReccordControllerMock);

        String response = sut.handleRequest("read 2");

        assertEquals("Another read result", response);
        assertEquals(2, medicalReccordControllerMock.getLastRead());
    }

    @Test
    void readMedicalReccordWithoutSpecifyingId() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setReadResult("Another read result");
        Router sut = new Router(medicalReccordControllerMock);

        String response = sut.handleRequest("read");

        assertEquals("Invalid command", response);
        assertEquals(null, medicalReccordControllerMock.getLastRead());
    }

    @Test
    void readMedicalReccordWithInvalidId() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setReadResult("Another read result");
        Router sut = new Router(medicalReccordControllerMock);

        String response = sut.handleRequest("read x");

        assertEquals("Invalid command", response);
        assertEquals(null, medicalReccordControllerMock.getLastRead());
    }

    @Test
    void createMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setCreateResult("Create result");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("create 1 Medical reccord for patient 1");

        assertEquals("Create result", actual);
        assertEquals(1, medicalReccordControllerMock.getLastCreatedPatientId());
        assertEquals("Medical reccord for patient 1", medicalReccordControllerMock.getLastCreatedText());
    }

    @Test
    void createAnotherMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setCreateResult("Another create result");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("create 2 Medical reccord for patient 2");

        assertEquals("Another create result", actual);
        assertEquals(2, medicalReccordControllerMock.getLastCreatedPatientId());
        assertEquals("Medical reccord for patient 2", medicalReccordControllerMock.getLastCreatedText());
    }

    @Test
    void createRequestWithoutParameters() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setCreateResult("Create result");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("create");

        assertEquals("Invalid command", actual);
        assertEquals(null, medicalReccordControllerMock.getLastCreatedPatientId());
        assertEquals(null, medicalReccordControllerMock.getLastCreatedText());
    }

    @Test
    void createRequestWithInvalidId() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setCreateResult("Create result");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("create x text");

        assertEquals("Invalid command", actual);
        assertEquals(null, medicalReccordControllerMock.getLastCreatedPatientId());
        assertEquals(null, medicalReccordControllerMock.getLastCreatedText());
    }

    @Test
    void deleteMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setDeleteResult("Deleted reccord");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("delete 1");

        assertEquals("Deleted reccord", actual);
        assertEquals(1, medicalReccordControllerMock.getLastDelete());
    }

    @Test
    void deleteAnotherMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setDeleteResult("Deleted another reccord");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("delete 2");

        assertEquals("Deleted another reccord", actual);
        assertEquals(2, medicalReccordControllerMock.getLastDelete());
    }

    @Test
    void deleteRequestWithoutId() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setDeleteResult("Deleted reccord");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("delete");

        assertEquals("Invalid command", actual);
        assertEquals(null, medicalReccordControllerMock.getLastDelete());
    }

    @Test
    void deleteRequestWithInvalidId() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setDeleteResult("Deleted reccord");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("delete x");

        assertEquals("Invalid command", actual);
        assertEquals(null, medicalReccordControllerMock.getLastDelete());
    }

    @Test
    void testUpdateMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setUpdateResult("Updated medical reccord");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("update 1 New text");

        assertEquals("Updated medical reccord", actual);
        assertEquals(1, medicalReccordControllerMock.getLastUpdateId());
        assertEquals("New text", medicalReccordControllerMock.getLastUpdateText());
    }

    @Test
    void testUpdateAnotherMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setUpdateResult("Updated another medical reccord");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("update 2 New text 2");

        assertEquals("Updated another medical reccord", actual);
        assertEquals(2, medicalReccordControllerMock.getLastUpdateId());
        assertEquals("New text 2", medicalReccordControllerMock.getLastUpdateText());
    }

    @Test
    void updateRequestWithoutParameters() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setUpdateResult("Updated medical reccord");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("update");

        assertEquals("Invalid command", actual);
        assertEquals(null, medicalReccordControllerMock.getLastUpdateId());
        assertEquals(null, medicalReccordControllerMock.getLastUpdateText());
    }

    @Test
    void updateRequestWithInvalidId() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        medicalReccordControllerMock.setUpdateResult("Updated medical reccord");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("update x text");

        assertEquals("Invalid command", actual);
        assertEquals(null, medicalReccordControllerMock.getLastUpdateId());
        assertEquals(null, medicalReccordControllerMock.getLastUpdateText());
    }

    @Test
    void unknownRequest() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("unknown");

        assertEquals("Unknown command", actual);
    }


    class MedicalReccordControllerMock implements MedicalReccordControlling {

        private Integer lastCreatedPatientId;
        private String lastCreatedText;
        private String createResult;

        private  Integer lastRead;
        private String readResult;

        private Integer lastUpdateId;
        private String lastUpdateText;
        private String updateResult;

        private Integer lastDelete;
        String deleteResult;

        public void setReadResult(String readResult) {
            this.readResult = readResult;
        }
        public void setCreateResult(String createResult) {
            this.createResult = createResult;
        }
        public void setDeleteResult(String deleteResult) {
            this.deleteResult = deleteResult;
        }
        public void setUpdateResult(String updateResult) {
            this.updateResult = updateResult;
        }

        public Integer getLastRead() {
            return lastRead;
        }
        public Integer getLastCreatedPatientId() {
            return lastCreatedPatientId;
        }
        public String getLastCreatedText() {
            return lastCreatedText;
        }
        public Integer getLastDelete() {
            return lastDelete;
        }
        public Integer getLastUpdateId() {
            return lastUpdateId;
        }
        public String getLastUpdateText() {
            return lastUpdateText;
        }

        @Override
        public String create(int patientId, int nurseId, String text) {
            lastCreatedPatientId = patientId;
            lastCreatedText = text;
            return createResult;
        }

        @Override
        public String read(int id) {
            lastRead = id;
            return readResult;
        }

        @Override
        public String update(int id, String newText) {
            lastUpdateId = id;
            lastUpdateText = newText;
            return updateResult;
        }

        @Override
        public String delete(int id) {
            lastDelete = id;
            return deleteResult;
        }
    }
}