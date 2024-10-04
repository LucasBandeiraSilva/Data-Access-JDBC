package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Main {
    public static void main( String[] args ) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n===TEST 2: seller findByDepartment ===");
        Department department = new Department(2,null);
        List<Seller>list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);

        System.out.println("\n===TEST 3: seller findAll===");
        list = sellerDao.findAll();
        list.forEach(System.out::println);

        System.out.println("\n===TEST 4: seller insert===");
        Seller newSeller = new Seller(null,"Lucas Bandeira","lucas@gmail.com", LocalDate.parse("02/02/2003",timeFormatter),4000.0,department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id = " + newSeller.getId());


    }
}