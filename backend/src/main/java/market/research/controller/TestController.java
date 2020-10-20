package market.research.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/research")
public class TestController {
	
	@RequestMapping("/test")
	public String test() {
		return "Hello World";
	}
}
