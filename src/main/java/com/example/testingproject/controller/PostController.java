package com.example.testingproject.controller;

import com.example.testingproject.controller.request.PostRequest.SavePostRequest;
import com.example.testingproject.controller.request.PostRequest.UpdatePostRequest;
import com.example.testingproject.controller.response.PostResponse.FindAllPostsResponse;
import com.example.testingproject.controller.response.PostResponse.PostResponse;
import com.example.testingproject.entity.Comment;
import com.example.testingproject.service.PostService;
import com.example.testingproject.service.commentRequest.CommentPutRequest;
import com.example.testingproject.service.exceptions.CommentExceptions.CommentAlreadyExists;
import com.example.testingproject.service.exceptions.CommentExceptions.NoCommentException;
import com.example.testingproject.service.exceptions.PostExceptions.NoPostException;
import com.example.testingproject.service.exceptions.PostExceptions.PostCreationErrorException;
import com.example.testingproject.service.exceptions.PostExceptions.PostDoesNotExistsError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    void save(@RequestBody @Valid SavePostRequest post){
        try {
            postService.save(post);
        } catch(PostCreationErrorException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Error!", e
            );
        }
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable @Valid Integer id){
        try {
            postService.delete(id);
        } catch(PostDoesNotExistsError e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Requested post does not exist", e
            );
        }
    }

    @PutMapping()
    void update(@RequestBody @Valid UpdatePostRequest request) {
        try {
            postService.update(request);
        } catch(PostDoesNotExistsError e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Requested post does not exist", e
            );
        }
    }

    @GetMapping
    List<FindAllPostsResponse> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    PostResponse findById(@PathVariable @Valid Integer id) {
        try {
            return postService.findById(id);
        } catch(NoPostException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Given post does not exist",
                    e
            );
        }
    }

    @PostMapping("/comment")
    void addComment(@RequestBody @Valid Comment comment) {
        try {
            postService.addComment(comment);
        } catch(CommentAlreadyExists e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Given comment already exists: " + comment,
                    e
            );
        }
    }

    @DeleteMapping("/comment/{id}")
    void addComment(@PathVariable @Valid Integer id) {
        try {
            postService.deleteCommentById(id);
        } catch(NoCommentException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Given comment not exists: " + id,
                    e
            );
        }
    }

    @PutMapping("/comment")
    void changeComment(@RequestBody @Valid CommentPutRequest request) {
        try {
            postService.changeComment(request);
        } catch (NoCommentException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Comment with given id does not exists",
                    e
            );
        }
    }
}
