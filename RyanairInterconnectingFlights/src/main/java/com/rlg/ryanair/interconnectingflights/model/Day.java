/**
 * 
 */
package com.rlg.ryanair.interconnectingflights.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author [Rafael León Gómez]
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {
	
	private Integer day;
	private Flight[] flights;

	/**
	 * 
	 */
	public Day() {
	}

	/**
	 * @param day
	 * @param flights
	 */
	public Day(Integer day, Flight[] flights) {
		this.day = day;
		this.flights = flights;
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * @return the flights
	 */
	public Flight[] getFlights() {
		return flights;
	}

	/**
	 * @param flights the flights to set
	 */
	public void setFlights(Flight[] flights) {
		this.flights = flights;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + Arrays.hashCode(flights);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (!Arrays.equals(flights, other.flights))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Day [day=" + day + ", flights=" + Arrays.toString(flights) + "]";
	}

}
