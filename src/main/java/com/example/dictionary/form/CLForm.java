package com.example.dictionary.form;

import java.util.List;

import com.example.dictionary.entity.Content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 詳細List : Form
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CLForm {
	/** wordId　*/
	private Integer wordId;
	/** ContentのList */
	private List<Content> cLForm;

}
