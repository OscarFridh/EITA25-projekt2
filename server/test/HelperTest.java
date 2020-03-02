import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void generateUniqueId() {
        assertNotEquals(Helper.generateUniqueId(), Helper.generateUniqueId());
    }
}