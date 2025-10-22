package it.cri.demo;

import it.cri.demo.service.*;
import it.cri.demo.ui.FormTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

        //manual dependency injection
		VolunteerService volunteerService = ctx.getBean(VolunteerService.class);
        AssociativeFeeService associativeFeeService = ctx.getBean(AssociativeFeeService.class);
        BrevetService brevetService = ctx.getBean(BrevetService.class);
        MedicalVisitService medicalVisitService = ctx.getBean(MedicalVisitService.class);
        PromotionService promotionService = ctx.getBean(PromotionService.class);
        QualificationService qualificationService = ctx.getBean(QualificationService.class);
        RecallService recallService = ctx.getBean(RecallService.class);



		System.setProperty("java.awt.headless","false");

		SwingUtilities.invokeLater(() -> new FormTest(
                volunteerService,
                associativeFeeService,
                brevetService,
                medicalVisitService,
                promotionService,
                qualificationService,
                recallService
                ).setVisible(true));
	}

}
