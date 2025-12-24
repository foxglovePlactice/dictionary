package com.example.dictionary.form;

import java.util.List;

import com.example.dictionary.entity.Content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 見出し語 : Form
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordForm {
	/** 見出し語ID　*/
	private Integer wordId;
	/** 見出し語 */
	private String word;
	/** 読み */
	private String pronounce;
	/** タグ所持状況 */
	private String haveTag;
	/** 詳細 */
	private List<Content> contents;

}
