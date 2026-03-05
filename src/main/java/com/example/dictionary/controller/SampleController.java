package com.example.dictionary.controller;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dictionary.entity.Word;
import com.example.dictionary.repository.WordRepository;
import com.example.dictionary.service.ContentService;
import com.example.dictionary.service.TagService;
import com.example.dictionary.service.WordService;

import lombok.RequiredArgsConstructor;

/**
 * Sample : コントローラ
 */
@Controller
@RequestMapping("/sample")
//@NoArgsConstructor
@RequiredArgsConstructor
public class SampleController {
	/** DI */
	@Autowired
	private final WordService wService;
	@Autowired
	private final ContentService cService;
	@Autowired
	private final TagService tService;
	
//	@Autowired
	private final WordRepository wordRepo;
	
//	@Autowired
	private EntityManager em;
	
//	public SampleController() {
//		this.cService = new ContentServiceImpl(null);
//		this.tService = new TagServiceImpl(null);
//		this.wordRepo = null;
//		this.wService = new WordServiceImpl(this.wordRepo);
//	}
	
//	@Autowired
//	public SampleController(WordRepository wordRepo) {
//		this.wService = null;
//		this.cService = null;
//		this.tService = null;
//		this.wordRepo = wordRepo;
//	}
	
	/**
	 * 見出し語のリストを表示する
	 */
	@GetMapping
	public String list(Model model, @PageableDefault(page = 1, size = 2, sort = "updatedAt") Pageable pageable) {
		System.err.println(wordRepo);
		Word word = wService.findByIdWord(0);
		Page<Word> wordPage = wordRepo.findByWordId(0, pageable);
		model.addAttribute("wordPage", wordPage);
		
		model.addAttribute("words", wService.findAllWord());  //表示するHTMLでwService.findAllWord()を${words}として使えるようにする
		model.addAttribute("contents", cService.findAllContennt());
		model.addAttribute("tags", tService.findAllTag());
		return "dictionary/list";  //正常に動いたときの遷移先 templates直下からの相対パスを入れる
	}

}
