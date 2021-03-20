package tech.xiying.activemq;


import org.apache.activemq.broker.SslContext;
import org.apache.activemq.spring.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.net.ssl.*;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CustomSpringSslContext extends SslContext {

    private static final transient Logger logger = LoggerFactory.getLogger(CustomSpringSslContext.class);

    private String keyStoreType = "jks";
    private String trustStoreType = "jks";
    private String secureRandomAlgorithm = "SHA1PRNG";
    private String keyStoreAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
    private String trustStoreAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
    private String keyStore;
    private String trustStore;
    private String keyStoreKeyPassword;
    private String keyStorePassword;
    private String trustStorePassword;
    private String crlPath;

    public CustomSpringSslContext() {
    }

    @PostConstruct
    private void postConstruct() {
        logger.info("keystore init: start");
        logger.info("====================== CustomSpringSslContext ==================");
        try {
            this.afterPropertiesSet();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
        logger.info("keystore init: end");
    }

    public void afterPropertiesSet() throws Exception {
        this.keyManagers.addAll(this.createKeyManagers());
        this.trustManagers.addAll(this.createTrustManagers());
        System.out.println("keyManagers:"+keyManagers);
        System.out.println("trustManagers:"+trustManagers);
        if (this.secureRandom == null) {
            this.secureRandom = this.createSecureRandom();
        }

    }

    private SecureRandom createSecureRandom() throws NoSuchAlgorithmException {
        return SecureRandom.getInstance(this.secureRandomAlgorithm);
    }

    private Collection<TrustManager> createTrustManagers() throws Exception {
        boolean ocsp = Boolean.valueOf(Security.getProperty("ocsp.enable"));
        KeyStore ks = this.createTrustManagerKeyStore();
        if (ks == null) {
            return new ArrayList(0);
        } else {
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(this.trustStoreAlgorithm);
            boolean initialized = false;
            if ((ocsp || this.crlPath != null) && this.trustStoreAlgorithm.equalsIgnoreCase("PKIX")) {
                PKIXBuilderParameters pkixParams = new PKIXBuilderParameters(ks, new X509CertSelector());
                if (this.crlPath != null) {
                    pkixParams.setRevocationEnabled(true);
                    Collection<? extends CRL> crlList = this.loadCRL();
                    if (crlList != null) {
                        pkixParams.addCertStore(CertStore.getInstance("Collection", new CollectionCertStoreParameters(crlList)));
                    }
                }

                tmf.init(new CertPathTrustManagerParameters(pkixParams));
                initialized = true;
            }

            if (!initialized) {
                tmf.init(ks);
            }

            return Arrays.asList(tmf.getTrustManagers());
        }
    }

    private Collection<KeyManager> createKeyManagers() throws Exception {
        KeyStore ks = this.createKeyManagerKeyStore();
        if (ks == null) {
            return new ArrayList(0);
        } else {
            KeyManagerFactory tmf = KeyManagerFactory.getInstance(this.keyStoreAlgorithm);
            tmf.init(ks, this.keyStoreKeyPassword == null ? (this.keyStorePassword == null ? null : this.keyStorePassword.toCharArray()) : this.keyStoreKeyPassword.toCharArray());
            return Arrays.asList(tmf.getKeyManagers());
        }
    }

    private KeyStore createTrustManagerKeyStore() throws Exception {
        if (this.trustStore == null) {
            return null;
        } else {
            KeyStore ks = KeyStore.getInstance(this.trustStoreType);
            InputStream is = Utils.resourceFromString(this.trustStore).getInputStream();

            try {
                ks.load(is, this.trustStorePassword == null ? null : this.trustStorePassword.toCharArray());
            } finally {
                is.close();
            }

            return ks;
        }
    }

    private KeyStore createKeyManagerKeyStore() throws Exception {
        if (this.keyStore == null) {
            return null;
        } else {
            KeyStore ks = KeyStore.getInstance(this.keyStoreType);
            InputStream is = Utils.resourceFromString(this.keyStore).getInputStream();

            try {
                ks.load(is, this.keyStorePassword == null ? null : this.keyStorePassword.toCharArray());
            } finally {
                is.close();
            }

            return ks;
        }
    }

    public String getTrustStoreType() {
        return this.trustStoreType;
    }

    public String getKeyStoreType() {
        return this.keyStoreType;
    }

    public String getKeyStore() {
        return this.keyStore;
    }

    public void setKeyStore(String keyStore) throws MalformedURLException {
        this.keyStore = keyStore;
    }

    public String getTrustStore() {
        return this.trustStore;
    }

    public void setTrustStore(String trustStore) throws MalformedURLException {
        this.trustStore = trustStore;
    }

    public String getKeyStoreAlgorithm() {
        return this.keyStoreAlgorithm;
    }

    public void setKeyStoreAlgorithm(String keyAlgorithm) {
        this.keyStoreAlgorithm = keyAlgorithm;
    }

    public String getTrustStoreAlgorithm() {
        return this.trustStoreAlgorithm;
    }

    public void setTrustStoreAlgorithm(String trustAlgorithm) {
        this.trustStoreAlgorithm = trustAlgorithm;
    }

    public String getKeyStoreKeyPassword() {
        return this.keyStoreKeyPassword;
    }

    public void setKeyStoreKeyPassword(String keyPassword) {
        this.keyStoreKeyPassword = keyPassword;
    }

    public String getKeyStorePassword() {
        return this.keyStorePassword;
    }

    public void setKeyStorePassword(String keyStorePassword) {
        logger.info("================ keyStorePassword decrypt: start ============================");
//        if(StringUtils.isBlank(keyStorePassword)){
//            logger.info("================ pwd is blank, init success =======================");
//            keyStorePassword= "123456";
//        }else {
//            /***
//             * 传进来的密码是加密过的
//             *
//             * 通过bic-crypt进行解密
//             */
//            keyStorePassword = BicUtils.dataDecrypt(keyStorePassword);
//        }
        logger.info("================ decrypt pwd success ==========");
        this.keyStorePassword = keyStorePassword;
    }

    public String getTrustStorePassword() {
        return this.trustStorePassword;
    }

    public void setTrustStorePassword(String trustPassword) {
        this.trustStorePassword = trustPassword;
    }

    public void setKeyStoreType(String keyType) {
        this.keyStoreType = keyType;
    }

    public void setTrustStoreType(String trustType) {
        this.trustStoreType = trustType;
    }

    public String getSecureRandomAlgorithm() {
        return this.secureRandomAlgorithm;
    }

    public void setSecureRandomAlgorithm(String secureRandomAlgorithm) {
        this.secureRandomAlgorithm = secureRandomAlgorithm;
    }

    public String getCrlPath() {
        return this.crlPath;
    }

    public void setCrlPath(String crlPath) {
        this.crlPath = crlPath;
    }

    private Collection<? extends CRL> loadCRL() throws Exception {
        if (this.crlPath == null) {
            return null;
        } else {
            Resource resource = Utils.resourceFromString(this.crlPath);
            InputStream is = resource.getInputStream();

            Collection var3;
            try {
                var3 = CertificateFactory.getInstance("X.509").generateCRLs(is);
            } finally {
                is.close();
            }

            return var3;
        }
    }
}
