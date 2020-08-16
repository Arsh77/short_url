package com.url.shortner.urlshortner.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.shortner.urlshortner.entity.GeneratedShortUrl;
import com.url.shortner.urlshortner.entity.PairedUrl;
import com.url.shortner.urlshortner.pojo.Url;
import com.url.shortner.urlshortner.repository.GeneratedShortUrlRepository;
import com.url.shortner.urlshortner.repository.PairedUrlRepository;

@Service
public class ShortUrlService {

	@Autowired
	private GeneratedShortUrlRepository generatedRepository;

	@Autowired
	private PairedUrlRepository pairedRepository;

	private PairedUrl pu = new PairedUrl();

	private GeneratedShortUrl gsu = new GeneratedShortUrl();

	private static int c1 = 10;
	private static int c2 = 15;
	private static final int maxRowCountGeneratedUrl = 10000;
	private static long countRowGenerated = 0;
	private static final String char62 = "abcAB9CDdef8stX7YuvFGHIJw5EKL54Mhijkl3mnoN2Ogp1STPQqrRUxyzVWZ0";

	public Url setUrl(String longurl) {
		countRowGenerated = generatedRepository.count();
		try {
			Url url = new Url();
			GeneratedShortUrl gsu = generatedRepository.findOneShorturl();
			pu.setLongurl(longurl);
			String shortUrl = gsu.getShorturl();
			pu.setShorturl(shortUrl);
			pu.setId(shortUrl.charAt(0) + "" + shortUrl.charAt(shortUrl.length() - 1));
			pairedRepository.save(pu);
			url.setUrl(gsu.getShorturl());
			generatedRepository.delete(gsu);
			--countRowGenerated;
			return url;

		} catch (Exception e) {
			if (generatedRepository.count() == 0) {
				addGeneratedOneUrl();
			}
			return setUrl(longurl);
		} finally {
			if (countRowGenerated < 0.2 * maxRowCountGeneratedUrl) {
				addGeneratedUrl();
			}
		}
	}

	private void addGeneratedUrl() {
		Set<GeneratedShortUrl> setGSU= new HashSet<GeneratedShortUrl>();
		while (setGSU.size()<maxRowCountGeneratedUrl) {
			GeneratedShortUrl temp = new GeneratedShortUrl();
			temp.setShorturl(generateRandomUrl());
			setGSU.add(temp);
		}
		generatedRepository.saveAll(setGSU);
	}

	private void addGeneratedOneUrl() {
		int c = 0;
		while (c < 1) {
			gsu.setShorturl(generateRandomUrl());
			try {
				generatedRepository.save(gsu);
			} catch (Exception e) {
				continue;
			}
			c++;
		}
	}

	private String generateRandomUrl() {
		c1 = c1 % 62;
		c2 = c2 % 62;
		String randomString = RandomStringUtils.randomAlphanumeric(6);
		randomString = char62.charAt(c1) + randomString + char62.charAt(c2);
		c1 += 1;
		c2 += 3;
		return randomString;
	}

	public String getLongUrl(String shortUrl) {
		pu = pairedRepository.findByShorturl(shortUrl);
		return pu.getLongurl();
	}

}
