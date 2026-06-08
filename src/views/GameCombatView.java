package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import models.Player;
import utils.AppFont;
import utils.CustomJSwing;
import utils.Session;
/**
 * Vista principal del combate del juego.
 *
 * <p>Esta clase representa la interfaz gráfica donde se desarrolla el combate
 * entre el jugador y el enemigo. Gestiona la visualización de personajes,
 * barras de vida, animaciones, acciones de combate y mensajes informativos.</p>
 *
 * <p>Forma parte de la capa de vistas (View) siguiendo una arquitectura MVC.</p>
 * @author Hugo  
 * @author Emiliano
 * @version 1.0
 */
public class GameCombatView extends JPanel {

	GameWindow window;

	Player player;
	
	private Timer animationIdle;
	private Timer animation;

	private int selfFrame = 0;
	private int foeFrame = 0;

	private JProgressBar healthBar;

	private JButton attack;
	private JButton block;
	private JButton heal;
	private JButton analyze;

	private JButton back;
	private JButton switchTurn;

	private JLabel characterSelf;
	private JLabel characterFoe;
	private JLabel lblTopPanelMessage;
	

	Image combatBackground;

	private String[] idleFramesSelf = { 
			"/img/IdleSelf/IdleSelf0.png", 
			"/img/IdleSelf/IdleSelf1.png",
			"/img/IdleSelf/IdleSelf2.png",
			"/img/IdleSelf/IdleSelf3.png"
			};

	private String[] idleFramesFoe = { 
			"/img/IdleFoe/IdleFoe0.png", 
			"/img/IdleFoe/IdleFoe1.png",
			"/img/IdleFoe/IdleFoe2.png", 
			"/img/IdleFoe/IdleFoe3.png"
			};

	private String[] attackFramesSelf = {
			"/img/AttackSelf/AttackSelf0.png",
			"/img/AttackSelf/AttackSelf1.png",
			"/img/AttackSelf/AttackSelf2.png",
			"/img/AttackSelf/AttackSelf3.png"
			};

	private String[] attackFramesFoe = {
			"/img/AttackFoe/AttackFoe0.png",
			"/img/AttackFoe/AttackFoe1.png",
			"/img/AttackFoe/AttackFoe2.png",
			"/img/AttackFoe/AttackFoe3.png"
			};

	private String[] blockFramesSelf = {
			"/img/BlockSelf/BlockSelf0.png",
			"/img/BlockSelf/BlockSelf1.png",
			"/img/BlockSelf/BlockSelf2.png",
			"/img/BlockSelf/BlockSelf3.png"
			};

	private String[] blockFramesFoe = { 
			"/img/BlockFoe/BlockFoe0.png",
			"/img/BlockFoe/BlockFoe1.png",
			"/img/BlockFoe/BlockFoe2.png", 
			"/img/BlockFoe/BlockFoe3.png"
			};

	private String[] healFramesSelf = {
			"/img/HealSelf/HealSelf0.png",
			"/img/HealSelf/HealSelf1.png",
			"/img/HealSelf/HealSelf2.png", 
			"/img/HealSelf/HealSelf3.png" 
			};

	private String[] healFramesFoe = {
			"/img/HealFoe/HealFoe0.png",
			"/img/HealFoe/HealFoe1.png",
			"/img/HealFoe/HealFoe2.png", 
			"/img/HealFoe/HealFoe3.png" 
			};

	private String[] damageFramesSelf = {
			"/img/DamageSelf/DamageSelf0.png",
			"/img/DamageSelf/DamageSelf1.png",
			"/img/DamageSelf/DamageSelf2.png", 
			"/img/DamageSelf/DamageSelf3.png"
			};

	private String[] damageFramesFoe = {
			"/img/DamageFoe/DamageFoe0.png", 
			"/img/DamageFoe/DamageFoe1.png",
			"/img/DamageFoe/DamageFoe2.png", 
			"/img/DamageFoe/DamageFoe3.png" };

	// GETTERS Y SETTERS
	
	/**
	 * Obtiene el botón de ataque.
	 *
	 * @return botón utilizado para realizar ataques.
	 */
	
