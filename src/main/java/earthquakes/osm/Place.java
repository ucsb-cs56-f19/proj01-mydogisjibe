package earthquakes.osm;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

public class Place {
  private static Logger logger = LoggerFactory.getLogger(Place.class);
  public long place_id;
  public double lat, lon;
  public String display_name, type;

 /**
     * Create a Place object from json representation
     * 
     * @param json String of json returned by API endpoint {@code /classes/search}
     * @return a new Place object
     * @see <a href=
     *      "https://tools.ietf.org/html/rfc7946">https://tools.ietf.org/html/rfc7946</a>
     */
    public static List<Place> listFromJSON(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            List<Place> places = objectMapper.readValue(json, new TypeReference<List<Place>>(){});
            return places;
        } catch (JsonProcessingException jpe) {
            logger.error("JsonProcessingException:" + jpe);
            return null;
        } catch (Exception e) {
            logger.error("Exception:" + e);
            return null;
        }
    }
}
