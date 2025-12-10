package com.example.dictionary.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 詳細 : Form
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentForm {
	/** 詳細ID */
	private Integer contentId;
	/** 見出し語ID　*/
	private Integer wordId;
	/** タグ所持状況 */
	private String haveTag;
	/** 詳細 */
	private String content;

}
