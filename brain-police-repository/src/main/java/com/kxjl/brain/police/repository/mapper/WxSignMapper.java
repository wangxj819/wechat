package com.kxjl.brain.police.repository.mapper;

import com.kxjl.brain.police.dto.wx.WxSignDTo;
import com.kxjl.brain.police.repository.bean.WxSignObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xjwang5 on 2017/5/27.
 */
public interface WxSignMapper
{

    Integer insertSign(WxSignDTo dto);

    Integer deleteSign(@Param("id") String id);

    List<WxSignObject> querySignInfo(@Param("id") String id);

    Integer updateSignInfo(WxSignObject wxSignObject);
}
