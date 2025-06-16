package software.xdev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class RootController
{
	@GetMapping
	public String respond()
	{
		return """
			<html>
			<body>
			Go to <a href="/actuator/prometheus">Prometheus metrics</a>
			</body>
			</html>
			""";
	}
}
