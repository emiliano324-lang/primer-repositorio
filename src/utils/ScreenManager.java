package utils;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class ScreenManager {

    private static CardLayout layout;
    private static JPanel container;

 
    public static void initialize(CardLayout l, JPanel c) {

        layout = l;
        container = c;
    }

   
    public static void addPanel(JPanel panel, String name) {

        container.add(panel, name);
    }

   
    public static void showPanel(String name) {

        layout.show(container, name);
    }
    
    
    
}
	
	
	
	
	

