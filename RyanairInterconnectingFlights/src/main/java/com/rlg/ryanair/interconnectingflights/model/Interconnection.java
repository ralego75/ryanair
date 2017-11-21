/**
 * 
 */
package com.rlg.ryanair.interconnectingflights.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author [Rafael León Gómez]
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Interconnection {

	private Integer stops;
	private Leg[] legs;
		
	/**
	 * 
	 */
	public Interconnection() {
	}

	/**
	 * @param stops
	 * @param legs
	 */
	public Interconnection(Integer stops, Leg[] legs) {
		this.stops = stops;
		this.legs = legs;
	}

	/**
	 * @return the stops
	 */
	public Integer getStops() {
		return stops;
	}

	/**
	 * @param stops the stops to set
	 */
	public void setStops(Integer stops) {
		this.stops = stops;
	}

	/**
	 * @return the legs
	 */
	public Leg[] getLegs() {
		return legs;
	}

	/**
	 * @param legs the legs to set
	 */
	public void setLegs(Leg[] legs) {
		this.legs = legs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((legs == null) ? 0 : legs.hashCode());
		result = prime * result + ((stops == null) ? 0 : stops.hashCode());
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
		Interconnection other = (Interconnection) obj;
		if (legs == null) {
			if (other.legs != null)
				return false;
		} else if (!legs.equals(other.legs))
			return false;
		if (stops == null) {
			if (other.stops != null)
				return false;
		} else if (!stops.equals(other.stops))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Interconnection [stops=" + stops + ", legs=" + legs + "]";
	}

}
