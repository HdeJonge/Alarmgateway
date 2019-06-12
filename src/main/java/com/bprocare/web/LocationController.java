package com.bprocare.web;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bprocare.domain.Location;
import com.bprocare.service.LocationService;
import com.bprocare.web.dto.LocationDto;
import com.bprocare.web.dto.LocationDtoChange;

@Controller
public class LocationController {

	private static final String UNEXPECTED_ERROR = null;

	private MessageSource messageSource;
	
	public LocationController() {

	}

	@Autowired
	LocationService locationService;

    @Autowired
    private ModelMapper modelMapper;
	
    
  
    @GetMapping("/location")
    public String getAllLocation(Model model) {
    	
        return "location";
    } 

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }
	 
    
	@GetMapping("/location/add")
	public String addLocation(Model model) {

		model.addAttribute("location", new LocationDto());
		model.addAttribute("labelslist", locationService.getAllLabels());

		return "location_add";
	}

	@GetMapping("/location/edit/{id}")
	public String changeLocation(@PathVariable("id") Long id, Model model) {

		Location locationFromDb = locationService.getLocation(id);
		LocationDtoChange locationDtoChange = modelMapper.map(locationFromDb, LocationDtoChange.class);
			
		model.addAttribute("location", locationDtoChange);
		model.addAttribute("labelslist", locationService.getAllLabels());

		return "location_change";
	}

	// create location
	@PostMapping("/addlocation")
	public String createLocation(@Valid @ModelAttribute("location") LocationDto locationdto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("labelslist", locationService.getAllLabels());
			return "location_add";
		}

		Location location = modelMapper.map(locationdto, Location.class);
		locationService.createLocation(location);

		model.addAttribute("locations", locationService.getAllLocations());

		return "redirect:/location";
	}

	// change location
	@PostMapping("/changelocation/{id}")
	public String changeLocation(@PathVariable("id") Long id, @Valid @ModelAttribute("location") LocationDtoChange locationdtochange, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("labelslist", locationService.getAllLabels());
			return "location_change";
		}
		
		Location locationToDb = modelMapper.map(locationdtochange, Location.class);
		locationService.changeLocation(locationToDb);
			
		model.addAttribute("locations", locationService.getAllLocations());

		return "redirect:/location";
	}

	// delete location
	@GetMapping("/location/delete/{id}")
	public String deleteLocation(@PathVariable("id") Long id, Model model) {

		try {
  		  locationService.deleteLocation(id);
		} catch (Exception ex)
		{
			model.addAttribute("error", "Kan locatie niet verwijderen");
			return "location";
		}

		model.addAttribute("locations", locationService.getAllLocations());
		return "redirect:/location";
	}


	
}
