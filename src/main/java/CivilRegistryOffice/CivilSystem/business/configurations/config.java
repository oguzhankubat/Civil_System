package CivilRegistryOffice.CivilSystem.business.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean("ModelMapper")
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
