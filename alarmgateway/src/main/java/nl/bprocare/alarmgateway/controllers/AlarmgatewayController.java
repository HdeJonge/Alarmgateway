package nl.bprocare.alarmgateway.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import nl.bprocare.alarmgateway.dto.AlarmgatewayDto;
import nl.bprocare.alarmgateway.dto.LocationDto;
import nl.bprocare.alarmgateway.pojo.Alarmgateway;
import nl.bprocare.alarmgateway.pojo.Location;
import nl.bprocare.alarmgateway.service.AlarmgatewayService;
import nl.bprocare.alarmgateway.service.LocationService;

@Controller
@RequestMapping("private/alarmgateways")
public class AlarmgatewayController {
	
	private  MapperFacade mapper;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private AlarmgatewayService alarmgatewayService;
	
	public AlarmgatewayController() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	    mapperFactory.classMap(Alarmgateway.class, AlarmgatewayDto.class).byDefault();
	    mapperFactory.classMap(AlarmgatewayDto.class, Alarmgateway.class).byDefault();
	    mapperFactory.classMap(Location.class, LocationDto.class).byDefault();
	    mapperFactory.classMap(LocationDto.class, Location.class).byDefault();
	    mapper = mapperFactory.getMapperFacade();
	}
	@GetMapping("restgateways")
	public String getRestgateways(Model model) {
		return "private/alarmgateways/restgateways";
	}
	
	@GetMapping("alarmgateways")
	public String getAllAlarmgateways(Model model) {
		/*getting*/
		List<AlarmgatewayDto> alarmgatewaysDto = alarmgatewayService.getAllAlarmgateways();
		/*mapping*/
		Alarmgateway alarmGateways = mapper.map(alarmgatewaysDto, Alarmgateway.class);
		model.addAttribute("gateways", alarmGateways);
		return "private/alarmgateways/restgateways";
	}
	
	@GetMapping("addGateway")
	public String addGateway(Model model) {
		model.addAttribute("gateway", new Alarmgateway());
		/*getting*/
		List<LocationDto> locationsDto = locationService.getAllLocations();
		/*mapping*/
		List<Location> locations = mapper.mapAsList(locationsDto, Location.class);
		model.addAttribute("locations",locations );
		return "private/alarmgateways/addAlarmgateway";
	}
	@PostMapping("saveGateway")
	public String saveGateway(@ModelAttribute Alarmgateway alarmgateway, Model model) {
		/*mapping*/
		AlarmgatewayDto alarmGatewayDto = mapper.map(alarmgateway, AlarmgatewayDto.class);
		alarmgatewayService.saveAlarmgateway(alarmGatewayDto);
		return "redirect:/private/alarmgateways/restgateways";
	}

	@GetMapping("editGateway/{id}")
	public String editGateways(@PathVariable(value="id")Long id, Model model){
		/*getting*/
		AlarmgatewayDto alarmgatewayDto = alarmgatewayService.getAlarmgateway(id);
		/*mapping*/
		Alarmgateway alarmgateway = mapper.map(alarmgatewayDto, Alarmgateway.class);
		model.addAttribute("gateway",alarmgateway);
		/*getting*/
		List<LocationDto> locationsDto = locationService.getAllLocations();
		/*mapping*/
		Location locations = mapper.map(locationsDto, Location.class);
		model.addAttribute("locations", locations);
		return "private/alarmgateways/editAlarmgateway";
	}
	@PostMapping("updateGateway/{id}")
	public String updateGateway(@PathVariable (value="id")Long id, @Valid Alarmgateway alarmgateway, Model model) {
		/*mapping*/
		AlarmgatewayDto alarmGatewayDto = mapper.map(alarmgateway, AlarmgatewayDto.class);
		alarmgatewayService.saveAlarmgateway(alarmGatewayDto);
		return "redirect:/private/alarmgateways/restgateways";
	}
	
	@GetMapping("deleteGateway/{id}")
	public String deleteGateway(@PathVariable(value="id")Long id, Model model){
		alarmgatewayService.deleteAlarmgateway(id);
		return "redirect:/private/alarmgateways/restgateways";
	}
}
