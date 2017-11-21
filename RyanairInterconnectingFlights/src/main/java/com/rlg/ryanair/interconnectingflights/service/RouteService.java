/**
 * 
 */
package com.rlg.ryanair.interconnectingflights.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.rlg.ryanair.interconnectingflights.model.Route;

/**
 * @author [Rafael Le�n G�mez]
 *
 */
@Service("routeService")
@Transactional
public class RouteService implements IRouteService {
	
	private static final String REST_CORE_SERVICE_URI = "https://api.ryanair.com/core/3";

	/**
	 * 
	 */
	public RouteService() {
		
	}
	
    /* GET */
    /**
     * @return
     */
    public Route[] getAllRoutes(){
        RestTemplate restTemplate = new RestTemplate();
        String url = REST_CORE_SERVICE_URI+"/routes";
        Route[] routes = restTemplate.getForObject(url, Route[].class);
        
        if (routes!=null) return routes;
        else return null;
    }

}
