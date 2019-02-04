package nl.bprocare.alarmgateway.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping("/locations")
	public String getLocations(Model model) {
		locationService.getAllLocations();
		return "locations";
	}

}
