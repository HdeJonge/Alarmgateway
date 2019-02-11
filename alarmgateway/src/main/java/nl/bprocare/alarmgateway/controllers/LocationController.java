package nl.bprocare.alarmgateway.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.bprocare.alarmgateway.domain.Location;
import nl.bprocare.alarmgateway.service.LabelService;
import nl.bprocare.alarmgateway.service.LocationService;

@Controller
@RequestMapping("private/locations")
public class LocationController {

	@Autowired
	private LocationService locationService;
	

	@Autowired
	private LabelService labelService;
	
	@GetMapping("locations")
	public String getLocations(Model model) {
		model.addAttribute("locations",locationService.getAllLocations());
		return "private/locations/locations";
	}
	
	@GetMapping("addLocation")
	public String addLocation(Model model) {
		model.addAttribute("location", new Location());
		model.addAttribute("labels", labelService.getAllLabels());
		return "private/locations/addLocation";
		
	}
	@PostMapping("saveLocation")
	public String saveLocation(@ModelAttribute Location location, Model model) {
		
		locationService.saveLocation(location);
		model.addAttribute("locations",locationService.getAllLocations());
		return "redirect:/private/locations/locations";
	}
	
	@GetMapping("editLocation/{id}")
	public String editLocation(@PathVariable (value="id")Long id, Model model) {
		model.addAttribute("location", locationService.getLocation(id));
		model.addAttribute("labels", labelService.getAllLabels());
		return "private/locations/editLocation";
		
	}
	@PostMapping("updateLocation/{id}")
	public String updateLocation(@PathVariable (value="id")Long id, @Valid Location location, Model model) {
		locationService.updateLocation(location);
		model.addAttribute("locations",locationService.getAllLocations());
		return "redirect:/private/locations/locations";
	}
	@GetMapping("deleteLocation/{id}")
	public String deleteLocation(@PathVariable(value="id") Long noteId, Model model) {
		locationService.deleteLocation(noteId);
		model.addAttribute("locations",locationService.getAllLocations());
		return "redirect:/private/locations/locations";
	}
}
