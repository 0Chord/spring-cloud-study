package com.example.catalogservice.service;

import org.springframework.stereotype.Service;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

	private final CatalogRepository repository;

	@Override
	public Iterable<CatalogEntity> getAllCatalogs() {
		return repository.findAll();
	}
}
