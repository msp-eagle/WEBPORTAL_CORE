package com.msp.webportal.businesslogic.commonUtil;//package com.service.philyps.commonUtil;
//
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.google.gson.Gson;
//import com.service.philyps.dto.ClientIdSecretKeyRequestDto;
//import com.service.philyps.dto.NewTokenRequestDto;
//import org.apache.http.Header;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.conn.ssl.TrustAllStrategy;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.TimeZone;
//
//@Component
//public class TokenGenerator {
//
//    private static final Logger logger = LoggerFactory.getLogger(TokenGenerator.class);
//
//    @Autowired
//    Environment environment;
//
//    @Value("${app.base.url}")
//    private String baseUrl;
//
//    @Value("${app.id}")
//    private String appID;
//
//    @Value("${app.username}")
//    private String userName;
//
//    @Value("${app.password}")
//    private String password;
//
//	private static final String AUTHORIZATION = "Authorization=";
//
//    /**
//     * This method gets the token for the user details present in config server.
//     *
//     * @return
//     * @throws IOException
//     */
//    public String getToken() throws NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
////        try {
//            return generateToken(setRequestDto());
////        }catch (Exception e){
////            ExceptionUtils.getStackTrace(e);
////
////        }
//
//    }
//
//    private String generateToken(ClientIdSecretKeyRequestDto dto)
//            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
//
//        String token = System.getProperty("token");
//        boolean isValid = false;
//
//        if (token != null) {
//            DecodedJWT decodedJWT = JWT.decode(token);
//            LocalDateTime expiryTime = convertUTCToLocalDateTime(getUTCTimeFromDate(decodedJWT.getExpiresAt()));
//            if (getUTCCurrentDateTime().isBefore(expiryTime)) {
//                isValid=true;
//            }
//        }
//        if (!isValid) {
//            NewTokenRequestDto tokenRequest = new NewTokenRequestDto();
//            tokenRequest.setId(environment.getProperty("token.request.id"));
//
//            tokenRequest.setRequesttime(getUTCCurrentDateTimeString());
//            tokenRequest.setRequest(dto);
//            tokenRequest.setVersion(environment.getProperty("token.request.version"));
//
//            Gson gson = new Gson();
//            HttpClient httpClient = HttpClientBuilder.create().build();
//
//            httpClient = HttpClients
//                    .custom()
//                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
//                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//                    .build();
//            String url = baseUrl+"/v1/authmanager/authenticate/useridPwd";
//            HttpPost post = new HttpPost(url);
////            HttpPost post = new HttpPost(InitConstants.url+"/v1/authmanager/authenticate/clientidsecretkey");
//            try {
//                StringEntity postingString = new StringEntity(gson.toJson(tokenRequest));
//                post.setEntity(postingString);
//                post.setHeader("Content-type", "application/json");
//                HttpResponse response = httpClient.execute(post);
//                org.apache.http.HttpEntity entity = response.getEntity();
//                String responseBody = EntityUtils.toString(entity, "UTF-8");
//                logger.info("Token generation successfully" , responseBody);
//                Header[] cookie = response.getHeaders("Set-Cookie");
//                if (cookie.length == 0){
//                    logger.error("Token generation failed");
//                    throw new RuntimeException();
//                }
//                if (Arrays.stream(response.getHeaders("Authorization")).findAny().isPresent()){
//                    token = response.getHeaders("Set-Cookie")[0].getValue();
//                    logger.debug("Cookie => " + cookie[0]);
//                    System.setProperty("token", token.substring(14, token.indexOf(';')));
//                    return token.substring(0, token.indexOf(';'));
//                } else {
//                    logger.error("Token generation failed");
//                    throw new RuntimeException("Exception occurred while accessing " + url+" - "+responseBody);
//                }
//            } catch (IOException e) {
//                logger.error("Token generation failed" , (e));
////                ExceptionUtils.getStackTrace(e);
//                throw e;
//            }
//        }
//        return AUTHORIZATION + token;
//    }
//
//    private ClientIdSecretKeyRequestDto setRequestDto() {
//        ClientIdSecretKeyRequestDto request = new ClientIdSecretKeyRequestDto();
//        request.setAppId(appID);
//        request.setUserName(userName);
//        request.setPassword(password);
//        return request;
//    }
//
//    public static LocalDateTime convertUTCToLocalDateTime(String utcDateTime) {
//        return ZonedDateTime.parse(utcDateTime).toLocalDateTime();
//    }
//
//    public static String getUTCTimeFromDate(Date date) {
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//        return dateFormatter.format(date);
//    }
//
//    public static LocalDateTime getUTCCurrentDateTime() {
//        return ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
//    }
//    public static String getUTCCurrentDateTimeString() {
//        return OffsetDateTime.now().toInstant().toString();
//    }
//
//}
