package com.test.BoilerAndroid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Boiler;
import com.test.mapper.Boiler_state;

@RestController
public class BoilerAndroid {
	@Autowired
	Boiler_state b_state;
	
	@GetMapping("/boiler/boilerList")
	List<Boiler> getBoilerList() {
		return b_state.selectBoilerList();
	}
	
	@GetMapping("/boiler/on_off/{state}")
	void getUpdateState(@PathVariable("state") String state) {
		String boiler = "boiler_state";
		b_state.updateState(state, boiler);
	}
}
