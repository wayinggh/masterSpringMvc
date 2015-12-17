package com.github.spring.date;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by heliming on 2015/12/17.
 */
public class LocalDateFormatter implements Formatter<LocalDate>{

    public static final String NORMAL_PATTERN = "dd/MM/yyyy";
    public static final String CH_PATTERN = "yyyy-MM-dd";
    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern(getPattern(locale), locale));
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return DateTimeFormatter.ofPattern(getPattern(locale)).format(localDate);
    }

    public static String getPattern(Locale locale) {
        return Locale.CHINA.getCountry().equals(locale.getCountry()) ? CH_PATTERN : NORMAL_PATTERN;
    }
}
