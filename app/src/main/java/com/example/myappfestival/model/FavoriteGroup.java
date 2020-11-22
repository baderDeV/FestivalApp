package com.example.myappfestival.model;

public class FavoriteGroup {
    private Long id;
    private Group group;
    private User user;
    private boolean isLiked;

    public FavoriteGroup(Group group, User user, boolean isLiked) {
        this.group = group;
        this.user = user;
        this.isLiked = isLiked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
