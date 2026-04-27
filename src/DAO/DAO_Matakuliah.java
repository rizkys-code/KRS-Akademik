package DAO;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import model.varMatakuliah;
import koneksi.koneksi;

public class DAO_Matakuliah implements DAO_Interface<varMatakuliah>{

    Connection conn;

    public DAO_Matakuliah(){
        conn = koneksi.getConnection();
    }

    String INSERT = "INSERT INTO matakuliah(KodeMtk, NamaMtk, SKS, kodeprasyarat) VALUES(?,?,?,?)";
    String UPDATE = "UPDATE matakuliah SET NamaMtk=?, SKS=?, kodeprasyarat=? WHERE KodeMtk=?";
    String DELETE = "DELETE FROM matakuliah WHERE KodeMtk=?";
    String SELECT = "SELECT * FROM matakuliah";
    String CARI   = "SELECT * FROM matakuliah WHERE KodeMtk LIKE ? OR NamaMtk LIKE ?";



    @Override
    public void insert(varMatakuliah Object) {

        PreparedStatement st=null;

        try{
            String cek = "SELECT * FROM matakuliah WHERE KodeMtk=?";
            st=conn.prepareStatement(cek);
            st.setString(1,Object.getvKodeMtk());
            ResultSet rs=st.executeQuery();

            if(rs.next()){
                JOptionPane.showMessageDialog(null,"Data sudah pernah disimpan");
            }
            else{
                st=conn.prepareStatement(INSERT);

                st.setString(1,Object.getvKodeMtk());
                st.setString(2,Object.getvNamaMtk());
                st.setInt(3,Object.getvSKS());
                st.setString(4,Object.getvKodePrasyarat());

                st.executeUpdate();

                JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            }

            st.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    @Override
    public void update(varMatakuliah Object) {

        PreparedStatement st=null;

        try{

            st=conn.prepareStatement(UPDATE);

            st.setString(1,Object.getvNamaMtk());
            st.setInt(2,Object.getvSKS());
            st.setString(3,Object.getvKodePrasyarat());
            st.setString(4,Object.getvKodeMtk());

            st.executeUpdate();

            JOptionPane.showMessageDialog(null,"Data berhasil diubah");

            st.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    @Override
    public void delete(String Key) {

        PreparedStatement st=null;

        try{

            st=conn.prepareStatement(DELETE);
            st.setString(1,Key);

            st.executeUpdate();

            JOptionPane.showMessageDialog(null,"Data berhasil dihapus");

            st.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    @Override
    public List<varMatakuliah> getAll() {

        List<varMatakuliah> list=null;
        PreparedStatement st=null;

        try{

            list = new ArrayList<>();
            st=conn.prepareStatement(SELECT);

            ResultSet rs=st.executeQuery();

            while(rs.next()){

                varMatakuliah obj=new varMatakuliah();

                obj.setvKodeMtk(rs.getString("KodeMtk"));
                obj.setvNamaMtk(rs.getString("NamaMtk"));
                obj.setvSKS(rs.getInt("SKS"));
                obj.setvKodePrasyarat(rs.getString("kodeprasyarat"));

                list.add(obj);
            }

            st.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }



    @Override
    public List<varMatakuliah> getCari(String Key) {

        List<varMatakuliah> list=null;
        PreparedStatement st=null;

        try{

            list = new ArrayList<>();

            st=conn.prepareStatement(CARI);
            st.setString(1,"%"+Key+"%");
            st.setString(2,"%"+Key+"%");

            ResultSet rs=st.executeQuery();

            while(rs.next()){

                varMatakuliah obj=new varMatakuliah();

                obj.setvKodeMtk(rs.getString("KodeMtk"));
                obj.setvNamaMtk(rs.getString("NamaMtk"));
                obj.setvSKS(rs.getInt("SKS"));
                obj.setvKodePrasyarat(rs.getString("kodeprasyarat"));

                list.add(obj);
            }

            st.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

}