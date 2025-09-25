package springbookapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.akumosstl.springbookapi.security.JwtUtil;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private final String secret = "dGVzdFNlY3JldEtleQ==";
    private final String username = "usuarioTeste";

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(secret, 60000);
    }

    @Test
    void testGenerateTokenAndValidate() {
        String token = jwtUtil.generateToken(username);
        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    void testExtractUsername() {
        String token = jwtUtil.generateToken(username);
        String extracted = jwtUtil.extractUsername(token);
        assertEquals(username, extracted);
    }

    @Test
    void testInvalidToken() {
        String invalidToken = "invalid.token.value";
        assertFalse(jwtUtil.validateToken(invalidToken));

    }
}