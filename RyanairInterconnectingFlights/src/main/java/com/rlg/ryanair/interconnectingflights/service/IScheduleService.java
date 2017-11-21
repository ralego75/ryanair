/**
 * 
 */
package com.rlg.ryanair.interconnectingflights.service;

import com.rlg.ryanair.interconnectingflights.model.Schedule;

/**
 * @author [Rafael León Gómez]
 *
 */
public interface IScheduleService {
	
	public Schedule getSchedule(String departure, String arrival, Integer year, Integer month);

}
