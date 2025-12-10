package com.example.dictionary.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dictionary.entity.Content;
import com.example.dictionary.repository.ContentMapper;
import com.example.dictionary.service.ContentService;

import lombok.RequiredArgsConstructor;

/**
 * Content : サービス実装クラス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
	
	/** DI */
	private final ContentMapper cMapper;

	@Override
	public List<Content> findAllContennt() {
		// TODO 自動生成されたメソッド・スタブ
		return cMapper.selectAll();
	}

	@Override
	public Content findByIdContent(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return cMapper.selectById(id);
	}

	@Override
	public void insertContent(Content content) {
		// TODO 自動生成されたメソッド・スタブ
		cMapper.insert(content);

	}

	@Override
	public void updateContent(Content content) {
		// TODO 自動生成されたメソッド・スタブ
		cMapper.update(content);

	}

	@Override
	public void deleteContent(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		cMapper.delete(id);

	}
	
	@Override
	public void updateAllContent(List<Content> contents) {
		// TODO 自動生成されたメソッド・スタブ
		for (Content content : contents) {
			cMapper.update(content);
		}
	}

}
