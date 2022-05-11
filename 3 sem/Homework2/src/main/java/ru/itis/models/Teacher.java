package ru.itis.models;

public class Teacher {
    private Integer id;
    private String first_name;
    private String last_name;
    private String subject;
    private String password;

    public Teacher(Integer id, String first_name, String last_name, String subject, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.subject = subject;
        this.password = password;
    }

    public Teacher(String first_name, String last_name, String subject, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.subject = subject;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", subject='" + subject + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
