package com.lt.tools;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * sql 构造工具类
 * Created by Lt on 2017/8/4.
 */
public class SqlBuilder {

    public static String QUERY_TYPE_EQ = "=";
    public static String QUERY_TYPE_LIKE = "LIKE";
    public static SqlBuilder builder;

    public static SqlBuilder getInstance() {
        if (builder == null) {
            builder = new SqlBuilder();
        }
        return builder;
    }

    /**
     * 构造=查询条件
     *
     * @param params 条件，key是表字段名，value是条件值
     * @return 构造好的条件
     */
    @NotNull
    static String buildQueryCondition(final Map<String, Object> params, String queryType) throws Exception {
        StringBuffer condition = new StringBuffer(" WHERE ");
        params.forEach((s, o) -> {
            if (o instanceof Integer) {
                condition.append(s).append(" = ").append(o.toString());
            } else {
                if (QUERY_TYPE_LIKE.equals(queryType)) {
                    condition.append(s).append(" ").append(QUERY_TYPE_LIKE).append(" '%").append(o.toString()).append("%'");
                } else {
                    condition.append(s).append(" ").append(QUERY_TYPE_EQ).append(" '").append(o.toString()).append("'");
                }
            }
            condition.append(" and ");
        });
        condition.append(" 1=1 ");
        return condition.toString();
    }

    /**
     * 构造sql查询语句条件部分
     *
     * @param params    条件，key是表字段名，value是条件值
     * @param queryType =或者LIKE
     * @return
     * @throws Exception
     */
    public String buildQuerySql(Map<String, Object> params, String queryType) throws Exception {
        if (params == null) {
            return "";
        }
        if (StringUtils.isEmpty(queryType)) {
            queryType = QUERY_TYPE_EQ;
        }
        if (QUERY_TYPE_EQ.equals(queryType) || QUERY_TYPE_LIKE.equals(queryType)) {
            return buildQueryCondition(params, queryType);
        } else {
            throw new Exception("queryType 不可用，只能是‘=’和‘LIKE'其中之一.");
        }
    }

}
