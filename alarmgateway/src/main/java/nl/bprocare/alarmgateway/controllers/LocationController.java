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
import nl.bprocare.alarmgateway.dto.LabelDto;
import nl.bprocare.alarmgateway.dto.LocationDto;
import nl.bprocare.alarmgateway.pojo.Label;
import nl.bprocare.alarmgateway.pojo.Location;
import nl.bprocare.alarmgateway.service.LabelService;
import nl.bprocare.alarmgateway.service.LocationService;

@Controller
@RequestMapping("private/locations")
public class LocationController {

	private  MapperFacade mapper;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private LabelService labelService;
	
	public LocationController() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	    mapperFactory.classMap(Location.class, LocationDto.class).byDefault();
	    mapperFactory.classMap(LocationDto.class, Location.class).byDefault();
	    mapper = mapperFactory.getMapperFacade();
	}
	
	@GetMapping("restlocations")
	public String getRestLocations(Model model) {
		return "private/locations/restLocations";
	}
	@GetMapping("locations")
	public String getLocations(Model model) {
		/*getting*/
		List<LocationDto> locationsDto = locationService.getAllLocations();
		/* mapping */
		List<Location> locations = mapper.mapAsList(locationsDto, Location.class);
		model.addAttribute("locations",locations);
		return "private/locations/restlocations";
	}
	
	@GetMapping("addLocation")
	public String addLocation(Model model) {
		model.addAttribute("location", new Location());
		/*getting*/
		List<LabelDto> labelsDto= labelService.getAllLabels();
		/* mapping */
		List<Label> labels = mapper.mapAsList(labelsDto, Label.class);
		model.addAttribute("labels", labels);
		return "private/locations/addLocation";
		
	}
	@PostMapping("saveLocation")
	public String saveLocation(@Valid Location location, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "private/locations/addLocation";
        }
        /*mapping*/
        LocationDto locationDto= mapper.map(location, LocationDto.class);
		locationService.saveLocation(locationDto);
		return "redirect:/private/locations/locations";
	}
	
	@GetMapping("editLocation/{id}")
	public String editLocation(@PathVariable (value="id")Long id, Model model) {
		/*getting*/
		LocationDto locationDto = locationService.getLocation(id);
		/*mapping*/
		Location location = mapper.map(locationDto, Location.class);
		model.addAttribute("location", location);
		/*getting*/
		List<LabelDto> labelsDto = labelService.getAllLabels();
		/*mapping*/
		List<Label> locations = mapper.mapAsList(labelsDto, Label.class);
		model.addAttribute("labels", locations);
		return "private/locations/editLocation";
		
	}
	@PostMapping("updateLocation/{id}")
	public String updateLocation(@PathVariable (value="id")Long id, @Valid Location location, Model model) {
        /*mapping*/
		LocationDto locationDto= mapper.map(location, LocationDto.class);
		locationService.updateLocation(locationDto);
		return "redirect:/private/locations/locations";
	}
	
	@GetMapping("deleteLocation/{id}")
	public String deleteLocation(@PathVariable(value="id") Long noteId, Model model) {
		locationService.deleteLocation(noteId);
		return "redirect:/private/locations/restlocations";
	}
}
