package com.kxjl.brain.police.repository.mapper;

import com.kxjl.brain.police.dto.wx.RegistUserDTO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by xxqin on 2017-05-08 09:54:37.
 */

public interface RegistUserMapper {

     Integer insertUser(RegistUserDTO  model);

     RegistUserDTO queryRegistUser(@Param("openid") String openid);
}
