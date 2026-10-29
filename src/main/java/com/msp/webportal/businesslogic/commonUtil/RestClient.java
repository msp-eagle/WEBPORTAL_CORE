package com.msp.webportal.businesslogic.commonUtil;//package com.service.philyps.commonUtil;
//
//
////import com.msp.tspasservice.commons.LoggerClazz;
////import com.msp.tspasservice.commons.SslOff;
////import com.msp.tspasservice.controller.AuthenticationController;
////import com.msp.tspasservice.exception.ApisResourceAccessException;
////import com.msp.tspasservice.exception.ExceptionUtils;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.core.env.Environment;
//import org.springframework.http.*;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import javax.net.ssl.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.*;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class RestClient {
//
//    public static String TOKEN = "";
//    @Autowired
//    private TokenGenerator tokenGenerator;
//
//    private static final Logger logger =LoggerFactory.getLogger(RestClient.class);
//
//    @Autowired
//    RestTemplateBuilder builder;
//
//
//    @Autowired
//    Environment environment;
//
//    public <T> T getApi(URI uri, Class<?> responseType) {
//        try {
//            SslOff sslOff = new SslOff();
//            RestTemplate restTemplate = sslOff.turnOffSslChecking();
//            logger.info("Api called",uri.toString());
//             T response = (T) restTemplate.exchange(uri, HttpMethod.GET, setRequestHeader(null, null, tokenGenerator.getToken()), responseType)
//                    .getBody();
//            logger.info("Api response has received",response.toString());
//            return response;
//        } catch (Exception e) {
////            logger.error(e.getMessage() + ExceptionUtils.getStackTrace(e));
//            if (e.getMessage().contains("401")){
////                throw new ApisResourceAccessException("Exception occurred while accessing - " + uri+" - "+e.getMessage(),
////                        e);
//            }
////            throw new ApisResourceAccessException("Exception occurred while accessing - " + uri, e);
//        }
//
//        return null;
//    }
//
//    public Object getApi(String apiName, List<String> pathsegments, String queryParamName, String queryParamValue,
//                         Class<?> responseType) {
//
//        Object obj = null;
//        String apiHostIpPort = environment.getProperty(apiName);
//        UriComponentsBuilder uriComponentsBuilder = null;
//        UriComponents uriComponents = null;
//        if (apiHostIpPort != null) {
//            uriComponentsBuilder = UriComponentsBuilder.fromUriString(apiHostIpPort);
//            if (!((pathsegments == null) || (pathsegments.isEmpty()))) {
//                for (String segment : pathsegments) {
//                    if (!((segment == null) || (("").equals(segment)))) {
//                        uriComponentsBuilder.pathSegment(segment);
//                    }
//                }
//            }
//
//
//            if (!queryParamName.isEmpty()) {
//
//                String[] queryParamNameArr = queryParamName.split(",");
//                String[] queryParamValueArr = queryParamValue.split(",");
//                for (int i = 0; i < queryParamNameArr.length; i++) {
//                    uriComponentsBuilder.queryParam(queryParamNameArr[i], queryParamValueArr[i]);
//                }
//
//            }
//            try {
//
//                uriComponents = uriComponentsBuilder.build(false).encode();
//                obj = getApi(uriComponents.toUri(), responseType);
//
//            } catch (Exception e) {
//                e.printStackTrace();
////                throw new ApisResourceAccessException("Exception occured while accessing - ", e);
//
//            }
//        }
//
//        return obj;
//    }
//
//
//    public Object getApi(String apiName, List<String> pathsegments, List<String> queryParamName, List<Object> queryParamValue,
//                         Class<?> responseType, String token)  {
//
//        Object obj = null;
//        String apiHostIpPort = environment.getProperty(apiName);
//        UriComponentsBuilder uriComponentsBuilder = null;
//        UriComponents uriComponents = null;
//        if (apiHostIpPort != null) {
//            uriComponentsBuilder = UriComponentsBuilder.fromUriString(apiHostIpPort);
//            if (!((pathsegments == null) || (pathsegments.isEmpty()))) {
//                for (String segment : pathsegments) {
//                    if (!((segment == null) || (("").equals(segment)))) {
//                        uriComponentsBuilder.pathSegment(segment);
//                    }
//                }
//            }
//
//            if (!((queryParamName == null) || (("").equals(queryParamName)))) {
//
//                for (int i = 0; i < queryParamName.size(); i++) {
//                    uriComponentsBuilder.queryParam(queryParamName.get(i), queryParamValue.get(i));
//                }
//
//            }
//            try {
//
//                uriComponents = uriComponentsBuilder.build(false).encode();
//                obj = getApi(uriComponents.toUri(), responseType);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
////                throw new ApisResourceAccessException("Exception occurred while accessing - ", e);
//
//            }
//        }
//
//        return obj;
//    }
//
//    @SuppressWarnings({ "unchecked", "null" })
//    public <T> T getApi(String apiName, Map<String, String> pathsegments, Class<?> responseType, String token)
//            throws Exception {
//
//        String apiHostIpPort = environment.getProperty(apiName);
//        Object obj = null;
//        UriComponentsBuilder uriComponentsBuilder = null;
//        if (apiHostIpPort != null) {
//
//            uriComponentsBuilder = UriComponentsBuilder.fromUriString(apiHostIpPort);
//
//            URI urlWithPath = uriComponentsBuilder.build(pathsegments);
//            try {
//                obj = getApi(urlWithPath, responseType);
//
//            } catch (Exception e) {
//                throw new Exception(e);
//            }
//
//        }
//        return (T) obj;
//    }
//
//    @SuppressWarnings("unchecked")
//    public <T> T postApi(String uri, MediaType mediaType, Object requestType, Class<?> responseClass) {
//        try {
//            String token = tokenGenerator.getToken();
//            SslOff sslOff = new SslOff();
//            RestTemplate restTemplate = sslOff.turnOffSslChecking();
//            logger.info("Api called - "+uri);
//            T response = (T) restTemplate.postForObject(uri, setRequestHeader(requestType, mediaType, token),
//                    responseClass);
//
//            logger.info("Api response has received",response.toString());
//            return response;
//        } catch (Exception e) {
//            logger.error( e.getMessage() ,(e));
//            if (e.getMessage().contains("401")){
////                throw new ApisResourceAccessException("Exception occurred while accessing - " + uri+" - "+e.getMessage(),
////                        e);
//            }
////            throw new ApisResourceAccessException("Exception occurred while accessing - " + uri, e);
//        }
//        return null;
//    }
//    @SuppressWarnings("unchecked")
//    public <T> T postApiforAuth(String uri, MediaType mediaType, Object requestType, Class<?> responseClass,String signature) {
//        try {
//            String token = tokenGenerator.getToken();
//            SslOff sslOff = new SslOff();
//            RestTemplate restTemplate = sslOff.turnOffSslChecking();
//            logger.info("Api called - "+uri);
//            T response = (T) restTemplate.postForObject(uri, setRequestHeaderforAuth(requestType, mediaType,signature,token),
//                    responseClass);
//
//            logger.info("Api response has received",response.toString());
//            return response;
//        } catch (Exception e) {
//            logger.error( e.getMessage() , (e));
//            if (e.getMessage().contains("401")){
////                throw new ApisResourceAccessException("Exception occurred while accessing - " + uri+" - "+e.getMessage(),
////                        e);
//            }
////            throw new ApisResourceAccessException("Exception occurred while accessing - " + uri, e);
//        }
//        return null;
//    }
//
//
//
//    /**
//     * Patch api.
//     *
//     * @param <T>
//     *            the generic type
//     * @param uri
//     *            the uri
//     * @param requestType
//     *            the request type
//     * @param responseClass
//     *            the response class
//     * @return the t
//     */
//    @SuppressWarnings("unchecked")
//    public <T> T patchApi(String uri, MediaType mediaType, Object requestType, Class<?> responseClass, String token){
//
//        T result = null;
//        try {
//            SslOff sslOff = new SslOff();
//            RestTemplate restTemplate = sslOff.turnOffSslChecking();
//            logger.info(uri);
//            result = (T) restTemplate.patchForObject(uri, setRequestHeader(requestType, mediaType, token),
//                    responseClass);
//
//        } catch (Exception e) {
//
////            logger.error(e.getMessage() + ExceptionUtils.getStackTrace(e));
//
////            throw new ApisResourceAccessException("Exception occurred while accessing - " + uri, e);
//        }
//        return result;
//    }
//
//    public <T> T patchApi(String uri, Object requestType, Class<?> responseClass, String token) throws Exception {
//        return patchApi(uri, null, requestType, responseClass, token);
//    }
//
//    /**
//     * this method sets token to header of the request
//     *
//     * @param requestType
//     * @param mediaType
//     * @return
//     * @throws IOException
//     */
//    @SuppressWarnings("unchecked")
//    private HttpEntity<Object> setRequestHeaderforAuth(Object requestType, MediaType mediaType,String signature, String token)
//            throws IOException {
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        headers.add("Cookie", token);
//        headers.add("Authorization", token);
//        if (mediaType != null) {
//            headers.add("Content-Type", mediaType.toString());
//        }
//        if(signature !=null){
//            headers.add("signature",signature);
//        }
//        if (requestType != null) {
//            try {
//                HttpEntity<Object> httpEntity = (HttpEntity<Object>) requestType;
//                HttpHeaders httpHeader = httpEntity.getHeaders();
//                Iterator<String> iterator = httpHeader.keySet().iterator();
//                while (iterator.hasNext()) {
//                    String key = iterator.next();
//                    if (!(headers.containsKey("Content-Type") && key == "Content-Type"))
//                        headers.add(key, httpHeader.get(key).get(0));
//                }
//                return new HttpEntity<Object>(httpEntity.getBody(), headers);
//            } catch (ClassCastException e) {
//                return new HttpEntity<Object>(requestType, headers);
//            }
//        } else
//            return new HttpEntity<Object>(headers);
//    }
//    private HttpEntity<Object> setRequestHeader(Object requestType, MediaType mediaType, String token)
//            throws IOException {
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        headers.add("Cookie", token);
//        headers.add("Authorization", token);
//        if (mediaType != null) {
//            headers.add("Content-Type", mediaType.toString());
//        }
//        if (requestType != null) {
//            try {
//                HttpEntity<Object> httpEntity = (HttpEntity<Object>) requestType;
//                HttpHeaders httpHeader = httpEntity.getHeaders();
//                Iterator<String> iterator = httpHeader.keySet().iterator();
//                while (iterator.hasNext()) {
//                    String key = iterator.next();
//                    if (!(headers.containsKey("Content-Type") && key == "Content-Type"))
//                        headers.add(key, httpHeader.get(key).get(0));
//                }
//                return new HttpEntity<Object>(httpEntity.getBody(), headers);
//            } catch (ClassCastException e) {
//                return new HttpEntity<Object>(requestType, headers);
//            }
//        } else
//            return new HttpEntity<Object>(headers);
//    }
//
//
//    public String getrest(String url) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        ResponseEntity<String> response = null;
//        HttpMethod requestMethod = HttpMethod.GET;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        headers.add( "Cookie", tokenGenerator.getToken());
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(null, headers);
//        response= restTemplate.exchange(url, requestMethod, requestEntity, String.class);
//        System.out.println("response:"+response);
//        JSONObject jsonObject = new JSONObject(response.getBody());
//
//        return jsonObject.toString();
//
//    }
//
//    public String biometricsApi(String http_url, String methodType, String request) throws IOException {
//
//        logger.info("biometricsApi Api called");
//        OutputStream ouputStream = null;
//        String biometricsApiFieldResponse = null;
//        JSONArray searchField = null;
//        JSONObject segment=null;
//        byte[] faceByteArray=null;
//        try {
//            URLConnection url = new URL(http_url).openConnection();
//            url.setRequestProperty("Content-Type", "application/json");
//            url.setRequestProperty("Accept", "application/json");
//            url.setDoOutput(true);
//            url.setRequestProperty("Cookie", tokenGenerator.getToken());
//            ouputStream = url.getOutputStream();
//            ouputStream.write(request.getBytes());
//            ouputStream.flush();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(url.getInputStream()));
//            String tmpStr = null;
//            JSONParser jsonParser = new JSONParser();
//            while ((tmpStr = reader.readLine()) != null) {
//                biometricsApiFieldResponse = tmpStr;
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
//            throw new RuntimeException(e);
//        }
//        return  biometricsApiFieldResponse;
//    }
//
//
//    public String gethttp(String http_url, String methodType, String connectionType) throws IOException {
//        OutputStream ouputStream = null;
//        String searchFieldResponse = null;
//        JSONArray searchField = null;
//        JSONObject segment = null;
//        byte[] faceByteArray = null;
//        JSONObject jsonObject = null;
//        try {
//            URLConnection url = new URL(http_url).openConnection();
//            url.setRequestProperty("Content-Type", "application/json");
//            url.setRequestProperty("Accept", "application/json");
//            url.setDoOutput(true);
//            url.setRequestProperty("Cookie", "Authorization=" + TOKEN);
//            url.setRequestProperty("Cookie", tokenGenerator.getToken());
//            BufferedReader reader = new BufferedReader(new InputStreamReader(url.getInputStream()));
//            String tmpStr = null;
//            while ((tmpStr = reader.readLine()) != null) {
//                searchFieldResponse = tmpStr;
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
//            throw new RuntimeException(e);
//        }
//        return searchFieldResponse;
//    }
//
//    public static void sslOff() throws KeyManagementException, NoSuchAlgorithmException {
//        final SSLContext sc = SSLContext.getInstance("SSL");
//        sc.init(null, UNQUESTIONING_TRUST_MANAGER, null);
//        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//        // Create all-trusting host name verifier
//        HostnameVerifier allHostsValid = new HostnameVerifier() {
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        };
//        // Install the all-trusting host verifier
//        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//    }
//
//    public static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[] { new X509TrustManager() {
//        @Override
//        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//        }
//        public X509Certificate[] getAcceptedIssuers() {
//            return null;
//        }
//        /* (non-Javadoc)
//         * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
//         */
//        @Override
//        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
//                throws CertificateException {
//            // TODO Auto-generated method stub
//        }
//    } };
//}
