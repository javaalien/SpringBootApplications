package com.alien.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.entity.Post;
import com.alien.entity.PostStatus;
import com.alien.repository.PostRepository;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@PostMapping("/create")
	public String createPost(@RequestBody Post post, Principal principal) {
		post.setStatus(PostStatus.PENDING);
		post.setUserName(principal.getName());
		postRepository.save(post);
		return principal.getName() + " Your post published successfully, Required ADMIN/MODERATOR Action ! ";
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
	@GetMapping("/approvePost/{postId}")
	public String approvePost(@PathVariable int postId) {
		Post post = postRepository.findById(postId).get();
		post.setStatus(PostStatus.APPROVED);
		postRepository.save(post);
		return "Post Approved";
	}

	@GetMapping("/approveAll")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
	public String approveAll() {
		postRepository.findAll().stream().filter(post -> post.getStatus().equals(PostStatus.PENDING)).forEach(post -> {
			post.setStatus(PostStatus.APPROVED);
			postRepository.save(post);
		});
		return "Approved all posts !";
	}

	@GetMapping("/removePost/{postId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
	public String removePost(@PathVariable int postId) {
		Post post = postRepository.findById(postId).get();
		post.setStatus(PostStatus.REJECTED);
		postRepository.save(post);
		return "Post Rejected !";
	}

	@GetMapping("/rejectAll")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
	public String rejectAll() {
		postRepository.findAll().stream().filter(post -> post.getStatus().equals(PostStatus.PENDING)).forEach(post -> {
			post.setStatus(PostStatus.REJECTED);
			postRepository.save(post);
		});

		return "Rejected All posts !";
	}

	@GetMapping("/viewAll")
	public List<Post> viewAll() {
		return postRepository.findAll().stream().filter(post -> post.getStatus().equals(PostStatus.APPROVED))
				.collect(Collectors.toList());
	}
}
