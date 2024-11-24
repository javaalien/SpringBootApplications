package com.alien.endpoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.alien.dto.ProdRelease;

@Component
@Endpoint(id = "releases")
public class FeatureReleasesEndPoint {

	List<ProdRelease> prodReleases = new ArrayList<>();

	@WriteOperation
	public void addNewReleaseInfo(@Selector String crq, @Selector String releaseDt, String features) {
		ProdRelease release = ProdRelease.builder().crq(crq).releaseDt(releaseDt)
				.features(Arrays.stream(features.split(",")).collect(Collectors.toList())).build();

		prodReleases.add(release);
	}

	@ReadOperation
	public List<ProdRelease> getAllReleases() {
		return prodReleases;
	}

	@ReadOperation
	public ProdRelease getReleaseByCRQ(@Selector String crq) {
		return prodReleases.stream().filter(prodRelease -> prodRelease.getCrq().equals(crq)).findAny().get();
	}

	@DeleteOperation
	public void deleteRelease(@Selector String crq) {
		prodReleases.remove(getReleaseByCRQ(crq));
	}
}
