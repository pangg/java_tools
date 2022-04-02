package com.xxx.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.reader.entity.Book;
import com.xxx.reader.entity.Evaluation;
import com.xxx.reader.entity.Member;
import com.xxx.reader.mapper.BookMapper;
import com.xxx.reader.mapper.EvaluationMapper;
import com.xxx.reader.mapper.MemberMapper;
import com.xxx.reader.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("evaluationService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {
    @Resource
    private EvaluationMapper evaluationMapper;

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private BookMapper bookMapper;


    /**
     * 按照图书编号查询有效短评
     *
     * @param bookId 图书编号
     * @return 评论列表
     */
    @Override
    public List<Evaluation> selectByBookId(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<Evaluation>();
        queryWrapper.eq("book_id", bookId);
        queryWrapper.eq("state", "enable");
        queryWrapper.orderByDesc("create_time");
        List<Evaluation> evaluationList = evaluationMapper.selectList(queryWrapper);
        for (Evaluation eva:evaluationList) {
            Member member = memberMapper.selectById(eva.getMemberId());
            eva.setMember(member);
            eva.setBook(book);
        }
        return evaluationList;
    }
}
