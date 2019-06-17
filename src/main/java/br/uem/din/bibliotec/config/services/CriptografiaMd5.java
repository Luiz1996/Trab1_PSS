package br.uem.din.bibliotec.config.services;

import java.security.*;
import java.math.*;

public class CriptografiaMd5 {

    public String makeEncryptionMd5(String Senha) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");

        m.update(Senha.getBytes(),0,Senha.length());
        return (new BigInteger(1,m.digest()).toString(16).trim());
    }
}
