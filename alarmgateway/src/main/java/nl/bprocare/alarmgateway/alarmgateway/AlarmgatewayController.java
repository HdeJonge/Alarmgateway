package nl.bprocare.alarmgateway.alarmgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import nl.bprocare.alarmgateway.location.LocationService;

@Controller
public class AlarmgatewayController {
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private AlarmgatewayService alarmgatewayService;
	
	@GetMapping("/gateways")
	public String getAllAlarmgateways(Model model) {
		model.addAttribute("gateways", alarmgatewayService.getAllAlarmgateways());
		return "alarmgateways";
	}
	
	@GetMapping("/addGateway")
	public String addGateway(Model model) {
		model.addAttribute("gateway", new Alarmgateway());
		model.addAttribute("locations", locationService.getAllLocations());
		return "addAlarmgateway";
	}
	@PostMapping("/saveGateway")
	public String saveGateway(@ModelAttribute Alarmgateway alarmgateway, Model model) {
		alarmgatewayService.saveAlarmgateway(alarmgateway);
		model.addAttribute("gateways", alarmgatewayService.getAllAlarmgateways());

		return "alarmgateways";
	}
	@GetMapping("/editGateway/{id}")
	public String editGateways(@PathVariable(value="id")Long id, Model model){
		model.addAttribute("gateway",alarmgatewayService.getAlarmgateway(id));
		model.addAttribute("locations", locationService.getAllLocations());
		return "editAlarmgateway";
	}
	
	@GetMapping("/deleteGateway/{id}")
	public String deleteGateway(@PathVariable(value="id")Long id, Model model){
		alarmgatewayService.deleteAlarmgateway(id);
		model.addAttribute("gateways", alarmgatewayService.getAllAlarmgateways());
		return "alarmgateways";
	}
}
