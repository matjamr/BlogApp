package com.example.testingproject.service;

import com.example.testingproject.controller.request.PostRequest.SavePostRequest;
import com.example.testingproject.controller.request.PostRequest.UpdatePostRequest;
import com.example.testingproject.controller.response.UserResponse.FindAllPostsResponse;
import com.example.testingproject.converter.PostConverter;
import com.example.testingproject.entity.Post;
import com.example.testingproject.repository.PostRepository;
import com.example.testingproject.service.exceptions.PostExceptions.PostCreationErrorException;
import com.example.testingproject.service.exceptions.PostExceptions.PostDoesNotExistsError;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    final private PostRepository postRepository;
    final private PostConverter postConverter;

    public PostService(final PostRepository postRepository, final PostConverter postConverter) {
        this.postRepository = postRepository;
        this.postConverter = postConverter;
    }

    public void save(final SavePostRequest request) throws PostCreationErrorException {
        final Post post = postConverter.toPost(request);
        if(postRepository.exists(post.getTitle(), post.getContent())) {
            throw new PostCreationErrorException("There has been error creating post: " + post);
        }

        postRepository.save(post);
    }

    public void delete(final Integer id) throws PostDoesNotExistsError {
        if(!postRepository.existsById(id)) {
            throw new PostDoesNotExistsError("Error with deleting post with id: " + id);
        }
        postRepository.deleteById(id);
    }

    public void update(final UpdatePostRequest request) throws PostDoesNotExistsError {
        if(!postRepository.existsById(request.getId())) {
            throw new PostDoesNotExistsError("Error with updating post with id: " + request.getId());
        }
        final Post post = postRepository.findById(request.getId()).get();
        post.setContent(request.getContent());
        post.setTitle(request.getTitle());

        postRepository.save(post);
    }


    public List<FindAllPostsResponse> findAll() {
        return postRepository.findAll().stream()
                .map(FindAllPostsResponse::new)
                .collect(Collectors.toList());
    }



}
