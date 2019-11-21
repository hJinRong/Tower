package com.example.tower.Character;

public class UserInformation {
    String name, gender, no, eduStartDate, faculty, major, dormitory;

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setEduStartDate(String eduStartDate) {
        this.eduStartDate = eduStartDate;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    /*@Override
    public String toString() {
        return "{" +
                "\"name\":" + '\"' + name + '\"' +
                ", \"gender\":" + '\"' + gender + '\"' +
                ", \"no\":" + '\"' + no + '\"' +
                ", \"eduStartDate\":" + '\"' + eduStartDate + '\"' +
                ", \"faculty\":" + '\"' + faculty + '\"' +
                ", \"major\":" + '\"' + major + '\"' +
                ", \"dormitory\":" + '\"' + dormitory + '\"' +
                '}';
    }*/
}
