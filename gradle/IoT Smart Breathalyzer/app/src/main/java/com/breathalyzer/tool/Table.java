package com.breathalyzer.tool;

import com.breathalyzer.model.Register;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Table {
    
    public int matchModel (ArrayList<Register> registers, int ID) {
        for (int i = 0; i < registers.size(); i++) {
            if (registers.get(i).getId() == ID) {
                return i;
            }
        }
        
        return -1;
    }
    
    public void addModel(DefaultTableModel model, ArrayList<Register> registers) {
        registers.forEach((element) -> {
            model.addRow(new Object [] {element.getId(), element.getName(), element.getSex(), element.getCurp(), element.getPlate(), element.getAlcohol(), element.getTemperature(), element.getViability(), element.getSense(), element.getRegistration()});
        });
    }
    
    public void clearModel(DefaultTableModel model) {
        for(int i = model.getRowCount()-1; i >=0 ; i--){ 
            model.removeRow(i);
        }
    }
    
    public void updateModel(DefaultTableModel model, ArrayList<Register> registers) {
        clearModel(model);
        addModel(model, registers);
    }
    
    public void filterModel (DefaultTableModel model, JTable table, String search) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }
    
}
