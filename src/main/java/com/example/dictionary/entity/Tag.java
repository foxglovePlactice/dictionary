package com.example.dictionary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * タグ : エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
	/** タグID */
	private Integer tagId;
	/** タグ */
	private String tagName;

}
