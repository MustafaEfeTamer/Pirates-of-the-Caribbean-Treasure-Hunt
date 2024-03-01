module com.example.proje2_1deneme {
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.media;


    opens com.example.proje2_1deneme to javafx.fxml;
    exports com.example.proje2_1deneme;
    exports com.example.proje2_1deneme.HareketsizEngeller;
    opens com.example.proje2_1deneme.HareketsizEngeller to javafx.fxml;
}