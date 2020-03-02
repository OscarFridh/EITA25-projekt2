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
    void unknownRequest() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock();
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("unknown");

        assertEquals("Unknown command", actual);
    }


    class MedicalReccordControllerMock implements MedicalReccordControlling {

        private  Integer lastRead;
        private String readResult;

        private Integer lastCreatedPatientId;
        private String lastCreatedText;
        private String createResult;

        public void setReadResult(String readResult) {
            this.readResult = readResult;
        }

        public void setCreateResult(String createResult) {
            this.createResult = createResult;
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

        @Override
        public String read(int id) {
            lastRead = id;
            return readResult;
        }

        @Override
        public String create(int patientId, String text) {
            lastCreatedPatientId = patientId;
            lastCreatedText = text;
            return createResult;
        }
    }
}