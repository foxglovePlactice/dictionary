package com.example.dictionary.service;

import java.util.List;

import com.example.dictionary.entity.Content;

/**
 * Content : サービス
 */
public interface ContentService {
	/**
	 * 全詳細を検索
	 */
	List<Content> findAllContennt();
	
	/**
	 * 指定されたIDの詳細を検索
	 */
	Content findByIdContent(Integer id);
	
	/**
	 * 詳細を登録
	 */
	void insertContent(Content content);
	
	/**
	 * 詳細を更新
	 */
	void updateContent(Content content);
	
	/**
	 * 指定されたIDの詳細を削除
	 */
	void deleteContent(Integer id);
	
	/**
	 * 複数の詳細を更新
	 */
	void updateAllContent(List<Content> contents);

}
