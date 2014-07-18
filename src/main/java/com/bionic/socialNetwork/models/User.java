package com.bionic.socialNetwork.models;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User's entity
 *
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
@Entity
@Table(name = "Users")
@XmlRootElement
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @XmlTransient
    private String login;

    private String name;

    private String surname;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Interests",
               joinColumns = {@JoinColumn(name = "user_id")},
               inverseJoinColumns = {@JoinColumn(name = "interest_id")})
    private Set<Interest> interests = new HashSet<Interest>(0);

    private String position;

    @OneToOne
    @JoinColumn(name = "id")

    private Password password;

    @OneToMany(mappedBy = "user", targetEntity = Post.class, fetch = FetchType.EAGER)
    private List<Post> posts;



    @OneToMany(mappedBy = "user", targetEntity = SessionUser.class, fetch = FetchType.EAGER)
    private List<Post> sessions;

    public List<Post> getPosts() {
        return posts;
    }

    public Set<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User() {

    }



    public User(String login, String name, String surname,
                String position) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public List<Post> getSessions() {
        return sessions;
    }

    public void setSessions(List<Post> sessions) {
        this.sessions = sessions;
    }
}
