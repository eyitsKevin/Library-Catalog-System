package com.soen343.server.gateways;

import com.soen343.server.models.Transaction;
import com.soen343.server.models.catalog.Book;
import com.soen343.server.models.catalog.CatalogItem;
import com.soen343.server.models.catalog.Music;
import com.soen343.server.models.catalog.Movie;

import com.soen343.databaseConnection.Connector;

import org.hibernate.validator.cfg.defs.Mod11CheckDef;
import org.hibernate.validator.constraints.Mod10Check;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import java.util.ArrayList;

@Service
public class TransactionGateway {
    private final String URL = "jdbc:mysql://testdbinstance.cwtjkaidrsfz.us-east-2.rds.amazonaws.com:3306/testdb?useSSL=false";
    private final String USERNAME = "test";
    private final String PASSWORD = "testtest";
        /**
     * Gateways objects
     */
    private BookGateway bookGateway;
    private MusicGateway musicGateway;
    private MovieGateway movieGateway;

    private static TransactionGateway transactionGateway = null;

    private  Connector connector;

    private TransactionGateway() {
        bookGateway = BookGateway.getBookGateway();
        musicGateway = MusicGateway.getMusicGateway();
        movieGateway = MovieGateway.getMovieGateway();
    }

    public static TransactionGateway getGateway() {
        if (transactionGateway == null) {
            transactionGateway = new TransactionGateway();
        }
        return transactionGateway;
    }

    public void insert(Transaction transaction){
        String columns = "user_email, item_type, item_id, checkout_date, due_date";
        String userEmail = transaction.getUserEmail();
        String tableName = getTableName(transaction.getCatalogItem());
        long itemID = transaction.getCatalogItem().getId();
        String checkoutDate = transaction.getCheckoutDate().toString();
        String dueDate = transaction.getDueDate().toString();
        String values = "'" + userEmail + "', '" + tableName + "', '" + itemID + "', '" + checkoutDate + "', '" + dueDate + "'";

        String query = "INSERT INTO testdb.transaction (" + columns + ") VALUES (" + values + ")";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            long id = 0;
            while (keys.next()) {
                id = keys.getLong(1);
            }
            transaction.setId(id);

            conn.close();
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
            e.printStackTrace();
        }
    }

    public List<Transaction> getAllTransactions(){

        List<Transaction> transArrayList = new ArrayList<>();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeQuery("SELECT  * from testdb.transaction");
            ResultSet transResultSet = stmt.getResultSet();
    
            while (transResultSet.next()) {
                Transaction transaction = new Transaction(
                    transResultSet.getString("user_email"),
                    transResultSet.getString("item_type"),
                    transResultSet.getInt("item_id"),
                    transResultSet.getString("checkout_date"),
                    transResultSet.getString("due_date")
            );
            if(transaction.itemType.equals("book")) {
                Book b = bookGateway.get(transaction.item_id);
                transArrayList.add(new Transaction(transaction.getUserEmail(), b, "book", transaction.getDueDate(), transaction.getCheckoutDate()));
            }
            else if(transaction.itemType.equals("movie")) {
                Movie mo = movieGateway.get(transaction.item_id);
                transArrayList.add(new Transaction(transaction.getUserEmail(), mo, "movie", transaction.getDueDate(), transaction.getCheckoutDate()));
            } 
            else if(transaction.itemType.equals("music")) {
                Music mu = musicGateway.get(transaction.item_id);
                transArrayList.add(new Transaction(transaction.getUserEmail(), mu, "music", transaction.getDueDate(), transaction.getCheckoutDate()));
            } 
            else {}
               
            //transArrayList.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return transArrayList;
      
    }
    public List<Transaction> getuserTransactions(String userEmail){

        List<Transaction> transArrayList = new ArrayList<>();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeQuery("SELECT  * from testdb.transaction WHERE user_email= '" + userEmail+ "'");
            ResultSet transResultSet = stmt.getResultSet();
    
        while (transResultSet.next()) {
            Transaction transaction = new Transaction(
                transResultSet.getString("user_email"),
                transResultSet.getString("item_type"),
                transResultSet.getInt("item_id"),
                transResultSet.getString("checkout_date"),
                transResultSet.getString("due_date")
            );
        if(transaction.itemType.equals("book")) {
            Book b = bookGateway.get(transaction.item_id);
            transArrayList.add(new Transaction(transaction.getUserEmail(), b, "book", transaction.getDueDate(), transaction.getCheckoutDate()));
        }
        else if(transaction.itemType.equals("movie")) {
            Movie mo = movieGateway.get(transaction.item_id);
            transArrayList.add(new Transaction(transaction.getUserEmail(), mo, "movie", transaction.getDueDate(), transaction.getCheckoutDate()));
        } 
        else if(transaction.itemType.equals("music")) {
            Music mu = musicGateway.get(transaction.item_id);
            transArrayList.add(new Transaction(transaction.getUserEmail(), mu, "music", transaction.getDueDate(), transaction.getCheckoutDate()));
        } 
        else {}
               
            //transArrayList.add(transaction);
        }} catch (SQLException e) {
            e.printStackTrace();
        } 
        return transArrayList;
      
    }


    private Connection connect(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (Exception e){
            System.out.print(e);
        }
        return connection;
    }

    private String getTableName(CatalogItem catalogItem){
        if (catalogItem.getClass() == Book.class) {
            return "book";
        } else if (catalogItem.getClass() == Music.class) {
            return "music";
        } else {
            return "movie";
        }
    }
}
