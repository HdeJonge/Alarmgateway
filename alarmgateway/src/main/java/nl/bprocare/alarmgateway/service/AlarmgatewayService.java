package nl.bprocare.alarmgateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.bprocare.alarmgateway.dto.AlarmgatewayDto;
import nl.bprocare.alarmgateway.pojo.Alarmgateway;
import nl.bprocare.alarmgateway.repository.AlarmgatewayRepository;

@Service
public class AlarmgatewayService {
	
	@Autowired
	private AlarmgatewayRepository alarmgatewayRepository;
	
	public List<AlarmgatewayDto> getAllAlarmgateways(){
		return alarmgatewayRepository.findAll();
	}

	public void saveAlarmgateway(AlarmgatewayDto alarmgateway) {
		alarmgatewayRepository.save(alarmgateway);
		
	}

	public AlarmgatewayDto getAlarmgateway(Long id) {
		return alarmgatewayRepository.findById(id).get();
	}

	public void deleteAlarmgateway(Long id) {
		alarmgatewayRepository.deleteById(id);
		
	}

}
