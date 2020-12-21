package dhbw.einpro.blogengine.impl;

import java.util.*;
import java.util.stream.Collectors;

import dhbw.einpro.blogengine.exceptions.DuplicateEmailException;
import dhbw.einpro.blogengine.exceptions.DuplicateUserException;
import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.PostNotFoundException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;
import dhbw.einpro.blogengine.interfaces.IBlogEngine;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Klasse implementiert die Funktionalität einer Blog Engine.
 */
public class BlogEngine implements IBlogEngine
{
    private List<IUser> users;
    private List<IPost> posts;

    public BlogEngine() {
        users = new ArrayList<>();
        posts = new ArrayList<IPost>();
    }

    @Override
    public int size() {
        return users.size();
    }

    @Override
    public boolean addUser(IUser p_user) throws DuplicateEmailException, DuplicateUserException {
        for (IUser user:users) {
            if(user.equals(p_user))
                throw new DuplicateUserException("Benutzer gibts scho!");
            if(user.getEmail().equals(p_user.getEmail()))
                throw new DuplicateEmailException("Email gibts scho!");
        }
        return users.add(p_user);
    }

    @Override
    public boolean removeUser(IUser p_user) {
        return users.remove(p_user);
    }

    @Override
    public int addPost(IPost p_post) throws IllegalOperationException {
        if(!users.contains(p_post.getAuthor()))
            throw new IllegalOperationException("Wer bist du?!");

        p_post.setId(posts.size()+1);
        posts.add(p_post);
        return p_post.getId();
    }

    @Override
    public void removePost(IUser p_author, int p_postId) throws PostNotFoundException, IllegalOperationException {
        IPost activePost = null;
        for (IPost post: posts) {
            if(post.getId() == p_postId){
                activePost = post;
                break;
            }
        }
        if(activePost == null)
            throw new PostNotFoundException("Post not found!");
        if(!activePost.getAuthor().equals(p_author))
            throw new IllegalOperationException("Du bist nicht der Author!");
        posts.remove(activePost);
    }

    @Override
    public List<IPost> getPosts() {
        return posts;
    }

    @Override
    public List<IPost> findPostsByAuthor(IUser p_author) {
        List<IPost> activeList = new ArrayList<>();
        for (IPost post: posts) {
            if(post.getAuthor().equals(p_author))
                activeList.add(post);
        }
        return activeList;
    }

    @Override
    public IPost findPostById(int p_postId) {
        IPost activePost = null;
        for (IPost post: posts) {
            if(post.getId() == p_postId){
                activePost = post;
                break;
            }
        }
        return activePost;
    }

    @Override
    public boolean containsPost(int p_postId) {
        IPost post = findPostById(p_postId);
        return post != null;
    }

    @Override
    public IUser findUserByEmail(String p_email) throws UserNotFoundException {
        IUser activeUser = null;
        for (IUser user: users) {
            if(user.getEmail() == p_email){
                activeUser = user;
                break;
            }
        }
        if(activeUser == null)
            throw new UserNotFoundException("Benutzer existiert nicht!");
        return activeUser;
    }

    @Override
    public boolean containsUser(String p_email) {
        try {
            findUserByEmail(p_email);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    @Override
    public List<IPost> sortPostsByTitle() {
        return this.posts.stream()
                .sorted(Comparator.comparing(IPost::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<IPost> findPostsByTitle(String title) {
        List<IPost> activeList = new ArrayList<>();
        for (IPost post: posts) {
            if(post.getTitle().equals(title))
                activeList.add(post);
        }
        return activeList;
    }
}
