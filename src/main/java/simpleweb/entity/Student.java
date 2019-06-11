package simpleweb.entity;

import simpleweb.util.StringUtil;

public class Student {

    private String username;
    private String password;
    private String salt;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private int role;
    private int status; // 1. active | 0. deactive | -1. deleted.

    public Student() {
        this.status = Status.ACTIVE.getValue();
    }

    public boolean isUser() {
        return this.role == Role.USER.getValue();
    }

    public boolean isStudent() {
        return this.role == Role.STUDENT.getValue();
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN.getValue();
    }

    public boolean isMember() {
        return (this.isUser() || this.isStudent() || this.isAdmin());
    }

    public enum Status {
        ACTIVE(1), DEACTIVE(0), DELETED(-1);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public static Status findByValue(int value) {
            for (Status s : Status.values()) {
                if (s.value == value) {
                    return s;
                }
            }
            return Status.DEACTIVE;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public enum Role {
        USER(1), ADMIN(2), STUDENT(3);

        private int value;

        Role(int value) {
            this.value = value;
        }

        public static Role findByValue(int value) {
            for (Role r : Role.values()) {
                if (r.value == value) {
                    return r;
                }
            }
            return Role.USER;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void hashPassword(String password) {
        if (this.salt == null || this.salt.length() == 0) {
            this.salt = StringUtil.generateSalt();
        }
        this.password = StringUtil.hashPassword(password, this.salt);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role.getValue();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status.getValue();
    }
}
