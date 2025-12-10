package com.example.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 見出し語 : エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
	/** 見出し語ID */
	private Integer wordId;
	/** 見出し語 */
	private String word;
	/** 読み */
	private String pronounce;
	/** 所持タグ */
	private String haveTag;

}
