package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi {

    private static final String URL = "jdbc:mysql://localhost:3306/db_akademik_2311500777";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Koneksi Berhasil");
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");

            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Koneksi Gagal Karena: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        koneksi.getConnection();
    }
}