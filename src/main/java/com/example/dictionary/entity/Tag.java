package com.example.dictionary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * タグ : エンティティ
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
	/** タグID */
	@Id
	private Integer tagId;
	/** タグ */
	private String tagName;

}
