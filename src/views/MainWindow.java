package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	public static final String HOME = "HOME";
	public static final String USERS = "USERS";
	 
	
	
	public JMenuItem mItemExit;
	public JButton btnUsers;
	public JButton btnHome;
	public UsersView usersPanel;
	public JButton btnPlay;
	
	
	private CardLayout cardLayout;
	private JPanel container;

	public MainWindow() {

		setSize(500, 500);
		setTitle("Mi aplicación");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setMenu();

		JPanel panel = new JPanel();
		add(panel);
		
		createNavbar();
		createViews();
		
		setVisible(true);
	}
	
	public void createNavbar() {
		
		JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		btnHome = new JButton("Inicio");
		btnUsers = new JButton("Usuarios");
	
		navbar.add(btnHome);
		navbar.add(btnUsers);
	
		add(navbar, BorderLayout.NORTH);
	}
	
	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		
		JPanel homePanel = new JPanel();
		homePanel.add(new JLabel("Bienvenido al Sistema"));
		
		usersPanel = new UsersView();
		
		container.add(homePanel, HOME);
		container.add(usersPanel, USERS);
		
		add(container, BorderLayout.CENTER);
		
	}
	
	public void showView(String view) {
		cardLayout.show(container, view);
	}

	public void setMenu() {

	    JMenuBar mb = new JMenuBar();
	    setJMenuBar(mb);

	    JMenu menuFile = new JMenu("File");
	    menuFile.setMnemonic(KeyEvent.VK_F);
	    mb.add(menuFile);

	    JMenuItem mItemOpen = new JMenuItem("Open");
	    mItemOpen.setMnemonic(KeyEvent.VK_O);
	    menuFile.add(mItemOpen);

	    JMenuItem mItemSave = new JMenuItem("Save");
	    mItemSave.setMnemonic(KeyEvent.VK_S);
	    menuFile.add(mItemSave);

	    menuFile.addSeparator();
	    
	    
	    
	    
	    mItemExit = new JMenuItem("Exit");
	    mItemExit.setMnemonic(KeyEvent.VK_E);
	    menuFile.add(mItemExit);

	    JMenu menuOtherOption = new JMenu("Other Option");
	    menuOtherOption.setMnemonic(KeyEvent.VK_O);
	    mb.add(menuOtherOption);

	    JMenu menuOption1 = new JMenu("Option 1");
	    menuOtherOption.add(menuOption1);

	    JMenuItem mItemOption3 = new JMenuItem("Option 3");
	    menuOption1.add(mItemOption3);

	    JMenuItem mItemOption2 = new JMenuItem("Option 2");
	    menuOtherOption.add(mItemOption2);

	}
	
	
	
	
	
	public int confirmExit() {
	    return JOptionPane.showConfirmDialog(
	        this,
	        "¿Seguro que deseas regresar? Se perderán todos los datos",
	        "¿Seguro?",
	        JOptionPane.YES_NO_OPTION
	    );
	}
}