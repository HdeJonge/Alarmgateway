package nl.bprocare.alarmgateway.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import nl.bprocare.alarmgateway.dto.CreateLabelDTO;
import nl.bprocare.alarmgateway.dto.EditLabelDTO;
import nl.bprocare.alarmgateway.dto.EditLocationDTO;
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
	    mapperFactory.classMap(EditLabelDTO.class, Label.class).byDefault();
	    mapperFactory.classMap(Label.class, EditLabelDTO.class).byDefault();
	    mapperFactory.classMap(CreateLabelDTO.class, Label.class).byDefault();
	    mapperFactory.classMap(Label.class, CreateLabelDTO.class).byDefault();
	    mapper = mapperFactory.getMapperFacade();
	}
	@GetMapping("labels")
	public String getLocations(Model model) {
		model.addAttribute("test", "test");
		return "private/labels/labels";
	}
	@GetMapping("addLabel")
	public String addLabel(Model model) {
		model.addAttribute("createLabelDTO", new CreateLabelDTO());
		return "private/labels/addLabel";
	}
	@PostMapping("saveLabel")
	public String saveLabel(@Valid CreateLabelDTO createLabelDTO,BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("createLabelDTO", createLabelDTO);
			return "private/labels/addLabel";
		}
		/*mapping*/
		Label label = mapper.map(createLabelDTO, Label.class);
		labelService.saveLabel(label);
		return "redirect:/private/labels";
	}
	@GetMapping("editLabel/{id}")
	public String editLabel(@PathVariable(value="id") Long id, Model model) {

		/*getting*/
		Label label = labelService.getLabel(id);
		/*mapping*/
		EditLabelDTO labelDto = mapper.map(label, EditLabelDTO.class);
		model.addAttribute("editLabelDTO",labelDto );
		return "private/labels/editLabel";
	}
	@PostMapping("updateLabel/{id}")
	public String updateLabel(@Valid EditLabelDTO editLabelDTO, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("editLabelDTO", editLabelDTO);
			return "private/labels/editLabel";
		}
		/*mapping*/
		Label label = mapper.map(editLabelDTO, Label.class);
		labelService.saveLabel(label);
		return "redirect:/private/labels";
	}
	@GetMapping("deleteLabel/{id}")
	public String deleteLabel(@PathVariable(value="id") Long id,Model model) {
		try{
			labelService.deleteLabel(id);
		}catch(DataIntegrityViolationException e){
			model.addAttribute("error", "cannot delete label, label is must likely in use");
			return "private/labels/labels";
		}
		return "redirect:/private/labels";
	}
}
