package Controller;

import DAO.DAO_Interface;
import DAO.DAO_Matakuliah;
import model.varMatakuliah;
import view.frmMatakuliah;

import java.util.List;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Controller_Matakuliah {

    frmMatakuliah form;
    DAO_Interface<varMatakuliah> model;
    List<varMatakuliah> list;
    String[] header;

    // Konstruktor
    public Controller_Matakuliah(frmMatakuliah form) {

        this.form = form;
        model = new DAO_Matakuliah();
        list = model.getAll();

        header = new String[]{
            "Kode Matakuliah",
            "Nama Matakuliah",
            "SKS",
            "Kode Prasyarat"
        };

        form.getTblMatakuliah().setShowGrid(true);
        form.getTblMatakuliah().setShowVerticalLines(true);
        form.getTblMatakuliah().setGridColor(Color.blue);
    }


    public void reset(){

        form.getTxtKodeMtk().setText("");
        form.getTxtNamaMtk().setText("");
        form.getTxtSKS().setText("");
        form.getTxtKodePrasyarat().setText("");

        form.getTxtKodeMtk().requestFocus();

        isiTabel();
    }


    public void isiTabel(){

        list = model.getAll();

        DefaultTableModel tblModel =
                new DefaultTableModel(
                        new Object[][]{},
                        header){

            @Override
            public boolean isCellEditable(
                    int row,
                    int col){
                return false;
            }

        };

        Object[] data = new Object[header.length];

        for(varMatakuliah obj : list){

            data[0]=obj.getvKodeMtk();
            data[1]=obj.getvNamaMtk();
            data[2]=obj.getvSKS();
            data[3]=obj.getvKodePrasyarat();

            tblModel.addRow(data);
        }

        form.getTblMatakuliah().setModel(tblModel);
    }



    public void isiField(int row){

        form.getTxtKodeMtk()
                .setText(
                        list.get(row)
                        .getvKodeMtk()
                );

        form.getTxtNamaMtk()
                .setText(
                        list.get(row)
                        .getvNamaMtk()
                );

        form.getTxtSKS()
                .setText(
                        String.valueOf(
                                list.get(row)
                                .getvSKS()
                        )
                );

        form.getTxtKodePrasyarat()
                .setText(
                        list.get(row)
                        .getvKodePrasyarat()
                );
    }



    public void insert(){

        varMatakuliah obj =
                new varMatakuliah();

        obj.setvKodeMtk(
                form.getTxtKodeMtk().getText()
        );

        obj.setvNamaMtk(
                form.getTxtNamaMtk().getText()
        );

        obj.setvSKS(
                Integer.parseInt(
                        form.getTxtSKS().getText()
                )
        );

        obj.setvKodePrasyarat(
                form.getTxtKodePrasyarat().getText()
        );

        model.insert(obj);
    }



    public void update(){

        varMatakuliah obj =
                new varMatakuliah();

        obj.setvKodeMtk(
                form.getTxtKodeMtk().getText()
        );

        obj.setvNamaMtk(
                form.getTxtNamaMtk().getText()
        );

        obj.setvSKS(
                Integer.parseInt(
                        form.getTxtSKS().getText()
                )
        );

        obj.setvKodePrasyarat(
                form.getTxtKodePrasyarat().getText()
        );

        model.update(obj);
    }



    public void delete(){

        if(!form.getTxtKodeMtk()
                .getText()
                .trim()
                .isEmpty()){

            String kode=
                    form.getTxtKodeMtk()
                    .getText();

            model.delete(kode);

        }else{

            JOptionPane.showMessageDialog(
                    form,
                    "Pilih data yang akan dihapus"
            );

        }

    }



    public void isiTabelCari(){

        list=model.getCari(
                form.getTxtKodeMtk()
                        .getText()
                        .trim()
        );

        DefaultTableModel tblModel=
                new DefaultTableModel(
                        new Object[][]{},
                        header
                );

        Object[] data=
                new Object[header.length];

        for(varMatakuliah obj:list){

            data[0]=obj.getvKodeMtk();
            data[1]=obj.getvNamaMtk();
            data[2]=obj.getvSKS();
            data[3]=obj.getvKodePrasyarat();

            tblModel.addRow(data);
        }

        form.getTblMatakuliah()
                .setModel(tblModel);

    }

}