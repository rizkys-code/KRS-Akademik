/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAO_Interface;
import DAO.DAO_Mahasiswa;
import model.varMahasiswa;
import view.frmMahasiswa;
import java.util.List;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
/**
 *
 * @author astse
 */
public class Controller_Mahasiswa {
    frmMahasiswa form;
    DAO_Interface<varMahasiswa> model;
    List<varMahasiswa> list;
    String[] header;
    
    // Deklarasi Konstruktor
    public Controller_Mahasiswa(frmMahasiswa form) {
        this.form = form;
        model = new DAO_Mahasiswa();
        list = model.getAll();
        header = new String[]{"NIM", "Nama", "Alamat"};
        
        // Pengaturan tampilan tabel
        form.getTblmahasiswa().setShowGrid(true);
        form.getTblmahasiswa().setShowVerticalLines(true);
        form.getTblmahasiswa().setGridColor(Color.blue);
    }
    
    public void reset() {
        form.getTxtnim().setText("");
        form.getTxtnama().setText("");
        form.getTxtalamat().setText("");
        form.getTxtnim().requestFocus();
        isiTabel();
    }
    
    public void isiTabel() {
        list = model.getAll();
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        
        Object[] data = new Object[header.length];
        for (varMahasiswa objMhs : list) {
            data[0] = objMhs.getvNIM();
            data[1] = objMhs.getvNama();
            data[2] = objMhs.getvAlamat();
            tblModel.addRow(data);
        }
        form.getTblmahasiswa().setModel(tblModel);
    }
    
    public void isiField(int row) {
        form.getTxtnim().setText(list.get(row).getvNIM());
        form.getTxtnama().setText(list.get(row).getvNama());
        form.getTxtalamat().setText(list.get(row).getvAlamat());
    }
    
    public void insert() {
        // Membuat objek
        varMahasiswa objMhs = new varMahasiswa();
        
        // Mengisi variabel objMhs dengan data yang di entri
        objMhs.setvNIM(form.getTxtnim().getText());
        objMhs.setvNama(form.getTxtnama().getText());
        objMhs.setvAlamat(form.getTxtalamat().getText());
        
        // Menjalankan method insert yang ada di DAO_Mahasiswa
        model.insert(objMhs);
    }

    public void update() {
        // Membuat objek
        varMahasiswa objMhs = new varMahasiswa();
        
        // Mengisi variabel objMhs dengan data yang di entri
        objMhs.setvNIM(form.getTxtnim().getText());
        objMhs.setvNama(form.getTxtnama().getText());
        objMhs.setvAlamat(form.getTxtalamat().getText());
        
        // Menjalankan method update yang ada di DAO_Mahasiswa
        model.update(objMhs);
    }

    public void delete() {
        if (!form.getTxtnim().getText().trim().isEmpty()) {
            // Mengambil data NIM dari field
            String nim = form.getTxtnim().getText();
            
            // Menjalankan method delete yang ada di DAO_Mahasiswa
            model.delete(nim);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan di hapus");
        }
    }
    
    public void isiTabelCari() {
        list = model.getCari(form.getTxtnim().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header);
        
        Object[] data = new Object[header.length];
        for (varMahasiswa objMhs : list) {
            data[0] = objMhs.getvNIM();
            data[1] = objMhs.getvNama();
            data[2] = objMhs.getvAlamat();
            tblModel.addRow(data);
        }
        form.getTblmahasiswa().setModel(tblModel);
    }
}