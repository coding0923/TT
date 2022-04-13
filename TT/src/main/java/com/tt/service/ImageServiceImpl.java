package com.tt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.ImageDTO;
import com.tt.mapper.ImageMapper;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public boolean uploadImage(ImageDTO params) {
        int queryResult = 0;

        System.out.println("//" + queryResult);
        queryResult = imageMapper.insertImage(params);

        System.out.println(queryResult);

        return (queryResult == 1) ? true : false;
    }

}