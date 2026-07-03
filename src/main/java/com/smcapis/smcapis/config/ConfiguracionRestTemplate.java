package com.smcapis.smcapis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class ConfiguracionRestTemplate {

    @Bean
    @SuppressWarnings({"java:S4830", "java:S5527"})
    public RestTemplate restTemplate() {
        try {
            TrustManager[] administradoresConfianza = { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                public void checkClientTrusted(X509Certificate[] certificados, String tipoAutenticacion) {
                    // confiar en todos los certificados cliente (API interna)
                }
                public void checkServerTrusted(X509Certificate[] certificados, String tipoAutenticacion) {
                    // confiar en todos los certificados servidor (API interna)
                }
            } };

            SSLContext contextoSSL = SSLContext.getInstance("TLS");
            contextoSSL.init(null, administradoresConfianza, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(contextoSSL.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((nombreHost, sesion) -> true);

            SimpleClientHttpRequestFactory fabrica = new SimpleClientHttpRequestFactory();
            fabrica.setConnectTimeout(10000);
            fabrica.setReadTimeout(30000);

            return new RestTemplate(fabrica);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Error configurando SSL", e);
        }
    }

}
