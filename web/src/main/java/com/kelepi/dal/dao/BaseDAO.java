package com.kelepi.dal.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 *
 * 包装了sqlMapClientTemplate 的dao操作方法,扔出了checkedException,模仿common-dao.jar
 *
 * @create Mar 26, 2008
 * @author dongbai mailto:dongbai@taobao.com
 *
 */
public class BaseDAO {

    /**
     * 注入 sqlMapClientTemplate
     */
    private SqlMapClientTemplate sqlMapClientTemplate;

    public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
        this.sqlMapClientTemplate = sqlMapClientTemplate;
    }

    public Object queryForObject(String statementName, Object parameterObject) {
        return sqlMapClientTemplate.queryForObject(statementName, parameterObject);
    }

    public List<?> queryForList(String statementName, Object parameterObject) {
        return sqlMapClientTemplate.queryForList(statementName, parameterObject);
    }

    public Map<?, ?> queryForMap(String statementName, Object parameterObject, String keyProperty) {
        return sqlMapClientTemplate.queryForMap(statementName, parameterObject, keyProperty);
    }

    public int update(String statementName, Object parameterObject) {
        return sqlMapClientTemplate.update(statementName, parameterObject);
    }

    public Object insert(String statementName, Object parameterObject) {
        return sqlMapClientTemplate.insert(statementName, parameterObject);
    }

    public Object QueryForObject(String statementName) {
        return sqlMapClientTemplate.queryForObject(statementName);
    }

    public Object insertOrUpdate(String countStatementName, String insertStatementName, String updateStatementName,
                                 Object parameterObject) {
        Integer count = (Integer) this.queryForObject(countStatementName, parameterObject);
        if (null != count && count.intValue() > 0) {
            return this.update(updateStatementName, parameterObject);
        }
        return this.insert(insertStatementName, parameterObject);
    }

    public List<?> queryForList(String statementName) {
        return sqlMapClientTemplate.queryForList(statementName);
    }

    public Object queryForObject(String statementName) {
        return sqlMapClientTemplate.queryForObject(statementName);
    }

    public int delete(String statementName) {
        return sqlMapClientTemplate.delete(statementName);
    }

    public int delete(String statementName, Object parameterObject) {
        return sqlMapClientTemplate.delete(statementName, parameterObject);
    }

    /**
     * 批量更新
     * 只能对同类型的更新有效
     *
     * @param statementName
     * @param parameterObjects
     * @return
     */
    public int batchUpdate(final String statementName, final List<Object> parameterObjects) {

        return (Integer)sqlMapClientTemplate.execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                for (int i = 0; i < parameterObjects.size(); i++) {
                    executor.update(statementName, parameterObjects.get(i));
                }
                return executor.executeBatch();
            }
        });
    }

    /**
     * 批量插入
     * 只能对同类型的更新有效
     *
     * @param statementName
     * @param parameterObjects
     * @return
     */
    public int batchInsert(final String statementName, final List<Object> parameterObjects) {

        return (Integer)sqlMapClientTemplate.execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                for (int i = 0; i < parameterObjects.size(); i++) {
                    executor.insert(statementName, parameterObjects.get(i));
                }
                return executor.executeBatch();
            }
        });
    }
}
