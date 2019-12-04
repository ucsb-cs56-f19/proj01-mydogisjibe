package earthquakes.services;

import java.util.Arrays;


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

public class EarthquakeQuery {

    private Logger logger = LoggerFactory.getLogger(EarthquakeQuery.class);

    public String getJSON(int distance, int minmag) {
      String fakeJson = "{ \"key\": \"value\" }";
      String json = fakeJson;
      logger.info("json=" + json);
      return json;
    }

}
