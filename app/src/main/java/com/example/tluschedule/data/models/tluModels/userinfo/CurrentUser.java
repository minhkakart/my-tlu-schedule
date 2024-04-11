package com.example.tluschedule.data.models.tluModels.userinfo;

import com.example.tluschedule.data.models.JsonModelBase;

import java.util.List;

public class CurrentUser extends JsonModelBase {
    public long id;
    public String displayName;
    public String username;
    public boolean changePass;
    public boolean active;
    public long dob;
    public String birthPlace;
    public String email;
    public Person person;
    public boolean hasPhoto;
    public List<Role> roles;
    public boolean setPassword;
}
