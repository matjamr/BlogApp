package com.example.testingproject.service;

import com.example.testingproject.controller.request.PostRequest.SavePostRequest;
import com.example.testingproject.controller.request.PostRequest.UpdatePostRequest;
import com.example.testingproject.controller.response.PostResponse.FindAllPostsResponse;
import com.example.testingproject.controller.response.PostResponse.PostResponse;
import com.example.testingproject.converter.PostConverter;
import com.example.testingproject.entity.Comment;
import com.example.testingproject.entity.Post;
import com.example.testingproject.repository.CommentRepository;
import com.example.testingproject.repository.PostRepository;
import com.example.testingproject.service.commentRequest.CommentPutRequest;
import com.example.testingproject.service.exceptions.CommentExceptions.CommentAlreadyExists;
import com.example.testingproject.service.exceptions.CommentExceptions.NoCommentException;
import com.example.testingproject.service.exceptions.PostExceptions.NoPostException;
import com.example.testingproject.service.exceptions.PostExceptions.PostCreationErrorException;
import com.example.testingproject.service.exceptions.PostExceptions.PostDoesNotExistsError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    final private PostRepository postRepository;
    final private CommentRepository commentRepository;

    final private PostConverter postConverter;

    public PostService(final PostRepository postRepository,
                       final PostConverter postConverter,
                       final CommentRepository commentRepository) {

        this.postRepository = postRepository;
        this.postConverter = postConverter;
        this.commentRepository = commentRepository;
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

    public PostResponse findById(final Integer id) throws NoPostException {
        if(!postRepository.existsById(id)) {
            throw new NoPostException("There is no post with given id: " + id);
        }

        return postConverter.toPostResponse(postRepository.findById(id).get());
    }

    // TODO
    // Add handling for user_id and post_id
    // rewrite put and patch mappings to use converters!
    public void addComment(final Comment comment) throws CommentAlreadyExists {
        if(commentRepository.existsByContent(comment.getContent())) {
            throw new CommentAlreadyExists("Comment with given content: " + comment.getContent() + " already exists");
        }

        commentRepository.save(comment);
    }

    public void deleteCommentById(final Integer id) throws NoCommentException {
        if(!commentRepository.existsById(id)) {
            throw new NoCommentException("There is no comment with given id: " + id);
        }

        commentRepository.deleteById(id);
    }

    public void changeComment(final CommentPutRequest request) throws NoCommentException {
        final Integer id = request.getId();
        if(!commentRepository.existsById(id)) {
            throw new NoCommentException("There is no comment with given id: " + id);
        }

        final Comment comment = commentRepository.findById(id).get();
        comment.setContent(request.getContent());
        commentRepository.save(comment);
    }



}
