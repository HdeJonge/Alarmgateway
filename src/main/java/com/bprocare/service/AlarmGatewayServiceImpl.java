package com.bprocare.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bprocare.domain.AlarmGateway;
import com.bprocare.repository.AlarmGatewayRepository;

@Service
public class AlarmGatewayServiceImpl implements AlarmGatewayService {

	@Autowired
	AlarmGatewayRepository alarmGatewayRepository;

	public List<AlarmGateway> getAllAlarmGateways() {
		return (List<AlarmGateway>) alarmGatewayRepository.findAll();

	}	

	public AlarmGateway getAlarmGateway(Long id) {
		return alarmGatewayRepository.findById(id)
				.orElse(null);
	}

	@Transactional
	public void createAlarmGateway(AlarmGateway alarmGateway) {
		alarmGatewayRepository.save(alarmGateway);
	}

	@Transactional
	public void changeAlarmGateway(AlarmGateway alarmGateway) {
				
		alarmGatewayRepository.save(alarmGateway);
	}

	@Transactional
	public void deleteAlarmGateway(Long id) throws Exception {
		AlarmGateway alarmGateway = alarmGatewayRepository.findById(id)
				.orElse(null);
		
    	if (alarmGateway != null)
    	{
		  alarmGatewayRepository.delete(alarmGateway);
    	}
    	else
    		throw new Exception();
	}

}
