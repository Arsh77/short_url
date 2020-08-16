package com.url.shortner.urlshortner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="GEN_SHORT_URL")
public class GeneratedShortUrl {

	@Id
	@Column(name="shorturl", unique=true, nullable=false)
	private String shorturl;
	
}
