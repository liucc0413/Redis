package com.redis.demo.Nineteen;



public interface RoleService {
    public void delete(int id);
    public Role getRole(int id);
    public Role update(Role role);
    public Role insert(Role role);
}
