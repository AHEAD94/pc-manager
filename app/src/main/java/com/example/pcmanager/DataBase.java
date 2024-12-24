package com.example.pcmanager;

import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    public String id;
    public Long SSN;
    public String gender;

    public DataBase() {
        // default constructor
    }
    public DataBase(String id, Long ssn, String gender) {
        this.id = id;
        this.SSN = ssn;
        this. gender = gender;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("SSN", SSN);
        result.put("gender", gender);
        return  result;
    }
}
