package com.example.mystudentapplication;

import java.util.Date;

public class StudentReportModel {


    private String firstName;
    private String lastName;
    private String emailAddress;
    private String subjects;
    private String examDate;
    private String dateCreated;
    private String examVenue;

    public StudentReportModel(String firstName, String lastName, String emailAddress, String subjects, String examDate, String dateCreated, String examVenue) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.subjects = subjects;
        this.examDate = examDate;
        this.dateCreated = dateCreated;
        this.examVenue = examVenue;
    }

    public StudentReportModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getExamVenue() {
        return examVenue;
    }

    public void setExamVenue(String examVenue) {
        this.examVenue = examVenue;
    }

    @Override
    public String toString() {
        return "StudentReportModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", subjects='" + subjects + '\'' +
                ", examDate=" + examDate +
                ", dateCreated=" + dateCreated +
                ", examVenue='" + examVenue + '\'' +
                '}';
    }
}
