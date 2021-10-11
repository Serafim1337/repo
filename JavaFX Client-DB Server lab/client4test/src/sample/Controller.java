package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;


import java.awt.*;
import java.io.*;
import java.net.Socket;

import java.util.ArrayList;


public class Controller  {


    @FXML
    private Button connectButton;

    @FXML
    private TextField ipField;

    @FXML
    private TextField portField;

    @FXML
    private Button sendButton;

    @FXML
    private TextField sentField;

    @FXML
    private TextField sentField1;

    @FXML
    private TextField sentField2;

    @FXML
    private TextField sentField3;

    @FXML
    private TextField sentField4;

    @FXML
    private TextField resultsField;

    @FXML
    private Button fillButton;

    @FXML
    private TableView<profession> tableView;

    @FXML
    private TableColumn<profession, String> tableCol1;

    @FXML
    private TableColumn<profession, String> tableCol2;

    @FXML
    private TableColumn<profession, String> tableCol3;

    @FXML
    private TableColumn<profession, String> tableCol4;

    @FXML
    private TableColumn<profession, String> tableCol5;

    @FXML
    private TableColumn<profession, String> tableCol6;

    @FXML
    private Button connectSqlButton;

    @FXML
    private Button selectSqlButton;

    @FXML
    private Button editRowButton;

    @FXML
    private TextField editField1;

    @FXML
    private TextField editField2;

    @FXML
    private TextField editField3;

@FXML
private ComboBox<combo> comboBox;





    Socket socket = null;
    ObjectInputStream is = null;
    DataOutputStream os = null;



    byte[] bytes = new byte[100000];
    private ArrayList arrayList;
    private ObservableList <profession> professionsList = FXCollections.observableArrayList();



    public void createTable() throws IOException, ClassNotFoundException {

if(arrayList!=null&&professionsList!=null)
{
    arrayList.clear();
    professionsList.clear();
}
        String selectSql ="selectSql";

            os.writeUTF(selectSql);
            arrayList=(ArrayList)is.readUnshared();
            System.out.println("Created table ->>> "+arrayList);

        for(int i = 0; i < arrayList.size(); i++) {
            String[] receivedData = arrayList.get(i).toString().split(" ");
            professionsList.add(new profession(receivedData[0], receivedData[1], receivedData[2], receivedData[3], receivedData[4], receivedData[5]));
        }
            tableCol6.setCellValueFactory(new PropertyValueFactory<profession,String>("idprofessions"));
            tableCol1.setCellValueFactory(new PropertyValueFactory<profession,String>("profession_name"));
            tableCol2.setCellValueFactory(new PropertyValueFactory<profession,String>("profession_edu"));
            tableCol3.setCellValueFactory(new PropertyValueFactory<profession,String>("profession_diff"));
            tableCol4.setCellValueFactory(new PropertyValueFactory<profession,String>("profession_salary"));
            tableCol5.setCellValueFactory(new PropertyValueFactory<profession,String>("profession_pop"));
        tableView.setItems(professionsList);

    }



@FXML
    void initialize()
{
    connectButton.setVisible(false);

    connectButton.setOnAction(actionEvent -> {

        try {

            if (ipField.getText().length()>=Const.ADDRESS.length()&&portField.getText().length()>=4) {
                resultsField.clear();
                System.out.println("Connecting.......");
                socket = new Socket(ipField.getText(), Integer.parseInt(portField.getText()));
                is = new ObjectInputStream(socket.getInputStream());
                os = new DataOutputStream(socket.getOutputStream());
                System.out.println("Connected!");
            } else {resultsField.setText("ERROR ENTER ADDRESS & PORT");}
        } catch (IOException e) {
            e.printStackTrace();
        }

    });

    fillButton.setOnAction(actionEvent -> {
        ipField.setText(Const.ADDRESS);
        portField.setText(String.valueOf(Const.PORT));
        connectButton.setVisible(true);

    });

    connectSqlButton.setOnAction(actionEvent -> {
String connectSql="connectSql";
        try {
            os.writeUTF(connectSql);
arrayList=(ArrayList)is.readUnshared();
System.out.println("Received arrayList ->"+arrayList);
createTable();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    });

    selectSqlButton.setOnAction(actionEvent -> {
String selectSql ="selectSql";
        try {
            os.writeUTF(selectSql);
            arrayList=(ArrayList)is.readUnshared();
            System.out.println("Received arrayList ->"+arrayList);
            createTable();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    });

    sendButton.setOnAction(actionEvent -> {
        String addTableRow = "addTableRow"+" "+sentField.getText()+" "+sentField1.getText()+" "+
                sentField2.getText()+" "+ sentField3.getText()+ " "+ sentField4.getText()+ " ";

     if (sentField.getText().length()>0&&sentField1.getText().length()>0&&sentField2.getText().length()>0&&
     sentField3.getText().length()>0&&sentField4.getText().length()>0)
     {
         try {
             resultsField.clear();
             os.writeUTF(addTableRow);
             arrayList = (ArrayList) is.readUnshared();
             System.out.println("Received arrayList ->"+arrayList);
             createTable();
         } catch (IOException | ClassNotFoundException e) {
             e.printStackTrace();
         }
     } else {resultsField.setText("ERROR ENTER ROW VALUE"); }
    });

    editRowButton.setOnAction(actionEvent -> {

        String editTableRow = "editTableRow" + " " + editField1.getText() + " " +
                editField2.getText().toLowerCase() + " " + editField3.getText() + " ";

            if (editField1.getText().length()>0&&editField2.getText().length()>0&&editField3.getText().length()>0)
            {
                if (editField2.getText().toLowerCase().equals("name")||
                        editField2.getText().toLowerCase().equals("education")||
                        editField2.getText().toLowerCase().equals("difficulty")||
                        editField2.getText().toLowerCase().equals("salary")||
                        editField2.getText().toLowerCase().equals("popularity")){
                try {
                    resultsField.clear();

                    os.writeUTF(editTableRow);
                    arrayList = (ArrayList) is.readUnshared();
                    System.out.println("Received arrayList ->"+arrayList);
                    createTable();

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                } else{resultsField.setText("ERROR ENTER CORRECT COLUMN");}
            }
            else {resultsField.setText("ERROR ENTER EDIT VALUES");}


    });

    ObservableList<combo> list = comboDAO.getPlanetList();
    comboBox.setItems(list);

    comboBox.setOnAction(actionEvent -> {
        editField2.setText(String.valueOf(comboBox.getValue()));
    });
}



}



