package sample;

public class combo {
    public String code;
    public String name;
    public combo()
    {

    }

    public combo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString()  {
        return this.name;
    }
}
