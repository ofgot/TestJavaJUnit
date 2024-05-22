module org.example.secondhomeworkts1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.secondhomeworkts1 to javafx.fxml;
    exports org.example.secondhomeworkts1;
}