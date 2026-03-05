package com.example.dictionary.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dictionary.entity.Word;
import com.example.dictionary.repository.WordMapper;
import com.example.dictionary.repository.WordRepository;
import com.example.dictionary.service.WordService;

import lombok.RequiredArgsConstructor;

/**
 * Word : サービス実装クラス
 */
@Service
@Transactional
//@NoArgsConstructor
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {  //コントローラで使うメソッドのクラス 多少複雑な処理も書いていい
	
	/** DI */
	@Autowired
	private final WordMapper wMapper;
//	private final WordRepository wordRepo;
//	
	public WordServiceImpl() {
		this.wMapper = null;
//		this.wordRepo = null;
		
	}
	
	@Override
	public List<Word> findAllWord() {
		// TODO 自動生成されたメソッド・スタブ
		return wMapper.selectAll();
	}
	
//	@Override
//	public List<Word> findAllWord(Pageable pageable){
//		return wMapper.selectAll();
//	}

	@Override
	public Word findByIdWord(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return wMapper.selectById(id);
	}

	@Override
	public void insertWord(Word word) {
		// TODO 自動生成されたメソッド・スタブ
		wMapper.insert(word);

	}

	@Override
	public void updateWord(Word word) {
		// TODO 自動生成されたメソッド・スタブ
		wMapper.update(word);

	}

	@Override
	public void deleteWord(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		wMapper.delete(id);

	}
	
	@Override
	public List<Integer> findByIdTag(Integer id){
		// TODO 自動生成されたメソッド・スタブ
		List<Integer> list = new ArrayList<>();
		if (wMapper.selectByIdTag(id) != null) {
			String str = wMapper.selectByIdTag(id);
			while (str.indexOf(",") != -1) {
				list.add(Integer.parseInt(str.substring(0, str.indexOf(","))));
				str = str.substring(str.indexOf(",") + 1);
			}
			list.add(Integer.parseInt(str.substring(str.indexOf(",") + 1)));
		}
		return list;
	}

	public WordServiceImpl(WordRepository wordRepository) {
		this.wMapper = null;
//		this.wordRepo = wordRepository;
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
