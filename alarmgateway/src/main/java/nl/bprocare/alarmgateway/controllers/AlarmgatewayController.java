package nl.bprocare.alarmgateway.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.bprocare.alarmgateway.domain.Alarmgateway;
import nl.bprocare.alarmgateway.domain.Location;
import nl.bprocare.alarmgateway.service.AlarmgatewayService;
import nl.bprocare.alarmgateway.service.LocationService;

@Controller
@RequestMapping("private/alarmgateways")
public class AlarmgatewayController {
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private AlarmgatewayService alarmgatewayService;
	
	
	@GetMapping("restgateways")
	public String getRestgateways(Model model) {
		return "private/alarmgateways/restgateways";
	}
	
	@GetMapping("alarmgateways")
	public String getAllAlarmgateways(Model model) {
		model.addAttribute("gateways", alarmgatewayService.getAllAlarmgateways());
		return "private/alarmgateways/restgateways";
	}
	
	@GetMapping("addGateway")
	public String addGateway(Model model) {
		model.addAttribute("gateway", new Alarmgateway());
		model.addAttribute("locations", locationService.getAllLocations());
		return "private/alarmgateways/addAlarmgateway";
	}
	@PostMapping("saveGateway")
	public String saveGateway(@ModelAttribute Alarmgateway alarmgateway, Model model) {
		alarmgatewayService.saveAlarmgateway(alarmgateway);
		return "redirect:/private/alarmgateways/restgateways";
	}
	@GetMapping("editGateway/{id}")
	public String editGateways(@PathVariable(value="id")Long id, Model model){
		model.addAttribute("gateway",alarmgatewayService.getAlarmgateway(id));
		model.addAttribute("locations", locationService.getAllLocations());
		return "private/alarmgateways/editAlarmgateway";
	}
	@PostMapping("updateGateway/{id}")
	public String updateGateway(@PathVariable (value="id")Long id, @Valid Alarmgateway alarmgateway, Model model) {
		alarmgatewayService.saveAlarmgateway(alarmgateway);
		return "redirect:/private/alarmgateways/restgateways";
	}
	
	@GetMapping("deleteGateway/{id}")
	public String deleteGateway(@PathVariable(value="id")Long id, Model model){
		alarmgatewayService.deleteAlarmgateway(id);
		model.addAttribute("gateways", alarmgatewayService.getAllAlarmgateways());
		return "redirect:/private/alarmgateways/restgateways";
	}
}
