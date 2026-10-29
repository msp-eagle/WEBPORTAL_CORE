package com.msp.webportal.businesslogic.commonUtil;

import com.msp.webportal.businesslogic.dto.Otp.EmailSend;
//import com.service.philyps.dto.Otp.EmailSend;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class ApiConnection {

    private static String TOKEN = "";
    private static String ACCESS_TOKEN = "";

    private static LocalDateTime EXPIRED_DATE;
    private static LocalDateTime EXPIRY_TIME;
    private static final String CLIENT_ID = "1000.QX5G8IUUA10M49MJ2NQJGFSXPKJMJA";
    private static final String CLIENT_SECRET = "94052b6b7161636ea9e7e1fc549d1ca3ecdc72ce6e";
    // private static final String REFRESH_TOKEN = "your_refresh_token";

    @Autowired
    static RestTemplate restTemplate;

    @Autowired
    Environment environment;

    public static String connectApi(String http_url, String methodType, String request, String connectionType) {
        OutputStream ouputStream = null;
        String http_response = null;
        try {
            URLConnection url = new URL(http_url).openConnection();
            url.setRequestProperty("Content-Type", "application/json");
            url.setRequestProperty("Accept", "application/json");
            url.setDoOutput(true);
            if (connectionType.equals("Cookie")) {
                url.setRequestProperty("Cookie", "Authorization=" + TOKEN);
            }
            if (methodType.equals("POST")) {
                ouputStream = url.getOutputStream();
                ouputStream.write(request.getBytes());
                ouputStream.flush();
            } else if (methodType.equals("PATCH")) {
                return connectHTTPSApis(http_url, request);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.getInputStream()));
            if (connectionType.equals("Auth")) {
                TOKEN = url.getHeaderField("Authorization");
                EXPIRED_DATE = LocalDateTime.now().plusDays(1);
                System.out.println("EXPIREED_DATE :" + EXPIRED_DATE);
            }
            // System.out.println("API.....Token :"+TOKEN);
            String tmpStr = null;
            while ((tmpStr = reader.readLine()) != null) {
                http_response = tmpStr;
            }
            System.out.println("API.....TText :" + http_response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return http_response;
    }

    // this is for Email sending purpose because of formdata
    public static String connectApiForMultipart(String http_url, EmailSend request, String connectionType) {
        HttpURLConnection connection = null;
        String http_response = "";
        try {

            URL url = new URL(http_url);
            turnOffSslChecking();
            connection = (HttpURLConnection) url.openConnection();
            String boundary = Long.toHexString(System.currentTimeMillis()); // Unique boundary
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Cookie", "Authorization=" + TOKEN);
            connection.setDoOutput(true);

            if ("Cookie".equals(connectionType)) {
                connection.setRequestProperty("Cookie", "Authorization=" + TOKEN);
            }

            try (OutputStream outputStream = connection.getOutputStream();
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),
                            true)) {

                // Add text field example
                addFormField(writer, "mailContent", request.getMailContent(), boundary);
                addFormField(writer, "mailSubject", request.getMailSubject(), boundary);
                addFormField(writer, "mailTo", request.getMailTo(), boundary);
                // End of the multipart/form-data body
                writer.append("--" + boundary + "--").append("\r\n");
                writer.flush();
            }

            // Read the response from the server
            int responseCode = connection.getResponseCode();
            System.out.println(connection);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    http_response += line;
                }
            }
            System.out.println(http_response);
            // http_response = "Server returned non-OK status: " + responseCode;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return http_response;
    }

    private static void addFormField(PrintWriter writer, String name, String value, String boundary) {
        writer.append("--" + boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append("\r\n");
        writer.append("Content-Type: text/plain; charset=UTF-8").append("\r\n");
        writer.append("\r\n");
        writer.append(value).append("\r\n");
        writer.flush();
    }

    public static void turnOffSslChecking() throws NoSuchAlgorithmException, KeyManagementException {
        // Install the all-trusting trust manager
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, UNQUESTIONING_TRUST_MANAGER, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    /***** Checking FP Device Ends ******/
    public static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[] { new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.
         * X509Certificate[], java.lang.String)
         */
        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub
        }
    } };

    public static String postRest(MultiValueMap<String, Object> body, String url)
            throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, JSONException {

        RestTemplate restTemplatetest = new RestTemplate();
        turnOffSslChecking();
        // ApiConnection.authenticate();
        // ResourceBundle bundle = ApiConnection.readConfig();
        HttpMethod requestMethod = HttpMethod.POST;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Cookie", "Authorization=" + TOKEN);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        System.out.println("url checking:" + url);
        ResponseEntity<String> response = restTemplatetest.exchange(url, requestMethod, requestEntity, String.class);
        System.out.println("response:" + response);
        return response.getBody();
    }

    public static String connectHTTPSApis(String http_url, String request) {
        System.out.println("Entering Patch" + http_url);
        String response = null;
        OutputStream outputStream = null;
        BufferedReader reader = null;

        try {

            // CloseableHttpClient httpClient = HttpClients.createDefault();
            // HttpPatch httpPatch = new HttpPatch(http_url);
            // httpPatch.addHeader("Content-Type", "application/json");
            // httpPatch.addHeader("Accept", "application/json");
            // httpPatch.addHeader("Cookie", "Authorization=" + TOKEN);
            // CloseableHttpResponse responses = httpClient.execute(httpPatch);

            // StringEntity requestBody = new StringEntity("{\"key\": \"value\"}",
            // ContentType.APPLICATION_JSON);
            // httpPatch.setEntity(requestBody);

            // HttpClient httpClient = HttpClient.newBuilder().build();
            // HttpRequest requesta = HttpRequest.newBuilder()
            // .uri(URI.create(http_url))
            // .header("Content-Type", "application/json")
            // .header("Accept", "application/json")
            // .header("Cookie", "Authorization=" + TOKEN)
            // .method("PATCH", HttpRequest.BodyPublishers.ofString(request)) // Request
            // body
            // .build();

            // HttpResponse<String> responses;
            // try {
            // responses = httpClient.send(requesta, HttpResponse.BodyHandlers.ofString());
            // System.out.println("Response Code: " + responses.statusCode());
            // } catch (InterruptedException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }

            // allowMethods("PATCH");
            System.out.println("Entering Patch 1");
            HttpsURLConnection urlConnection = (HttpsURLConnection) new URL(http_url).openConnection();
            System.out.println("Entering Patch 2");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("X-HTTP-Method-Override", "PATCH");

            // Set authorization token if available
            if (!TOKEN.isEmpty()) {
                urlConnection.setRequestProperty("Cookie", "Authorization=" + TOKEN);
            }

            urlConnection.setDoOutput(true);
            outputStream = urlConnection.getOutputStream();
            outputStream.write(request.getBytes());
            outputStream.flush();

            // Read response
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            response = responseBuilder.toString();
            System.out.println("API HTTP Text: " + response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static String connectApiThroughWebClient(String httpUrl, String request, String httpMethod)
            throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        reactor.netty.http.client.HttpClient httpClient = reactor.netty.http.client.HttpClient.create()
                .secure(t -> t.sslContext(sslContext));
        WebClient webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
        HttpMethod method = HttpMethod.resolve(httpMethod.toUpperCase()); // Convert method to HttpMethod
        String val = webClient.method(method) // Use the specified HTTP method
                .uri(httpUrl)
                .header("Cookie", "Authorization=" + TOKEN)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("Value: " + val);
        return val;
    }



    public static String responseEntity(String path, String request, String methodType, String connectType) {
        String apiRes = "";
        HttpHeaders headers = new HttpHeaders();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headerss = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "merge-patch+json");
        headers.setContentType(mediaType);
        headers.add(HttpHeaders.COOKIE, "Authorization=" + TOKEN);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; utf-8");
        headers.add(HttpHeaders.ACCEPT, "application/json");
        HttpEntity<String> entity = new HttpEntity<>(request, headerss);
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplates = new RestTemplate(requestFactory);
        ResponseEntity<String> response1 = restTemplates.exchange(path, HttpMethod.PATCH, entity, String.class);

        System.out.println(response1);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        System.out.println("check 1");
        try {
            System.out.println("TOKEN" + TOKEN);
            headers.add(HttpHeaders.COOKIE, "Authorization=" + TOKEN);
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json; utf-8");
            headers.add(HttpHeaders.ACCEPT, "application/json");
            System.out.println("check 2");
            HttpEntity<String> req = new HttpEntity<>(headers);
            HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.PATCH, requestEntity,
                    String.class);
            System.out.println("Response" + response);
            // System.out.println("..........."+response.getStatusCode().toString());
            if (response.getStatusCode().toString().equals("200 OK")) {
                JSONObject resJson = new JSONObject(response.getBody());
                try {
                    apiRes = resJson.getJSONObject("response").toString();
                } catch (org.json.JSONException e) {
                    apiRes = null;
                }
            } else {

                apiRes = null;
            }
        } catch (Exception e) {
            apiRes = e.getLocalizedMessage();
        }
        return apiRes;
    }

    // public static ResourceBundle readConfig(){
    // ResourceBundle bundle = null;
    // try{
    // String filePath = new FileSystemResource("").getFile().getAbsolutePath();
    // FileInputStream inputStream = new
    // FileInputStream(filePath+"/WEB_config.conf");
    // bundle = new PropertyResourceBundle(inputStream);
    // } catch (FileNotFoundException exception){
    // System.out.println("FileNotFoundException ");
    // } catch (IOException e) {
    // System.out.println("IOException ");
    // }
    // return bundle;
    // }

    private static void allowMethods(String... methods) {

        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/* static field */, newMethods);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String formatToISOString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    }

    public static String formatToGenerateID(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static LocalDateTime getUTCCurrentDateTime() {
        return ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
    }

    // public static void authenticate() {
    // ResourceBundle bundle = readConfig();
    // System.out.println("bubdle"+bundle);
    // JSONObject template = new JSONObject(bundle.getString("template"));
    // try {
    //
    // JSONObject auth_request = template.getJSONObject("request");
    // auth_request.put("appId", bundle.getString("auth.appid"));
    // auth_request.put("clientId", bundle.getString("auth.pwd"));
    // auth_request.put("secretKey", bundle.getString("auth.username"));
    // template.put("requesttime",
    // ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
    // template.put("request", auth_request);
    // System.out.println("auth_request :" + auth_request.toString());
    // System.out.println("Template :" + template.toString());
    // EXPIRED_DATE= LocalDateTime.now().plusDays(1);
    // }catch (Exception e){
    // e.printStackTrace();
    // }
    // try{
    //
    // connectApi(bundle.getString("base.url")+bundle.getString("version")+bundle.getString("authmanager.url"),
    // "POST",template.toString(),"Auth");
    // }catch (Exception exception){
    // exception.printStackTrace();
    // }
    // }

    public void authenticateHub() {
        // ResourceBundle bundle = readConfig();
        System.out.println("template :" + environment.getProperty("template"));
        JSONObject template = new JSONObject(environment.getProperty("template"));
        try {
            JSONObject auth_request = template.getJSONObject("request");
            auth_request.put("appId", environment.getProperty("auth.appid"));
            auth_request.put("clientId", environment.getProperty("auth.pwd"));
            auth_request.put("secretKey", environment.getProperty("auth.username"));
            template.put("requesttime", ApiConnection.formatToISOString(ApiConnection.getUTCCurrentDateTime()));
            template.put("request", auth_request);
            System.out.println("auth_request :" + auth_request.toString());
            System.out.println("Template :" + template.toString());
            EXPIRED_DATE = LocalDateTime.now().plusDays(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            connectApi(
                    environment.getProperty("base.url") + environment.getProperty("version")
                            + environment.getProperty("authmanager.url"),
                    "POST", template.toString(), "Auth");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void authenticateZohoHub() {
        try {
            String authUrl = "https://accounts.zoho.in/oauth/v2/token?grant_type=client_credentials&client_id="
                    + CLIENT_ID + "&client_secret=" + CLIENT_SECRET
                    + "&scope=Desk.tickets.ALL&soid=zohosupport.60011134493";
            // String authUrl = "http://192.168.11.192:8001/service-provider/check";

            String response = connectApiWithAccessToken(authUrl, "POST", "", "Bearer");
            JSONObject jsonResponse = new JSONObject(response);
            System.out.println("13" + jsonResponse);

            ACCESS_TOKEN = jsonResponse.getString("access_token");
            long expiresIn = jsonResponse.getLong("expires_in");
            EXPIRY_TIME = LocalDateTime.now().plusSeconds(expiresIn);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String connectApiWithAccessToken(String httpUrl, String methodType, String request,
            String connectionType) {
        OutputStream outputStream = null;
        String httpResponse = null;
        System.out.println("1");
        try {

            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(methodType);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            System.out.println("2" + connection);

            if (connectionType.equals("Bearer")) {
                connection.setRequestProperty("orgId", "60011134493");
                connection.setRequestProperty("Authorization", "Zoho-oauthtoken " + ACCESS_TOKEN);
                System.out.println("3" + ACCESS_TOKEN);

            }

            if (methodType.equals("POST")) {
                System.out.println("4");

                outputStream = connection.getOutputStream();
                outputStream.write(request.getBytes());
                outputStream.flush();
            }
            System.out.println("5");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("6");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                httpResponse = response.toString();
                reader.close();
            }

            if (connectionType.equals("Bearer")) {
                // Check if the token needs to be refreshed
                if (isTokenExpired()) {
                    System.out.println("7");

                    refreshToken();
                }
            }

        } catch (IOException e) {
            System.out.println("8");

            e.printStackTrace();
        }
        return httpResponse;
    }

    public static boolean isTokenExpired() {

        return EXPIRY_TIME == null || LocalDateTime.now().isAfter(EXPIRY_TIME);
    }

    public static void refreshToken() {
        try {
            System.out.println("9");

            String refreshTokenUrl = "https://accounts.zoho.in/oauth/v2/token?grant_type=client_credentials&client_id="
                    + CLIENT_ID + "&client_secret=" + CLIENT_SECRET
                    + "&scope=Desk.tickets.ALL&soid=zohosupport.60011134493";

            HttpURLConnection connection = (HttpURLConnection) new URL(refreshTokenUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream()) {
                System.out.println("10");

                outputStream.write(refreshTokenUrl.getBytes());
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("11");

                StringBuilder response = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                ACCESS_TOKEN = jsonResponse.getString("access_token");
                long expiresIn = jsonResponse.getLong("expires_in");
                EXPIRY_TIME = LocalDateTime.now().plusSeconds(expiresIn);
            }
        } catch (Exception e) {
            System.out.println("12");

            e.printStackTrace();
        }
    }

}
