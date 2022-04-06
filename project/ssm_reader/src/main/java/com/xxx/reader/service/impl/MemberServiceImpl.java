package com.xxx.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.reader.entity.Member;
import com.xxx.reader.exception.BussiException;
import com.xxx.reader.mapper.MemberMapper;
import com.xxx.reader.md5.MD5Utils;
import com.xxx.reader.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;

    @Transactional
    @Override
    public void createMember(String username, String password, String nickname) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",username);
        List list = memberMapper.selectList(queryWrapper);
        if(list.size()>0){
            //用户名已经存在抛出异常
            throw new BussiException("M01","用户名已存在！");
        }
        Member member=new Member();
        member.setUsername(username);
        member.setCreateTime(new Date());
        int salt=new Random().nextInt(1000)+1000;
        String md5 = MD5Utils.md5Digest(password, salt);
        member.setSalt(salt);
        member.setPassword(md5);
        member.setNickname(nickname);
        memberMapper.insert(member);
    }

    @Override
    public Member checkLogin(String username, String password) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("username",username);
        Member member = memberMapper.selectOne(queryWrapper);
        if(member==null){
            throw new BussiException("M02","用户名错误");
        }
        String md5 = MD5Utils.md5Digest(password, member.getSalt());
        if(!md5.equals(member.getPassword())){
            throw new BussiException("M03","密码错误");
        }
        return member;
    }


}
