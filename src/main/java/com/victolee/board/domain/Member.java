package com.victolee.board.domain;
import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_member;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="pw")
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

    public Long getId_member() {
        return id_member;
    }
    public void setId(Long id_member) {
        this.id_member = id_member;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}