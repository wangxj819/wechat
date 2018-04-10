package com.kxjl.brain.police.repository.mapper;

import com.kxjl.brain.police.dto.wx.WXReportDTO;
import com.kxjl.brain.police.repository.bean.WXReportObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**微信报表Dao
 * @Author liyu
 * @Date 2017/5/18 15:29
 */
public interface WXReportMapper {

     int insertInfo(WXReportObject wo);

     void updateInfo(WXReportObject wo);

     List<WXReportDTO> queryInfo(WXReportObject wo);

     WXReportDTO queryById(@Param("id")Long id);

     void deleteInfo(@Param("id")Long id);
}
