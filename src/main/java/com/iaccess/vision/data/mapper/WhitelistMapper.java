package com.iaccess.vision.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WhitelistMapper {

    List<String> searchIpAddress(@Param("clientName") String clientName,
                                 @Param("appName") String appName,
                                 @Param("envName") String envName);
}
