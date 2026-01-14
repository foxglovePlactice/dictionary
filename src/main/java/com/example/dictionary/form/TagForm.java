package com.example.dictionary.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * タグ : Form
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagForm {
	/** タグID */
	private Integer tagId;
	/** タグ */
	private String tagName;

}
