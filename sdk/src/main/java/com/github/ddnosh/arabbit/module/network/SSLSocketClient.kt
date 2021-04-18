package com.github.ddnosh.arabbit.module.network

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
    private val trustManager: Array<TrustManager> = arrayOf(
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

//    //okhttp3开启https证书校验: 单向验证
//    fun getSSLSocketFactory(): SSLSocketFactory? {
//        return try {
//            val sslContext = SSLContext.getInstance("TLS");
//            val trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            trustStore.load(null, null);
//            trustStore.setCertificateEntry("your_crt", Utility.setKeystoreOfCA());
//            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(trustStore);
//            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
//            val sslSocketFactory = sslContext.getSocketFactory();
//            sslSocketFactory;
//        } catch (e: Exception) {
//            e.printStackTrace();
//            null;
//        } finally {
//        }
//    }
//    //okhttp3开启https证书校验: 双向验证

    //获取HostnameVerifier
    val hostnameVerifier: HostnameVerifier = HostnameVerifier { hostname, session -> true }
}