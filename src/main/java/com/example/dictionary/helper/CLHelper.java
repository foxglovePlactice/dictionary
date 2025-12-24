package com.example.dictionary.helper;

import com.example.dictionary.entity.ContentList;
import com.example.dictionary.form.CLForm;

/**
 * 詳細List : Helper
 */
public class CLHelper {
	/** ContentListへの変換 */
	public static ContentList convertCL(CLForm cLForm) {
		ContentList cL = new ContentList();
		cL.setContentList(cLForm.getCLForm());
		
		return cL;
	}
	
	/** CLFormへの変換 */
	public static CLForm convertCLForm(ContentList cL) {
		CLForm cLForm =  new CLForm();
		cLForm.setCLForm(cL.getContentList());
		
		return cLForm;
	}

}
