package nl.bprocare.alarmgateway.controllers;

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
import nl.bprocare.alarmgateway.dto.LabelDto;
import nl.bprocare.alarmgateway.dto.LocationDto;
import nl.bprocare.alarmgateway.pojo.Label;
import nl.bprocare.alarmgateway.pojo.Location;
import nl.bprocare.alarmgateway.service.LabelService;


@Controller
@RequestMapping("/private")
public class LabelController {
	
	private  MapperFacade mapper;
	
	@Autowired
	private LabelService labelService;
	
	public LabelController() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	    mapperFactory.classMap(Label.class, LabelDto.class).byDefault();
	    mapperFactory.classMap(LabelDto.class, Label.class).byDefault();
	    mapper = mapperFactory.getMapperFacade();
	}
	@GetMapping("labels")
	public String getLocations(Model model) {
		return "private/labels/labels";
	}
	@GetMapping("addLabel")
	public String addLabel(Model model) {
		model.addAttribute("label", new LabelDto());
		return "private/labels/addLabel";
	}
	@PostMapping("saveLabel")
	public String saveLabel(@ModelAttribute LabelDto label, Model model) {
		/*mapping*/
		LabelDto labelDto = mapper.map(label, LabelDto.class);
		labelService.saveLabel(labelDto);
		return "redirect:/private/labels";
	}
	@GetMapping("editLabel/{id}")
	public String editLabel(@PathVariable(value="id") Long id, Model model) {
		/*getting*/
		LabelDto label = labelService.getLabel(id);
		/*mapping*/
		LabelDto labelDto = mapper.map(label, LabelDto.class);
		model.addAttribute("label",labelDto );
		return "private/labels/editLabel";
	}
	@PostMapping("updateLabel/{id}")
	public String updateLabel(@ModelAttribute LabelDto label) {
		/*mapping*/
		LabelDto labelDto = mapper.map(label, LabelDto.class);
		labelService.saveLabel(label);
		return "redirect:/private/labels";
	}
	@GetMapping("deleteLabel/{id}")
	public String deleteLabel(@PathVariable(value="id") Long id) {
		labelService.deleteLabel(id);
		return "redirect:/private/labels";
	}
}
