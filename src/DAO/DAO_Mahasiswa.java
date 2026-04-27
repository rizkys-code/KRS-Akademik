/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import model.varMahasiswa;
import java.util.List;
import koneksi.koneksi;

/**
 *
 * @author rizky
 */
public class DAO_Mahasiswa implements DAO_Interface<varMahasiswa>{
    
    
    Connection conn;
    public DAO_Mahasiswa(){
        conn = koneksi.getConnection();
    }
    
    // Deklarasi SQL statement
      String INSERT = "INSERT INTO mahasiswa(NIM, nama, alamat) VALUES(?,?,?)";
      String UPDATE = "UPDATE mahasiswa set nama=?, alamat=? WHERE NIM=?";
      String DELETE = "DELETE FROM mahasiswa WHERE NIM=?";
      String SELECT = "SELECT * FROM mahasiswa";
      String CARI = "SELECT * FROM mahasiswa WHERE NIM=?";
      
      
    @Override
    public void insert(varMahasiswa Object) {
        PreparedStatement st = null;
try {
    st = conn.prepareStatement(CARI);
    st.setString(1, Object.getvNIM());
    ResultSet rs = st.executeQuery();
    
    if (rs.next()) {
        JOptionPane.showMessageDialog(null, "Data sudah pernah di simpan");
    } 
    else {
        st = null;
        st = conn.prepareStatement(INSERT);
        st.setString(1, Object.getvNIM());
        st.setString(2, Object.getvNama());
        st.setString(3, Object.getvAlamat());
        st.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Data Berhasil di simpan");
    }
    
    st.close();
    
} catch (Exception e) {
    e.printStackTrace();
}
    }

    @Override
    public void update(varMahasiswa Object) {
        PreparedStatement st = null;
try {
    st = null;
    st = conn.prepareStatement(UPDATE);
    st.setString(1, Object.getvNama());
    st.setString(2, Object.getvAlamat());
    st.setString(3, Object.getvNIM());
    st.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Data Berhasil di ubah");
    st.close();
    
} catch (Exception e) {
    e.printStackTrace();
}
    }

    @Override
    public void delete(String Key) {
        PreparedStatement st = null;
        try {
            st = null;
            st = conn.prepareStatement(DELETE);
            st.setString(1, Key);
            st.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<varMahasiswa> getAll() {
        List<varMahasiswa> list = null;
        PreparedStatement st = null;
try {
    st = null;
    list = new ArrayList<varMahasiswa>();
    st = conn.prepareStatement(SELECT);
    ResultSet rs = st.executeQuery();
    while(rs.next()) {
        varMahasiswa objMhs = new varMahasiswa();
        objMhs.setvNIM(rs.getString("NIM"));
        objMhs.setvNama(rs.getString("Nama"));
        objMhs.setvAlamat(rs.getString("Alamat"));
        list.add(objMhs);
    }
    st.close();
} catch (Exception e) {
    e.printStackTrace();
}
return list;
    }

    @Override
    public List<varMahasiswa> getCari(String Key) {
        List<varMahasiswa> list = null;
        PreparedStatement st = null;
try {
    st = null;
    list = new ArrayList<varMahasiswa>();
    st = conn.prepareStatement(SELECT);
    st.setString(1, "%" + Key + "%");
    ResultSet rs = st.executeQuery();
    while(rs.next()) {
        varMahasiswa objMhs = new varMahasiswa();
        objMhs.setvNIM(rs.getString("NIM"));
        objMhs.setvNama(rs.getString("Nama"));
        objMhs.setvAlamat(rs.getString("Alamat"));
        list.add(objMhs);
    }
    st.close();
} catch (Exception e) {
    e.printStackTrace();
}
return list;
    }
    
}
