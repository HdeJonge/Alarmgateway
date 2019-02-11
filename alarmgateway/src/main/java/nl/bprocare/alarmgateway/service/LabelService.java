package nl.bprocare.alarmgateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.bprocare.alarmgateway.domain.Label;
import nl.bprocare.alarmgateway.repository.LabelRepository;

@Service
public class LabelService {
	@Autowired
	private LabelRepository labelRepository;
	
	public List<Label> getAllLabels(){
		return labelRepository.findAll();
	}
	public void saveLabel(Label label) {
		labelRepository.save(label);
	}
	public void deleteLabel(Long labelId) {
		labelRepository.deleteById(labelId);
	}
	public Label getLabel(Long id) {
		return labelRepository.findById(id).get();
	}

}
