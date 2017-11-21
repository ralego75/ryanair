/**
 * 
 */
package com.rlg.ryanair.interconnectingflights.helper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rlg.ryanair.interconnectingflights.bean.FlightScheduleLocalDateTime;
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
							FlightScheduleLocalDateTime firstFlightScheduleLocalDateTime = 
									getFlightScheduleLocalDateTime(departureSchedule, 
											departureDay, departureFlight);
							if (firstFlightScheduleLocalDateTime.getDepartureLocalDateTime().
									isBefore(departureLocalDateTime) == false)  {
								Route arrivalRoute = interconnectedRoutes.get(departureRoute);
								if (arrivalRoute != null) {
									for (Schedule arrivalSchedule : arrivalRoute.getSchedules()) {
										if (arrivalSchedule != null) {
											for (Day arrivalDay : arrivalSchedule.getDays()) {
												for (Flight arrivalFlight : arrivalDay.getFlights()) {
													FlightScheduleLocalDateTime secondFlightScheduleLocalDateTime = 
															getFlightScheduleLocalDateTime(arrivalSchedule, 
																	arrivalDay, arrivalFlight);
													if (secondFlightScheduleLocalDateTime.getArrivalLocalDateTime().
															isAfter(arrivalLocalDateTime) == false) {
														if (secondFlightScheduleLocalDateTime.getDepartureLocalDateTime().
																isBefore(firstFlightScheduleLocalDateTime.
																getArrivalLocalDateTime().plusHours(2)) == false) {
															Interconnection interconnection = new Interconnection();
															Leg[] legs = new Leg[2];
															Leg firtsFlightLeg = setFlightLeg(departureRoute, 
																	firstFlightScheduleLocalDateTime);
															legs[0] = firtsFlightLeg;
															Leg secondFlightLeg = setFlightLeg(arrivalRoute, 
																	secondFlightScheduleLocalDateTime);
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
									if (firstFlightScheduleLocalDateTime.getArrivalLocalDateTime().
											isAfter(arrivalLocalDateTime) == false) {
										Interconnection interconnection = new Interconnection();
										Leg[] legs = new Leg[1];
										Leg firtsFlightLeg = setFlightLeg(departureRoute, 
												firstFlightScheduleLocalDateTime);
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
	
	private static Leg setFlightLeg(Route route, FlightScheduleLocalDateTime 
			flightScheduleLocalDateTime) {
		Leg flightLeg = new Leg();
		flightLeg.setDepartureAirport(route.getAirportFrom());
		flightLeg.setArrivalAirport(route.getAirportTo());
		flightLeg.setDepartureDateTime(flightScheduleLocalDateTime.
				getDepartureLocalDateTime().toString());
		flightLeg.setArrivalDateTime(flightScheduleLocalDateTime.
				getDepartureLocalDateTime().toString());
		return null;	
	}
	
	private static FlightScheduleLocalDateTime getFlightScheduleLocalDateTime(
			Schedule schedule, Day day, Flight flight) {
		
		FlightScheduleLocalDateTime flightScheduleLocalDateTime = 
				new FlightScheduleLocalDateTime();
		Integer firstFlightDepartureHour = LocalTime.parse(flight.
				getDepartureTime()).getHour();
		Integer firstFlightDepartureMinute = LocalTime.parse(flight.
				getDepartureTime()).getMinute();
		flightScheduleLocalDateTime.setDepartureLocalDateTime(LocalDateTime.of(
				schedule.getYear(), schedule.getMonth(), day.getDay(), 
				firstFlightDepartureHour, firstFlightDepartureMinute));
		Integer firstFlightArrivalHour = LocalTime.parse(flight.
				getArrivalTime()).getHour();
		Integer firstFlightArrivalMinute = LocalTime.parse(flight.
				getArrivalTime()).getMinute();
		flightScheduleLocalDateTime.setArrivalLocalDateTime(LocalDateTime.of(
				schedule.getYear(), schedule.getMonth(), day.getDay(), 
				firstFlightArrivalHour, firstFlightArrivalMinute));
		
		return flightScheduleLocalDateTime;
	}

}
