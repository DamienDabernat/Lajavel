package app.domain.entity;

public class Author {
    public String firstname;
    public String lastname;

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getAvatar() {
        return "https://avatars.dicebear.com/api/miniavs/" + this.firstname + this.lastname + ".svg";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}