package br.com.zupacademy.mateus.proposta.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public abstract class DocumentoEncrypter {

    private static String salt = "8350408e50657bbd";

    public static String encriptarDocumento(String documento) {
        TextEncryptor encryptor = Encryptors.queryableText("document", salt);

        return encryptor.encrypt(documento);
    }

    public static String descripitarDocumento(String documento) {
        TextEncryptor encryptor = Encryptors.queryableText("document", salt);

        return encryptor.decrypt(documento);
    }
}