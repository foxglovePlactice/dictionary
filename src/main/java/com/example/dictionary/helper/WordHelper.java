package com.example.dictionary.helper;

import com.example.dictionary.entity.Word;
import com.example.dictionary.form.WordForm;

/**
 * 見出し語 : Helper
 */
public class WordHelper {  //エンティティとフォームの相互変換 正直なんであるのかよくわかってない そのままでよくない？
	/**
	 * Wordへの変換
	 */
	public static Word convertWord(WordForm wForm) {
		Word word = new Word();
		word.setWordId(wForm.getWordId());
		word.setWord(wForm.getWord());
		word.setPronounce(wForm.getPronounce());
		word.setHaveTag(wForm.getHaveTag());
		word.setContents(wForm.getContents());
		
		return word;
	}
	
	/**
	 * WordFormへの変換
	 */
	public static WordForm convertWordForm(Word word) {
		WordForm wForm = new WordForm();
		wForm.setWordId(word.getWordId());
		wForm.setWord(word.getWord());
		wForm.setPronounce(word.getPronounce());
		wForm.setHaveTag(word.getHaveTag());
		wForm.setContents(word.getContents());
		
		return wForm;
	}

}
