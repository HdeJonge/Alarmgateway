package nl.bprocare.alarmgateway.alarmgateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmgatewayService {
	
	@Autowired
	AlarmgatewayRepository alarmgatewayRepository;
	
	public List<Alarmgateway> getAllAlarmgateways(){
		return alarmgatewayRepository.findAll();
	}

	public void saveAlarmgateway(Alarmgateway alarmgateway) {
		alarmgatewayRepository.save(alarmgateway);
		
	}

}
