package Controller;

import DAO.DAO_Interface;
import DAO.DAO_Periode;
import model.varPeriode;
import view.frmPeriode;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controller_Periode {
    private frmPeriode form;
    private DAO_Interface<varPeriode> model;
    private List<varPeriode> list;
    private String[] header;

    public Controller_Periode(frmPeriode form){
        this.form = form;
        this.model = new DAO_Periode();
        this.header = new String[]{"TA", "Semester"};

        // setting tabel
        form.getTblPeriode().setShowGrid(true);
        form.getTblPeriode().setShowVerticalLines(true);
        form.getTblPeriode().setGridColor(Color.BLUE);

        isiTabel(); // langsung load data
    }

    public void isiTabel(){
        list = model.getAll();

        DefaultTableModel tblModel = new DefaultTableModel(null, header){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };

        for(varPeriode obj : list){
            Object[] data = {
                obj.getvTahunAjaran(),
                obj.getvSemester()
            };
            tblModel.addRow(data);
        }

        form.getTblPeriode().setModel(tblModel);
    }

    public void isiField(int row){
        if(row >= 0 && row < list.size()){
            form.getTxtTA().setText(list.get(row).getvTahunAjaran());
            form.getTxtSemester().setText(list.get(row).getvSemester());
        }
    }

    public void reset(){
        form.getTxtTA().setText("");
        form.getTxtSemester().setText("");
        form.getTxtTA().requestFocus();
        isiTabel();
    }

    public void insert(){
        if(form.getTxtTA().getText().trim().isEmpty() ||
           form.getTxtSemester().getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(form, "Data tidak boleh kosong!");
            return;
        }

        varPeriode obj = new varPeriode();
        obj.setvTahunAjaran(form.getTxtTA().getText());
        obj.setvSemester(form.getTxtSemester().getText());

        model.insert(obj);
        JOptionPane.showMessageDialog(form, "Data berhasil ditambah");
        reset();
    }

    public void update(){
        varPeriode obj = new varPeriode();
        obj.setvTahunAjaran(form.getTxtTA().getText());
        obj.setvSemester(form.getTxtSemester().getText());

        model.update(obj);
        JOptionPane.showMessageDialog(form, "Data berhasil diupdate");
        reset();
    }

    public void delete(){
        if(!form.getTxtTA().getText().trim().isEmpty()){
            String ta = form.getTxtTA().getText();
            model.delete(ta);
            JOptionPane.showMessageDialog(form, "Data berhasil dihapus");
            reset();
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data dulu!");
        }
    }

    public void isiTabelCari(){
        list = model.getCari(form.getTxtTA().getText().trim());

        DefaultTableModel tblModel = new DefaultTableModel(null, header);

        for(varPeriode obj : list){
            Object[] data = {
                obj.getvTahunAjaran(),
                obj.getvSemester()
            };
            tblModel.addRow(data);
        }

        form.getTblPeriode().setModel(tblModel);
    }
}