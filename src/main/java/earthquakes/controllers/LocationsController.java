package earthquakes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.HashMap;

import com.nimbusds.oauth2.sdk.client.ClientReadRequest;

import earthquakes.services.EarthquakeQuery;
import earthquakes.searches.LocSearch;
import earthquakes.geojson.FeatureCollection;

@Controller
public class LocationsController {

    @GetMapping("/location/search")
    public String getLocationSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken, LocSearch locSearch) {
        return "location/search";
    }

    @GetMapping("/location/results")
    public String getLocationResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken, LocSearch locSearch) {

        LocationQuery l = new LocationQuery();

        model.addAttribute("locSearch", locSearch);

        String json = l.getJSON(locSearch.getLocation());
        model.addAttribute("json", json);
        return "location/results";
    }
}
