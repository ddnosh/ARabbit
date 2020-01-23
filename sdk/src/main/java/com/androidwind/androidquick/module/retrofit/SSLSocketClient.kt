package com.androidwind.androidquick.module.retrofit

import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object SSLSocketClient {

    //okhttp3不开启https证书校验
    val trustManager: Array<TrustManager> = arrayOf(
        object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    )

    //okhttp3不开启https证书校验
    val noSSLSocketFactory: SSLSocketFactory = try {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustManager, SecureRandom())
        sslContext.socketFactory
    } catch (e: Exception) {
        throw RuntimeException(e)
    }

    //okhttp3开启https证书校验
//    public static SSLSocketFactory getSSLSocketFactory() {
//        try {
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            trustStore.load(null, null);
//            trustStore.setCertificateEntry("your_crt", Utility.setKeystoreOfCA());
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(trustStore);
//            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
//            SSLSocketFactory factory = sslContext.getSocketFactory();
//            return factory;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//        }
//    }

    //获取HostnameVerifier
    fun getHostnameVerifier(): HostnameVerifier = HostnameVerifier { s, sslSession -> true }
}