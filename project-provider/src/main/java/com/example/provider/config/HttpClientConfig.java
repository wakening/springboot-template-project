package com.example.provider.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * 项目统一http客户端配置类
 *
 * @author wakening
 */
@Configuration
public class HttpClientConfig {

    @Value("${template.httpclient.maxTotal:400}")
    private int maxTotal;

    @Value("${template.httpclient.maxPerRoute:100}")
    private int maxPerRoute;

    @Value("${template.httpclient.connectTimeout:4000}")
    private int connectTimeout;

    @Value("${template.httpclient.connectionRequestTimeout:4000}")
    private int connectionRequestTimeout;

    @Value("${template.httpclient.socketTimeout:10000}")
    private int socketTimeout;

    /**
     * httpClient客户端，同时支持http和https，支持全部SSL、TLS协议
     *
     * @return HttpClient
     */
    @Bean(destroyMethod = "close")
    public CloseableHttpClient commonHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        // https SSL配置
        // allows all certificates
        TrustStrategy trustStrategy = (chain, authType) -> true;
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, trustStrategy)
                .build();
        // 支持协议
        String[] supportedProtocols = {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
        SSLConnectionSocketFactory httpsSslSocketFactory = new SSLConnectionSocketFactory(
                sslContext, supportedProtocols, null, NoopHostnameVerifier.INSTANCE);
        // 注册http、https
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", httpsSslSocketFactory)
                .build();
        // 设置路由
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                // 指客户端和服务器建立连接的timeout
                .setConnectTimeout(connectTimeout)
                // 指从连接池获取连接的timeout
                .setConnectionRequestTimeout(connectionRequestTimeout)
                // 客户端和服务器建立连接后，客户端从服务端读取数据的timeout
                .setSocketTimeout(socketTimeout)
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }

    /**
     * RestTemplate，内部使用httpClient客户端
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(@Qualifier("commonHttpClient") CloseableHttpClient commonHttpClient) {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(commonHttpClient));
    }

}
