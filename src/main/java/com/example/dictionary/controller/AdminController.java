package com.example.dictionary.controller;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.example.dictionary.entity.Content;
import com.example.dictionary.entity.Tag;
import com.example.dictionary.entity.Word;
import com.example.dictionary.form.CLForm;
import com.example.dictionary.form.ContentForm;
import com.example.dictionary.form.TagForm;
import com.example.dictionary.form.WordForm;
import com.example.dictionary.helper.ContentHelper;
import com.example.dictionary.helper.TagHelper;
import com.example.dictionary.helper.WordHelper;
import com.example.dictionary.service.ContentService;
import com.example.dictionary.service.TagService;
import com.example.dictionary.service.WordService;

import lombok.RequiredArgsConstructor;

/**
 * Dictionary admin : コントローラ
 */
@Controller
@RequestMapping("/wordsAdmin")
@RequiredArgsConstructor
public class AdminController {
	/** DI */
	private final WordService wService;
	private final ContentService cService;
	private final TagService tService;
	
	/**
	 * 管理者用リスト
	 */
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("words", wService.findAllWord());
		model.addAttribute("contents", cService.findAllContennt());
		model.addAttribute("tags", tService.findAllTag());
		return "dictionary/adminList";
	}
	
	/**
	 * 管理者用詳細
	 */
	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		Word word = wService.findByIdWord(id);
		if (word != null) {
			List<Content> contents = new ArrayList<>();
			for (Content c : cService.findAllContennt()) {
				contents.add(c);
			}
			for (Content c : contents) {
				c.setContent(c.getContent().replaceAll("\n", "<br>"));
			}
			model.addAttribute("word", word);
			model.addAttribute("contents", contents);
			model.addAttribute("allTags", tService.findAllTag());
			model.addAttribute("tags", wService.findByIdTag(id));
			return "dictionary/adminDetail";
		} else {
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			return "redirect:/words";
		}
	}
	
	/**
	 * 管理者用タグ関係画面
	 */
	@GetMapping("/tag")
	public String tag(Model model) {
		model.addAttribute("tags", tService.findAllTag());
		return "dictionary/adminTag";
	}
	
	/**
	 * 新規登録画面を表示する
	 */
	@GetMapping("/form")
	public String newWord(@ModelAttribute WordForm wForm, @ModelAttribute ContentForm cForm, Model model) {
		//新規登録画面の設定
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
	 * 指定された見出し語IDの編集画面を表示
	 */
	@GetMapping("/editWord/{id}")
	public String editWord(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		//IDに対応する見出し語を取得
		Word target = wService.findByIdWord(id);
		List<Content> cTarget = cService.findAllContennt();
		if (target != null) {
			//対象データがあるならForm
			WordForm wForm = WordHelper.convertWordForm(target);
			List<ContentForm> cForm = new ArrayList<>();
			List<Content> cList = new ArrayList<>();
			for (Content content : cTarget) {
				if (content.getWordId() == wForm.getWordId()) {
					cForm.add(ContentHelper.convertContentForm(content));
					cList.add(content);
				}
			}
			CLForm cLForm = new CLForm(wForm.getWordId(), cList);
			ContentForm contentForm = cForm.getFirst();
			wForm.setContents(cList);
			//モデルに格納
			model.addAttribute("wordForm", wForm);
			model.addAttribute("cLForm", cLForm);
			model.addAttribute("contentForm", contentForm);
			model.addAttribute("allTags", tService.findAllTag());
			model.addAttribute("haveList", wService.findByIdTag(id));
			return "dictionary/updateWord";
		} else {
			//対象データがないならフラッシュメッセージを設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			//一覧画面にリダイレクト
			return "redirect:/wordsAdmin/list";
		}
	}
	
	/**
	 * 見出し語の情報を更新する
	 */
	@PostMapping("/updateWord")
	public String update(WordForm wForm, RedirectAttributes attributes) {
		//見出し語の更新
		List<Content> cList = new ArrayList<>();
		for (Content content : cService.findAllContennt()) {
			if (wForm.getWordId() == content.getWordId()) {
				cList.add(content);
			}
		}
		wForm.setContents(cList);
		Word word = WordHelper.convertWord(wForm);
		wService.updateWord(word);
		
		//フラッシュメッセージ
		attributes.addFlashAttribute("message", "見出し語が更新されました");
		//PRGパターン
		return "redirect:/wordsAdmin/list";
	}
	
	/**
	 * 詳細追加画面を表示する
	 */
	@GetMapping("/addContent/{id}")
	public String addContent(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		if (wService.findByIdWord(id) != null) {
			WordForm wForm = WordHelper.convertWordForm(wService.findByIdWord(id));
			List<Content> contents = new ArrayList<>();
			for (Content addContent : cService.findAllContennt()) {
				if (addContent.getWordId() == id) {
					addContent.setContent(addContent.getContent().replaceAll("\n", "<br>"));
					contents.add(addContent);
				}
			}
			ContentForm cForm = new ContentForm();
			cForm.setWordId(id);
			model.addAttribute("wordForm", wForm);
			model.addAttribute("newContent", cForm);
			model.addAttribute("cLForm", contents);
			return "dictionary/InsertContent";
		} else {
			//対象データがないならフラッシュメッセージを設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			//一覧画面にリダイレクト
			return "redirect:/wordsAdmin";
		}
	}
	
	/**
	 * 詳細追加を実行する
	 */
	@PostMapping("/insertContent")
	public String insertContent(ContentForm cForm, RedirectAttributes attributes, UriComponentsBuilder builder) {
		//詳細を持ってきて、エンティティに戻す
		Content content = ContentHelper.convertContent(cForm, WordHelper.convertWordForm(wService.findByIdWord(cForm.getWordId())));
		cService.insertContent(content);
		//追加後のフラッシュメッセージ
		attributes.addFlashAttribute("message", "詳細が追加されました");
		//リダイレクト先URLを作成
		URI location = builder.path("/wordsAdmin/" + content.getWordId()).build().toUri();
		//PRGパターン
		return "redirect:" + location.toString();
	}
	
	/**
	 * 詳細編集画面を表示
	 */
	@GetMapping("/editContent/{id}")
	public String editContent(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		model.addAttribute("contentForm", ContentHelper.convertContentForm(cService.findByIdContent(id)));
		model.addAttribute("wordForm", WordHelper.convertWordForm(wService.findByIdWord(cService.findByIdContent(id).getWordId())));
		return "dictionary/updateContent";
	}
	
	/**
	 * 詳細編集を実行
	 */
	@PostMapping("/updateContent")
	public String updateContent(ContentForm cForm, RedirectAttributes attributes) {
		//詳細を持ってきて、エンティティに戻す
		Content content = ContentHelper.convertContent(cForm, WordHelper.convertWordForm(wService.findByIdWord(cForm.getWordId())));
		cService.updateContent(content);
		//追加後のフラッシュメッセージ
		attributes.addFlashAttribute("message", "詳細が追加されました");
		//PRGパターン
		return "redirect:/words";
	}
	
	/**
	 * タグの追加画面を表示
	 */
	@GetMapping("/addTag")
	public String addTag(@ModelAttribute TagForm tForm, Model model) {
		model.addAttribute("allTags", tService.findAllTag());
		model.addAttribute("newTag", new TagForm());
		return "dictionary/insertTag";
	}
	
	/**
	 * タグを追加
	 */
	@PostMapping("/insertTag")
	public String insertTag(TagForm tForm, RedirectAttributes attributes) {
		Tag tag = TagHelper.convertTag(tForm);
		tService.insertTag(tag);
		attributes.addFlashAttribute("message", "新しいタグが作成されました");
		return "redirect:/wordsAdmin/tag";
	}
	
	/**
	 * タグ編集画面を表示
	 */
	@GetMapping("/editTag/{id}")
	public String editTag(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		model.addAttribute("allTags", tService.findAllTag());
		model.addAttribute("tagForm", tService.findByIdTag(id));
		
		return "dictionary/updateTag";
	}
	
	/**
	 * タグを更新
	 */
	@PostMapping("updateTag")
	public String updateTag(TagForm tForm, RedirectAttributes attributes) {
		Tag tag = TagHelper.convertTag(tForm);
		tService.updateTag(tag);
		attributes.addFlashAttribute("message", "タグを編集しました");
		
		return "redirect:/wordsAdmin/tag";
	}

}
