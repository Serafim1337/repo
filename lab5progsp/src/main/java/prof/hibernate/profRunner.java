package prof.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class profRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) throws IOException {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        profRunner ProfRunner = new profRunner();

while (true) {
    switch (Integer.parseInt(menu())) {
        case 1:
            System.out.println("Adding new profession record to the DB");
            BufferedReader addReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("name ->");
String name = addReader.readLine();
            System.out.println("edu ->");
String edu = addReader.readLine();
            System.out.println("diff ->");
            String diff = addReader.readLine();
            System.out.println("salary ->");
            String salary = addReader.readLine();
            ProfRunner.addProf(0, name, edu, diff, salary);
System.out.println(">>record added successfully<<");

            System.out.println("===================================");

            System.out.println("Final list of students");
            System.out.println("===================================");

            selectList(listProfs());
            System.out.println("===================================");
            break;
        case 2:
            selectList(listProfs());
            break;
        case 3:
            String whatToDo;
            String editID;
            BufferedReader editReader = new BufferedReader(new InputStreamReader(System.in));
            switch (Integer.parseInt(editMenu()))
            {

                case 1:
                    System.out.println("Enter id -> ");
                 editID = editReader.readLine();
                 System.out.println("Enter new value -> ");
                String editName = editReader.readLine();
                whatToDo="name";
                updateProf(Integer.parseInt(editID), editName,whatToDo);
                System.out.println("Row id : "+editID+" name changed to :"+editName);
                selectList(listProfs());
                break;
                case 2:
                    System.out.println("Enter id -> ");
                   editID = editReader.readLine();
                    System.out.println("Enter new value -> ");
                    String editEdu = editReader.readLine();
                    whatToDo="edu";
                    updateProf(Integer.parseInt(editID), editEdu,whatToDo);
                    System.out.println("Row id : "+editID+" education changed to :"+editEdu);
                    selectList(listProfs());
                    break;
                case 3:
                    System.out.println("Enter id -> ");
                    editID = editReader.readLine();
                    System.out.println("Enter new value -> ");
                    String editDiff = editReader.readLine();
                    whatToDo="diff";
                    updateProf(Integer.parseInt(editID), editDiff,whatToDo);
                    System.out.println("Row id : "+editID+" difficulty changed to :"+editDiff);
                    selectList(listProfs());
                    break;
                case 4:
                    System.out.println("Enter id -> ");
                    editID = editReader.readLine();
                    System.out.println("Enter new value -> ");
                    String editSalary = editReader.readLine();
                    whatToDo="salary";
                    updateProf(Integer.parseInt(editID), editSalary,whatToDo);
                    System.out.println("Row id : "+editID+" salary changed to :"+editSalary);
                    selectList(listProfs());
                    break;
                case 5:
                    break;
                default: System.out.println("error number"); break;
            }
            break;
        case 4:String deleteID;
            BufferedReader deleteReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter id -> ");
            deleteID = deleteReader.readLine();
            deleteProf(Integer.parseInt( deleteID));
            break;
        case 5:
            return;
        default:
            System.out.println("error number");
            break;
    }
}

    }

    public void addProf(int id, String name, String edu, String diff, String salary) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        prof profession = new prof(id, name, edu, diff, salary);
        session.save(profession);
        transaction.commit();
        session.close();
    }


    public static List<prof> listProfs() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List professions = session.createQuery("FROM prof").list();

        transaction.commit();
        session.close();
        return professions;
    }
    public static String menu() throws IOException {
        BufferedReader menuReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose functions : ");
        System.out.println("1-add");
        System.out.println("2-select");
        System.out.println("3-edit");
        System.out.println("4-delete row");
        System.out.println("5-quit");
        System.out.println("->");
       String choice=menuReader.readLine();
        System.out.println("Your choice : "+choice);
return choice;
    }

    public static String editMenu() throws IOException {
        BufferedReader editMenuReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose column to edit : ");
        System.out.println("1-name");
        System.out.println("2-education");
        System.out.println("3-difficulty");
        System.out.println("4-salary");
        System.out.println("5-back");
        System.out.println("->");
        String choice=editMenuReader.readLine();
        System.out.println("Your choice : "+choice);
        return choice;
    }

    public static void selectList(List<prof> profs)
    {
        System.out.println("==================================================================================");

        System.out.println("List of professions");

        System.out.println("==================================================================================");
        System.out.println("              ID   |      Name   |    Education   |   Difficulty   |   Salary   |");
        List<prof> professions = listProfs();
        for (prof profession : professions) {
            String separator[]= profession.toString().split(" ");
            String str1=separator[0];
            String str2=separator[1];
            String str3=separator[2];
            String str4=separator[3];
            String str5=separator[4];
            String str6=separator[5];
            System.out.format("%s%15s%15s%15s%15s%15s\n", str1, str2, str3,str4, str5,str6);

        }
        System.out.println("==================================================================================");

    }

    public static void updateProf(int id, String columnToEdit,String whatToDo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        if (whatToDo.equals("name")){
            prof student =  session.get(prof.class, id);
            student.setName(columnToEdit);
            session.update(student);
        }
        if (whatToDo.equals("edu")){
            prof student =  session.get(prof.class, id);
            student.setEdu(columnToEdit);
            session.update(student);
        }
        if (whatToDo.equals("diff")){
            prof student =  session.get(prof.class, id);
            student.setDiff(columnToEdit);
            session.update(student);
        }
        if (whatToDo.equals("salary")){
            prof student =  session.get(prof.class, id);
            student.setSalary(columnToEdit);
            session.update(student);
        }


        transaction.commit();
        session.close();
    }

    public static void deleteProf(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        prof profession =  session.get(prof.class, id);
        session.delete(profession);
        System.out.println("Row id : "+id+" deleted ");
        transaction.commit();
        session.close();
    }


}
