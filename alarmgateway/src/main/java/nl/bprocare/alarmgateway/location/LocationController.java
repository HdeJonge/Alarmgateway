package nl.bprocare.alarmgateway.location;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@GetMapping("/locations")
	public String getLocations(Model model) {
		model.addAttribute("locations",locationService.getAllLocations());
		return "locations";
		
	}
	
	@GetMapping("/locationForm")
	public String addLocation(Model model) {
		model.addAttribute("location", new Location());
		return "locationForm";
		
	}
	@PostMapping("/saveLocation")
	public String saveLocation(@ModelAttribute Location location, Model model) {
		
		locationService.saveLocation(location);
		model.addAttribute("locations",locationService.getAllLocations());
		return "locations";
		
	}
}
