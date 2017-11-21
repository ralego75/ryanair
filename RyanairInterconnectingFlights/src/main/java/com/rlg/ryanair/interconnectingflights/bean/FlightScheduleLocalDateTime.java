package com.rlg.ryanair.interconnectingflights.bean;

import java.time.LocalDateTime;

/**
 * @author [Rafael León Gómez]
 *
 */
public class FlightScheduleLocalDateTime {
	
	private LocalDateTime departureLocalDateTime;
	private LocalDateTime arrivalLocalDateTime;

	/**
	 * 
	 */
	public FlightScheduleLocalDateTime() {
	}

	/**
	 * @param departureLocalDateTime
	 * @param arrivalLocalDateTime
	 */
	public FlightScheduleLocalDateTime(LocalDateTime departureLocalDateTime, 
			LocalDateTime arrivalLocalDateTime) {
		this.departureLocalDateTime = departureLocalDateTime;
		this.arrivalLocalDateTime = arrivalLocalDateTime;
	}

	/**
	 * @return the departureLocalDateTime
	 */
	public LocalDateTime getDepartureLocalDateTime() {
		return departureLocalDateTime;
	}

	/**
	 * @param departureLocalDateTime the departureLocalDateTime to set
	 */
	public void setDepartureLocalDateTime(LocalDateTime departureLocalDateTime) {
		this.departureLocalDateTime = departureLocalDateTime;
	}

	/**
	 * @return the arrivalLocalDateTime
	 */
	public LocalDateTime getArrivalLocalDateTime() {
		return arrivalLocalDateTime;
	}

	/**
	 * @param arrivalLocalDateTime the arrivalLocalDateTime to set
	 */
	public void setArrivalLocalDateTime(LocalDateTime arrivalLocalDateTime) {
		this.arrivalLocalDateTime = arrivalLocalDateTime;
	}

	
}
