package com.example.dictionary.helper;

import com.example.dictionary.entity.Content;
import com.example.dictionary.form.ContentForm;
import com.example.dictionary.form.WordForm;

/**
 * 詳細 : Helper
 */
public class ContentHelper {
	/**
	 * Contentへの変換
	 */
	public static Content convertContent(ContentForm cForm, WordForm wForm) {
		Content content = new Content();
		content.setContentId(cForm.getContentId());
		content.setWordId(wForm.getWordId());
		content.setHaveTag(cForm.getHaveTag());
		content.setContent(cForm.getContent());
		
		return content;
	}
	
	/**
	 * ContentFormへの変換
	 */
	public static ContentForm convertContentForm(Content content) {
		ContentForm cForm = new ContentForm();
		cForm.setContentId(content.getContentId());
		cForm.setWordId(content.getWordId());
		cForm.setHaveTag(content.getHaveTag());
		cForm.setContent(content.getContent());
		
		return cForm;
	}

}
