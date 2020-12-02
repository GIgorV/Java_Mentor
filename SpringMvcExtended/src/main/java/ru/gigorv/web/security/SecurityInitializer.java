package ru.gigorv.web.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    //пустой класс, использующийся для регистрации модуля в спринг-контейнере
    //обязателен для не boot-приложения. Кода в нем нет, но требуется для регистрации секьюрити в cпринг-контейнере
}
