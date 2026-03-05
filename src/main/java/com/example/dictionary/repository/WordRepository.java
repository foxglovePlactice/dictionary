package com.example.dictionary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dictionary.entity.Word;

public interface WordRepository extends JpaRepository<Word, Integer> { //<エンティティの型, 主キーの型>の形
	Page<Word> findByWordId(Integer wordId, Pageable pageable);

}
