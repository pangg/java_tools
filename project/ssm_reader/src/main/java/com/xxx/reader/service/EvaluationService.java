package com.xxx.reader.service;

import com.xxx.reader.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    /**
     * 按照图书编号查询有效短评
     * @param bookId 图书编号
     * @return 评论列表
     */
    public List<Evaluation> selectByBookId(Long bookId);
}
