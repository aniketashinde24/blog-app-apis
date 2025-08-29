package com.example.blog.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.config.AppConstant;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.services.FileService;
import com.example.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// get single post by id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId) {
		PostDto singlePost = this.postService.getSinglePost(postId);

		return new ResponseEntity<PostDto>(singlePost, HttpStatus.OK);
	}

	// get by user
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> postByUser = this.postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser, HttpStatus.OK);
	}

	// get by category
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postByCatgory = this.postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postByCatgory, HttpStatus.OK);
	}

	// get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {
		PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}

	// delete by id
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true),
				HttpStatus.BAD_REQUEST);
	}

	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePoEntity(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatePost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	// search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {

		List<PostDto> post = this.postService.searchPost(keyword);

		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);

	}

	// upload image
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException {
		PostDto postDto = this.postService.getSinglePost(postId);
		String fileName = this.fileService.uploadImage(path, image);

		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
		@GetMapping("/post/image/{imageName}")
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

}