	public JButton getAttack() {
		return attack;
	}
	/**
	 * Obtiene el boton de bloqueo
	 * 
	 * @return boton utilizado para bloquear
	 */
	public JButton getBlock() {
		return block;
	}
	/**
	 * Bobtiene el boton de curacion
	 * 
	 * @return boton utilizado para curar
	 */
	public JButton getHeal() {
		return heal;
	}
	/** 
	 * Obtiene el boton de analizar 
	 * 
	 * @return boton utilizado para ver la vida del enemigo
	 */

	public JButton getAnalyze() {
		return analyze;
	}
	/**
	 * Obtiene el boton de  cambiar turno
	 * 
	 * @return boton utilizado para cambiar de turno y dejar que el enemigo tome su turno
	 */
	
	public JButton getSwitchTurn(){
		return switchTurn;
	}
	
	/**
	 * Metodo para obtener la ventana GameWindow
	 * 
	 * @return se utiliza para obtener metodos de GameWindow
	 */
	public GameWindow getWindow() {
		return window;
	}

	/**
	 * Obtiene el temporizador utilizado para las animaciones de reposo.
	 *
	 * @return temporizador de animaciones idle.
	 */

	public Timer getAnimationIdle() {
		return animationIdle;
	}
	/**
	 * Obtiene el temporizador utilizado para las animaciones de acción.
	 *
	 * @return temporizador de animaciones.
	 */
	public Timer getAnimation() {
		return animation;
	}
	/**
	 * Obtiene el índice del frame actual del personaje jugador.
	 *
	 * @return índice del frame actual.
	 */
	public int getSelfFrame() {
		return selfFrame;
	}
	/**
	 * Establece el frame actual del personaje jugador.
	 *
	 * @param selfFrame nuevo índice del frame.
	 */
	public void setSelfFrame(int selfFrame) {
		this.selfFrame = selfFrame;
	}
	/**
	 * Establece el frame actual del personaje enemigo.
	 *
	 * @param foeFrame nuevo índice del frame.
	 */
	public void setFoeFrame(int foeFrame) {
		this.foeFrame = foeFrame;
	}
	/**
	 * Obtiene el índice del frame actual del personaje enemigo.
	 *
	 * @return índice del frame actual.
	 */
	public int getFoeFrame() {
		return foeFrame;
	}
	/**
	 * Obtiene la secuencia de imágenes de reposo del jugador.
	 *
	 * @return arreglo con las rutas de los frames de reposo del jugador.
	 */
	public String[] getIdleFramesSelf() {
		return idleFramesSelf;
	}
	/**
	 * Obtiene la secuencia de imágenes de reposo del enemigo.
	 *
	 * @return arreglo con las rutas de los frames de reposo del enemigo.
	 */
	public String[] getIdleFramesFoe() {
		return idleFramesFoe;
	}
	/**
	 * Obtiene la secuencia de imágenes de ataque del jugador.
	 *
	 * @return arreglo con las rutas de los frames de ataque del jugador.
	 */
	public String[] getAttackFramesSelf() {
		return attackFramesSelf;
	}
	/**
	 * Obtiene la secuencia de imágenes de ataque del enemigo.
	 *
	 * @return arreglo con las rutas de los frames de ataque del enemigo.
	 */
	public String[] getAttackFramesFoe() {
		return attackFramesFoe;
	}
	/**
	 * Obtiene la secuencia de imágenes de bloqueo del jugador.
	 *
	 * @return arreglo con las rutas de los frames de bloqueo del jugador.
	 */
	public String[] getBlockFramesSelf() {
		return blockFramesSelf;
	}
	/**
	 * Obtiene la secuencia de imágenes de bloqueo del enemigo.
	 *
	 * @return arreglo con las rutas de los frames de bloqueo del enemigo.
	 */
	public String[] getBlockFramesFoe() {
		return blockFramesFoe;
	}
	/**
	 * Obtiene la secuencia de imágenes de curación del jugador.
	 *
	 * @return arreglo con las rutas de los frames de curación del jugador.
	 */
	public String[] getHealFramesSelf() {
		return healFramesSelf;
	}

