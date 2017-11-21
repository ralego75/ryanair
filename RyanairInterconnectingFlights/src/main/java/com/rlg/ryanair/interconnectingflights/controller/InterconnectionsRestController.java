package com.rlg.ryanair.interconnectingflights.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rlg.ryanair.interconnectingflights.helper.RoutesHelper;
import com.rlg.ryanair.interconnectingflights.model.Interconnection;
import com.rlg.ryanair.interconnectingflights.model.Route;
import com.rlg.ryanair.interconnectingflights.model.Schedule;
import com.rlg.ryanair.interconnectingflights.service.IRouteService;
import com.rlg.ryanair.interconnectingflights.service.IScheduleService;
 
/**
 * @author [Rafael León Gómez]
 *
 */
@RestController
public class InterconnectionsRestController {
	
	private static final int MONTH_JANUARY = 1;
	private static final int MONTH_DICEMBER = 12;
	
    @Autowired
    IRouteService routeService;

    @Autowired
    IScheduleService scheduleService;
    
	public InterconnectionsRestController() {

	}
	
	@RequestMapping(value="/interconnections", 
			params = {"departure", "arrival", "departureDateTime", "arrivalDateTime"})
	public @ResponseBody ResponseEntity<List<Interconnection>> getInterconnections(
			@RequestParam(value = "departure") String departure, 
			@RequestParam(value = "arrival") String arrival, 
			@RequestParam(value = "departureDateTime") String departureDateTime, 
			@RequestParam(value = "arrivalDateTime") String arrivalDateTime) { 
		
		LocalDateTime departureLocalDateTime = LocalDateTime.parse(departureDateTime, 
				DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LocalDateTime arrivalLocalDateTime = LocalDateTime.parse(arrivalDateTime, 
				DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		
		Integer departureYear = departureLocalDateTime.getYear();
		Integer departureMonth = departureLocalDateTime.getMonth().getValue();
		Integer arrivalYear = arrivalLocalDateTime.getYear();
		Integer arrivalMonth = arrivalLocalDateTime.getMonth().getValue();
    	
        Route[] routes = routeService.getAllRoutes();
        
        HashMap<Route, Route> interconnectedRoutes = 
        		RoutesHelper.getInterconnectedRoutesByDepartureAndArrivalAirports(
        				routes, departure, arrival);
        
        Schedule schedule = null;
        
        if (departureYear > arrivalYear) {
        	//You many decide to return HttpStatus.BAD_REQUEST
            return new ResponseEntity<List<Interconnection>>(HttpStatus.BAD_REQUEST);
		}
        
    	for (int actualYear = departureYear; actualYear <= arrivalYear; actualYear++) {
    		
    		int initialMonth, finalMonth;
    		if (departureYear == arrivalYear) {
    			initialMonth = departureMonth;
    			finalMonth = arrivalYear;
    		} else if (actualYear == departureYear) {
				initialMonth = departureMonth;
				finalMonth = MONTH_DICEMBER;
			} else if (actualYear == arrivalYear) {
    			initialMonth = MONTH_JANUARY;
    			finalMonth = arrivalMonth;
			} else {
    			initialMonth = MONTH_JANUARY;
    			finalMonth = MONTH_DICEMBER;
    		}
    		
    		for (int actualMonth = initialMonth; actualMonth <= finalMonth; actualMonth++) {
		        for (Route departureRoute : interconnectedRoutes.keySet()) {
		        	Route arrivalRoute = interconnectedRoutes.get(departureRoute);
		        	schedule = scheduleService.getSchedule(departureRoute.getAirportFrom()
		        			, departureRoute.getAirportTo(), actualYear, actualMonth);
		        	if (schedule != null) schedule.setYear(actualYear);
		        	departureRoute.getSchedules().add(schedule);
		        	if (arrivalRoute != null) {
			        	schedule = scheduleService.getSchedule(arrivalRoute.getAirportFrom()
			        			, arrivalRoute.getAirportTo(), actualYear, actualMonth);
			        	if (schedule != null) schedule.setYear(actualYear);
			        	arrivalRoute.getSchedules().add(schedule);
		        	}
		        }
        	}
    		
        }
    	
    	List<Interconnection> validInterconnectedRoutes = RoutesHelper.
    			getValidInterconnectedRoutes(interconnectedRoutes, 
    					departureLocalDateTime, arrivalLocalDateTime);
        
        if(routes == null || routes.length <= 0 || validInterconnectedRoutes == null 
        		|| validInterconnectedRoutes.isEmpty()){
        	//You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<Interconnection>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<Interconnection>>(validInterconnectedRoutes, HttpStatus.OK);
		
	}

}
