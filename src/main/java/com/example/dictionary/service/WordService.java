package com.example.dictionary.service;

import java.util.List;

import com.example.dictionary.entity.Word;

/**
 * Word : サービス
 */
public interface WordService {
	/**
	 * 全見出し語を検索
	 */
	List<Word> findAllWord();
	
	/**
	 * 指定されたIDの見出し語を検索
	 */
	Word findByIdWord(Integer id);
	
	/**
	 * 見出し語を登録
	 */
	void insertWord(Word word);
	
	/**
	 * 見出し語を更新
	 */
	void updateWord(Word word);
	
	/**
	 * 指定されたIDの見出し語を削除
	 */
	void deleteWord(Integer id);
	
	/**
	 * 指定されたIDの見出し語のタグを検索
	 */
	List<Integer> findByIdTag(Integer id);

}
