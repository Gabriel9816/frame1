package br.edu.ifms.frame1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class Frame1Application {

	public static void main(String[] args) {
		SpringApplication.run(Frame1Application.class, args);
	}

//GET, POST, DELETE, PUT, PATCH

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
}