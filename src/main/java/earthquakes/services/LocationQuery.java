package earthquakes.services;

import java.util.Arrays;
import java.net.URLEncoder;
import earthquakes.services.EarthquakeQuery;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

public class LocationQuery {

    private Logger logger = LoggerFactory.getLogger(EarthquakeQuery.class);

    public String getJSON(String location) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://nominatim.openstreetmap.org/search/";
        String retVal="";
        try {
            String params = String.format("%s/?format=json",
                URLEncoder.encode(location, StandardCharsets.UTF_8.toString())
            );

            String url = uri + params;
            logger.info("url=" + url);

            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
             MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        } catch(UnsupportedEncodingException e) {
            logger.error("Unsupported Encoding Exception Received");
        }
        logger.info("from LocationQuery.getJSON: " + retVal);
        return retVal;
    }

}
