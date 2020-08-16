package com.url.shortner.urlshortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.url.shortner.urlshortner.pojo.Url;
import com.url.shortner.urlshortner.service.ShortUrlService;

@RestController
public class ShortUrlController {
	
	@Autowired
	private ShortUrlService sus;
	
	@GetMapping("/{shortUrl}")
	public RedirectView getLongUrl(@PathVariable(value="shortUrl") String shortUrl) {
		String url="";
		url=sus.getLongUrl(shortUrl);
		return new RedirectView(url);
	}
	
	@PostMapping("/shorturl")
	public Url postUrl(@RequestBody Url longUrl) {
		return sus.setUrl(longUrl.getUrl());
	}
}

 