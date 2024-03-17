package com.example.proje2_1deneme;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SonucArayuz extends Application {

    @FXML
    private TextArea field1;

    @FXML
    private TextArea field2;

    @FXML
    private TextArea field3;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setTitle("Sonuç Ekranı");
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // sonuç ekranındaki bilgileri yazdırır.
    @FXML
    private void initialize() {
        ArrayList<Lokasyon> altin = new ArrayList<>();
        ArrayList<Lokasyon> gumus = new ArrayList<>();
        ArrayList<Lokasyon> zumrut = new ArrayList<>();
        ArrayList<Lokasyon> bakir = new ArrayList<>();

        for(Lokasyon eleman : Uygulama.clonedList){
            if(eleman.getSandikTurr() == "altın"){
                altin.add(new Lokasyon(eleman.getxKoordinati(), eleman.getyKoordinati(), eleman.getMesafe(), eleman.getIndex(), eleman.getSandikTurr()));
            }else if(eleman.getSandikTurr() == "gümüş"){
                gumus.add(new Lokasyon(eleman.getxKoordinati(), eleman.getyKoordinati(), eleman.getMesafe(), eleman.getIndex(), eleman.getSandikTurr()));
            }else if(eleman.getSandikTurr() == "zümrüt"){
                zumrut.add(new Lokasyon(eleman.getxKoordinati(), eleman.getyKoordinati(), eleman.getMesafe(), eleman.getIndex(), eleman.getSandikTurr()));
            }else if(eleman.getSandikTurr() == "bakır"){
                bakir.add(new Lokasyon(eleman.getxKoordinati(), eleman.getyKoordinati(), eleman.getMesafe(), eleman.getIndex(), eleman.getSandikTurr()));
            }
        }
        for(Lokasyon eleman : altin){
            field1.appendText(eleman.getSandikTurr() + " sandık toplandı ! (" + eleman.getxKoordinati() + ", " + eleman.getyKoordinati() + ") konumunda bulundu.\n");
        }
        for(Lokasyon eleman : gumus){
            field1.appendText(eleman.getSandikTurr() + " sandık toplandı ! (" + eleman.getxKoordinati() + ", " + eleman.getyKoordinati() + ") konumunda bulundu.\n");
        }
        for(Lokasyon eleman : zumrut){
            field1.appendText(eleman.getSandikTurr() + " sandık toplandı ! (" + eleman.getxKoordinati() + ", " + eleman.getyKoordinati() + ") konumunda bulundu.\n");
        }
        for(Lokasyon eleman : bakir){
            field1.appendText(eleman.getSandikTurr() + " sandık toplandı ! (" + eleman.getxKoordinati() + ", " + eleman.getyKoordinati() + ") konumunda bulundu.\n");
        }

        for(int i = 0; i < Koordinat.koordinatlar.size(); i++){
            field2.appendText( (i+1)+". koordinat : (" + Koordinat.koordinatlar.get(i).getX() + ", " + Koordinat.koordinatlar.get(i).getY() + ")\n");
        }

        field3.setText("Toplam adım sayısı : " + Koordinat.koordinatlar.size());
    }
}
