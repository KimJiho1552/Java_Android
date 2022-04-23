package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.entity.Boiler;
import com.test.mapper.Boiler_state;

@Controller
public class BoilerController {
	@Autowired
	Boiler_state b_state; //의존성 주입
	
	@GetMapping("/boiler/boiler")
	public void list_b_state(Model model) {
		model.addAttribute("state", b_state.selectBoilerList());
	}
	
	@GetMapping("/boiler/boilerRegistry")
	public void getboilerRegistry() {}
	 
	@PostMapping("/boiler/boilerRegistry")
	public String getboilerRegistry(Boiler bs) {
		b_state.InsertBoiler(bs.getBoiler(), bs.getState(), bs.getTem());
		return "redirect:/boiler/boiler";
	}
}
