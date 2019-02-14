package nl.bprocare.alarmgateway.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nl.bprocare.alarmgateway.domain.Label;
import nl.bprocare.alarmgateway.service.LabelService;


@Controller
@RequestMapping("/private")
public class LabelController {
	
	@Autowired
	private LabelService labelService;
	
	@GetMapping("labels")
	public String getLocations(Model model) {
		return "private/labels/labels";
	}
	@GetMapping("addLabel")
	public String addLabel(Model model) {
		model.addAttribute("label", new Label());
		return "private/labels/addLabel";
	}
	@PostMapping("saveLabel")
	public String saveLabel(@ModelAttribute Label label, Model model) {
		labelService.saveLabel(label);
		return "redirect:/private/labels";
	}
	@GetMapping("editLabel/{id}")
	public String editLabel(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("label", labelService.getLabel(id));
		return "private/labels/editLabel";
	}
	@PostMapping("updateLabel/{id}")
	public String updateLabel(@ModelAttribute Label label) {
		labelService.saveLabel(label);
		return "redirect:/private/labels";
	}
	@GetMapping("deleteLabel/{id}")
	public String deleteLabel(@PathVariable(value="id") Long id) {
		labelService.deleteLabel(id);
		return "redirect:/private/labels";
	}
}
