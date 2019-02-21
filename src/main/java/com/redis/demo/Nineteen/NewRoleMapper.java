package com.redis.demo.Nineteen;

import org.springframework.stereotype.Repository;

@Repository
public interface NewRoleMapper {
    public Role getRole(int id);
    public void update(Role role);
    public void delete(int id);
    public void insert(Role role);
}
