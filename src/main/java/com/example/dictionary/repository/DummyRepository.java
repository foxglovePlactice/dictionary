package com.example.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dictionary.entity.Word;

public interface DummyRepository extends JpaRepository<Word, Integer> {

}
