package nl.bprocare.alarmgateway.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import nl.bprocare.alarmgateway.dto.CreateAlarmgatewayDTO;
import nl.bprocare.alarmgateway.dto.EditAlarmgatewayDTO;
import nl.bprocare.alarmgateway.dto.EditLocationDTO;
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
	    mapperFactory.classMap(EditAlarmgatewayDTO.class, Alarmgateway.class).byDefault();
	    mapperFactory.classMap(Alarmgateway.class, EditAlarmgatewayDTO.class).byDefault();
	    mapperFactory.classMap(EditLocationDTO.class, Location.class).byDefault();
	    mapperFactory.classMap(Location.class, EditLocationDTO.class).byDefault();
	    mapper = mapperFactory.getMapperFacade();
	}
	@GetMapping("restgateways")
	public String getRestgateways(Model model) {
		return "private/alarmgateways/restgateways";
	}
	
	@GetMapping("alarmgateways")
	public String getAllAlarmgateways(Model model) {
		/*getting*/
		List<Alarmgateway> alarmgatewaysDto = alarmgatewayService.getAllAlarmgateways();
		/*mapping*/
		EditAlarmgatewayDTO alarmGateways = mapper.map(alarmgatewaysDto, EditAlarmgatewayDTO.class);
		model.addAttribute("gateways", alarmGateways);
		return "private/alarmgateways/restgateways";
	}
	
	@GetMapping("addGateway")
	public String addGateway(Model model) {
		model.addAttribute("createAlarmgatewayDTO", new CreateAlarmgatewayDTO());
		/*getting*/
		List<Location> locations = locationService.getAllLocations();
		/*mapping*/
		List<EditLocationDTO> locationDTO = mapper.mapAsList(locations, EditLocationDTO.class);
		model.addAttribute("locationsDTO",locationDTO );
		return "private/alarmgateways/addAlarmgateway";
	}
	@PostMapping("saveGateway")
	public String saveGateway(@Valid CreateAlarmgatewayDTO alarmgateway, BindingResult result, Model model) {
        if (result.hasErrors()) {
    		List<Location> locations = locationService.getAllLocations();
    		List<EditLocationDTO> locationDTO = mapper.mapAsList(locations, EditLocationDTO.class);
    		model.addAttribute("locationsDTO",locationDTO );
            return "private/alarmgateways/addAlarmgateway";
        }
		/*mapping*/
		Alarmgateway alarmGatewayDto = mapper.map(alarmgateway, Alarmgateway.class);
		alarmgatewayService.saveAlarmgateway(alarmGatewayDto);
		return "redirect:/private/alarmgateways/restgateways";
	}

	@GetMapping("editGateway/{id}")
	public String editGateways(@PathVariable(value="id")Long id, Model model){
		/*getting*/
		Alarmgateway alarmgateway = alarmgatewayService.getAlarmgateway(id);
		/*mapping*/
		EditAlarmgatewayDTO alarmgatewayDTO = mapper.map(alarmgateway, EditAlarmgatewayDTO.class);
		model.addAttribute("alarmgatewayDTO",alarmgatewayDTO);
		/*getting*/
		List<Location> locations = locationService.getAllLocations();
		/*mapping*/
		List<EditLocationDTO> locationsDTO = mapper.mapAsList(locations, EditLocationDTO.class);
		model.addAttribute("locationsDTO", locationsDTO);
		return "private/alarmgateways/editAlarmgateway";
	}
	@PostMapping("updateGateway/{id}")
	public String updateGateway(@PathVariable (value="id")Long id, @Valid EditAlarmgatewayDTO alarmgatewayDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
    		List<Location> locations = locationService.getAllLocations();
    		List<EditLocationDTO> locationDTO = mapper.mapAsList(locations, EditLocationDTO.class);
    		model.addAttribute("locationsDTO",locationDTO );
            return "private/alarmgateways/addAlarmgateway";
        }
		
		/*mapping*/
		Alarmgateway alarmGateway = mapper.map(alarmgatewayDTO, Alarmgateway.class);
		alarmgatewayService.saveAlarmgateway(alarmGateway);
		return "redirect:/private/alarmgateways/restgateways";
	}
	
	@GetMapping("deleteGateway/{id}")
	public String deleteGateway(@PathVariable(value="id")Long id, Model model){
		alarmgatewayService.deleteAlarmgateway(id);
		return "redirect:/private/alarmgateways/restgateways";
	}
}
