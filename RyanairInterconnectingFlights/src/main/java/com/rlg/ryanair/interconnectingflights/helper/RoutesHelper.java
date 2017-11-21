/**
 * 
 */
package com.rlg.ryanair.interconnectingflights.helper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rlg.ryanair.interconnectingflights.model.Day;
import com.rlg.ryanair.interconnectingflights.model.Flight;
import com.rlg.ryanair.interconnectingflights.model.Interconnection;
import com.rlg.ryanair.interconnectingflights.model.Leg;
import com.rlg.ryanair.interconnectingflights.model.Route;
import com.rlg.ryanair.interconnectingflights.model.Schedule;

/**
 * @author [Rafael León Gómez]
 *
 */
public class RoutesHelper {

	/**
	 * 
	 */
	public RoutesHelper() {
	}
	
	public static HashMap<Route, Route> getInterconnectedRoutesByDepartureAndArrivalAirports(
			Route[] routes, String departure, String arrival) {
		
		HashMap<Route, Route> result = new HashMap<Route, Route>();
		
		for (Route departureRoute : routes) {
			if (departureRoute.getAirportFrom().equals(departure)) { 
				String airportFromForArrivalRoute = departureRoute.getAirportTo();
				if (departureRoute.getAirportTo().equals(arrival)) {
					result.put(departureRoute, null);
				} else {
					for (Route arrivalRoute : routes) {
						if (arrivalRoute.getAirportFrom().equals(airportFromForArrivalRoute) && 
								arrivalRoute.getAirportTo().equals(arrival)) {
							result.put(departureRoute, arrivalRoute);
						}
					}
				}
			}
		}
		
		return result;
	}
	
	public static List<Interconnection> getValidInterconnectedRoutes(
			HashMap<Route, Route> interconnectedRoutes, LocalDateTime departureLocalDateTime, 
			LocalDateTime arrivalLocalDateTime) {
		
		List<Interconnection> interconnections = new ArrayList<Interconnection>();
		
		for (Route departureRoute : interconnectedRoutes.keySet()) {
			for (Schedule departureSchedule : departureRoute.getSchedules()) {
				if (departureSchedule != null) {
					for (Day departureDay : departureSchedule.getDays()) {
						for (Flight departureFlight : departureDay.getFlights()) {
							Integer firstFlightDepartureHour = LocalTime.parse(departureFlight.getDepartureTime()).getHour();
							Integer firstFlightDepartureMinute = LocalTime.parse(departureFlight.getDepartureTime()).getMinute();
							LocalDateTime firstFlightScheduleDepartureLocalDateTime = LocalDateTime.of(departureSchedule.getYear(), 
									departureSchedule.getMonth(), departureDay.getDay(), firstFlightDepartureHour, firstFlightDepartureMinute);
							Integer firstFlightArrivalHour = LocalTime.parse(departureFlight.getArrivalTime()).getHour();
							Integer firstFlightArrivalMinute = LocalTime.parse(departureFlight.getArrivalTime()).getMinute();
							LocalDateTime firstFlightScheduleArrivalLocalDateTime = LocalDateTime.of(departureSchedule.getYear(), 
									departureSchedule.getMonth(), departureDay.getDay(), firstFlightArrivalHour, firstFlightArrivalMinute);
							if (firstFlightScheduleDepartureLocalDateTime.isBefore(departureLocalDateTime) == false)  {
								Route arrivalRoute = interconnectedRoutes.get(departureRoute);
								if (arrivalRoute != null) {
									
									for (Schedule arrvivalSchedule : arrivalRoute.getSchedules()) {
										if (arrvivalSchedule != null) {
											for (Day arrivalDay : arrvivalSchedule.getDays()) {
												for (Flight arrivalFlight : arrivalDay.getFlights()) {
													Integer secondFlightDepartureHour = LocalTime.parse(arrivalFlight.getDepartureTime()).getHour();
													Integer secondFlightDepartureMinute = LocalTime.parse(arrivalFlight.getDepartureTime()).getMinute();
													LocalDateTime secondFlightScheduleDepartureLocalDateTime = LocalDateTime.of(arrvivalSchedule.getYear(), 
															arrvivalSchedule.getMonth(), arrivalDay.getDay(), secondFlightDepartureHour, secondFlightDepartureMinute);
													Integer secondFlightArrivalHour = LocalTime.parse(arrivalFlight.getArrivalTime()).getHour();
													Integer secondFlightArrivalMinute = LocalTime.parse(arrivalFlight.getArrivalTime()).getMinute();
													LocalDateTime secondFlightScheduleArrivalLocalDateTime = LocalDateTime.of(arrvivalSchedule.getYear(), 
															arrvivalSchedule.getMonth(), arrivalDay.getDay(), secondFlightArrivalHour, secondFlightArrivalMinute);
													if (secondFlightScheduleArrivalLocalDateTime.isAfter(arrivalLocalDateTime) == false) {
														if (secondFlightScheduleDepartureLocalDateTime.isBefore(firstFlightScheduleArrivalLocalDateTime.plusHours(2)) == false) {
															Interconnection interconnection = new Interconnection();
															Leg[] legs = new Leg[2];
															Leg firtsFlightLeg = new Leg();
															firtsFlightLeg.setDepartureAirport(departureRoute.getAirportFrom());
															firtsFlightLeg.setArrivalAirport(departureRoute.getAirportTo());
															firtsFlightLeg.setDepartureDateTime(firstFlightScheduleDepartureLocalDateTime.toString());
															firtsFlightLeg.setArrivalDateTime(firstFlightScheduleArrivalLocalDateTime.toString());
															legs[0] = firtsFlightLeg;
															Leg secondFlightLeg = new Leg();
															secondFlightLeg.setDepartureAirport(arrivalRoute.getAirportFrom());
															secondFlightLeg.setArrivalAirport(arrivalRoute.getAirportTo());
															secondFlightLeg.setDepartureDateTime(secondFlightScheduleDepartureLocalDateTime.toString());
															secondFlightLeg.setArrivalDateTime(secondFlightScheduleArrivalLocalDateTime.toString());
															legs[1] = secondFlightLeg;
															interconnection.setLegs(legs);
															interconnection.setStops(1);
															interconnections.add(interconnection);
														}
													}
												}
											}
										}
									}
								} else {
									if (firstFlightScheduleArrivalLocalDateTime.isAfter(arrivalLocalDateTime) == false) {
										Interconnection interconnection = new Interconnection();
										Leg[] legs = new Leg[1];
										Leg firtsFlightLeg = new Leg();
										firtsFlightLeg.setDepartureAirport(departureRoute.getAirportFrom());
										firtsFlightLeg.setArrivalAirport(departureRoute.getAirportTo());
										firtsFlightLeg.setDepartureDateTime(firstFlightScheduleDepartureLocalDateTime.toString());
										firtsFlightLeg.setArrivalDateTime(firstFlightScheduleArrivalLocalDateTime.toString());
										legs[0] = firtsFlightLeg;
										interconnection.setLegs(legs);
										interconnection.setStops(0);
										interconnections.add(interconnection);
									}
								}
							}
						}
					}
				}
			}
		}
		
		return interconnections;
	}

}
