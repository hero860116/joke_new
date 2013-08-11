package com.kelepi.common.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 默认的result实现。
 *
 * @author Michael Zhou
 * @version $Id: ResultSupport.java 1298 2005-03-15 01:17:27Z baobao $
 */
public class ResultSupport
        implements Result {
    private static final long serialVersionUID = 3976733653567025460L;
    private boolean           success         = true;
    private Map               models          = new HashMap(4);
    private String            defaultModelKey = DEFAULT_MODEL_KEY;

    /**
     * 创建一个result。
     */
    public ResultSupport() {
    }

    /**
     * 创建一个result。
     *
     * @param success 是否成功
     */
    public ResultSupport(boolean success) {
        this.success = success;
    }

    /**
     * 请求是否成功。
     *
     * @return 如果成功，则返回<code>true</code>
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置请求成功标志。
     *
     * @param success 成功标志
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }


    /**
     * 取得model对象。
     * 
     * <p>
     * 此调用相当于<code>getModels().get(DEFAULT_MODEL_KEY)</code>。
     * </p>
     *
     * @return model对象
     */
    public Object getDefaultModel() {
        return models.get(DEFAULT_MODEL_KEY);
    }

    /**
     * 设置model对象。
     * 
     * <p>
     * 此调用相当于<code>getModels().put(DEFAULT_MODEL_KEY, model)</code>。
     * </p>
     *
     * @param model model对象
     */
    public void setDefaultModel(Object model) {
        setModel(DEFAULT_MODEL_KEY, model);
    }

    /**
     * 取得所有model对象。
     *
     * @return model对象表
     */
    public Map getModels() {
        return models;
    }

    public void setModel(String key, Object model) {
        //To change body of implemented methods use File | Settings | File Templates.
        models.put(key, model);
    }

    public Object getModule(String key) {
        return getModels().get(key);
    }

    /**
     * 转换成字符串的表示。
     *
     * @return 字符串表示
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Result {\n");
        buffer.append("    success    = ").append(success).append(",\n");
        buffer.append("    models     = {");

        if (models.isEmpty()) {
            buffer.append("}\n");
        } else {
            buffer.append("\n");

            for (Iterator i = models.entrySet().iterator(); i.hasNext();) {
                Map.Entry entry = (Map.Entry) i.next();
                Object    key   = entry.getKey();
                Object    value = entry.getValue();

                buffer.append("        ").append(key).append(" = ");

                buffer.append(value);

                if (i.hasNext()) {
                    buffer.append(",");
                }

                buffer.append("\n");
            }

            buffer.append("    }\n");
        }

        buffer.append("}");

        return buffer.toString();
    }
}
