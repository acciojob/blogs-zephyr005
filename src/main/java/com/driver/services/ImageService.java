package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        List<Image> imageList = blog.getImageList();
        if(imageList==null) imageList=new ArrayList<>();
        imageList.add(image);

        blog.setImageList(imageList);
        blogRepository.save(blog);

        return image;

    }

    public void deleteImage(Image image){
        imageRepository2.delete(image);
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        if (screenDimensions.split("X").length == 2 || Objects.nonNull(image)) {
            Integer maxLength = Integer.parseInt(screenDimensions.split("X")[0]) / Integer.parseInt(image.getDimensions().split("X")[0]) ;
            Integer maxBreadth = Integer.parseInt(screenDimensions.split("X")[1]) / Integer.parseInt(image.getDimensions().split("X")[1]);
            return maxLength * maxBreadth;
        }
        return 0;


    }
}