	/**
	 * Obtiene la secuencia de imágenes de curación del enemigo.
	 *
	 * @return arreglo con las rutas de los frames de curación del enemigo.
	 */
	public String[] getHealFramesFoe() {
		return healFramesFoe;
	}
	/**
	 * Obtiene la secuencia de imágenes de daño recibido del jugador.
	 *
	 * @return arreglo con las rutas de los frames de daño.
	 */
	public String[] getDamageFramesSelf() {
		return damageFramesSelf;
	}

	/**
	 * Obtiene la secuencia de imágenes de daño recibido del enemigo.
	 *
	 * @return arreglo con las rutas de los frames de daño.
	 */
	public String[] getDamageFramesFoe() {
		return damageFramesFoe;
	}
	/**
	 * Obtiene la etiqueta gráfica que representa al jugador.
	 *
	 * @return etiqueta del personaje jugador.
	 */
	public JLabel getCharacterSelf() {
		return characterSelf;
	}
	/**
	 * Obtiene la etiqueta gráfica que representa al enemigo.
	 *
	 * @return etiqueta del personaje enemigo.
	 */
	public JLabel getCharacterFoe() {
		return characterFoe;
	}
	/**
	 * Obtiene la imagen de fondo utilizada durante el combate.
	 *
	 * @return imagen de fondo del combate.
	 */
	public Image getCombatBackground() {
		return combatBackground;
	}

	
	/*
	 * Constructor de combatView
	 * 
	 * Inicializa los componentes e inicia las animaciones
	 */
	public GameCombatView() {
		setLayout(new BorderLayout());

		loadImage();
		initializeComponents();
		animationIdle(idleFramesSelf, idleFramesFoe);

	}

	// MÉTODOS 	
	/**
	 * Se inicializa los componentes
	 * 
	 * se crea borderLeyout donde se insertaran los componentes 
	 */
	private void initializeComponents() {

		JPanel registersPanel = createTopPanel();
		JPanel centerPanel = createCenterPanel();
		JPanel southBar = createActionsPanel();

		add(registersPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southBar, BorderLayout.SOUTH);
	}

	/**
	 * Se inicializa el panel central del juego
	 * 
	 * <p>Este panel contiene la representacion grafica del jugador y el enemigo
	 * ambios personajes se posicionan  utilizando un GridBagLayout. Y se cargan
	 * las imagenes de reposo de cada personaje 
	 * 
	 * @return panel central con los personajes
	 */
	private JPanel createCenterPanel() {

		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;

		ImageIcon iconSelf = loadIcon("/img/IdleSelf/IdleSelf0.png", 128 * 2, 192 * 2);
		characterSelf = new JLabel(iconSelf);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.SOUTHWEST;

		gbc.insets = new Insets(180, 0, 0, 0);

		centerPanel.add(characterSelf, gbc);

		ImageIcon iconFoe = loadIcon("/img/IdleFoe/IdleFoe0.png", 128, 192);
		characterFoe = new JLabel(iconFoe);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.NORTHEAST;

		gbc.insets = new Insets(200, 0, 0, 0);

		centerPanel.add(characterFoe, gbc);

		return centerPanel;
	}

	/**
	 * Crea y configura el panel superior de la interfaz de combate.
	 * 
	 * <p> Este panel muestra mansajes informativos hacerca del combate.
	 * 
	 * @return panel superior 
	 */
	private JPanel createTopPanel() {

		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);

		lblTopPanelMessage = createLabel("Ha comenzado la pelea");

		topPanel.add(lblTopPanelMessage);

