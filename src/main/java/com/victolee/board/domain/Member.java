package com.victolee.board.domain;
import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name="name")
    private String name;
    private String email;
    private String pw;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }
    public void setPw(String pw) { this.pw = pw; }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}