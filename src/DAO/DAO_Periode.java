package DAO;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import model.varPeriode;       // ganti dari varMatakuliah
import koneksi.koneksi;

public class DAO_Periode implements DAO_Interface<varPeriode> {

    Connection conn;

    public DAO_Periode() {
        conn = koneksi.getConnection();
    }

    // Sesuaikan nama tabel jika berbeda
    String INSERT = "INSERT INTO periode(TA, Semester) VALUES(?,?)";
    String UPDATE = "UPDATE periode SET Semester=? WHERE TA=?";
    String DELETE = "DELETE FROM periode WHERE TA=?";
    String SELECT = "SELECT * FROM periode";
    String CARI   = "SELECT * FROM periode WHERE TA LIKE ? OR Semester LIKE ?";

    @Override
    public void insert(varPeriode Object) {
        PreparedStatement st = null;
        try {
            // Cek duplikat berdasarkan TA + Semester
            String cek = "SELECT * FROM periode WHERE TA=? AND Semester=?";
            st = conn.prepareStatement(cek);
            st.setString(1, Object.getvTahunAjaran());
            st.setString(2, Object.getvSemester());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Data sudah pernah disimpan");
            } else {
                st = conn.prepareStatement(INSERT);
                st.setString(1, Object.getvTahunAjaran());
                st.setString(2, Object.getvSemester());
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(varPeriode Object) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(UPDATE);
            st.setString(1, Object.getvSemester());
            st.setString(2, Object.getvTahunAjaran());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String Key) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(DELETE);
            st.setString(1, Key);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<varPeriode> getAll() {
        List<varPeriode> list = null;
        PreparedStatement st = null;
        try {
            list = new ArrayList<>();
            st = conn.prepareStatement(SELECT);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                varPeriode obj = new varPeriode();
                obj.setvTahunAjaran(rs.getString("TA"));
                obj.setvSemester(rs.getString("Semester"));
                list.add(obj);
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<varPeriode> getCari(String Key) {
        List<varPeriode> list = null;
        PreparedStatement st = null;
        try {
            list = new ArrayList<>();
            st = conn.prepareStatement(CARI);
            st.setString(1, "%" + Key + "%");
            st.setString(2, "%" + Key + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                varPeriode obj = new varPeriode();
                obj.setvTahunAjaran(rs.getString("TA"));
                obj.setvSemester(rs.getString("Semester"));
                list.add(obj);
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}