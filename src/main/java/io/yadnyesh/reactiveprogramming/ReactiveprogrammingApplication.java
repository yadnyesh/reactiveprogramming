package io.yadnyesh.reactiveprogramming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class ReactiveprogrammingApplication {

	public static void main(String[] args) {
		ReactorDebugAgent.init();
		SpringApplication.run(ReactiveprogrammingApplication.class, args);
	}

}
