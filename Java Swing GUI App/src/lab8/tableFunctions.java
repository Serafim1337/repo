package lab8;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringTokenizer;
import java.io.*;
public class tableFunctions{


   static Object[] newRow = {"Наименование:", "Артикул:", "Модель:","Производитель:","Цвет:","Размер:","Дата выпуска:","Цена:"};

public static class sortM implements MouseListener
{
    JLabel test;
    DefaultTableModel model;
    Object [] [] data;
    sortM(Object [] [] data,JLabel test,DefaultTableModel model)
    {
        this.data=data;
        this.test=test;
        this.model=model;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

        StringTokenizer tokenizer = new StringTokenizer(data.toString());
        int n = tokenizer.countTokens();
        String[] tokens = new String[n];
        for (int i =0;i<n;i++)
        {
            tokens[i]=tokenizer.nextToken();

            if(tokens[i].equals("M"))
            {
                JOptionPane.showMessageDialog(null, "Есть в наличии", "Сортировка", JOptionPane.PLAIN_MESSAGE);

            }else JOptionPane.showMessageDialog(null, "Отсутствует", "Cортировка", JOptionPane.PLAIN_MESSAGE);

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
test.setBackground(Color.cyan);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        test.setBackground(Color.orange);
    }
}

    public static class sortS implements MouseListener
    {
        JLabel test2;
        DefaultTableModel model;
        Object [] [] data;
        sortS(Object [] [] data,JLabel test2,DefaultTableModel model)
        {
            this.data=data;
            this.test2=test2;
            this.model=model;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

            StringTokenizer tokenizer = new StringTokenizer(data.toString());
            int n = tokenizer.countTokens();
            String[] tokens = new String[n];
            for (int i =0;i<n;i++)
            {
                tokens[i]=tokenizer.nextToken();

                if(tokens[i].equals("S")|| tokens[i].equals("XS"))
                {
                    JOptionPane.showMessageDialog(null, "Есть в наличии", "Сортировка", JOptionPane.PLAIN_MESSAGE);

                }else JOptionPane.showMessageDialog(null, "Отсутствует", "Cортировка", JOptionPane.PLAIN_MESSAGE);

            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            test2.setBackground(Color.cyan);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            test2.setBackground(Color.orange);
        }
    }

    public static class sortL implements MouseListener
    {
        JLabel test3;
        DefaultTableModel model;
        Object [] [] data;
        sortL(Object [] [] data,JLabel test3,DefaultTableModel model)
        {
            this.data=data;
            this.test3=test3;
            this.model=model;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

            StringTokenizer tokenizer = new StringTokenizer(data.toString());
            int n = tokenizer.countTokens();
            String[] tokens = new String[n];
            for (int i =0;i<n;i++)
            {
                tokens[i]=tokenizer.nextToken();

                if(tokens[i].equals("L")|| tokens[i].equals("XL"))
                {
                    JOptionPane.showMessageDialog(null, "Есть в наличии", "Сортировка", JOptionPane.PLAIN_MESSAGE);

                }else JOptionPane.showMessageDialog(null, "Отсутствует", "Cортировка", JOptionPane.PLAIN_MESSAGE);

            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            test3.setBackground(Color.cyan);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            test3.setBackground(Color.orange);
        }
    }

    public static class ChangeDataEvent implements MouseListener {

        JTable table;


        ChangeDataEvent(JTable jTabPeople) {
            this.table = jTabPeople;

        }

        public void deleteDefaultMsg() {
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();

            for (int i = 0; i != newRow.length; i++) {
                if (table.getValueAt(row, column).equals(newRow[i])) {
                    table.setValueAt("", row, column);
                }
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {

            deleteDefaultMsg();

            JTextField textField = new JTextField();

            textField.setBorder(null);


            DefaultCellEditor customCellEditor = new DefaultCellEditor(textField);

            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellEditor(customCellEditor);
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class AddFieldEvent implements MouseListener {
        JLabel addField;
        DefaultTableModel model;


        AddFieldEvent(JLabel addField, DefaultTableModel model) {
            this.addField = addField;
            this.model = model;
        }


        @Override
        public void mouseClicked(MouseEvent e) {

            JOptionPane.showMessageDialog(null, "Добавлено новое поле", "Редактирование", JOptionPane.PLAIN_MESSAGE);
            model.addRow(newRow);

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class clearAll implements MouseListener
    {
        JLabel test;
        DefaultTableModel model;
        clearAll(JLabel test,DefaultTableModel model)
        {
this.test=test;
this.model=model;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

            JOptionPane.showMessageDialog(null, "Позиция удаляемого поля : "+model.getRowCount(), "Удаление последнего поля", JOptionPane.PLAIN_MESSAGE);
            model.removeRow(model.getRowCount()-1);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static class listener1 implements MouseListener
    {
JLabel addField;
listener1(JLabel addField)
{
    this.addField=addField;
}
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            addField.setBackground(Color.green);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            addField.setBackground(Color.orange);
        }
    }

    public static class listener2 implements MouseListener
    {
        JLabel test;
        listener2(JLabel test)
        {
            this.test=test;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            test.setBackground(Color.red);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            test.setBackground(Color.orange);
        }
    }
}
