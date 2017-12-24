package by.plisunov.meritgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import by.plisunov.meritgroup.config.TradeValidatorConfig;

/**
 * (non-Javadoc)
 * 
 * @see org.springframework.boot.web.support.SpringBootServletInitializer
 * @author Andrey
 *
 */
@SpringBootApplication
public class TradeValidatorApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TradeValidatorApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(new Class[] { TradeValidatorApplication.class, TradeValidatorConfig.class }, args);
	}
}
