import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestControllerTest {

    @Test
    void handleRequestForMedicalReccords() {
        RequestController sut = new RequestController();

        String actual = sut.handleRequest("Medical");

        assertEquals("Medical Records", actual);
    }

    @Test
    void handleAnotherRequest() {
        RequestController sut = new RequestController();

        String actual = sut.handleRequest("abc");

        assertEquals("cba", actual);
    }
}