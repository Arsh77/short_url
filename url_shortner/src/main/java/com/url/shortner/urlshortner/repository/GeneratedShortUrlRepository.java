package com.url.shortner.urlshortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.url.shortner.urlshortner.entity.GeneratedShortUrl;

public interface GeneratedShortUrlRepository extends JpaRepository<GeneratedShortUrl, Integer> {
	@Query(value = "select shorturl from GEN_SHORT_URL limit 1" , nativeQuery=true)
	public GeneratedShortUrl findOneShorturl();

}
