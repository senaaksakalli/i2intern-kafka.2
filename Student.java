package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Student {
    public int id;
    public String name;

    public Student() {
        // JSON deserialization işlemi için boş constructor
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Student nesnesini JSON string'e dönüştür
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    // JSON string'inden Student nesnesi oluştur
    public static Student fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Student.class);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}
