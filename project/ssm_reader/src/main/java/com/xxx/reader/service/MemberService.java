package com.xxx.reader.service;

import com.xxx.reader.entity.Evaluation;
import com.xxx.reader.entity.Member;
import com.xxx.reader.entity.MemberReadState;

public interface MemberService {
    public Member createMember(String username,String password,String nickname);

    public Member checkLogin(String username, String password);

    /**
     * 获取阅读状态
     * @param memberId 会员编号
     * @param bookId 图书编号
     * @return 阅读状态对象
     */
    public MemberReadState selectMemberReadState(Long memberId, Long bookId);


    /**
     * 更新阅读状态
     * @param memberId 会员编号
     * @param bookId   图书编号
     * @param readState 阅读状态
     * @return 阅读状态对象
     */
    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState);

    /**
     * 发布新的短评
     * @param memberId 会员编号
     * @param bookId  图书编号
     * @param score   评分
     * @param content  短评内容
     * @return  短评对象
     */
    public Evaluation evaluate(Long memberId, Long bookId, Integer score, String content);

    /**
     * 短评点赞
     * @param evaluationId 短评编号
     * @return 短评对象
     */
    public Evaluation enjoy(Long evaluationId);
}
