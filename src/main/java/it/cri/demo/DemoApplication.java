package it.cri.demo;

import it.cri.demo.entity.Volunteer;
import it.cri.demo.service.MedicalVisitService;
import it.cri.demo.service.VolunteerService;
import it.cri.demo.ui.FormTest;
import it.cri.demo.ui.MedicalVisitUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
		VolunteerService service = ctx.getBean(VolunteerService.class);

		System.setProperty("java.awt.headless","false");

		SwingUtilities.invokeLater(() -> new FormTest(service).setVisible(true));
	}

}
