package com.rlg.ryanair.interconnectingflights.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author [Rafael León Gómez]
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

	private String airportFrom;
	private String airportTo; 
	private String connectingAirport;
	private Boolean newRoute;
	private Boolean seasonalRoute;
	private String group;
	private List<Schedule> schedules;

	/**
	 * 
	 */
	public Route() {
		schedules = new ArrayList<Schedule>();
	}

	/**
	 * @param airportFrom
	 * @param airportTo
	 * @param connectingAirport
	 * @param newRoute
	 * @param seasonalRoute
	 * @param group
	 * @param schedules
	 */
	public Route(String airportFrom, String airportTo, String connectingAirport, Boolean newRoute,
			Boolean seasonalRoute, String group, List<Schedule> schedules) {
		this.airportFrom = airportFrom;
		this.airportTo = airportTo;
		this.connectingAirport = connectingAirport;
		this.newRoute = newRoute;
		this.seasonalRoute = seasonalRoute;
		this.group = group;
		this.schedules = schedules;
	}

	/**
	 * @return the airportFrom
	 */
	public String getAirportFrom() {
		return airportFrom;
	}

	/**
	 * @param airportFrom the airportFrom to set
	 */
	public void setAirportFrom(String airportFrom) {
		this.airportFrom = airportFrom;
	}

	/**
	 * @return the airportTo
	 */
	public String getAirportTo() {
		return airportTo;
	}

	/**
	 * @param airportTo the airportTo to set
	 */
	public void setAirportTo(String airportTo) {
		this.airportTo = airportTo;
	}

	/**
	 * @return the connectingAirport
	 */
	public String getConnectingAirport() {
		return connectingAirport;
	}

	/**
	 * @param connectingAirport the connectingAirport to set
	 */
	public void setConnectingAirport(String connectingAirport) {
		this.connectingAirport = connectingAirport;
	}

	/**
	 * @return the newRoute
	 */
	public Boolean getNewRoute() {
		return newRoute;
	}

	/**
	 * @param newRoute the newRoute to set
	 */
	public void setNewRoute(Boolean newRoute) {
		this.newRoute = newRoute;
	}

	/**
	 * @return the seasonalRoute
	 */
	public Boolean getSeasonalRoute() {
		return seasonalRoute;
	}

	/**
	 * @param seasonalRoute the seasonalRoute to set
	 */
	public void setSeasonalRoute(Boolean seasonalRoute) {
		this.seasonalRoute = seasonalRoute;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the schedules
	 */
	public List<Schedule> getSchedules() {
		return schedules;
	}

	/**
	 * @param schedules the schedules to set
	 */
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportFrom == null) ? 0 : airportFrom.hashCode());
		result = prime * result + ((airportTo == null) ? 0 : airportTo.hashCode());
		result = prime * result + ((connectingAirport == null) ? 0 : connectingAirport.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((newRoute == null) ? 0 : newRoute.hashCode());
//		result = prime * result + ((schedules == null) ? 0 : schedules.hashCode());
		result = prime * result + ((seasonalRoute == null) ? 0 : seasonalRoute.hashCode());
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
		Route other = (Route) obj;
		if (airportFrom == null) {
			if (other.airportFrom != null)
				return false;
		} else if (!airportFrom.equals(other.airportFrom))
			return false;
		if (airportTo == null) {
			if (other.airportTo != null)
				return false;
		} else if (!airportTo.equals(other.airportTo))
			return false;
		if (connectingAirport == null) {
			if (other.connectingAirport != null)
				return false;
		} else if (!connectingAirport.equals(other.connectingAirport))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (newRoute == null) {
			if (other.newRoute != null)
				return false;
		} else if (!newRoute.equals(other.newRoute))
			return false;
		if (schedules == null) {
			if (other.schedules != null)
				return false;
		} else if (!schedules.equals(other.schedules))
			return false;
		if (seasonalRoute == null) {
			if (other.seasonalRoute != null)
				return false;
		} else if (!seasonalRoute.equals(other.seasonalRoute))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Route [airportFrom=" + airportFrom + ", airportTo=" + airportTo + ", connectingAirport="
				+ connectingAirport + ", newRoute=" + newRoute + ", seasonalRoute=" + seasonalRoute + ", group=" + group
				+ ", schedules=" + schedules + "]";
	}

}