		return topPanel;
	}
	/**
	 * Crea y configura el panel de acciones del jugador.
	 * 
	 * <p>Este metodo contiene la barra de vida y los diferentes botones usados durante el combate
	 * como ataca, bloquer, curarse, pasar turno, analizar, y terminar turno.
	 * 
	 * @retur pnanel de acciones configurado para el combate.
	 */

	private JPanel createActionsPanel() {

		JPanel actionsPanel = new JPanel();
		actionsPanel.setOpaque(false);

		healthBar = createProgressBar();

		attack = CustomJSwing.createJButton("Atacar");
		block = CustomJSwing.createJButton("Bloquear");
		heal = CustomJSwing.createJButton("Curarse");
		analyze = CustomJSwing.createJButton("Analizar");
		switchTurn = CustomJSwing.createJButton("Terminar Turno");
		

		actionsPanel.add(healthBar);
		actionsPanel.add(attack);
		actionsPanel.add(block);
		actionsPanel.add(heal);
		actionsPanel.add(analyze);
		actionsPanel.add(switchTurn);

		return actionsPanel;
	}
	/**
	 * Carga una imagen desde los recursos del proyecto.
	 * 
	 *  <p>La imagen cargada se utiliza para representar personajes,
	 * animaciones u otros elementos gráficos de la interfaz.</p>
	 * 
	 * @param path ruta de la imagen	
	 * @param w anchura de la imagen
	 * @param h altura de la imagen
	 * @return ImageIcon con la imagen deseada o null si ocurre un error.
	 */
	private ImageIcon loadIcon(String path, int w, int h) {
		
		try {
			Image icon = ImageIO.read(getClass().getResource(path));
			icon = icon.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icon);
		
		} catch (Exception ex) {
			System.out.println(path);
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * Crea una imagen desde los recursos del proyecto.
	 * 
	 * <p>La imagen se obtiene desde los recursos del proyecto y se almacena
	 * para ser renderizada posteriormente en la interfaz gráfica.</p>
	 * 
	 *  <p>Si la imagen no puede cargarse, se mostrara un mensaje de error
	 * en la consola.</p>
	 */
	private void loadImage() {
		try {
			combatBackground = ImageIO.read(getClass().getResource("/img/fondo_pelea.jpg"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	}
	/**
	 *  Dibuja los componentes graficos personalizados de la vista.
	 * 
	 * Este método se encarga de renderizar la imagen de fondo del combate
	 * ajustándola al tamaño actual del panel.</p>
	 * 
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(combatBackground, 0, 0, getWidth(), getHeight(), null);
	}
	/**
	 * Inicia la animación de reposo de los personajes.
	 * 
	 * <p> Reproduce la animacion de reposo utilizando los frames proporcionados como parametro
	 * del jugador y el enemigo, esto se hace de forma ciclica<p>.
	 * 
	 * @param framesSelf arreglo con las rutas de los frames de reposo del jugador.
	 * @param framesFoe arreglo con las rutas de los frames de reposo del enemigo.
	 */
	public void animationIdle(String[] framesSelf, String[] framesFoe) {

		animation = new Timer(250, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ImageIcon frameSelf = loadIcon(framesSelf[selfFrame], 128 * 2, 192 * 2);
				ImageIcon frameFoe = loadIcon(framesFoe[foeFrame], 128 * 2, 192 * 2);

				characterSelf.setIcon(frameSelf);
				characterFoe.setIcon(frameFoe);

				selfFrame++;
				foeFrame++;

				if (selfFrame >= framesSelf.length) {
					selfFrame = 0;
				}

				if (foeFrame >= framesFoe.length) {
					foeFrame = 0;
				}
			}
		});

		animation.start();
	}
	
	/**
	 * Reproduce una animación una sola vez para ambos personajes.
	 * 
	 *<p>Al finalizar la secuencia de frames, la animación se detiene,
	 * se reinician los índices de los frames y se vuelve a iniciar
	 * la animación de reposo.</p>
	 * 
	 * 
	 * @param framesSelf arreglo con las rutas de los frames de acción del jugador.
	 * @param framesFoe arreglo con las rutas de los frames de acción del enemigo.
	 */

	public void animateOnce(String[] framesSelf, String[] framesFoe) {

		animation = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ImageIcon frameSelf = loadIcon(framesSelf[selfFrame], 128 * 2, 192 * 2);
				ImageIcon frameFoe = loadIcon(framesFoe[foeFrame], 128 * 2, 192 * 2);

				characterSelf.setIcon(frameSelf);
				characterFoe.setIcon(frameFoe);

				selfFrame++;
				foeFrame++;

				if (selfFrame >= framesSelf.length || foeFrame >= framesFoe.length) {

					animation.stop();

					selfFrame = 0;
					foeFrame = 0;

					animationIdle(idleFramesSelf, idleFramesFoe);
				}
			}
		});

		animation.start();
	}

	/**
	 * Reproduce una animación de acción para ambos personajes.
	 * 
	 * <p>Esta animacion utiliza imagenes de mayor tamaño para resaltar
	 * visualmente acciones de combate como ataques, bloqueos o curaciones.
	 * Una vez finalizada la secuencia, se restauran los índices de los frames
	 * y se reanuda la animación de reposo.</p>
	 * 
	 * @param framesSelf
	 * @param framesFoe
	 */
	
	public void animateAction(String[] framesSelf, String[] framesFoe) {

		animation = new Timer(250, null);

		selfFrame = 0;
		foeFrame = 0;

		animation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ImageIcon frameSelf = loadIcon(framesSelf[selfFrame], 128 * 3, 192 * 3);
				ImageIcon frameFoe = loadIcon(framesFoe[foeFrame], 128 * 3, 192 * 3);

				characterSelf.setIcon(frameSelf);
				characterFoe.setIcon(frameFoe);

				selfFrame++;
				foeFrame++;

				if (selfFrame >= framesSelf.length || foeFrame >= framesFoe.length) {

					animation.stop();

					selfFrame = 0;
					foeFrame = 0;

					animationIdle(idleFramesSelf, idleFramesFoe);
				}
			}
		});

		animation.start();
	}
	/**
	 * Actualiza el mensaje mostrado en el panel superior del combate.
	 *
	 * <p>El texto se actualiza inmediatamente y se refresca la etiqueta
	 * para reflejar el nuevo mensaje en la interfaz gráfica.</p>
	 *
	 * @param text mensaje que se mostrara al usuario.
	 */

	public void topPanelMessage(String text) {
	    lblTopPanelMessage.setText(text);
	    lblTopPanelMessage.repaint();
	    lblTopPanelMessage.revalidate();
	}
	/**
	 * Actualiza el jugador.
	 *
	 * <p>Inicializa al jugador dandole los valores del personaje a la barra de vida.</p>
	 *
	 * @param text mensaje que se mostrará al usuario.
	 */
	public void initializePlayer(Player player) {

		//Player player = Session.getPlayer();
		
	    healthBar.setMaximum(player.getMaxHealth());
	    
	    healthBar.setValue(healthBar.getMaximum());

	    updateHealthBar(player.getHealth());
	    
	    this.revalidate();
	    this.repaint();
	}
	/**
	 * Actualiza el valor y la apariencia de la barra de vida.
	 *
	 * <p>Ademas de modificar la cantidad de vida mostrada, el color de la
	 * barra cambia segun el porcentaje de salud restante:</p>
	 * <ul>
	 *   <li>Verde: mas del 60% de vida.</li>
	 *   <li>Naranja: entre 30% y 60% de vida.</li>
	 *   <li>Rojo: menos del 30% de vida.</li>
	 * </ul>
	 *
	 * @param currentHealth cantidad actual de puntos de vida.
	 */
	public void updateHealthBar(int currentHealth) {

	    healthBar.setValue(currentHealth);

	    double percentage = (double) currentHealth / healthBar.getMaximum();

	    if (percentage > 0.6) {
	        healthBar.setForeground(Color.GREEN);
	    } else if (percentage > 0.3) {
	        healthBar.setForeground(Color.ORANGE);
	    } else {
	        healthBar.setForeground(Color.RED);
	    }

	    healthBar.setString(currentHealth + "/" + healthBar.getMaximum());
	}
	
	/**
	 * Crea una etiqueta con el estilo visual utilizado por la interfaz.
	 *
	 * <p>La etiqueta se configura con la fuente, color y margenes
	 * definidos para los mensajes del combate.</p>
	 *
	 * @param text texto inicial de la etiqueta.
	 * @return etiqueta configurada.
	 */
	private JLabel createLabel(String text) {

		JLabel label = new JLabel(text);

		label.setFont(AppFont.small());
		label.setForeground(new Color(254, 252, 251));
		label.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		return label;
	}
	/**
	 * Crea y configura la barra de vida utilizada durante el combate.
	 *
	 * <p>La barra muestra graficamente la salud actual del jugador y
	 * permite visualizar el valor numerico correspondiente.</p>
	 *
	 * @return barra de progreso configurada para representar la vida.
	 */
	private JProgressBar createProgressBar() {
		JProgressBar progressBar = new JProgressBar();

		
		
		progressBar.setForeground(Color.GREEN);
		progressBar.setValue(progressBar.getMaximum());

		progressBar.setStringPainted(true);
		
		return progressBar;
	}
}
