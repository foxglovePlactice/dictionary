package com.example.dictionary.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dictionary.entity.Tag;
import com.example.dictionary.repository.TagMapper;
import com.example.dictionary.service.TagService;

import lombok.RequiredArgsConstructor;

/**
 * Tag : サービス実装クラス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
	
	/** DI */
	private final TagMapper tMapper;

	@Override
	public List<Tag> findAllTag() {
		// TODO 自動生成されたメソッド・スタブ
		return tMapper.selectAll();
	}

	@Override
	public Tag findByIdTag(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return tMapper.selectById(id);
	}

	@Override
	public void insertTag(Tag tag) {
		// TODO 自動生成されたメソッド・スタブ
		tMapper.insert(tag);

	}

	@Override
	public void updateTag(Tag tag) {
		// TODO 自動生成されたメソッド・スタブ
		tMapper.update(tag);

	}

	@Override
	public void deleteTag(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		tMapper.delete(id);

	}

}
