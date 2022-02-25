package com.xiaobai.springbootxiaobai.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaobai.springbootxiaobai.entity.User;
import com.xiaobai.springbootxiaobai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll() {
        List<User> all = userService.findAll();
        return all;
    }

    @GetMapping("/find/{id}")
    public User find(@PathVariable("id") Integer id) {
        return userService.find(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody User user) {
        return userService.updateById(user);
    }

    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("/remove/{id}")
    public int remove(@PathVariable("id") Integer id) {
        return userService.remove(id);
    }

    @PostMapping("/remove/batch")
    public boolean removeBatch(@RequestBody List<Integer> ids){
        return userService.removeBatchByIds(ids);
    }
    /**
     * 接口路径：/user/page?pageNum=3&pageSize=10
     * @param pageNum   分页的页数
     * @param pageSize 分页的每页显示的条数
     * @return
     */
   /* @GetMapping("/page")
    public Map<String,Object> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                   @RequestParam String likeParam){
        //分页查寻 自己写的
        pageNum = (pageNum-1) ;
        List data = userService.selectPage(pageNum,pageSize,likeParam);
        int total = userService.total(likeParam);
        Map<String, Object> mapPage = new HashMap<>();
        mapPage.put("data" , data);
        mapPage.put("total" , total);
        return mapPage;
    }*/

    @GetMapping("/page")
    public IPage<User> page(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String username,
                            @RequestParam(defaultValue = "") String email,
                            @RequestParam(defaultValue = "") String address){
        //分页查寻
        IPage<User> iPage = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.and(w ->w.like("nickname" ,nickname));  // 相当于 queryWrapper.like("nickname" ,nickname); 在SQL语句后加 AND与上个连接
        if (!"".equals(username)) {
            queryWrapper.like("username",username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email",email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address",address);
        }
        return userService.page(iPage, queryWrapper);
    }
}
