package com.alien.contributer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextContributor implements InfoContributor {

	@Autowired
	private ApplicationContext context;

	@Override
	public void contribute(Builder builder) {

		Map<String, Object> contextMap = new HashMap<>();

		contextMap.put("bean-definition-count", context.getBeanDefinitionCount());
		contextMap.put("bean-names", context.getBeanDefinitionNames());
		contextMap.put("application-startup-time", context.getStartupDate());
		builder.withDetail("context", contextMap);

	}

}
