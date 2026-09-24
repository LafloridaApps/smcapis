package com.smcapis.smcapis.services;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.smcapis.smcapis.dto.CodigoResponse;
import org.springframework.web.server.ResponseStatusException;
class CodigoServiceValidationTest {
    @Test void validaOchoDigitosAntesDeAccederRepositorio() {
        var service = new CodigoServiceImpl(codigo -> { fail("No debe consultar"); return null; });
        for (String codigo : new String[] { "", "123", "123456789", "abcdefgh" })
            assertEquals(400, assertThrows(ResponseStatusException.class, () -> service.obtenerCodigoInventario(codigo)).getStatusCode().value());
    }
    @Test void preservaCerosIniciales() {
        var service = new CodigoServiceImpl(codigo -> {
            assertEquals("00102003", codigo);
            return new CodigoResponse("0", "01", "02", "003", "Mesa");
        });
        assertEquals("Mesa", service.obtenerCodigoInventario("00102003").nombreart());
    }
}
