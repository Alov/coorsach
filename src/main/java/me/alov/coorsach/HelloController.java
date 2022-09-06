package me.alov.coorsach;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> middleNameColumn;

    @FXML
    TextField lastNameInput;
    @FXML
    TextField firstNameInput;
    @FXML
    TextField middleNameInput;
    @FXML
    Button saveButton;
    @FXML
    Button addButton;



    private ObservableList<Student> studentList = FXCollections.observableArrayList();


    @FXML
    private void addStudent() {
        String lastName = lastNameInput.getCharacters().toString();
        String firstName = firstNameInput.getCharacters().toString();
        String middleName = middleNameInput.getCharacters().toString();

        Student newStudent = new Student(
                new SimpleStringProperty(lastName),
                new SimpleStringProperty(firstName),
                new SimpleStringProperty(middleName)
        );
        studentList.add(newStudent);
    }

    @FXML
    private void saveStudents() {
        String lastName = lastNameInput.getCharacters().toString();
        String firstName = firstNameInput.getCharacters().toString();
        String middleName = middleNameInput.getCharacters().toString();

        int focusedIndex = studentsTable.getSelectionModel().getFocusedIndex();
        Student student = studentList.get(focusedIndex);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMiddleName(middleName);
    }


//    [студент1, студент2, студент3]
//       0           1          2

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

           studentsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showStudent(newValue));

        studentList.add(new Student(
                new SimpleStringProperty("Илья"),
                new SimpleStringProperty("Шаталов"),
                new SimpleStringProperty("Андреевич")

        ));

        studentList.add(new Student(
                new SimpleStringProperty("Олег"),
                new SimpleStringProperty("Анисимов"),
                new SimpleStringProperty("Борисович")
        ));

        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        middleNameColumn.setCellValueFactory(cellData -> cellData.getValue().middleNameProperty());

        studentsTable.setItems(studentList);
    }

    private void showStudent(Student st) {
        lastNameInput.setText(st.getLastName());
        firstNameInput.setText(st.getFirstName());
        middleNameInput.setText(st.getMiddleName());
    }
}