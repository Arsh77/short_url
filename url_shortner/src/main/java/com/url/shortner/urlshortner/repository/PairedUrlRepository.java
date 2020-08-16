package com.url.shortner.urlshortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url.shortner.urlshortner.entity.PairedUrl;

public interface PairedUrlRepository extends JpaRepository<PairedUrl, Integer> {
	// derived method
	// Product findByName(String name);
	// derived method
	// String deleteByName(String name);
	public PairedUrl findByShorturl(String shortUrl);

}
