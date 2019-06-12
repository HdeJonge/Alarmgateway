package com.bprocare.service;

import java.util.List;

import com.bprocare.domain.AlarmGateway;

public interface AlarmGatewayService {

   List<AlarmGateway> getAllAlarmGateways();
   AlarmGateway getAlarmGateway(Long id);
   void createAlarmGateway(AlarmGateway alarmGateway);
   void changeAlarmGateway(AlarmGateway alarmGateway);
   void deleteAlarmGateway(Long id) throws Exception;
	
}
