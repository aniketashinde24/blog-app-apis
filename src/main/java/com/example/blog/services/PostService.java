package com.example.blog.services;

import java.util.List;

import com.example.blog.payloads.PostDto;

public interface PostService {

	// create

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update

	PostDto updatePost(PostDto postDto, Integer postId);

	// delete

	void deletePost(Integer postId);

	// getAll

	List<PostDto> getAllPost();

	// get single
	PostDto getSinglePost(Integer postId);

	// get All post by user

	List<PostDto> getAllPostByUser(Integer userId);

	// get all post by category
	List<PostDto> getAllPostByCategory(Integer categoryId);

}
