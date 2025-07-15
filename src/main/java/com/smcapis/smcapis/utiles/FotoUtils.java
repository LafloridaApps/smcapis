package com.smcapis.smcapis.utiles;

import java.util.Base64;

public class FotoUtils {

    private FotoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String fotoConverter(byte[] imageBytes) {

        return Base64.getEncoder().encodeToString(imageBytes);

    }

}
