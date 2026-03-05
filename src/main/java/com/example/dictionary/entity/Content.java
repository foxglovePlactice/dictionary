package com.example.dictionary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 詳細 : エンティティ
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
	/** 詳細ID */
	@Id
	private Integer contentId;
	/** 見出し語ID */
	private Integer wordId;
	/** 所持タグ */
	private String haveTag;
	/** 詳細 */
	private String content;

}
