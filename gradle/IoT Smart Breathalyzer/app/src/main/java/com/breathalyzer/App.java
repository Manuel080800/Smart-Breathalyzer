package com.breathalyzer;

import com.breathalyzer.view.Home;

public class App {
    public void runApp() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
         new App().runApp();
    }
}
