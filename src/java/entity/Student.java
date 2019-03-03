package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {

    private int id;
    private String name, surname, patron;
    private LocalDate birthDate;
    private ArrayList<Grade> grades;

    public Student(String name, String surname, String patron, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.patron = patron;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatron() {
        return patron;
    }

    public String getShortName() {
        return surname + " " + name.charAt(0) + "." + (patron == null ? "" : " " + patron.charAt(0) + ".");
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return getShortName();
    }
}
