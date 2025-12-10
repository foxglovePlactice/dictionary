package com.example.dictionary.service;

import java.util.List;

import com.example.dictionary.entity.Tag;

/**
 * Tag : サービス
 */
public interface TagService {
	/**
	 * 全タグを検索
	 */
	List<Tag> findAllTag();
	
	/**
	 * 指定されたIDのタグを検索
	 */
	Tag findByIdTag(Integer id);
	
	/**
	 * タグを登録
	 */
	void insertTag(Tag tag);
	
	/**
	 * タグを更新
	 */
	void updateTag(Tag tag);
	
	/**
	 * 指定されたIDのタグを検索
	 */
	void deleteTag(Integer id);

}
