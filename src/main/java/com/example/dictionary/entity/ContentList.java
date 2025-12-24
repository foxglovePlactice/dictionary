package com.example.dictionary.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 詳細List : エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentList {
	/** wordId */
	private Integer wordId;
	/** 詳細リスト */
	private List<Content> contentList;

}