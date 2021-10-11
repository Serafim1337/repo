import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

public class Server {

    public static Connection databaseConnection;
    public static ResultSet resultSet;
    public static Statement statement;
   public static ServerSocket serverSocket = null;
   public static DataInputStream in = null;
   public static ObjectOutputStream out =null ;

    static ArrayList <String> professionsList = new ArrayList<String>();
    public static void connectDatabase() throws SQLException, IOException {
        String select = "SELECT * FROM "+Configs.dbName;
String connectionString ="jdbc:mysql://"+Configs.dbHost+":"+Configs.dbPort+"/"+Configs.dbName;
databaseConnection=DriverManager.getConnection(connectionString,Configs.dbUser,Configs.dbPassword);
    statement=databaseConnection.createStatement();
    resultSet=statement.executeQuery(select);
    System.out.println("Connected to DB successfully");
    while (resultSet.next())
    {
        String idprofessions=resultSet.getString(1);
        String profession_name = resultSet.getString(2);
                String profession_edu =resultSet.getString(3);
        String profession_diff = resultSet.getString(4);
                String profession_salary = resultSet.getString(5);
        String profession_pop = resultSet.getString(6);
        System.out.println(" id= "+idprofessions+" name= "+profession_name+ " edu= "+profession_edu+
                " diff= "+profession_diff+" salary= "+profession_salary+
                " pop= "+profession_pop);
        String databaseRow = idprofessions+" "+profession_name+" "+ profession_edu+
                " "+profession_diff+" "+ profession_salary+" "+profession_pop;
        professionsList.add(databaseRow);
    }
    out.writeUnshared(professionsList);
    }


    public static void selectDatabase() throws SQLException, IOException {
        professionsList.clear();


        String select = "SELECT * FROM "+Configs.dbName;
            resultSet=statement.executeQuery(select);

            while (resultSet.next())
            {
                String idprofessions=resultSet.getString(1);
                String profession_name = resultSet.getString(2);
                String profession_edu =resultSet.getString(3);
                String profession_diff = resultSet.getString(4);
                String profession_salary = resultSet.getString(5);
                String profession_pop = resultSet.getString(6);
                String databaseRow = idprofessions+" "+profession_name+" "+ profession_edu+
                        " "+profession_diff+" "+ profession_salary+" "+profession_pop;
                professionsList.add(databaseRow);
            }
            out.writeUnshared(professionsList);
    }


    public static void insertDatabase( String profession_name,String profession_edu,String profession_diff,String profession_salary,String profession_pop) throws SQLException, IOException {
        String insert="INSERT INTO "+Const.PROFESSIONS_TABLE+"("+Const.PROFESSION_NAME+","+
                Const.PROFESSION_EDU+","+Const.PROFESSION_DIFF+","+Const.PROFESSION_SALARY+
                ","+Const.PROFESSION_POP+")"+"VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement =databaseConnection.prepareStatement(insert);
        preparedStatement.setString(1,profession_name);
        preparedStatement.setString(2,profession_edu);
        preparedStatement.setString(3,profession_diff);
        preparedStatement.setString(4,profession_salary);
        preparedStatement.setString(5,profession_pop);
preparedStatement.executeUpdate();


        System.out.println("Added row");

        selectDatabase();

    }


    public static void editDatabaseRow(String id, String columnToEdit, String newValue) throws SQLException, IOException {

        String update = null;

        if (columnToEdit.toLowerCase().equals("name"))
        {
            System.out.println("Edit name received -> "+id+" "+columnToEdit+" "+newValue);
update="UPDATE "+Const.PROFESSIONS_TABLE+" SET "
        +Const.PROFESSION_NAME+" = ? WHERE "+Const.PROFESSION_ID+" = ?";

        }

        if (columnToEdit.toLowerCase().equals("education"))
        {
            System.out.println("Edit education received -> "+id+" "+columnToEdit+" "+newValue);
            update="UPDATE "+Const.PROFESSIONS_TABLE+" SET "
                    +Const.PROFESSION_EDU+" = ? WHERE "+Const.PROFESSION_ID+" = ?";
        }

        if (columnToEdit.toLowerCase().equals("difficulty"))
        {
            System.out.println("Edit difficulty received -> "+id+" "+columnToEdit+" "+newValue);
            update="UPDATE "+Const.PROFESSIONS_TABLE+" SET "
                    +Const.PROFESSION_DIFF+" = ? WHERE "+Const.PROFESSION_ID+" = ?";
        }

        if (columnToEdit.toLowerCase().equals("salary"))
        {
            System.out.println("Edit salary received -> "+id+" "+columnToEdit+" "+newValue);
            update="UPDATE "+Const.PROFESSIONS_TABLE+" SET "
                    +Const.PROFESSION_SALARY+" = ? WHERE "+Const.PROFESSION_ID+" = ?";
        }

        if (columnToEdit.toLowerCase().equals("popularity"))
        {
            System.out.println("Edit popularity received -> "+id+" "+columnToEdit+" "+newValue);
            update="UPDATE "+Const.PROFESSIONS_TABLE+" SET "
                    +Const.PROFESSION_POP+" = ? WHERE "+Const.PROFESSION_ID+" = ?";
        }
        PreparedStatement preparedStatement = databaseConnection.prepareStatement(update);

        preparedStatement.setString(1,newValue);
        preparedStatement.setString(2,id);
        preparedStatement.executeUpdate();
        System.out.println("Row edited");
        selectDatabase();
    }



public  static LinkedList <ServerClass> serverClassLinkedList = new LinkedList<>();
    public static void main(String args[]) throws SQLException, IOException  {
        int clientsConnectedCount=0;
        serverSocket = new ServerSocket(ClientConnectionHandler.PORT);
        System.out.println("Server started");
        try {
//Server.connectDatabase();
            while (true)
            {
                Socket newClient = serverSocket.accept();
                clientsConnectedCount++;
                System.out.println("Client connected ->"+clientsConnectedCount);
                serverClassLinkedList.add(new ServerClass(newClient));
            }
        } finally {
            serverSocket.close();
            System.out.println("Server stopped");
        }
    }
}
class ServerClass extends Thread
{
    Socket newClient;
    ResultSet resultSet;
    Statement statement;





    public ServerClass(Socket newClient) {
        this.newClient=newClient;
        start();
    }

    public void run()
    {
        byte[] bytes= new byte[100000];
        try {
            Server.in= new DataInputStream(newClient.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
           Server.out = new ObjectOutputStream(newClient.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
try {


        boolean flag = true;
        while (flag)
        {

                String receivedData= Server.in.readUTF();
            String[] dataSplit = receivedData.split(" ");
            if (dataSplit[0].equals("connectSql"))
            {
                Server.connectDatabase();
            }

            if (dataSplit[0].equals("selectSql"))
            {
Server.selectDatabase();
            }

            if (dataSplit[0].equals("addTableRow"))
            {

                System.out.println("----"+dataSplit[1]+dataSplit[2]+dataSplit[3]+dataSplit[4]+dataSplit[5]);
                Server.insertDatabase(dataSplit[1],dataSplit[2],dataSplit[3],dataSplit[4],dataSplit[5]);
            }

            if (dataSplit[0].equals("editTableRow"))
            {
                System.out.println("Edit row id : "+dataSplit[1]+" column : "+dataSplit[2]+" new value : "+dataSplit[3]);
            Server.editDatabaseRow(dataSplit[1],dataSplit[2],dataSplit[3]);
            }
        }

    } catch (IOException | SQLException e)
{
    e.printStackTrace();
}
    }

}