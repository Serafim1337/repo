package lab8;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class App extends JFrame {


    Panel mainWindow;

    JLabel addField;
    JLabel name;
    JLabel artikyl;
    JLabel modelClothes;
    JTable table;
JLabel proizvoditel,color,size,dataVipuska,price;
JLabel test,test1,test2,test3;

    Object[] headers = {"Name", "Articul", "model","proizvoditel","color","size","data vipuska","price"};


    Object[][] data = new String [][]{
            {"Майка", "459328", "Поло","Nike","Красный","S","04.12.2019","19.99"},
            {"Худи", "491453", "Оверсайз","DeFacto","Синий","M","25.08.2020","59.99"},
            {"Брюки", "719439", "Слим","LC Waikiki","Желтый","XS","14.01.2020","64.00"},
    };

    DefaultTableModel model;

    App(String stroka) {
        super(stroka);
        setSize(1300,900);
        setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        model = new DefaultTableModel(data, headers);

        mainWindow = new Panel();


        addField = new JLabel("ДОБАВЛЕНИЕ НОВОГО ПОЛЯ");
        addField.addMouseListener(new tableFunctions.AddFieldEvent(addField, model));
        addField.setBounds(50, 0, 200, 100);
        addField.setBackground(Color.orange);
        addField.setOpaque(true);
addField.addMouseListener(new tableFunctions.listener1(addField));


        table = new JTable(model);
        table.addMouseListener(new tableFunctions.ChangeDataEvent(table));
        table.setBackground(Color.yellow);
        table.setBounds(230, 240, 900, 500);


        table.setShowGrid(true);
        table.setRowHeight(table.getRowHeight() + 50);
        table.setSelectionBackground(null);


        name = new JLabel("Наименование");
        name.setBounds(240, 200, 100, 50);

        artikyl = new JLabel("Артикул");
        artikyl.setBounds(370, 200, 200, 50);

        modelClothes = new JLabel("Модель");
        modelClothes.setBounds(490, 200, 150, 50);

        proizvoditel = new JLabel("Производитель");
        proizvoditel.setBounds(575, 200, 150, 50);

        color =new JLabel("Цвет");
        color.setBounds(720, 200, 150, 50);

        size = new JLabel("Размер");
        size.setBounds(830, 200, 150, 50);

        dataVipuska = new JLabel("Дата выпуска");
        dataVipuska.setBounds(920, 200, 150, 50);

        price = new JLabel("Цена");
        price.setBounds(1050, 200, 150, 50);

        test = new JLabel("УДАЛЕНИЕ ПОСЛЕДЕНЕГО ВВЕДЕННОГО ПОЛЯ");
        test.setBounds(50, 100, 300, 100);
        test.addMouseListener(new tableFunctions.clearAll(test, model));
        test.setBackground(Color.orange);
        test.setOpaque(true);
        test.addMouseListener(new tableFunctions.listener2(test));

        test1 = new JLabel("ПОИСК НАЛИЧИЯ РАЗМЕРА М");
        test1.setBounds(20, 300, 200, 100);
        test1.addMouseListener(new tableFunctions.sortM(data,test1, model));
        test1.setOpaque(true);
        test1.setBackground(Color.orange);

        test2 = new JLabel("ПОИСК НАЛИЧИЯ РАЗМЕРА S(XS)");
        test2.setBounds(20, 400, 200, 100);
        test2.addMouseListener(new tableFunctions.sortS(data,test2, model));
        test2.setOpaque(true);
        test2.setBackground(Color.orange);

        test3 = new JLabel("ПОИСК НАЛИЧИЯ РАЗМЕРА L(XL)");
        test3.setBounds(20, 500, 200, 100);
        test3.addMouseListener(new tableFunctions.sortL(data,test3, model));
        test3.setOpaque(true);
        test3.setBackground(Color.orange);

        this.add(modelClothes);
        this.add(name);
        this.add(artikyl);
        this.add(table);
        this.add(addField);
        this.add(mainWindow);
        this.add(proizvoditel);
this.add(color);
this.add(size);
        this.add(dataVipuska);
        this.add(price);
this.add(test);
this.add(test1);
this.add(test2);
this.add(test3);
    }}








