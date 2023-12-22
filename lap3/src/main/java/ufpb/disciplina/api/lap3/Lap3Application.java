package ufpb.disciplina.api.lap3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import ufpb.disciplina.api.lap3.filtros.*;

@SpringBootApplication
public class Lap3Application {
	@Bean
	public  FilterRegistrationBean<TokenFiltro>filterjwt() {
		FilterRegistrationBean<TokenFiltro> filterRB = new FilterRegistrationBean<TokenFiltro>();
		filterRB.setFilter(new TokenFiltro());
		filterRB.addUrlPatterns("/v1/api/disciplinas" , "/v1/api/usuarios");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(Lap3Application.class, args);
	}

}
