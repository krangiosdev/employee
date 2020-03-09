package com.k9unit.employeedb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * Simplifies Locale resolver params
 */
@Component
public class MessageResolver {

    @Autowired
    ResourceBundleMessageSource messageSource;

    public String resolve(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
