package application;

import db.DB;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main( String[] args ) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Department department = new Department(1,"Books");
        Seller seller = new Seller(34,
                "John Marston",
                "John@gmail.com",
                LocalDate.parse("19/02/1873",dateTimeFormatter),
                3000.0,
                department);

        System.out.println(department);
        System.out.println(seller);
    }
}