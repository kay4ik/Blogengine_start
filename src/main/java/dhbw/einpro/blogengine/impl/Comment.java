package dhbw.einpro.blogengine.impl;

import java.time.LocalDateTime;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Klasse implementiert einen Kommentar zu einem Post.
 */
public class Comment implements IComment
{
    private String content;
    private IUser author;
    private IPost post;

    public Comment(String content, IUser author) {
        this.content = content;
        this.author = author;
    }


    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String p_content) throws IllegalOperationException {
        if(p_content.length() > 255)
            throw new IllegalOperationException("Kommentar zu lang!");
        else this.content = p_content;
    }

    @Override
    public IUser getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(IUser p_author) {
        author = p_author;
    }

    @Override
    public void setPost(IPost p_post) {
        post = p_post;
    }
}
