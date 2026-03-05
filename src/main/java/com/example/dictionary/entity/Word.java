package com.example.dictionary.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 見出し語 : エンティティ
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
	/** 見出し語ID */
	@Id
	private Integer wordId;
	/** 見出し語 */
	private String word;
	/** 読み */
	private String pronounce;
	/** 所持タグ */
	private String haveTag;
	/** 追加日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
	/** 詳細 */
	@OneToMany(mappedBy="wordId")
	private List<Content> contents;

}
