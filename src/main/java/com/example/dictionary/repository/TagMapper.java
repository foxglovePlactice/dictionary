package com.example.dictionary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.dictionary.entity.Tag;

/**
 * Tag : リポジトリ
 */
@Mapper
public interface TagMapper {
	/**
	 * 全てのタグを取得
	 */
	List<Tag> selectAll();
	
	/**
	 * 指定されたIDのタグを取得
	 */
	Tag selectById(@Param("tagId") Integer id);
	
	/**
	 * タグを登録
	 */
	void insert(Tag tag);
	
	/**
	 * タグを更新
	 */
	void update(Tag tag);
	
	/**
	 * 指定されたIDのタグを削除
	 */
	void delete(@Param("tagId") Integer id);

}
