package com.smcapis.smcapis.utiles;

public class NumeroUtils {

    private static final String[] UNIDADES = {
        "", "un", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"
    };

    private static final String[] DECENAS = {
        "", "diez", "veinte", "treinta", "cuarenta", "cincuenta",
        "sesenta", "setenta", "ochenta", "noventa"
    };

    private static final String[] DIEZ_DIES = {
        "", "once", "doce", "trece", "catorce", "quince",
        "dieciséis", "diecisiete", "dieciocho", "diecinueve"
    };

    private static final String[] VEINTI = {
        "", "veintiún", "veintidós", "veintitrés", "veinticuatro", "veinticinco",
        "veintiséis", "veintisiete", "veintiocho", "veintinueve"
    };

    private static final String[] CENTENAS = {
        "", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos",
        "seiscientos", "setecientos", "ochocientos", "novecientos"
    };

    private NumeroUtils() {}

    public static String numeroAPalabras(int numero) {
        if (numero == 0) return "cero";
        return convertir(numero).trim();
    }

    private static String convertir(int n) {
        if (n < 0) return "menos " + convertir(-n);
        if (n < 10) return UNIDADES[n];
        if (n < 20) return DIEZ_DIES[n - 10];
        if (n < 30) return VEINTI[n - 20];
        if (n < 100) return decenas(n);
        if (n < 1000) return centenas(n);
        if (n < 1_000_000) return miles(n);
        return millones(n);
    }

    private static String decenas(int n) {
        int d = n / 10;
        int u = n % 10;
        if (u == 0) return DECENAS[d];
        return DECENAS[d] + " y " + UNIDADES[u];
    }

    private static String centenas(int n) {
        int c = n / 100;
        int r = n % 100;
        if (c == 1 && r == 0) return "cien";
        String resultado = CENTENAS[c];
        if (r > 0) resultado += " " + convertir(r);
        return resultado;
    }

    private static String miles(int n) {
        int m = n / 1000;
        int r = n % 1000;
        String resultado = (m == 1) ? "mil" : convertir(m) + " mil";
        if (r > 0) resultado += " " + convertir(r);
        return resultado;
    }

    private static String millones(int n) {
        int mill = n / 1_000_000;
        int r = n % 1_000_000;
        String resultado = (mill == 1) ? "un millón" : convertir(mill) + " millones";
        if (r > 0) resultado += " " + convertir(r);
        return resultado;
    }

}
