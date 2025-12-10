package com.example.dictionary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dictionary.entity.Content;
import com.example.dictionary.entity.Word;
import com.example.dictionary.form.CLForm;
import com.example.dictionary.form.ContentForm;
import com.example.dictionary.form.WordForm;
import com.example.dictionary.helper.ContentHelper;
import com.example.dictionary.helper.WordHelper;
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
	
	/**
	 * 見出し語のリストを表示する
	 */
	@GetMapping
	public String list(Model model) {
		model.addAttribute("words", wService.findAllWord());
		model.addAttribute("contents", cService.findAllContennt());
		model.addAttribute("tags", tService.findAllTag());
		return "dictionary/list";
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
			System.out.println(contents);
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
	
	/**
	 * 新規登録画面を表示する
	 */
	@GetMapping("/form")
	public String newWord(@ModelAttribute WordForm wForm, @ModelAttribute ContentForm cForm, Model model) {
		//新規登録画面の設定
		wForm.setIsNew(true);
		model.addAttribute("allTags", tService.findAllTag());
		return "dictionary/New";
	}
	
	/**
	 * 新規登録を実行する
	 */
	@PostMapping("/save")
	public String create(WordForm wForm, ContentForm cForm, RedirectAttributes attributes) {
		//エンティティへの変換
		Word word = WordHelper.convertWord(wForm);
		Content content = ContentHelper.convertContent(cForm, wForm);
		//登録実行
		wService.insertWord(word);
		content.setWordId(wService.findAllWord().getLast().getWordId());
		cService.insertContent(content);
		//フラッシュメッセージ
		attributes.addFlashAttribute("message", "新しい見出し語が作成されました");
		//PRGパターン
		return "redirect:/words";
	}
	
	/**
	 * 指定されたIDの修正画面を表示
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		//IDに対応する見出し語を取得
		Word target = wService.findByIdWord(id);
		List<Content> cTarget = cService.findAllContennt();
		if (target != null) {
			//対象データがあるならForm
			WordForm wForm = WordHelper.convertWordForm(target);
			List<ContentForm> cForm = new ArrayList<>();
			for (Content content : cTarget) {
				if (content.getWordId() == wForm.getWordId()) {
					cForm.add(ContentHelper.convertContentForm(content));
				}
			}
			CLForm cLForm = new CLForm(cForm);
//			ContentForm contentForm = cForm.getFirst();
			//モデルに格納
			model.addAttribute("wordForm", wForm);
//			model.addAttribute("contentTarget", cForm);
			model.addAttribute("cLForm", cLForm);
//			model.addAttribute("contentForm", contentForm);
			model.addAttribute("allTags", tService.findAllTag());
			model.addAttribute("haveList", wService.findByIdTag(id));
			return "dictionary/Edit";
		} else {
			//対象データがないならフラッシュメッセージを設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			//一覧画面にリダイレクト
			return "redirect:/words";
		}
	}
	
	/**
	 * Contentの入力領域を増やす
	 */
	@GetMapping(value = "/add/{id}", params = "add")
	public String addList(@ModelAttribute List<ContentForm> cForm, Model model) {
		
		// リスト最後尾に空の項目追加
		cForm.add(new ContentForm());
		
		return "dictionary/Edit";
	}
	
	/**
	 * 見出し語の情報を更新する
	 */
	@PostMapping("/update")
	public String update(WordForm wForm, CLForm cLForm, RedirectAttributes attributes) {
		//見出し語の更新
		Word word = WordHelper.convertWord(wForm);
		wService.updateWord(word);
		//詳細の更新
		for (ContentForm contents : cLForm.getCLForm()) {
			Content content = ContentHelper.convertContent(contents, wForm);
			cService.updateContent(content);
		}
		//フラッシュメッセージ
		attributes.addFlashAttribute("message", "見出し語が更新されました");
		//PRGパターン
		return "redirect:/words";
	}
}
