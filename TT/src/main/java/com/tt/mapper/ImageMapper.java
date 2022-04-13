package com.tt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.ImageDTO;

@Mapper
public interface ImageMapper {

    public int insertImage(ImageDTO params);

}
