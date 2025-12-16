package com.revature.ra;

public class Post
{
    private String title;
    private String body;
    private int userId;

    public Post(String pojoTest, String PObject, int i) {
        title=pojoTest;
        body=PObject;
        userId=i;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }




}
