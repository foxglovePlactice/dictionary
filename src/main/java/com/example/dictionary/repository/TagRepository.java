package com.example.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dictionary.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
