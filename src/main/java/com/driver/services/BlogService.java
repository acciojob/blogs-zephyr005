package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        return  blogRepository1.findAll();

    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();

        //updating the blog details
        blog.setContent(content);
        blog.setTitle(title);
        User user = userRepository1.findById(userId).get();
        blog.setUser(user);
        blogRepository1.save(blog);
        //Updating the userInformation and changing its blogs

        List<Blog> currentBL = user.getBlogList();
        currentBL.add(blog);

        //setting again bloglist
        user.setBlogList(currentBL);

        userRepository1.save(user);



    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findById(blogId).get();

    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Blog blog=blogRepository1.findById(blogId).get();
        Image image=imageService1.createAndReturn(blog,description,dimensions);
        image.setBlog(blog);
        List<Image> imageList=blog.getImageList();
        if(imageList==null) imageList=new ArrayList<>();
        imageList.add(image);
        blog.setImageList(imageList);
        blogRepository1.save(blog);

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}