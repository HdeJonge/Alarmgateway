package nl.bprocare.alarmgateway.location;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@GetMapping("locations")
	public String getLocations(Model model) {
		model.addAttribute("locations",locationService.getAllLocations());
		return "locations";
	}
	
	@GetMapping("addLocation")
	public String addLocation(Model model) {
		model.addAttribute("location", new Location());
		return "addLocation";
		
	}
	@PostMapping("saveLocation")
	public String saveLocation(@ModelAttribute Location location, Model model) {
		
		locationService.saveLocation(location);
		model.addAttribute("locations",locationService.getAllLocations());
		return "locations";
	}
	
	@GetMapping("editLocation/{id}")
	public String editLocation(@PathVariable (value="id")Long id, Model model) {
		model.addAttribute("location", locationService.getLocation(id));
		return "redirect:/locations";
		
	}
	@PostMapping("updateLocation/{id}")
	public String updateLocation(@PathVariable (value="id")Long id, @Valid Location location, Model model) {
		System.out.println(location);
		locationService.updateLocation(location);
		model.addAttribute("locations",locationService.getAllLocations());
		return "redirect:/locations";
	}
	@GetMapping("deleteLocation/{id}")
	public String deleteLocation(@PathVariable(value="id") Long noteId, Model model) {
		locationService.deleteLocation(noteId);
		model.addAttribute("locations",locationService.getAllLocations());
		return "redirect:/locations";
	}
}
