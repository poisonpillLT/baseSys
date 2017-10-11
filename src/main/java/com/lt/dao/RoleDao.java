package com.lt.dao;

import com.lt.entity.BRole;
import com.lt.tools.SqlBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * Created by LT on 2017/8/4.
 */
@Component
@Mapper
public interface RoleDao {

    /**
     * 增
     */
    @Insert("Insert INTO B_ROLE(roleId, roleName, roleCode) VALUES(#{roleId}, #{roleName}, #{roleCode})")
    int add(BRole role);

    /**
     * 删
     */
    @DeleteProvider(type = RoleSqlBuilder.class, method = "delete")
    int delete(@Param("ids") String[] ids);

    /**
     * 改
     */
    @UpdateProvider(type = RoleSqlBuilder.class, method = "update")
    int update(BRole role);

    /**
     * 查
     */
    @SelectProvider(type = RoleSqlBuilder.class, method = "query")
    List<BRole> query(BRole role);

    /**
     * 根据用户查角色
     *
     * @param userId 用户ID
     * @return 角色
     */
    @Select("SELECT R.roleId,R.roleName,R.roleCode FROM B_ROLE R" +
            " LEFT JOIN B_USER_ROLE UR ON R.roleId = UR.roleId " +
            " WHERE UR.userId = #{userId}")
    List<BRole> getRoleByUser(String userId);

    class RoleSqlBuilder {

        /**
         * 删除语句构建
         */
        public String delete(@Param("ids") String[] ids) {
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM B_ROLE WHERE roleId IN(");
            for (int i = 0; i < ids.length; i++) {
                sql.append("'").append(ids[i]).append("'");
                if (i < ids.length - 1) {
                    sql.append(",");
                }
            }
            sql.append(")");
            return sql.toString();
        }

        /**
         * 更新语句构建
         */
        public String update(final BRole role) {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE B_ROLE SET ");
            if (!StringUtils.isEmpty(role.getRoleName())) {
                sql.append("roleName = '").append(role.getRoleName()).append("',");
            }
            if (!StringUtils.isEmpty(role.getRoleCode())) {
                sql.append("roleCode = '").append(role.getRoleCode()).append("'");
            }
            sql.append(" WHERE roleId = '").append(role.getRoleId()).append("'");
            return sql.toString();
        }

        /**
         * 查询语句构建
         */
        public String query(final Map<String, Object> params) {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT roleId, roleName, roleCode FROM B_ROLE ");
            try {
                sql.append(SqlBuilder.getInstance().buildQuerySql(params, SqlBuilder.QUERY_TYPE_LIKE));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sql.toString();
        }

    }

}
