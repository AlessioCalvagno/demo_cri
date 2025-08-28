package it.cri.demo;

import it.cri.demo.entity.Volunteer;
import it.cri.demo.service.VolunteerService;
import it.cri.demo.ui.FormTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		//debug
		VolunteerService service = ctx.getBean(VolunteerService.class);
		List<Volunteer> v = service.getAll();
		System.out.println(v);

//		String nm = System.getProperty("java.awt.headless");
//		System.out.println("java.awt.headless is: " + nm);
		System.setProperty("java.awt.headless","false");

		SwingUtilities.invokeLater(() -> new FormTest(service).setVisible(true));
	}

}
