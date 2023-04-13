package com.haiph.userservice;

import com.haiph.userservice.entity.person.Person;

import java.text.Normalizer;
import java.util.Optional;
import java.util.regex.Pattern;

public class Test {
    private String fullName;
    private String personCode;

    public Test(String fullName, String personCode) {
        this.fullName = fullName;
        this.personCode = personCode;
    }

    public Test() {
    }

    public Test(String fullName) {
        this.fullName = fullName;
        this.personCode = this.generatePersonCode(fullName);
//        this.personCode= this.generatePersonCode();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    @Override
    public String toString() {
        return "Test{" +
                "fullName='" + fullName + '\'' +
                ", personCode='" + personCode + '\'' +
                '}';
    }

    public String generatePersonCode() {
        String temp = Normalizer.normalize(fullName, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String output = pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
        String[] newFullName = output.split(" ");
        String newPersonCode = "";
        for (int i = 0; i < newFullName.length; i++) {
            newPersonCode += newFullName[newFullName.length - 1].toUpperCase();
            continue;

        }
//        String newPersonCode =
        return newPersonCode;
    }

    private String generatePersonCode(String fullName) {
        String temp = Normalizer.normalize(fullName, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String output = pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
        String[] newFullName = output.split(" ");
        String newPersonCode = "";
        String firstName = newFullName[0].substring(0, 2).toUpperCase();
        for (int i = 0; i < newFullName.length; i++) {
            newPersonCode = newFullName[newFullName.length - 1].toUpperCase() + firstName;
            break;
        }
        Test test = new Test("Phạm Hải");
        int number = 1;
        if (test.personCode.equals(newPersonCode)) {
            newPersonCode += number;
            while (test.personCode.equals(newPersonCode) == true) {
                number++;
                newPersonCode += number;
            }
            return newPersonCode;
        }

        return newPersonCode;
    }


    public static void main(String[] args) {
        Test test = new Test("Phạm Hải");
        Test test2 = new Test("Phạm Hải");
        System.out.println("test.toString() = " + test.toString());
        System.out.println("test2.toString() = " + test2.toString());
    }
}
