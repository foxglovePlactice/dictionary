package com.example.dictionary.helper;

import com.example.dictionary.entity.Tag;
import com.example.dictionary.form.TagForm;

/**
 * タグ : Helper
 */
public class TagHelper {
	/**
	 * Tagへの変換
	 */
	public static Tag convertTag(TagForm tForm) {
		Tag tag = new Tag();
		tag.setTagId(tForm.getTagId());
		tag.setTagName(tForm.getTagName());
		
		return tag;
	}
	
	/**
	 * TagFormへの変換
	 */
	public static TagForm convertTagForm(Tag tag) {
		TagForm tForm = new TagForm();
		tForm.setTagId(tag.getTagId());
		tForm.setTagName(tag.getTagName());
		
		return tForm;
	}

}
