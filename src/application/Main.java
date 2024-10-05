package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main( String[] args ) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);

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

        System.out.println("\n===TEST 5: seller update===");
        seller = sellerDao.findById(1);
        seller.setName("Bruce Wayne");
        sellerDao.update(seller);
        System.out.println("Update complete");

        System.out.println("\n===TEST 6: seller delete===");
        System.out.println("Enter id for delete: ");
        int id = scanner.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete completed");

        scanner.close();



    }
}