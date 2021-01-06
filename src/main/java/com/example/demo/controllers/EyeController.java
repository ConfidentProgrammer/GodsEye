package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.beans.Location;
import com.example.demo.database.DatabaseAccess;

@Controller
public class EyeController {
	@Autowired
	 DatabaseAccess da;
	
	@GetMapping("/")
	public String index(Location loc, Model model) {
		model.addAttribute("loc", loc);
		return "index";
	}
	
	@PostMapping("/")
	public String indexPost(@ModelAttribute Location loc, Model model) {
		model.addAttribute("loc", loc);
		da.insertLocation(loc);
		return "redirect:/";
	}
	
	@GetMapping("/dark")
	public String dark(Model model, Location loc) {
		ArrayList<Location> locList = da.getAlllocation(loc);
		model.addAttribute("loc", locList);
		return "secret";
	}
}
