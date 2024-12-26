package com.piseth.java.school.phoneshopenight.config;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;

@Configuration
public class ConfigDateTimeJsonFormat{
	 private static final String dateFormat = "yyyy-MM-dd";
	    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	    @Bean
	    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
	        return builder -> {
	            builder.simpleDateFormat(dateTimeFormat);
	            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
	            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
	            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
	            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
	        };
	    }

}
