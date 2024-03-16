package com.example.proje2_1deneme;

import com.example.proje2_1deneme.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AnaArayuz extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GAMERS🕹️");

        Group root = new Group();

        // Arka plan resmini yükle
        Image backgroundImage = new Image("file:///C:\\Users\\musta\\Desktop\\Engeller\\KarayıpKorsanlarıArkaPlan.jpg/");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Arka plan resminin boyutunu ve konumunu ayarla
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(800);
        backgroundImageView.setLayoutX(0);
        backgroundImageView.setLayoutY(0);

        // Ana arayüzde bir buton oluştur
        Button haritaButonu = new Button("Oyun Ekranına Gidiş 🕹️🎮");
        haritaButonu.setStyle("-fx-background-color: #9400d3; -fx-text-fill: white");
        haritaButonu.setLayoutX(325);
        haritaButonu.setLayoutY(700);

        // Butona tıklama olayını tanımla
        haritaButonu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Harita arayüzüne geçiş yap
                Main main = new Main();
                try {
                    main.start(new Stage());
                    // Ana arayüzü kapat
                    primaryStage.close();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Kök düğümüne arka plan resmi ve butonu ekle
        root.getChildren().addAll(backgroundImageView, haritaButonu);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
      /*  try {
            File soundFile = new File("C:\\JavaIntellijKodlarım\\karayip-korsanlari-orjinal-muzik (1).wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        launch(args);
    }
}

