package com.logging;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MDCFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {

			String userId = UUID.randomUUID().toString();
			MDC.put("UserId", userId);
			MDC.put("CorrelationID", UUID.randomUUID().toString());
			filterChain.doFilter(request, response);

		} catch (Exception e) {
			MDC.clear();
		}
	}
}
