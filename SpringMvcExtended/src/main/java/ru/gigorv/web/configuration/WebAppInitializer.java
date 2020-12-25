package ru.gigorv.web.configuration;

import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.gigorv.web.security.SecurityConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

// класс, заменяющий web.xml
public class WebAppInitializer implements WebApplicationInitializer {

    // что происходит при запуске приложения в tomcat?

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // создаем специальный объект-контекст для веб приложений
        AnnotationConfigWebApplicationContext springWebContext =
                new AnnotationConfigWebApplicationContext();

        // регистрируем в этом контексте контекст спринга
        springWebContext.register(AppConfig.class);
        // кладем SpringWeb в servletContext
        servletContext.addListener(new ContextLoaderListener(springWebContext));

        // создаем объекты DelegatingFilterProxy для того,
        // чтобы фильтры тоже были бинами и в них можно было бы автовайрить сервисы
//        DelegatingFilterProxy authFilter = new DelegatingFilterProxy("authenticationFilter");
//        DelegatingFilterProxy rolesFilter = new DelegatingFilterProxy("rolesFilter");
//        DelegatingFilterProxy redirectFilter = new DelegatingFilterProxy("redirectFilter");

        HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
        servletContext
                .addFilter("httpMethodFilter", httpMethodFilter)
                .addMappingForUrlPatterns(null, true, "/*");

        // в контекст сервлетов добавляем наши фильтры и указываем url-ы которые они обрабатывают
//        servletContext
//                .addFilter("authFilter", authFilter)
//                .addMappingForUrlPatterns(null, false, "/*");
//        servletContext
//                .addFilter("rolesFilter", rolesFilter)
//                .addMappingForUrlPatterns(null, false, "/*");
//
//        servletContext
//                .addFilter("redirectFilter", redirectFilter)
//                .addMappingForUrlPatterns(null, false, "/*");

//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
////
//        servletContext.addFilter("characterEncodingFilter", characterEncodingFilter)
//                .addMappingForUrlPatterns(null, false, "/*");
        // передаем ссылку на контекст сервлетов
        // внутрь контекста веб-приложения
        springWebContext.setServletContext(servletContext);
        // создаем диспатчер-сервлет и тут-же помещаем его в контекст
        // сервлетов
        // дисптачер-сервлету в свою очередь передаем контекст веб-приложения
        // чтобы он видел все бины
        // раньше это было автоматически - он находит context.xml
        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(springWebContext));
        // сказали что этот сервлет должен запускаться сразу при запуске приложения
        dispatcherServlet.setLoadOnStartup(1);
        // и принимал запросы на все урлы
        dispatcherServlet.addMapping("/");
    }
}
