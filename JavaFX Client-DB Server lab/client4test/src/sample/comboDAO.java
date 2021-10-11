package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class comboDAO {
    public static ObservableList<combo> getPlanetList() {
        combo name = new combo("name", "Name");
        combo education = new combo("edu", "Education");
        combo difficulty = new combo("dif", "Difficulty");
        combo salary = new combo("sal", "Salary");
        combo popularity = new combo("pop", "Popularity");

        ObservableList<combo> list //
                = FXCollections.observableArrayList(name,education,difficulty,salary,popularity);

        return list;
    }
}
