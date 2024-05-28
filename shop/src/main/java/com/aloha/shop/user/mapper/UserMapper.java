package com.aloha.shop.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.shop.user.model.UserAuth;
import com.aloha.shop.user.model.Users;

@Mapper
public interface UserMapper {

    // 로그인
    public Users login(String username) throws Exception;

    // 회원 조회
    public Users select(String username) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

}
