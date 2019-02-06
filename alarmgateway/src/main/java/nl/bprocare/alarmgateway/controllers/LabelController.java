package nl.bprocare.alarmgateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nl.bprocare.alarmgateway.service.LabelService;

@Controller
public class LabelController {
	
	@Autowired
	private LabelService labelService;
	
	@GetMapping("labels")
	public String getLocations(Model model) {
		model.addAttribute("labels", labelService.getAllLabels());
		return "labels";
	}
}
