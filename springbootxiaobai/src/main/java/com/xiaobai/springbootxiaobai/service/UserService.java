package com.xiaobai.springbootxiaobai.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaobai.springbootxiaobai.entity.User;
import com.xiaobai.springbootxiaobai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    UserMapper userMapper;

    public List<User> findAll(){
        List<User> all =  userMapper.findAll();
        return all;
    };
    public User find(Integer id){
        return getById(id);// mybatis-plus
    };
    public List<User> findLike(String likeName, String likeParam) {
        likeName = "xiaobai_user." + likeName;
        System.out.println(likeName+"likename===================");
        List<User> user = userMapper.findLike(likeName,likeParam);
        return user;
    }

    /*public int save(User user){
        return userMapper.save(user);
    };*/

    public boolean saveUser(User user){
        return save(user);// mybatis-plus
    }

    public boolean update(User user){
        return updateById(user);
    };

    public int remove(Integer id){
        return userMapper.remove(id);
    };

    public List<User> selectPageShou(Integer pageNum,Integer pageSize,String likeParam){
        return userMapper.selectPageShou(pageNum,pageSize,likeParam);
    }
    public int total(String likeParam){
        return userMapper.total(likeParam);
    }

}
