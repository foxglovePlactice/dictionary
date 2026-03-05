package com.example.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dictionary.entity.Content;

public interface ContentRepository extends JpaRepository<Content, Integer> {

}
