package com.redis.demo.Nineteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private NewRoleMapper roleMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    @CacheEvict(value = "cacheManagerTest", key = "'redis_role_'+#id", beforeInvocation = false)
    public void delete(int id) {
        Role role1 = (Role) redisTemplate.opsForValue().get("redis_role_"+id);
        System.out.println( "delete redis缓存中: " + role1.toString()+"\n");
        roleMapper.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Cacheable(value = "cacheManagerTest", key = "'redis_role_'+#id")
    public Role getRole(int id) {

        return roleMapper.getRole(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "cacheManagerTest",key = "'redis_role_'+#result.id")
    public Role update(Role role) {
        roleMapper.update(role);
        Role role1 = (Role) redisTemplate.opsForValue().get("redis_role_"+role.getId());
        System.out.println( "service update redis缓存中: " + role1.toString()+"\n");
        return role;

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "cacheManagerTest",key = "'redis_role_'+#result.id")
    public Role insert(Role role) {
        roleMapper.insert(role);
        return role;
    }
}
