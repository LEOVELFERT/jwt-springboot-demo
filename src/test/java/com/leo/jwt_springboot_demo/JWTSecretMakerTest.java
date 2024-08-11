package com.leo.jwt_springboot_demo;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

public class JWTSecretMakerTest {
    @Test
    public void generateSecretKey(){
        SecretKey key = Jwts.SIG.HS512.key().build();
        String secretKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println("The secret key is-------------->"+secretKey);

        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        System.out.println("Encoded Password: " + password);
    }
}
