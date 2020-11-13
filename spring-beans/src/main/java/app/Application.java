package app;

import app.config.AppConfig;
import app.model.AnimalsCage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext("ru.javamentor.spring-beans.app");
                new AnnotationConfigApplicationContext(AppConfig.class);
        AnimalsCage bean =
                applicationContext.getBean(AnimalsCage.class);
        for (int i = 0; i < 5; i++) {
            bean.whatAnimalSay();
        }
    }
}
