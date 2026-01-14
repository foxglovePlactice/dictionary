package com.example.dictionary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.dictionary.entity.Word;

/**
 * Word : リポジトリ
 */
@Mapper
public interface WordMapper {
	/**
	 * 全ての見出し語を取得
	 */
	List<Word> selectAll();
	
	/**
	 * 指定されたIDの見出し語を取得
	 */
	Word selectById(@Param("wordId") Integer id);
	
	/**
	 * 見出し語を登録
	 */
	void insert(Word word);
	
	/**
	 * 見出し語を更新
	 */
	void update(Word word);
	
	/**
	 * 指定されたIDの見出し語を削除
	 */
	void delete(@Param("wordId") Integer id);
	
	/**
	 * 指定されたIDの見出し語のタグを取得
	 */
	String selectByIdTag(@Param("wordId") Integer id);

}
