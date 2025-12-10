package com.example.dictionary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.dictionary.entity.Content;

/**
 * Content : リポジトリ
 */
@Mapper
public interface ContentMapper {
	/**
	 * 全ての詳細を取得
	 */
	List<Content> selectAll();
	
	/**
	 * 指定されたIDの詳細を取得
	 */
	Content selectById(@Param("id") Integer id);
	
	/**
	 * 詳細を登録
	 */
	void insert(Content content);
	
	/**
	 * 詳細を更新
	 */
	void update(Content content);
	
	/**
	 * 指定されたIDの詳細を削除
	 */
	void delete(@Param("id") Integer id);
	
	/**
	 * 複数の詳細を更新
	 */
	void updateAll(List<Content> contents);

}
