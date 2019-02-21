package com.redis.demo.Nineteen;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:MyBatisTest/spring-ceshi-mybatis19.xml"})
@EnableTransactionManagement

public class Test1 {
    @Autowired
    RoleService roleService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private NewRoleMapper roleMapper;
    @Test
    public void test1() {
        int id=11;

        roleService.insert(new Role(id,"redis"+id));

        Role role1 = (Role) redisTemplate.opsForValue().get("redis_role_"+id);
        System.out.println( "insert后redis缓存中: " + role1.toString()+"\n");

        roleService.update(new Role(id,"redis-update"+id ));
        System.out.println("update后缓存中数据: "+roleService.getRole(id).toString());
        System.out.println("update后数据库中数据:"+roleMapper.getRole(id).toString()+"\n");

        roleService.delete(id);
       /* System.out.println("delete后缓存中数据: "+roleService.getRole(id).toString());*/

        System.out.println("delete后数据库中数据:"+roleMapper.getRole(id));

    }
}
