package com.lt.dao;

import com.lt.entity.BUser;
import com.lt.tools.SqlBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户
 * Created by LT on 2017/8/2.
 */
@Component
@Mapper
public interface UserDao{

    /**
     * 新增用户
     *
     * @param user 新增对象
     * @return 结果
     */
    @Insert("INSERT INTO B_USER(userId, userName, mobilePhone, password, email, sysUserName, registTime, lastResetPasswordTime)" +
            " VALUES(#{userId},#{userName},#{mobilePhone},#{password},#{email},#{sysUserName}, #{registTime}, #{lastResetPasswordTime})")
    int add(BUser user);

    /**
     * 删除
     *
     * @param ids 需要删除的用户的ID
     * @return 结果
     */
    @DeleteProvider(type = UserSqlBuilder.class, method = "deleteSqlBuilder")
    int delete(@Param("ids") String[] ids);

    /**
     * 更新
     *
     * @param user 更新对象
     * @return 结果
     */
    @Update("UPDATE B_USER SET userName = #{userName}, mobilePhone = #{mobilePhone}," +
            " password = #{password}, email = #{email}, sysUserName = #{sysUserName} WHERE userId = #{userId}")
    int update(BUser user);

    /**
     * 根据条件查询用户信息
     *
     * @param params 条件（key=列命,value=条件值）
     * @return
     */
    @SelectProvider(type = UserSqlBuilder.class, method = "querySqlBuilder")
    List<BUser> query(Map<String, Object> params);

    /**
     * 根据系统用户名查询用户信息
     *
     * @param sysUserName 系统用户名
     * @return
     */
    @Select("SELECT userId FROM B_USER WHERE sysUserName = #{sysUserName}")
    BUser queryBySysUserName(String sysUserName);

    /**
     * 根据ID查询用户信息
     */
    @Select("SELECT userId, userName, mobilePhone, password, email, sysUserName FROM B_USER where userId = #{id}")
    BUser queryById(@Param("id") String id);

    class UserSqlBuilder {

        /**
         * 删除语句构造
         */
        public String deleteSqlBuilder(@Param("ids") String[] ids) {
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM B_USER WHERE userId IN(");
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
         * 查询语句构造
         */
        public String querySqlBuilder(final Map<String, Object> params) {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT userId, userName, mobilePhone, password, email, sysUserName FROM B_USER ");
            try {
                sql.append(SqlBuilder.getInstance().buildQuerySql(params, SqlBuilder.QUERY_TYPE_LIKE));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("构造SQL=============" + sql.toString());
            return sql.toString();
        }

    }

}
