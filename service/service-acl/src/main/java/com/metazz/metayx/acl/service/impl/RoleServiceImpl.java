package com.metazz.metayx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metayx.acl.mapper.RoleMapper;
import com.metazz.metayx.acl.service.AdminRoleService;
import com.metazz.metayx.acl.service.RoleService;
import com.metazz.metayx.model.acl.AdminRole;
import com.metazz.metayx.model.acl.Role;
import com.metazz.metayx.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;

    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam,
                                      RoleQueryVo roleQueryVo) {
        String roleName = roleQueryVo.getRoleName();
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(roleName)) {
            wrapper.like(Role::getRoleName,roleName);
        }
        IPage<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);
        return rolePage;
    }

    @Override
    public Map<String, Object> getRoleByAdminId(Long adminId) {
        //1 查询所有角色
        List<Role> allRolesList = baseMapper.selectList(null);
        //2 根据用户id查询用户分配角色列表
        //2.1 根据用户id查询 用户角色关系表 admin_role 查询用户分配角色id列表
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,adminId);
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);
        //2.2 通过第一步返回集合，获取所有角色id的列表List<AdminRole> -- List<Long>
        List<Long> roleIdsList =
                adminRoleList.stream()
                        .map(item -> item.getRoleId())
                        .collect(Collectors.toList());
        //2.3 创建新的list集合，用于存储用户配置角色
        List<Role> assignRoleList = new ArrayList<>();
        //2.4 遍历所有角色列表 allRolesList，得到每个角色
        //判断所有角色里面是否包含已经分配角色id，封装到2.3里面新的list集合
        for (Role role:allRolesList) {
            if(roleIdsList.contains(role.getId())) {
                assignRoleList.add(role);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("allRolesList",allRolesList);
        result.put("assignRoles",assignRoleList);
        return result;
    }

    @Override
    public void saveAdminRole(Long adminId, Long[] roleIds) {
        //1 删除用户已经分配过的角色数据
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,adminId);
        adminRoleService.remove(wrapper);
        //2 重新分配
        List<AdminRole> list = new ArrayList<>();
        for (Long roleId: roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            list.add(adminRole);
        }
        adminRoleService.saveBatch(list);
    }

}
