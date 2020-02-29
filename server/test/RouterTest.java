import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {

    @Test
    void readMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock("Read result");
        Router sut = new Router(medicalReccordControllerMock);

        String response = sut.handleRequest("read 1");

        assertEquals("Read result", response);
        assertEquals(1, medicalReccordControllerMock.getLastRead());
    }

    @Test
    void readAnotherMedicalReccord() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock("Another read result");
        Router sut = new Router(medicalReccordControllerMock);

        String response = sut.handleRequest("read 2");

        assertEquals("Another read result", response);
        assertEquals(2, medicalReccordControllerMock.getLastRead());
    }

    @Test
    void readMedicalReccordWithoutSpecifyingId() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock("Another read result");
        Router sut = new Router(medicalReccordControllerMock);

        String response = sut.handleRequest("read");

        assertEquals("Invalid command", response);
        assertEquals(null, medicalReccordControllerMock.getLastRead());
    }

    @Test
    void readMedicalReccordWithInvalidId() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock("Another read result");
        Router sut = new Router(medicalReccordControllerMock);

        String response = sut.handleRequest("read x");

        assertEquals("Invalid command", response);
        assertEquals(null, medicalReccordControllerMock.getLastRead());
    }

    @Test
    void handleRequestForMedicalReccords() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock("Read result");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("Medical");

        assertEquals("Medical Records", actual);
    }

    @Test
    void handleAnotherRequest() {
        MedicalReccordControllerMock medicalReccordControllerMock = new MedicalReccordControllerMock("Read result");
        Router sut = new Router(medicalReccordControllerMock);

        String actual = sut.handleRequest("abc");

        assertEquals("cba", actual);
    }




    class MedicalReccordControllerMock implements MedicalReccordControlling {

        private  Integer lastRead;
        private String readResult;

        public MedicalReccordControllerMock(String readResult) {
            this.readResult = readResult;
        }

        public Integer getLastRead() {
            return lastRead;
        }

        @Override
        public String read(int id) {
            lastRead = id;
            return readResult;
        }
    }
}