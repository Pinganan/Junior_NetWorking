
import java.io.*;
import java.rmi.*;
import java.util.ArrayList;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.lang.Thread;

public class Client {
    public static void main(String[] args) {
        FeatureInterface feature = null;
        UserAccount user = null;

        BeginGUI beginGUI = new BeginGUI();
        registerGUI registerGUI = new registerGUI();

        try {
            feature = (FeatureInterface) Naming.lookup("rmi://127.0.0.1/arithmetic");
            System.out.println("RMI server connected");
        } catch (Exception e) {
            System.out.println("--------------------------------------------------");
        }

        try {
            while (true) {
                // start GUI
                // button for login interface is clicked
                if (beginGUI.typeNum == 1) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {

                    }
                    if (feature.userLogin(beginGUI.getName(), beginGUI.getPass())) {
                        user = new UserAccount(beginGUI.getName(), beginGUI.getPass());
                        beginGUI.setVisible(false);
                        break;
                    }
                    System.out.println(beginGUI.getName() + beginGUI.getPass());
                }
                // button for register interface is clicked
                else if (beginGUI.typeNum == 2) {
                    beginGUI.setVisible(false);
                    registerGUI.setVisible(true);
                    while (true) {
                        if (registerGUI.isRegisterFin) {
                            registerGUI.isRegisterFin = false;
                            if (feature.userRegister(registerGUI.getUserName(), registerGUI.getPass())) {
                                registerGUI.setVisible(false);
                                beginGUI.setVisible(true);
                                beginGUI.typeNum = 0;
                                break;
                            }
                        }
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {

                        }
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                }
            }

            feature.AllSubject(user.getName());
        } catch (Exception e) {
            System.out.println("ArithmeticServer exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
