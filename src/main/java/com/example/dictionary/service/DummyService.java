package com.example.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dictionary.repository.DummyRepository;

@Service
public class DummyService {
	@Autowired
	private DummyRepository dRepo;
}
