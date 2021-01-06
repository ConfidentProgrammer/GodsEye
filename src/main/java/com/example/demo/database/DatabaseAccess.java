package com.example.demo.database;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.beans.Location;


@Component
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	
	//this is to add the app
	public void insertLocation(Location loc) {
		String query="INSERT INTO Location (latitude, longitude) VALUES (:lat, :lng)";

		MapSqlParameterSource namedParameters = 
						new MapSqlParameterSource();
		namedParameters.addValue("lat", loc.getLatitude());
		namedParameters.addValue("lng", loc.getLongitude());

		jdbc.update(query, namedParameters);
	}
	
	public ArrayList<Location> getAlllocation(Location loc) {
		String query = "SELECT latitude,longitude FROM Location";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("loc", loc);
		
		ArrayList<Location> locList = new ArrayList<Location>();
		
		locList = (ArrayList<Location>)jdbc.query(query, map, new BeanPropertyRowMapper<Location>(Location.class));
		//List<Map<String, Object>> list = jdbc.queryForList(query, map);
		return locList;
}
	
}
