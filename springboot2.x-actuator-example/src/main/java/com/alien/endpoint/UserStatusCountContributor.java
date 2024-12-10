package com.alien.endpoint;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.alien.dao.UserDatabase;

@Component
public class UserStatusCountContributor implements InfoContributor {

	@Autowired
	private UserDatabase database;

	@Override
	public void contribute(Info.Builder builder) {

		Map<String, Long> userStatusMap = new HashMap<>();

		userStatusMap.put("active", database.getUserStatusCountByStatus("active"));
		userStatusMap.put("inactive", database.getUserStatusCountByStatus("inactive"));

		builder.withDetail("userStatus", userStatusMap);

	}
}
