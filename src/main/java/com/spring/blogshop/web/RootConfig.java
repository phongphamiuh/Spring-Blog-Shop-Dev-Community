package com.spring.blogshop.web;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;


@Configuration
@Import(DataServiceConfiguration.class)
@ComponentScan(basePackages={"com.spring.blogshop.*"} )
public class RootConfig {
  
}