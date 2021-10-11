package prof.hibernate;

public class prof {
    private int id;
    private String name;
    private String edu;
    private String diff;
    private String salary;



    public prof(int id, String name, String edu,String diff,String salary) {
        this.id = id;
        this.name=name;
        this.edu=edu;
        this.diff=diff;
        this.salary=salary;
    }

    public prof() {
    }



    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getEdu() {
        return edu;
    }

    public String getDiff() {
        return diff;
    }

    public int getId() {
        return id;
    }

    @Override

    public String toString() {
        return " "+id+" "+name+" "+edu+" "+diff+" "+salary+" ";
    }
}
