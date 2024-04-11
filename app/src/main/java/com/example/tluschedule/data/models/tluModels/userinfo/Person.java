package com.example.tluschedule.data.models.tluModels.userinfo;

import com.example.tluschedule.data.models.JsonModelBase;

import java.util.List;

public class Person extends JsonModelBase {
    public long id;
    public String firstName;
    public String lastName;
    public String displayName;
    public long birthDate;
    public String birthDateString;
    public String birthPlace;
    public String gender;
    public String phoneNumber;
    public String idNumber;
    public String idNumberIssueBy;
    public long idNumberIssueDate;
    public String idNumberIssueDateString;
    public List<Address> address;
}
