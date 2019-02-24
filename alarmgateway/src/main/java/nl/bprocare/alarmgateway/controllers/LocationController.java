package nl.bprocare.alarmgateway.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import nl.bprocare.alarmgateway.dto.LabelDTO;
import nl.bprocare.alarmgateway.dto.LocationDTO;
import nl.bprocare.alarmgateway.pojo.Label;
import nl.bprocare.alarmgateway.pojo.Location;
import nl.bprocare.alarmgateway.service.LabelService;
import nl.bprocare.alarmgateway.service.LocationService;

@Controller
@RequestMapping("private/locations")
public class LocationController {

	private MapperFacade mapper;

	@Autowired
	private LocationService locationService;

	@Autowired
	private LabelService labelService;

	public LocationController() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(LocationDTO.class, Location.class).byDefault();
		mapperFactory.classMap(Location.class, LocationDTO.class).byDefault();
		mapper = mapperFactory.getMapperFacade();
	}

	@GetMapping("restlocations")
	public String getRestLocations(Model model) {
		return "private/locations/restLocations";
	}

	@GetMapping("locations")
	public String getLocations(Model model) {
		/* getting */
		List<Location> locationsDto = locationService.getAllLocations();
		/* mapping */
		List<LocationDTO> locations = mapper.mapAsList(locationsDto, LocationDTO.class);
		model.addAttribute("locations", locations);
		return "private/locations/restlocations";
	}

	@GetMapping("addLocation")
	public String addLocation(Model model) {
		
		 model.addAttribute("locationDTO", new LocationDTO());
		  
		  List<Label> labels= labelService.getAllLabels();
		  
		  List<LabelDTO> labelsDTO = mapper.mapAsList(labels, LabelDTO.class);
		  model.addAttribute("labelsDTO", labelsDTO); return
		  "private/locations/addLocation";

	}

	@PostMapping("saveLocation")
	public String saveLocation(@Valid Location locationDTO, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "private/locations/addLocation";
		}
		Location location = mapper.map(locationDTO, Location.class);
		locationService.saveLocation(location);
		return "redirect:/private/locations/locations";

	}

	@GetMapping("editLocation/{id}")
	public String editLocation(@PathVariable(value = "id") Long id, Model model) {
		/* getting */
		Location locationDto = locationService.getLocation(id);
		/* mapping */
		LocationDTO location = mapper.map(locationDto, LocationDTO.class);
		model.addAttribute("location", location);
		/* getting */
		List<Label> labelsDto = labelService.getAllLabels();
		/* mapping */
		List<LabelDTO> labelsDTO = mapper.mapAsList(labelsDto, LabelDTO.class);
		
		model.addAttribute("labelsDTO", labelsDTO);
		return "private/locations/editLocation";

	}

	@PostMapping("updateLocation/{id}")
	public String updateLocation(@PathVariable(value = "id") Long id, @Valid LocationDTO location, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "private/locations/editLocation";
		}
		/* mapping */
		Location locationDto = mapper.map(location, Location.class);
		locationService.updateLocation(locationDto);
		return "redirect:/private/locations/locations";
	}

	@GetMapping("deleteLocation/{id}")
	public String deleteLocation(@PathVariable(value = "id") Long noteId, Model model) {
		locationService.deleteLocation(noteId);
		return "redirect:/private/locations/restlocations";
	}
}
