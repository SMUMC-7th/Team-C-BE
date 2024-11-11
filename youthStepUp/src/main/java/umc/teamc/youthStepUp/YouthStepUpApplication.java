package umc.teamc.youthStepUp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class YouthStepUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouthStepUpApplication.class, args);
    }

}
