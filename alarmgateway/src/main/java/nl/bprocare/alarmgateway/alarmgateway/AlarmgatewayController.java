package nl.bprocare.alarmgateway.alarmgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@GetMapping("gateways/add")
	public String addGateway(Model model) {
		model.addAttribute("gateway", new Alarmgateway());
		model.addAttribute("locations", locationService.getAllLocations());
		return "addAlarmgateway";
	}
	@PostMapping("gateways/save")
	public String saveGateway(@ModelAttribute Alarmgateway alarmgateway, Model model) {
		alarmgatewayService.saveAlarmgateway(alarmgateway);
		model.addAttribute("gateways", alarmgatewayService.getAllAlarmgateways());
		return "alarmgateways";
	}

}
