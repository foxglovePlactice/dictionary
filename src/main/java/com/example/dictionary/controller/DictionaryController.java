package com.example.dictionary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dictionary.entity.Content;
import com.example.dictionary.entity.Word;
import com.example.dictionary.service.ContentService;
import com.example.dictionary.service.TagService;
import com.example.dictionary.service.WordService;

import lombok.RequiredArgsConstructor;

/**
 * Dictionary : コントローラ
 */
@Controller
@RequestMapping("/words")
@RequiredArgsConstructor
public class DictionaryController {
	/** DI */
	private final WordService wService;
	private final ContentService cService;
	private final TagService tService;
	
	//試行錯誤してたフィールドとコンストラクタ
//	private final WordRepository wordRepo;
//	
//	public DictionaryController() {
//		this.wService = null;
//		this.cService = null;
//		this.tService = null;
//		this.wordRepo = null;
//	}
//	
//	public DictionaryController(WordRepository wordRepo) {
//		this.wService = null;
//		this.cService = null;
//		this.tService = null;
//		this.wordRepo = wordRepo;
//	}
	
	/**
	 * 見出し語のリストを表示する
	 */
	@GetMapping
	public String list(Model model) {
//		Page<Word> wordPage = wordRepo.findAllWord(pageable);
//		model.addAttribute("wordPage", wordPage);
		
		model.addAttribute("words", wService.findAllWord());  //表示するHTMLでwService.findAllWord()を${words}として使えるようにする
		model.addAttribute("contents", cService.findAllContennt());
		model.addAttribute("tags", tService.findAllTag());
		return "dictionary/list";  //正常に動いたときの遷移先 templates直下からの相対パスを入れる
	}
	
	/**
	 * 指定されたIDの見出し語とその詳細を表示する
	 */
	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		//見出し語IDに対応する見出し語情報を取得
		Word word = wService.findByIdWord(id);
		if (word != null) {
			//対象データがあるならモデルに格納
			//詳細の文だけを取得して改行文字を<br>に変換
			List<Content> contents = new ArrayList<>();
			for (Content c : cService.findAllContennt()) {
				contents.add(c);
			}
			for (Content content : contents) {
				content.setContent(content.getContent().replaceAll("\n", "<br>"));
			}
			model.addAttribute("word", wService.findByIdWord(id));
//			model.addAttribute("contents", cService.findAllContennt());
			model.addAttribute("contents", contents);
			model.addAttribute("allTags", tService.findAllTag());
			model.addAttribute("tags", wService.findByIdTag(id));
			return "dictionary/detail";
		} else {
			//対象データがないならフラッシュメッセージを設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			//リダイレクト
			return "redirect:/words";
		}
	}
	
}
