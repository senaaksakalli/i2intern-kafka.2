package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Student {
    private int id;
    private String name;


    public Student() {

    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter ve Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }


    public static Student fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Student.class);
    }

    @Override
    public String toString() {

        return "Student{id=" + id + ", name='" + name + "'}";
    }
}
