package dhbw.einpro.blogengine.impl;

/**
 * Klasse repräsentiert ein Post.
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Die Klasse implementiert einen Post im Blog-System.
 */
public class Post implements IPost
{
    private int id;
    private String title;
    private String content;
    private IUser author;

    private List<IComment> comments;
    private List<IUser> likes;
    private List<IUser> dislikes;

    public Post(String title, String content, IUser author) {
        this.title = title;
        this.content = content;
        this.author = author;

        this.comments = new ArrayList<IComment>();
        this.likes = new ArrayList<IUser>();
        this.dislikes = new ArrayList<IUser>();
    }



    //MARK: Comments

    @Override
    public List<IComment> getComments() {
        return comments;
    }

    @Override
    public void addComment(IComment p_comment) throws IllegalOperationException {
        if(p_comment.getAuthor().equals(this.author))
            throw new IllegalOperationException("Du kannst nicht deinen eigenen Beitrag Kommentieren.");
        else comments.add(p_comment);
    }

    @Override
    public void removeComment(IUser p_user, IComment p_comment) throws IllegalOperationException {
        if(p_user.equals(p_comment.getAuthor()))
            comments.remove(p_comment);
        else throw new IllegalOperationException("Kommentar kann nicht entfernt werden");
    }


    //MARK: Likes

    @Override
    public void like(IUser p_person) throws IllegalOperationException {
        if(!this.author.equals(p_person) && !likes.contains(p_person))
            likes.add(p_person);
        else throw new IllegalOperationException("Fehler!");
    }

    @Override
    public void disLike(IUser p_person) throws IllegalOperationException {
        if(!this.author.equals(p_person) && !dislikes.contains(p_person))
            dislikes.add(p_person);
        else throw new IllegalOperationException("Fehler!");
    }

    @Override
    public List<IUser> getLikes() {
        return likes;
    }

    @Override
    public List<IUser> getDisLikes() {
        return dislikes;
    }

    @Override
    public int getScore() {
        return (likes.size()-dislikes.size())*10;
    }


    //MARK: BaseData

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int p_id) {
        id = p_id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String p_title) {
        title = p_title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String p_content) {
        content = p_content;
    }

    @Override
    public IUser getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(IUser p_author) {
        author = p_author;
    }
}
