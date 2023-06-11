package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable(name = "postId") long postId,
                              @PathVariable(name = "commentId") long commentId) {
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId).orElse(null);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found");
        }
        return comment;
    }

    @PostMapping(path = "/{postId}/comments")
    public void createComment(@RequestBody Comment comment,
                              @PathVariable(name = "postId") long postId) {
        comment.setPost(postRepository.findById(postId).orElse(null));
        commentRepository.save(comment);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public void updateComment(@RequestBody Comment comment,
                              @PathVariable(name = "commentId") long commentId,
                              @PathVariable(name = "postId") long postId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment with such id not found");
        }
        comment.setId(commentId);
        comment.setPost(postRepository.findById(postId).orElse(null));
        commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable(name = "postId") long postId,
                              @PathVariable(name = "commentId") long commentId) {
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId).orElse(null);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found");
        }
        commentRepository.delete(comment);
    }
    // END
}
