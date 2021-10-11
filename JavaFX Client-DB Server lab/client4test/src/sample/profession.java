package sample;

public class profession {
  private   String idprofessions;
    private String profession_name;
    private  String profession_edu;
    private   String profession_diff;
    private   String profession_salary;
    private  String profession_pop;

public profession(String idprofessions, String profession_name, String profession_edu, String profession_diff, String profession_salary, String profession_pop)
{
    this.idprofessions=idprofessions;
    this.profession_name=profession_name;
    this.profession_edu=profession_edu;
    this.profession_diff=profession_diff;
    this.profession_salary=profession_salary;
    this.profession_pop=profession_pop;
}

    public String getIdprofessions() {
        return idprofessions;
    }

    public String getProfession_diff() {
        return profession_diff;
    }

    public String getProfession_edu() {
        return profession_edu;
    }

    public String getProfession_name() {
        return profession_name;
    }

    public String getProfession_pop() {
        return profession_pop;
    }

    public String getProfession_salary() {
        return profession_salary;
    }

    public void setIdprofessions(String idprofessions) {
        this.idprofessions = idprofessions;
    }

    public void setProfession_diff(String profession_diff) {
        this.profession_diff = profession_diff;
    }

    public void setProfession_edu(String profession_edu) {
        this.profession_edu = profession_edu;
    }

    public void setProfession_name(String profession_name) {
        this.profession_name = profession_name;
    }

    public void setProfession_pop(String profession_pop) {
        this.profession_pop = profession_pop;
    }

    public void setProfession_salary(String profession_salary) {
        this.profession_salary = profession_salary;
    }

}
