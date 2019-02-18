package nl.bprocare.alarmgateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.bprocare.alarmgateway.dto.LabelDto;
import nl.bprocare.alarmgateway.repository.LabelRepository;

@Service
public class LabelService {
	@Autowired
	private LabelRepository labelRepository;
	
	public List<LabelDto> getAllLabels(){
		return labelRepository.findAll();
	}
	public void saveLabel(LabelDto label) {
		labelRepository.save(label);
	}
	public void deleteLabel(Long labelId) {
		labelRepository.deleteById(labelId);
	}
	public LabelDto getLabel(Long id) {
		return labelRepository.findById(id).get();
	}

}
