package ph.txtdis.util;

import java.io.IOException;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class Secure {

    public static String username() {
        return encode("username");
    }

    public static String password() {
        return encode("password");
    }

    public static String encode(String text) {
        Properties props = new EncryptableProperties(encryptor());
        load(props);
        return props.getProperty("spring.datasource." + text);
    }

    private static void load(Properties props) {
        try {
            props.load(Secure.class.getResourceAsStream("/config/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StandardPBEStringEncryptor encryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("I'mAdmin4txtDIS@PostgreSQL");
        return encryptor;
    }
}
