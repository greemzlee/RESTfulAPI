package ru.gazis.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Integer userId;
    private String userName;
    private String first;
    private String last;

    @JsonCreator
    public User(@JsonProperty("userId") Integer userId, @JsonProperty("userName") String userName, @JsonProperty("first") String first, @JsonProperty("last") String last) {
        this.userId = userId;
        this.userName = userName;
        this.first = first;
        this.last = last;
    }

    public User() { }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return first;
    }

    public String getLastName() {
        return last;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", firstName='" + first + '\'' +
                ", lastName='" + last + '\'' +
                '}';
    }
}


