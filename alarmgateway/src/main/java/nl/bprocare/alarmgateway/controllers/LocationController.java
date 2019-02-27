package nl.bprocare.alarmgateway.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import nl.bprocare.alarmgateway.dto.CreateLocationDTO;
import nl.bprocare.alarmgateway.dto.LabelDTO;
import nl.bprocare.alarmgateway.dto.EditLocationDTO;
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
		mapperFactory.classMap(EditLocationDTO.class, Location.class).byDefault();
		mapperFactory.classMap(CreateLocationDTO.class, Location.class).byDefault();
		mapperFactory.classMap(Location.class, EditLocationDTO.class).byDefault();
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
		List<EditLocationDTO> locations = mapper.mapAsList(locationsDto, EditLocationDTO.class);
		model.addAttribute("locations", locations);
		return "private/locations/restlocations";
	}

	@GetMapping("addLocation")
	public String addLocation(Model model) {
		 model.addAttribute("createLocationDTO", new CreateLocationDTO());
		  List<Label> labels= labelService.getAllLabels();
		  List<LabelDTO> labelsDTO = mapper.mapAsList(labels, LabelDTO.class);
		  model.addAttribute("labelsDTO", labelsDTO); 
		  return "private/locations/addLocation";
	}

	@PostMapping("saveLocation")
	public String saveLocation(@Valid CreateLocationDTO createLocationDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("createLocationDTO", createLocationDTO);
			/* getting */
			List<Label> labelsDto = labelService.getAllLabels();
			/* mapping */
			List<LabelDTO> labelsDTO = mapper.mapAsList(labelsDto, LabelDTO.class);
			model.addAttribute("labelsDTO", labelsDTO);
			return "private/locations/addLocation";
		}
		Location location = mapper.map(createLocationDTO, Location.class);
		locationService.saveLocation(location);
		return "redirect:/private/locations/locations";
	}

	@GetMapping("editLocation/{id}")
	public String editLocation(@PathVariable(value = "id") Long id, Model model) {
		/* getting */
		Location locationDto = locationService.getLocation(id);
		/* mapping */
		EditLocationDTO locationDTO = mapper.map(locationDto, EditLocationDTO.class);
		model.addAttribute("locationDTO", locationDTO);
		/* getting */
		List<Label> labelsDto = labelService.getAllLabels();
		/* mapping */
		List<LabelDTO> labelsDTO = mapper.mapAsList(labelsDto, LabelDTO.class);
		
		model.addAttribute("labelsDTO", labelsDTO);
		return "private/locations/editLocation";

	}

	@PostMapping("updateLocation/{id}")
	public String updateLocation(@PathVariable(value = "id") Long id, @Valid EditLocationDTO locationDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("locationDTO", locationDTO);
			/* getting */
			List<Label> labelsDto = labelService.getAllLabels();
			/* mapping */
			List<LabelDTO> labelsDTO = mapper.mapAsList(labelsDto, LabelDTO.class);
			model.addAttribute("labelsDTO", labelsDTO);
			return "private/locations/editLocation";
		}
		/* mapping */
		Location locationDto = mapper.map(locationDTO, Location.class);
		locationService.updateLocation(locationDto);
		return "redirect:/private/locations/locations";
	}

	@GetMapping("deleteLocation/{id}")
	public String deleteLocation(@PathVariable(value = "id") Long noteId, Model model) {
		try {
		locationService.deleteLocation(noteId);
		}catch(DataIntegrityViolationException e){
			model.addAttribute("error", "cannot delete location, location is must likely in use");
			return "private/locations/restlocations";
		}
		return "redirect:/private/locations/restlocations";
	}
}
