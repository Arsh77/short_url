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
@Entity(name="PAIRED_URL")
public class PairedUrl {

	@Id
	@Column(name="id", nullable=false)
	private String id;
	
	@Column(name="shorturl", unique=true)
	private String shorturl;
	
	@Column(name="longurl", nullable=false)
	private String longurl;
	
	
	
}
