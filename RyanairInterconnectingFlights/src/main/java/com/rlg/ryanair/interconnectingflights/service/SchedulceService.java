/**
 * 
 */
package com.rlg.ryanair.interconnectingflights.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.rlg.ryanair.interconnectingflights.model.Schedule;

/**
 * @author [Rafael León Gómez]
 *
 */
@Service("scheduleService")
@Transactional
public class SchedulceService implements IScheduleService {

	private static final String REST_TIMETABLE_SERVICE_URI = "https://api.ryanair.com/timetable/3";
	
	/**
	 * 
	 */
	public SchedulceService() {
	}

    /* GET */
    public Schedule getSchedule(String departure, String arrival, Integer year, Integer month) {         
        RestTemplate restTemplate = new RestTemplate();
        String url = REST_TIMETABLE_SERVICE_URI+"/schedules/"
        		+departure+"/"+arrival+"/years/"+year+"/months/"+month;

        Schedule schedule = null;
        try {
        	schedule = restTemplate.getForObject(url, Schedule.class);
		} catch (Exception e) {}
         
        if (schedule!=null) return schedule;
        else return null;
    }

}
