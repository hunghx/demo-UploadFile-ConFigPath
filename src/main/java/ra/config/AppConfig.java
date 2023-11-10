package ra.config;




import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletContext;
import java.io.IOException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ra"})
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {
    @Autowired
    private ServletContext servletContext;
    private String pathUpload = "C:\\Users\\hung1\\OneDrive\\Desktop\\demo-UploadFile-ConFigPath\\src\\main\\webapp\\assets\\uploads\\";
    private ApplicationContext applicationContext;
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver view = new InternalResourceViewResolver();
        view.setPrefix("/views/");
        view.setSuffix(".jsp");
        return view;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(52428800);
        return resolver;
    }
    // xử lí đường dẫn
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**", "/css/**","/upload/**")
                .addResourceLocations("classpath:/assets/image/", "classpath:/assets/css/","file:"+pathUpload);

    }
}
