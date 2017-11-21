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
public class Schedule {
	
	private Integer year;
	private Integer month;
	private Day[] days;

	/**
	 * 
	 */
	public Schedule() {
	}

	/**
	 * @param year
	 * @param month
	 * @param days
	 */
	public Schedule(Integer year, Integer month, Day[] days) {
		this.year = year;
		this.month = month;
		this.days = days;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @return the days
	 */
	public Day[] getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(Day[] days) {
		this.days = days;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(days);
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Schedule other = (Schedule) obj;
		if (!Arrays.equals(days, other.days))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Schedule [year=" + year + ", month=" + month + ", days=" + Arrays.toString(days) + "]";
	}

}
