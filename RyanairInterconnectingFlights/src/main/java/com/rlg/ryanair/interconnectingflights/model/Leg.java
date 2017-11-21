package com.rlg.ryanair.interconnectingflights.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author [Rafael León Gómez]
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg {

	private String departureAirport;
	private String arrivalAirport; 
	private String departureDateTime;
	private String arrivalDateTime;

	

	/**
	 * 
	 */
	public Leg() {
	}

	/**
	 * @param departureAirport
	 * @param arrivalAirport
	 * @param departureDateTime
	 * @param arrivalDateTime
	 */
	public Leg(String departureAirport, String arrivalAirport, String departureDateTime,
			String arrivalDateTime) {
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureDateTime = departureDateTime;
		this.arrivalDateTime = arrivalDateTime;
	}

	/**
	 * @return the departureAirport
	 */
	public String getDepartureAirport() {
		return departureAirport;
	}

	/**
	 * @param departureAirport the departureAirport to set
	 */
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	/**
	 * @return the arrivalAirport
	 */
	public String getArrivalAirport() {
		return arrivalAirport;
	}

	/**
	 * @param arrivalAirport the arrivalAirport to set
	 */
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	/**
	 * @return the departureDateTime
	 */
	public String getDepartureDateTime() {
		return departureDateTime;
	}

	/**
	 * @param departureDateTime the departureDateTime to set
	 */
	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	/**
	 * @return the arrivalDateTime
	 */
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	/**
	 * @param arrivalDateTime the arrivalDateTime to set
	 */
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalAirport == null) ? 0 : arrivalAirport.hashCode());
		result = prime * result + ((arrivalDateTime == null) ? 0 : arrivalDateTime.hashCode());
		result = prime * result + ((departureAirport == null) ? 0 : departureAirport.hashCode());
		result = prime * result + ((departureDateTime == null) ? 0 : departureDateTime.hashCode());
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
		Leg other = (Leg) obj;
		if (arrivalAirport == null) {
			if (other.arrivalAirport != null)
				return false;
		} else if (!arrivalAirport.equals(other.arrivalAirport))
			return false;
		if (arrivalDateTime == null) {
			if (other.arrivalDateTime != null)
				return false;
		} else if (!arrivalDateTime.equals(other.arrivalDateTime))
			return false;
		if (departureAirport == null) {
			if (other.departureAirport != null)
				return false;
		} else if (!departureAirport.equals(other.departureAirport))
			return false;
		if (departureDateTime == null) {
			if (other.departureDateTime != null)
				return false;
		} else if (!departureDateTime.equals(other.departureDateTime))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Leg [departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport
				+ ", departureDateTime=" + departureDateTime + ", arrivalDateTime=" + arrivalDateTime + "]";
	}

}
