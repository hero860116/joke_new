package com.kelepi.dal.dao;

import com.kelepi.dal.dataobject.UserDO;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-8-6 下午11:08
 */
public interface UserDAO {
    void save(UserDO userDO);

    void update(UserDO userDO);

    UserDO get(Long id);

    void delete(Long id);

    /**
     * 根据用户的来源类型和id找到对应的
     * @param sourceType
     * @param sourceId
     * @return
     */
    UserDO getUserBySource(Integer sourceType, String sourceId);

    /**
     * 更新完善用户资料
     * @param nikeName
     * @param email
     * @param faceImageUrl
     * @param id
     */
    void updateInfo(String nikeName, String email, String faceImageUrl, Long id);

    /**
     * 获取用户列表
     * @param userIds
     * @return
     */
    List<UserDO> getUserDOs(List<Long> userIds);
}
