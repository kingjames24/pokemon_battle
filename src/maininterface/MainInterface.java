package maininterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import players.Player1;
import players.Player2;
import players.Players;
import state.*; 
import pokemon.*; 
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import observer.Observer;
import observer.Subject;

import javax.imageio.ImageIO; 
/**
 *Class that houses the main interface
 *in which the user/users will interact with.
 *It houses the different graphical user interfaces of
 *the game, namely the battlefield, selection screen, 
 *attack screen, and credit screen. Furthermore, the 
 *main interface acts as the context class, which delegates
 *different behavior to the state objects. 
 */
public class MainInterface extends JFrame implements MouseListener
{
	private static MainInterface controlCenter; 
	private static final long serialVersionUID = 1L;
	private BufferedImage bulbasaurImg, charmanderImg, poliwagImg, ivysaurImg, charizardImg, squirtleImg, venusaurImg, charmeleonImg, wartortleImg, bellsproutImg, vulpixImg, blastoiseImg;
	private JLabel a, b, c, d, e, f, g, h, i, j, k, l;
	private JPanel bulbasaurPanel, bellsproutPanel, charizardPanel, charmanderPanel, ivysaurPanel, venusaurPanel, vulpixPanel, wartortlePanel, blastoisePanel, charmeleonPanel, squirtlePanel, poliwagPanel; 
	JPanel selected1 = new JPanel(new BorderLayout()); 
	JPanel selected2 = new JPanel(new BorderLayout()); 
	JPanel selected3 = new JPanel(new BorderLayout());
	JPanel selected4 = new JPanel(new BorderLayout()); 
	JPanel selected5 = new JPanel(new BorderLayout()); 
	JPanel selected6 = new JPanel(new BorderLayout());
	private JButton done; 
	private JPanel  center, left, right, bottom, rightPokemon, leftPokemon;
	private JLabel player1, player2;
	private JPanel fight, choose; 
	private int player1count=0, player2count=0, countClicks=0;  
	public Players playerone, playertwo;
	//Different game states for game
	public GameState gamesetup, endgame, player1Turn, player2Turn, currentState;
	private ScoreBoard score;
	private Boolean change = false; 
	
	/**
	 * Private constructor that implements the 
	 * singleton pattern ensuring that only one 
	 * instance of the MainInterface class is created.
	 * When invoked, the constructor will
	 * create new objects of the state and players
	 * package and set the current state to gamesetup, 
	 * which is where players choose their pokemon for the game
	 */
	private MainInterface()
	{
		playerone = new Player1(); 
		playertwo = new Player2(); 
		gamesetup= new GameSetup(this);
		player1Turn = new PlayerOneTurn(this); 
		player2Turn= new PlayerTwoTurn(this);
		endgame = new Score(this); 
		score = new ScoreBoard(endgame); 
		currentState = gamesetup; 
	}
	/**
	 * Static method that returns a reference to
	 * the MainInterface object
	 * @return a reference to the MainInterface
	 * object
	 */
	public static MainInterface Start()
	{
		if(controlCenter==null)
		{
			controlCenter = new MainInterface(); 
		}
		return controlCenter; 
	}
	/**
	 * Method that displays which player won the game
	 * @param winner an int that represents which player
	 * won the game
	 */
	public void displaycredits(int winner) 
	{
		getContentPane().removeAll();
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
	
		
		if (winner==1)
		{
			JOptionPane.showMessageDialog(null, "Player 1 Won!!!", "Game Over", JOptionPane.NO_OPTION);
			System.exit(0);	
		}
		else if (winner==2)
		{
			JOptionPane.showMessageDialog(null, "Player 2 Won!!!", "Game Over", JOptionPane.NO_OPTION);
			System.exit(0);
		
		}
	}
	/**
	 * Method that updates the currentstate of the game
	 * that concrete states of the state package use to interact 
	 * with the MainInterface
	 * @param state a reference to one of the concrete state objects
	 */
	public void updateState(GameState state)
	{
		currentState = state; 
	}
	
	/**
	 * Public inner class that keeps track of the  
	 * score and notifies 
	 * the Score class(an observer) of different actions
	 * in the game
	 */
	public class ScoreBoard implements Subject
	{
		private GameState state; 
		private Observer observer; 
		
		/**
		 * Public constructor that becomes instantiated
		 * when the MainInterface's private constructor is 
		 * instantiated 
		 * @param state a reference to the Score object
		 */
		public ScoreBoard(GameState state)
		{
			this.state=state; 
		}

		/**
		 * Concrete implementation of the 
		 * Subject interface found in the 
		 * observer package
		 */
		@Override
		public void notifyObservers() 
		{
			observer = (Observer) this.state; 
			observer.update(playerone, playertwo);
			
		}
		
	}
	
	/**
	 * Method that keeps track of a pokemon's health
	 * and makes sure that a pokemon cannot have a negative
	 * number.Also notifies the observers when a pokemon has 
	 * died 
	 */
	public int calculateHitPoints(int hit)
	{
		if (hit<=0)
		{
			score.notifyObservers();
			return 0; 
		}
		else
		{
			
			return hit; 
		}
		
	}
	
	/**
	 * Method that displays the main battlefield
	 * in which the players do battle with their three
	 * pokemon. Open file UpdatedMockGUI.png and see 
	 * Battle Screen diagram for specific layout structure
	 */
	public void displayBattlefield()
	{
		
		Pokemon p1 = playerone.getCurrentPokemon(); 
		Pokemon p2 = playertwo.getCurrentPokemon(); 
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout(30,30));
		
		JPanel mainPanel= new JPanel(new GridLayout(2,2));
		
		JPanel outline1 = new JPanel(new BorderLayout()); 
		outline1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		JPanel outline2 = new JPanel(new BorderLayout());
		outline2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		JPanel outline3 = new JPanel(new BorderLayout());
		outline3.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		JPanel outline4 = new JPanel(new BorderLayout());
		outline4.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		
		
		JPanel bottomLeft = new JPanel(new BorderLayout());
		bottomLeft.setSize(350, 300);
		JLabel p1Box = create(p1, bottomLeft.getWidth(), bottomLeft.getHeight()); 
		bottomLeft.add("North", p1Box);
		outline1.add(bottomLeft); 
		
		JPanel topLeft = new JPanel(new GridLayout(0,2)); 
		JButton player1Attack = new JButton("Player1 Attack");
		player1Attack.setFont(new Font("Serif", Font.BOLD, 30));
		player1Attack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(currentState instanceof PlayerOneTurn && p1.getCurrentHitPoints()>0)
				{
					getContentPane().removeAll();
					selectAttackDisplay(1); 
				}
				else
				{
					; 
				}
				
			}});
		JButton player1Switch = new JButton("Player1 Switch");
		player1Switch.setFont(new Font("Serif", Font.BOLD, 30));
		player1Switch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(currentState instanceof PlayerOneTurn)
				{
					getContentPane().removeAll();
					changePokemonDisplay(); 
				}
				else
				{
					; 
				}
			
				
			}});
		JPanel player1Bar = new JPanel(new GridLayout(0, 1));
		JPanel player1type = new JPanel(new GridLayout(0,1)); 
		JLabel progress = new JLabel("HP:"+ calculateHitPoints(p1.getCurrentHitPoints())+"/"+ p1.getMaxHitPoints(), SwingConstants.CENTER);
		progress.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel type = new JLabel("TYPE:" + p1.toString(), SwingConstants.CENTER);
		type.setFont(new Font("Serif", Font.BOLD, 30));
		player1Bar.add(progress); 
		player1type.add(type); 
		topLeft.add( player1Attack); 
		topLeft.add( player1Switch); 
		topLeft.add( player1Bar);
		topLeft.add(player1type);
		outline2.add(topLeft); 
		
		
		
		
		JPanel topRight = new JPanel(new BorderLayout());
		topRight.setSize(350, 300);
		JLabel p2Box = create(p2, topRight.getWidth(), topRight.getHeight()); 
		topRight.add("North", p2Box); 
		outline3.add(topRight); 
		
		JPanel bottomRight = new JPanel(new GridLayout(0, 2));
		JButton player2Attack = new JButton("Player2 Attack");
		player2Attack.setFont(new Font("Serif", Font.BOLD, 30));
		player2Attack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(currentState instanceof PlayerTwoTurn && p2.getCurrentHitPoints()>0)
				{
					getContentPane().removeAll();
					selectAttackDisplay(1); 
				}
				else
				{
					; 
				}
				
			}});
		JButton player2Switch = new JButton("Player2 Switch");
		player2Switch.setFont(new Font("Serif", Font.BOLD, 30));
		player2Switch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(currentState instanceof PlayerTwoTurn)
				{
					getContentPane().removeAll();
					changePokemonDisplay(); 
				}
				else
				{
					; 
				}
				
				
			}});
		JPanel player2Bar = new JPanel(new GridLayout(0,1));
		JPanel player2type = new JPanel(new GridLayout(0,1)); 
		JLabel progress2 = new JLabel("HP:"+ calculateHitPoints(p2.getCurrentHitPoints())+"/"+ p2.getMaxHitPoints(), SwingConstants.CENTER);
		progress2.setFont(new Font("Serif", Font.BOLD, 30));
		JLabel type2 = new JLabel("TYPE:" + p2.toString(), SwingConstants.CENTER); 
		type2.setFont(new Font("Serif", Font.BOLD, 30));
		player2Bar.add(progress2); 
		player2type.add(type2); 
		bottomRight.add( player2Attack); 
		bottomRight.add( player2Switch); 
		bottomRight.add( player2Bar);
		bottomRight.add(player2type);
		outline4.add(bottomRight); 
		
		mainPanel.add(outline2); 
		mainPanel.add(outline3);
		mainPanel.add(outline1);
		mainPanel.add(outline4);
		
		cp.add(mainPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle ("Battle");
		pack(); 
		setSize(1024, 800); 
		setVisible(true); 
		
		
	}
	/**
	 * Method that displays the attack select screen
	 * in which the players choose what type of
	 * attacks they want their pokemon to be
	 * loaded with. Open file UpdatedMockGUI.png and see 
	 * attack select diagram for specific layout structure
	 * @param field an int that represents a flag and 
	 * whether a player is allowed to actually do damage to 
	 * another player; if the field flag is zero that means
	 * that the player's pokemon has died and must change its pokemon
	 * and select another attack. If the field flag is one, that means that
	 * the player is allowed to attack the other player
	 */
	public void selectAttackDisplay(int field)
	{
		int enable=field; 
		Pokemon p1 = playerone.getCurrentPokemon(); 
		Pokemon p2 = playertwo.getCurrentPokemon(); 
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel pic = new JPanel(new BorderLayout());
		pic.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		pic.setSize(550,500);
		int width, height; 
		width=pic.getWidth(); 
		height=pic.getHeight(); 
		
	
		if (currentState instanceof PlayerOneTurn)
		{
			JLabel top = create(p1, width, height); 
			pic.add("North", top); 
			JPanel attacks = new JPanel(new GridLayout(4, 1));
			JButton attack1 = new JButton(p1.getAttack1());
			attack1.setFont(new Font("Serif", Font.BOLD, 30));
			attack1.setPreferredSize(getPreferredSize()); 
			JButton attack2 = new JButton(p1.getAttack2());
			attack2.setFont(new Font("Serif", Font.BOLD, 30)); 
			attack2.setPreferredSize(getPreferredSize());
			attack1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					currentState.select(p1.getAttack1());
					
				}});
			
			attack2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					currentState.select(p1.getAttack2());
					
				}
				
			});
			JButton done = new JButton("DONE");
			done.setFont(new Font ("Serif", Font.BOLD, 30));
			done.setPreferredSize(getPreferredSize());
			done.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					
					if(enable==0)
					{
						updateState(player2Turn); 
						getContentPane().removeAll();
						displayBattlefield(); 
					}
					else if (enable==1 && playerone.getAttack()!=null)
					{
						currentState.attack();
						updateState(player2Turn); 
						getContentPane().removeAll();
						displayBattlefield(); 
					}
					else
					{
						; 
					}
					
				}});
			JButton cancel = new JButton("CANCEL");
			cancel.setFont(new Font ("Serif", Font.BOLD, 30));
			cancel.setPreferredSize(getPreferredSize());
			cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					getContentPane().removeAll();
					displayBattlefield();
					
				}
				
			});
			attacks.add(attack1); 
			attacks.add(attack2);
			attacks.add(done);
			attacks.add(cancel);
			cp.add(pic, BorderLayout.NORTH);
			cp.add(attacks, BorderLayout.CENTER); 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle ("PlayerOne Select Pokemon Attack");
			pack(); 
			setSize(1024, 800); 
			setVisible(true); 
		}
		else if(currentState instanceof PlayerTwoTurn) 
		{
			JLabel top = create(p2, width, height); 
			pic.add("North", top); 
			JPanel attacks = new JPanel(new GridLayout(4, 1));
			JButton attack1 = new JButton(p2.getAttack1());
			attack1.setFont(new Font("Serif", Font.BOLD, 30));
			attack1.setPreferredSize(getPreferredSize()); 
			JButton attack2 = new JButton(p2.getAttack2());
			attack2.setFont(new Font("Serif", Font.BOLD, 30)); 
			attack2.setPreferredSize(getPreferredSize());
			attack1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					currentState.select(p2.getAttack1());
					
				}});
			
			attack2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					currentState.select(p2.getAttack2());
			
				}
				
			});
			JButton done = new JButton("DONE");
			done.setFont(new Font ("Serif", Font.BOLD, 30));
			done.setPreferredSize(getPreferredSize());
			done.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					if(enable==0)
					{
						updateState(player1Turn); 
						getContentPane().removeAll();
						displayBattlefield(); 
					}
					else if (enable==1 && playertwo.getAttack()!=null)
					{
						currentState.attack();
						updateState(player1Turn); 
						getContentPane().removeAll();
						displayBattlefield(); 
					}
					else
					{
						; 
					}
					
				}});
			JButton cancel = new JButton("CANCEL");
			cancel.setFont(new Font ("Serif", Font.BOLD, 30));
			cancel.setPreferredSize(getPreferredSize());
			cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					getContentPane().removeAll();
					displayBattlefield();
					
				}
				
			});
			
			attacks.add(attack1); 
			attacks.add(attack2);
			attacks.add(done);
			attacks.add(cancel);
			cp.add(pic, BorderLayout.NORTH);
			cp.add(attacks, BorderLayout.CENTER); 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle ("PlayerTwo Select Pokemon Attack");
			pack(); 
			setSize(1024, 800); 
			setVisible(true); 
		}
		
	}
	
	
	
	/**
	 * Method that displays the changing pokemon portion of the game.
	 * Open file UpdatedMockGUI.png and see 
	 * changing pokemon diagram for specific layout structure
	 */
	public void changePokemonDisplay()
	{
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel center2 = new JPanel(new GridLayout(0,1));

		int width, height; 
		
		if(currentState instanceof PlayerOneTurn  && player1count==0)
		{
			player1count++; 
			Pokemon[] p1selction = playerone.getPokemon(); 
			JPanel pokemon1 = new JPanel(new BorderLayout());
			pokemon1.setSize(250, 200);
			pokemon1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon1.getWidth(); 
			height = pokemon1.getHeight(); 
			JLabel first = create(p1selction[0], width, height);
			JLabel name1 = new JLabel(p1selction[0].toString(), SwingConstants.CENTER);
			
			JPopupMenu  p1stats = new JPopupMenu("Stats"); 
			JMenuItem  stat1 = new JMenuItem("AttackHit:"+p1selction[0].getAttack()); 
			JMenuItem  stat2 = new JMenuItem("Defense:"+p1selction[0].getDefense()); 
			JMenuItem  stat3 = new JMenuItem("MaxHit:"+p1selction[0].getMaxHitPoints());
			p1stats.add(stat1); p1stats.add(stat2); p1stats.add(stat3); 
			pokemon1.add("North", name1); 
			pokemon1.add(first);
			name1.add(p1stats);
			pokemon1.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {	}
				@Override
				public void mousePressed(MouseEvent e) 
				{	
					currentState.change(0); 
					playertwo.setOpponent(p1selction[0]);
					updateState(player2Turn); 
					getContentPane().removeAll();
					changePokemonDisplay();   
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					p1stats.show(name1, e.getX(), e.getY());
					
				}
				@Override
				public void mouseExited(MouseEvent e) {
					p1stats.setVisible(false);
				}});
			
			JPanel pokemon2 = new JPanel(new BorderLayout());
			pokemon2.setSize(250, 200);
			pokemon2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon2.getWidth(); 
			height = pokemon2.getHeight(); 
			JLabel second = create(p1selction[1], width, height);
			JLabel name2 = new JLabel(p1selction[1].toString(), SwingConstants.CENTER);
			JPopupMenu  p2stats = new JPopupMenu("Stats"); 
			JMenuItem  p2stat = new JMenuItem("AttackHit:"+p1selction[1].getAttack()); 
			JMenuItem  p2stat2 = new JMenuItem("Defense:"+p1selction[1].getDefense()); 
			JMenuItem  p2stat3 = new JMenuItem("MaxHit:"+p1selction[1].getMaxHitPoints());
			p2stats.add(p2stat); p2stats.add(p2stat2); p2stats.add(p2stat3); 
			pokemon2.add("North", name2); 
			pokemon2.add(second);
			name2.add(p2stats); 
			pokemon2.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) 
				{	
						currentState.change(1);
						playertwo.setOpponent(p1selction[1]);
						updateState(player2Turn); 
						getContentPane().removeAll();
						changePokemonDisplay();  
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					p2stats.show(name2, e.getX(), e.getY());
				}
				@Override
				public void mouseExited(MouseEvent e) {
					p2stats.setVisible(false);
				}});
			
			
			JPanel pokemon3 = new JPanel(new BorderLayout());
			pokemon3.setSize(250, 200 );
			pokemon3.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon3.getWidth(); 
			height = pokemon3.getHeight(); 
			JLabel third = create(p1selction[2], width, height);
			JLabel name3 = new JLabel(p1selction[2].toString(), SwingConstants.CENTER);
			JPopupMenu  p3stats = new JPopupMenu("Stats"); 
			JMenuItem  p3stat = new JMenuItem("AttackHit:"+p1selction[2].getAttack()); 
			JMenuItem  p3stat2 = new JMenuItem("Defense:"+p1selction[2].getDefense()); 
			JMenuItem  p3stat3 = new JMenuItem("MaxHit:"+p1selction[2].getMaxHitPoints());
			p3stats.add(p3stat); p3stats.add(p3stat2); p3stats.add(p3stat3); 
			pokemon3.add("North", name3); 
			pokemon3.add(third);
			name3.add(p3stats); 
			pokemon3.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e)
				{
					 
					currentState.change(2);
					playertwo.setOpponent(p1selction[2]);
					updateState(player2Turn); 
					getContentPane().removeAll();
					changePokemonDisplay();  
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					p3stats.show(name3, e.getX(), e.getY());
				}
				@Override
				public void mouseExited(MouseEvent e) {
					p3stats.setVisible(false);
				}});
			
			
			center2.add(pokemon1);
			center2.add(pokemon2);
			center2.add(pokemon3); 
			
			cp.add(center2, BorderLayout.NORTH); 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle ("PlayerOne Select Pokemon for Battle");
			pack(); 
			setSize(1024, 800); 
			setVisible(true); 
		}
		else if (currentState instanceof PlayerTwoTurn && player2count==0)
		{
			player2count++; 
			Pokemon[] p2selction= playertwo.getPokemon(); 
			JPanel pokemon1 = new JPanel(new BorderLayout());
			pokemon1.setSize(250, 200);
			pokemon1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon1.getWidth(); 
			height = pokemon1.getHeight(); 
			JLabel first = create(p2selction[0], width, height);
			JLabel name1 = new JLabel(p2selction[0].toString(), SwingConstants.CENTER);
			JPopupMenu  p1stats = new JPopupMenu("Stats"); 
			JMenuItem  p1stat = new JMenuItem("AttackHit:"+p2selction[0].getAttack()); 
			JMenuItem  p1stat2 = new JMenuItem("Defense:"+p2selction[0].getDefense()); 
			JMenuItem  p1stat3 = new JMenuItem("MaxHit:"+p2selction[0].getMaxHitPoints());
			p1stats.add(p1stat); p1stats.add(p1stat2); p1stats.add(p1stat3); 
			pokemon1.add("North", name1); 
			pokemon1.add(first);
			name1.add(p1stats); 
			pokemon1.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {	}
				@Override
				public void mousePressed(MouseEvent e) 
				{	
					currentState.change(0); 
					playerone.setOpponent(p2selction[0]);
					updateState(player1Turn); 
					getContentPane().removeAll();
					displayBattlefield(); 
					
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					p1stats.show(name1, e.getX(), e.getY());
				}
				@Override
				public void mouseExited(MouseEvent e) {	
					p1stats.setVisible(false);
				}});
			
			JPanel pokemon2 = new JPanel(new BorderLayout());
			pokemon2.setSize(250, 200); 
			pokemon2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon2.getWidth(); 
			height = pokemon2.getHeight(); 
			JLabel second = create(p2selction[1], width, height);
			JLabel name2 = new JLabel(p2selction[1].toString(), SwingConstants.CENTER); 
			JPopupMenu  p2stats = new JPopupMenu("Stats"); 
			JMenuItem  p2stat = new JMenuItem("AttackHit:"+p2selction[1].getAttack()); 
			JMenuItem  p2stat2 = new JMenuItem("Defense:"+p2selction[1].getDefense()); 
			JMenuItem  p2stat3 = new JMenuItem("MaxHit:"+p2selction[1].getMaxHitPoints());
			p2stats.add(p2stat); p2stats.add(p2stat2); p2stats.add(p2stat3); 
			pokemon2.add("North", name2); 
			pokemon2.add(second);
			name2.add(p2stats); 
			pokemon2.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) 
				{	
					currentState.change(1);
					playerone.setOpponent(p2selction[1]);
					updateState(player1Turn); 
					getContentPane().removeAll();
					displayBattlefield(); 
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {	
					p2stats.show(name2, e.getX(), e.getY());
				}
				@Override
				public void mouseExited(MouseEvent e) {	
					p2stats.setVisible(false);
				}});
			
			
			JPanel pokemon3 = new JPanel(new BorderLayout());
			pokemon3.setSize(250, 200 );
			pokemon3.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon3.getWidth(); 
			height = pokemon3.getHeight(); 
			JLabel third = create(p2selction[2], width, height);
			JLabel name3 = new JLabel(p2selction[2].toString(), SwingConstants.CENTER);
			JPopupMenu  p3stats = new JPopupMenu("Stats"); 
			JMenuItem  p3stat = new JMenuItem("AttackHit:"+p2selction[2].getAttack()); 
			JMenuItem  p3stat2 = new JMenuItem("Defense:"+p2selction[2].getDefense()); 
			JMenuItem  p3stat3 = new JMenuItem("MaxHit:"+p2selction[2].getMaxHitPoints());
			p3stats.add(p3stat); p3stats.add(p3stat2); p3stats.add(p3stat3); 
			pokemon3.add("North", name3); 
			pokemon3.add(third);
			name3.add(p3stats); 
			pokemon3.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) 
				{
					currentState.change(2); 
					playerone.setOpponent(p2selction[2]);
					updateState(player1Turn); 
					getContentPane().removeAll();
					displayBattlefield(); 
					
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					p3stats.show(name3, e.getX(), e.getY());
				}
				@Override
				public void mouseExited(MouseEvent e) {	
					p3stats.setVisible(false);
				}});
			
			
			
			center2.add(pokemon1);
			center2.add(pokemon2);
			center2.add(pokemon3); 
			
			cp.add(center2, BorderLayout.NORTH); 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle ("PlayerTwo Select Pokemon for Battle");
			pack(); 
			setSize(1024, 800); 
			setVisible(true); 
		}
		else if (currentState instanceof PlayerOneTurn  && player1count==1)
		{
			Pokemon[] p1selction = playerone.getPokemon(); 
			JPanel pokemon1 = new JPanel(new BorderLayout());
			pokemon1.setSize(250, 200);
			pokemon1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon1.getWidth(); 
			height = pokemon1.getHeight(); 
			JLabel first = create(p1selction[0], width, height);
			JLabel name1 = new JLabel(p1selction[0].toString(), SwingConstants.CENTER);
			JPopupMenu  p1stats = new JPopupMenu("Stats"); 
			JMenuItem  stat1 = new JMenuItem("AttackHit:"+p1selction[0].getAttack()); 
			JMenuItem  stat2 = new JMenuItem("Health:"+p1selction[0].getCurrentHitPoints()); 
			p1stats.add(stat1); p1stats.add(stat2);
			pokemon1.add("North", name1); 
			pokemon1.add(first);
			name1.add(p1stats); 
			pokemon1.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {	}
				@Override
				public void mousePressed(MouseEvent e) 
				{	
					if(p1selction[0].getCurrentHitPoints()!=0 && p1selction[0].getCurrentHitPoints()>0)
					{
						currentState.change(0); 
						playertwo.setOpponent(p1selction[0]);
						updateState(player2Turn);
						getContentPane().removeAll();
						displayBattlefield();
					}
					else
					{
						e.consume();
					}
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					if(p1selction[0].getCurrentHitPoints()!=0 && p1selction[0].getCurrentHitPoints()>0)
					{
						p1stats.show(name1, e.getX(), e.getY());
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {	
					p1stats.setVisible(false);
				}});
			
			JPanel pokemon2 = new JPanel(new BorderLayout());
			pokemon2.setSize(250, 200);
			pokemon2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon2.getWidth(); 
			height = pokemon2.getHeight(); 
			JLabel second = create(p1selction[1], width, height);
			JLabel name2 = new JLabel(p1selction[1].toString(), SwingConstants.CENTER);
			JPopupMenu  p2stats = new JPopupMenu("Stats"); 
			JMenuItem  p2stat = new JMenuItem("AttackHit:"+p1selction[1].getAttack()); 
			JMenuItem  p2stat2 = new JMenuItem("Health:"+p1selction[1].getCurrentHitPoints()); 
			p2stats.add(p2stat); p2stats.add(p2stat2); 
			pokemon2.add("North", name2); 
			pokemon2.add(second);
			name2.add(p2stats); 
			pokemon2.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) 
				{	
					if(p1selction[1].getCurrentHitPoints()!=0 && p1selction[1].getCurrentHitPoints()>0)
					{
						currentState.change(1);
						playertwo.setOpponent(p1selction[1]);
						updateState(player2Turn);
						getContentPane().removeAll();
						displayBattlefield();
					}
					else
					{
						e.consume();
					}
						  
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {	
					if(p1selction[1].getCurrentHitPoints()!=0 && p1selction[1].getCurrentHitPoints()>0)
					{
						p2stats.show(name2, e.getX(), e.getY());
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {	
					p2stats.setVisible(false);
				}});
			
			
			JPanel pokemon3 = new JPanel(new BorderLayout());
			pokemon3.setSize(250, 200 );
			pokemon3.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon3.getWidth(); 
			height = pokemon3.getHeight(); 
			JLabel third = create(p1selction[2], width, height);
			JLabel name3 = new JLabel(p1selction[2].toString(), SwingConstants.CENTER);
			JPopupMenu  p3stats = new JPopupMenu("Stats"); 
			JMenuItem  p3stat = new JMenuItem("AttackHit:"+p1selction[2].getAttack()); 
			JMenuItem  p3stat2 = new JMenuItem("Health:"+p1selction[2].getCurrentHitPoints()); 
			p3stats.add(p3stat); p3stats.add(p3stat2); 
			pokemon3.add("North", name3); 
			pokemon3.add(third);
			name3.add(p3stats); 
			pokemon3.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {}
				
				@Override
				public void mousePressed(MouseEvent e)
				{
					if(p1selction[2].getCurrentHitPoints()!=0 && p1selction[2].getCurrentHitPoints()>0)
					{
						currentState.change(2);
						playertwo.setOpponent(p1selction[2]);
						updateState(player2Turn);
						getContentPane().removeAll();
						displayBattlefield();
					}
					{
						e.consume();
					}
					
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {	
					if(p1selction[2].getCurrentHitPoints()!=0 && p1selction[2].getCurrentHitPoints()>0)
					{
						p3stats.show(name3, e.getX(), e.getY());
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {	
					p3stats.setVisible(false);
				}});
			
			JPanel redo = new JPanel(new BorderLayout());
			JLabel cancel = new JLabel("CANCEL", SwingConstants.CENTER);
			cancel.setFont(new Font("Serif", Font.BOLD, 30));
			redo.add("North", cancel); 
			redo.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) 
				{
					getContentPane().removeAll();
					displayBattlefield(); 
				
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {	}
				@Override
				public void mouseExited(MouseEvent e) {	
				}});
			center2.add(pokemon1);
			center2.add(pokemon2);
			center2.add(pokemon3); 
			center2.add(redo);
			cp.add(center2, BorderLayout.NORTH); 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle ("PlayerOne Select Pokemon for Battle");
			pack(); 
			setSize(1024, 800); 
			setVisible(true); 
		}
		else if (currentState instanceof PlayerTwoTurn && player2count==1)
		{
			Pokemon[] p2selction= playertwo.getPokemon(); 
			JPanel pokemon1 = new JPanel(new BorderLayout());
			pokemon1.setSize(250, 200);
			pokemon1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon1.getWidth(); 
			height = pokemon1.getHeight(); 
			JLabel first = create(p2selction[0], width, height);
			JLabel name1 = new JLabel(p2selction[0].toString(), SwingConstants.CENTER);
			JPopupMenu  p1stats = new JPopupMenu("Stats"); 
			JMenuItem  p1stat = new JMenuItem("AttackHit:"+p2selction[0].getAttack()); 
			JMenuItem  p1stat2 = new JMenuItem("Health:"+p2selction[0].getCurrentHitPoints()); 
			p1stats.add(p1stat); p1stats.add(p1stat2);  
			pokemon1.add("North", name1); 
			pokemon1.add(first); 
			name1.add(p1stats); 
			pokemon1.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {	}
				@Override
				public void mousePressed(MouseEvent e) 
				{	
					if(p2selction[0].getCurrentHitPoints()!=0 && p2selction[0].getCurrentHitPoints()>0)
					{
						currentState.change(0); 
						playerone.setOpponent(p2selction[0]);
						updateState(player1Turn); 
						getContentPane().removeAll();
						displayBattlefield();
					}
					else
					{
						e.consume();
					}
					
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					if(p2selction[0].getCurrentHitPoints()!=0 && p2selction[0].getCurrentHitPoints()>0)
					{
						p1stats.show(name1, e.getX(), e.getY());
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {	
					 p1stats.setVisible(false);
				}});
			
			JPanel pokemon2 = new JPanel(new BorderLayout());
			pokemon2.setSize(250, 200); 
			pokemon2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon2.getWidth(); 
			height = pokemon2.getHeight(); 
			JLabel second = create(p2selction[1], width, height);
			JLabel name2 = new JLabel(p2selction[1].toString(), SwingConstants.CENTER);
			JPopupMenu  p2stats = new JPopupMenu("Stats"); 
			JMenuItem  p2stat = new JMenuItem("AttackHit:"+p2selction[1].getAttack()); 
			JMenuItem  p2stat2 = new JMenuItem("Health:"+p2selction[1].getCurrentHitPoints()); 
			p2stats.add(p2stat); p2stats.add(p2stat2); 
			pokemon2.add("North", name2); 
			pokemon2.add(second);
			name2.add(p2stats); 
			pokemon2.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) 
				{	
					if(p2selction[1].getCurrentHitPoints()!=0 && p2selction[1].getCurrentHitPoints()>0)
					{
						currentState.change(1);
						playerone.setOpponent(p2selction[1]);
						updateState(player1Turn);
						getContentPane().removeAll();
						displayBattlefield();
					}
					else
					{
						e.consume();
					}
					
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					if(p2selction[1].getCurrentHitPoints()!=0 && p2selction[1].getCurrentHitPoints()>0)
					{
						p2stats.show(name2, e.getX(), e.getY());
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {	
					p2stats.setVisible(false);
				}});
			
			
			JPanel pokemon3 = new JPanel(new BorderLayout());
			pokemon3.setSize(250, 200 );
			pokemon3.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			width = pokemon3.getWidth(); 
			height = pokemon3.getHeight(); 
			JLabel third = create(p2selction[2], width, height);
			JLabel name3 = new JLabel(p2selction[2].toString(), SwingConstants.CENTER); 
			JPopupMenu  p3stats = new JPopupMenu("Stats"); 
			JMenuItem  p3stat = new JMenuItem("AttackHit:"+p2selction[2].getAttack()); 
			JMenuItem  p3stat2 = new JMenuItem("Health:"+p2selction[2].getCurrentHitPoints()); 
			p3stats.add(p3stat); p3stats.add(p3stat2); 
			pokemon3.add("North", name3); 
			pokemon3.add(third);
			name3.add(p3stats); 
			pokemon3.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) 
				{
					if(p2selction[2].getCurrentHitPoints()!=0 && p2selction[2].getCurrentHitPoints()>0)
					{
						currentState.change(2); 
						playerone.setOpponent(p2selction[2]);
						updateState(player1Turn);
						getContentPane().removeAll();
						displayBattlefield();
					}
					else
					{
						e.consume();
					}
					
					
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {
					if(p2selction[2].getCurrentHitPoints()!=0 && p2selction[2].getCurrentHitPoints()>0)
					{
						p3stats.show(name3, e.getX(), e.getY());
					}
				}
				@Override
				public void mouseExited(MouseEvent e) {	
					p3stats.setVisible(false);
				}});
			
			JPanel redo = new JPanel(new BorderLayout());
			JLabel cancel = new JLabel("CANCEL", SwingConstants.CENTER);
			cancel.setFont(new Font("Serif", Font.BOLD, 30));
			redo.add("North", cancel); 
			redo.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) 
				{
					getContentPane().removeAll();
					displayBattlefield(); 
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mouseEntered(MouseEvent e) {	}
				@Override
				public void mouseExited(MouseEvent e) {	
				}});
			
			center2.add(pokemon1);
			center2.add(pokemon2);
			center2.add(pokemon3); 
			center2.add(redo);
			cp.add(center2, BorderLayout.NORTH); 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle ("PlayerTwo Select Pokemon for Battle");
			pack(); 
			setSize(1024, 800); 
			setVisible(true); 
		}
		
	}
	
	/**
	 * Method that rescales a given png image
	 * of a pokemon to fit the game panels 
	 * and returns a Jlabel component that can
	 * be displayed on the screen
	 * @param p is a reference to a specific pokemon object
	 * @param w is an int that represents the width of a given JPanel
	 * @param h is an int that represents the height of a given JPanel
	 * @return a JLabel object that displays a specific pokemon
	 */
	public JLabel create(Pokemon p, int w, int h)
	{
		if(p instanceof Bellsprout)
		{
			return new JLabel(new ImageIcon(bellsproutImg.getScaledInstance(w, h, Image.SCALE_SMOOTH))); 
		}
		else if(p instanceof Blastoise)
		{
			return new JLabel(new ImageIcon(blastoiseImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if(p instanceof Bulbasaur)
		{
			return new JLabel(new ImageIcon(bulbasaurImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if (p instanceof Charizard)
		{
			return new JLabel(new ImageIcon(charizardImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if (p instanceof Charmander)
		{
			return new JLabel(new ImageIcon(charmanderImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if (p instanceof Charmeleon)
		{
			return new JLabel(new ImageIcon(charmeleonImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if (p instanceof Ivysaur)
		{
			return new JLabel(new ImageIcon(ivysaurImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if (p instanceof Poliwag)
		{
			return new JLabel(new ImageIcon(poliwagImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if (p instanceof Squirtle)
		{
			return new JLabel(new ImageIcon(squirtleImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if (p instanceof Venusaur)
		{
			return new JLabel(new ImageIcon(venusaurImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if (p instanceof Vulpix)
		{
			return new JLabel(new ImageIcon(vulpixImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else if (p instanceof Wartortle)
		{
			return new JLabel(new ImageIcon(wartortleImg.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		}
		else
		{
			return null;  
		}
	}
	
	/**
	 * Method that displays the pokemon selection portion of the game.
	 * Open file UpdatedMockGUI.png and see 
	 * pokemon selection  diagram for specific layout structure
	 * @throws IOException an exception that is thrown if an error 
	 * occurs during the reading of the png file
	 *  
	 */
	public void pokemonSelection() throws IOException 
	{
		Container cp = getContentPane(); 
		cp.setLayout(new BorderLayout());
		
		center = new JPanel(new GridLayout(4,3)); 
		left = new JPanel(new BorderLayout()); 
		leftPokemon = new JPanel(new GridLayout(4,1));
		right = new JPanel(new BorderLayout());
		rightPokemon = new JPanel(new GridLayout(4, 1));
		bottom = new JPanel(new GridLayout(1, 1)); 
		
		selected1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		selected2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		selected3.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		selected4.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		selected5.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		selected6.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		
		//grass pokemon
		bulbasaurImg =  ImageIO.read(new File("Bulbasaur.png"));
		ivysaurImg =  ImageIO.read(new File("Ivysaur.png"));
		venusaurImg =  ImageIO.read(new File("Venusaur.png"));
		bellsproutImg =  ImageIO.read(new File("Bellsprout.png"));
		
		bellsproutPanel = new JPanel(new BorderLayout());
		JLabel bellsproutTitle = new JLabel("Bellsprout", SwingConstants.CENTER);
		bellsproutPanel.add("North", bellsproutTitle);
		bellsproutPanel.setSize(250, 200);
		b = new JLabel(new ImageIcon(resize(bellsproutImg,bellsproutPanel.getWidth(),bellsproutPanel.getHeight()))); 
		bellsproutPanel.add(b);
		bellsproutPanel.addMouseListener(this);
		center.add(bellsproutPanel);
		
		bulbasaurPanel = new JPanel(new BorderLayout());
		JLabel bulbasaurTitle = new JLabel("Bulbasaur", SwingConstants.CENTER);
		bulbasaurPanel.add("North", bulbasaurTitle);
		bulbasaurPanel.setSize(250, 200);
		a = new JLabel(new ImageIcon(resize(bulbasaurImg, bulbasaurPanel.getWidth(), bulbasaurPanel.getHeight()))); 
		bulbasaurPanel.add(a);
		bulbasaurPanel.addMouseListener(this);
		center.add(bulbasaurPanel); 
		
		ivysaurPanel = new JPanel(new BorderLayout());
		JLabel ivysaurTitle = new JLabel("Ivysaur", SwingConstants.CENTER);
		ivysaurPanel.add("North", ivysaurTitle);
		ivysaurPanel.setSize(250, 200);
		e = new JLabel(new ImageIcon(resize(ivysaurImg,ivysaurPanel.getWidth(), ivysaurPanel.getHeight()))); 
		ivysaurPanel.add(e);
		ivysaurPanel.addMouseListener(this);
		center.add(ivysaurPanel);
		
		venusaurPanel = new JPanel(new BorderLayout());
		JLabel venusaurTitle = new JLabel("Venusaur", SwingConstants.CENTER);
		venusaurPanel.add("North", venusaurTitle);
		venusaurPanel.setSize(250, 200);
		h = new JLabel(new ImageIcon(resize(venusaurImg, venusaurPanel.getWidth(), venusaurPanel.getHeight()))); 
		venusaurPanel.add(h);
		venusaurPanel.addMouseListener(this);
		center.add(venusaurPanel);
		
		//fire pokemon
		charmanderImg = ImageIO.read(new File("Charmander.png"));
		charizardImg = ImageIO.read(new File("Charizard.png"));
		charmeleonImg = ImageIO.read(new File("Charmeleon.png")); 
		vulpixImg = ImageIO.read(new File("Vulpix.png"));
		
		vulpixPanel = new JPanel(new BorderLayout());
		JLabel vulpixTitle = new JLabel("Vulpix", SwingConstants.CENTER);
		vulpixPanel.add("North", vulpixTitle);
		vulpixPanel.setSize(250, 200);
		i = new JLabel(new ImageIcon(resize(vulpixImg,vulpixPanel.getWidth(),vulpixPanel.getHeight()))); 
		vulpixPanel.add(i);
		vulpixPanel.addMouseListener(this);
		center.add(vulpixPanel);
		
		charmeleonPanel = new JPanel(new BorderLayout());
		JLabel charmeleonTitle = new JLabel("Charmeleon", SwingConstants.CENTER);
		charmeleonPanel.add("North", charmeleonTitle);
		charmeleonPanel.setSize(250, 200);
		l = new JLabel(new ImageIcon(resize(charmeleonImg, charmeleonPanel.getWidth(), charmeleonPanel.getHeight()))); 
		charmeleonPanel.add(l);
		charmeleonPanel.addMouseListener(this);
		center.add(charmeleonPanel);
		
		charmanderPanel = new JPanel(new BorderLayout());
		JLabel charmanderTitle = new JLabel("Charmander", SwingConstants.CENTER);
		charmanderPanel.add("North", charmanderTitle);
		charmanderPanel.setSize(250, 200);
		d = new JLabel(new ImageIcon(resize(charmanderImg, charmanderPanel.getWidth(), charmanderPanel.getHeight()))); 
		charmanderPanel.add(d);
		charmanderPanel.addMouseListener(this);
		center.add(charmanderPanel);
		
		charizardPanel = new JPanel(new BorderLayout());
		JLabel charizardTitle = new JLabel("Charizard", SwingConstants.CENTER);
		charizardPanel.add("North", charizardTitle);
		charizardPanel.setSize(250, 200);
		c = new JLabel(new ImageIcon(resize(charizardImg, charizardPanel.getWidth(), charizardPanel.getHeight()))); 
		charizardPanel.add(c);
		charizardPanel.addMouseListener(this);
		center.add(charizardPanel);
		
		//water pokemon
		poliwagImg =  ImageIO.read(new File("Poliwag.png"));
		squirtleImg =  ImageIO.read(new File("Squirtle.png"));
		wartortleImg =  ImageIO.read(new File("Wartortle.png"));
		blastoiseImg = ImageIO.read(new File("Blastoise.png")); 
		
		poliwagPanel = new JPanel(new BorderLayout());
		JLabel poliwagTitle = new JLabel("Poliwag", SwingConstants.CENTER);
		poliwagPanel.add("North", poliwagTitle);
		poliwagPanel.setSize(250,200);
		f = new JLabel (new ImageIcon(resize(poliwagImg,poliwagPanel.getWidth(), poliwagPanel.getHeight()))); 
		poliwagPanel.add(f);
		poliwagPanel.addMouseListener(this);
		center.add(poliwagPanel);
				
		squirtlePanel = new JPanel(new BorderLayout());
		JLabel squirtleTitle = new JLabel("Squirtle", SwingConstants.CENTER);
		squirtlePanel.add("North", squirtleTitle);
		squirtlePanel.setSize(250, 200);
		g = new JLabel(new ImageIcon(resize(squirtleImg, squirtlePanel.getWidth(),squirtlePanel.getHeight()))); 
		squirtlePanel.add(g);
		squirtlePanel.addMouseListener(this);
		center.add(squirtlePanel);
		
		wartortlePanel = new JPanel(new BorderLayout());
		JLabel wartortleTitle = new JLabel("Wartortle", SwingConstants.CENTER);
		wartortlePanel.add("North", wartortleTitle);
		wartortlePanel.setSize(250, 200);
		j= new JLabel(new ImageIcon(resize(wartortleImg,wartortlePanel.getWidth(), wartortlePanel.getHeight()))); 
		wartortlePanel.add(j);
		wartortlePanel.addMouseListener(this);
		center.add(wartortlePanel);
		
		blastoisePanel = new JPanel(new BorderLayout());
		JLabel blastoiseTitle = new JLabel("Blastoise", SwingConstants.CENTER);
		blastoisePanel.add("North", blastoiseTitle);
		blastoisePanel.setSize(250, 200);
		k= new JLabel(new ImageIcon(resize(blastoiseImg,blastoisePanel.getWidth(), blastoisePanel.getHeight()))); 
		blastoisePanel.add(k);
		blastoisePanel.addMouseListener(this);
		center.add(blastoisePanel);
		
	
		
		player1 = new JLabel("          Player 1          "); 
		player1.setFont(new Font("Serif", Font.BOLD, 30));
		 
		left.add("North",player1);
		leftPokemon.add(selected1);
		leftPokemon.add(selected2);
		leftPokemon.add(selected3);
		left.add("Center", leftPokemon);
		
		player2 = new JLabel("          Player 2          "); 
		player2.setFont(new Font("Serif", Font.BOLD, 30));
		right.add("North", player2); 
		rightPokemon.add(selected4); 
		rightPokemon.add(selected5); 
		rightPokemon.add(selected6); 
		right.add("Center", rightPokemon);
		
		done = new JButton("DONE");
		done.setFont(new Font("Serif", Font.BOLD, 30));
		done.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				player1count=0; 
				updateState(player1Turn); 
				changePokemonDisplay(); 
				
			}
			
		});
		bottom.add(done); 
		
		JLabel title = new JLabel("SELECT YOUR POKEMON!!!", SwingConstants.CENTER);
		
		cp.add(center, BorderLayout.CENTER); 
		cp.add(left, BorderLayout.WEST);
		cp.add(right, BorderLayout.EAST);
		cp.add(bottom, BorderLayout.SOUTH);
		cp.add("North", title);
		
		
		
		pack(); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle ("Pokemon Selection"); 
		setSize(1024, 800); 
		setVisible(true); 
	
	}
	/**
	 * Helper method that takes in a raw png file and
	 * creates a resized BufferedImage after calling Graphics2d's
	 * drawImage method
	 * @param img a reference to a BufferedImage object that was read in by ImageIO
	 * @param newW an int representing the new width of the BufferedImage object
	 * @param newH an int representing the new height of the BufferedImage object
	 * @return a reference to a BufferedImage object
	 */
	public BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    return dimg;
	} 
	
	/**
	 * Main method that is called and executed by the initial thread
	 * which then creates a Runnable object that initializes the graphical user
	 * interface and schedules it for execution on the event dispatching thread
	 * (ie., a special thread that handles Swing events such as
	 * when a button is pressed and Swing methods; this thread is
	 * different from the inital thread in which the main method runs on)
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainInterface begin = MainInterface.Start(); 
				try {
					begin.pokemonSelection();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	/**
	 * Method that is invoked when a player chooses a 
	 * pokemon from the pokemon selection stage 
	 * @param s a string object that represents the pokemon chosen during the
	 * pokemon selection stage
	 */
	public void choose(String s)
	{
		currentState.choose(s);
	}
	/**
	 * Helper method that concrete states call to 
	 * update the currentstate of the game
	 * @param state a reference to an object of the state package
	 */
	public void setState(GameState state)
	{
		currentState = state; 
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {}
	/**
	 * Method that handles mouse click events that are generated from 
	 * the pokemon selection screen, in which the players choose their
	 * pokemon for the game
	 */
	@Override
	public void mousePressed(MouseEvent event) 
	{
		JPanel panelClicked = (JPanel) event.getSource();
		String name = " ";
		countClicks++; 
		if(countClicks>6)
		{
			event.consume();
		}
		else if(panelClicked == bulbasaurPanel)
		{
			if(player1count < 3)
			{
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(bulbasaurImg, bulbasaurPanel.getWidth(), bulbasaurPanel.getHeight()))));
					   choose("bulbasaur");
					   player1count++; 
				   }
				   else if(selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(bulbasaurImg, bulbasaurPanel.getWidth(), bulbasaurPanel.getHeight()))));
					   choose("bulbasaur");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(bulbasaurImg, bulbasaurPanel.getWidth(), bulbasaurPanel.getHeight()))));
					   choose("bulbasaur");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(bulbasaurImg, bulbasaurPanel.getWidth(), bulbasaurPanel.getHeight()))));
					   choose("bulbasaur");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(bulbasaurImg, bulbasaurPanel.getWidth(), bulbasaurPanel.getHeight()))));
					   choose("bulbasaur");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(bulbasaurImg, bulbasaurPanel.getWidth(), bulbasaurPanel.getHeight()))));
					   choose("bulbasaur");
				   }
			   }
		   }
		   
		
		else if (panelClicked == bellsproutPanel)
		{
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(bellsproutImg, bellsproutPanel.getWidth(), bellsproutPanel.getHeight()))));
					   choose("bellsprout");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(bellsproutImg, bellsproutPanel.getWidth(), bellsproutPanel.getHeight()))));
					   choose("bellsprout");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(bellsproutImg, bellsproutPanel.getWidth(), bellsproutPanel.getHeight()))));
					   choose("bellsprout");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(bellsproutImg, bellsproutPanel.getWidth(), bellsproutPanel.getHeight()))));
					   choose("bellsprout");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(bellsproutImg, bellsproutPanel.getWidth(), bellsproutPanel.getHeight()))));
					   choose("bellsprout");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(bellsproutImg, bellsproutPanel.getWidth(), bellsproutPanel.getHeight()))));
					   choose("bellsprout");
				   }
			   }
		   }
		
		
		else if (panelClicked == charizardPanel) 
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(charizardImg, charizardPanel.getWidth(), charizardPanel.getHeight()))));
					   choose("charizard");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(charizardImg, charizardPanel.getWidth(), charizardPanel.getHeight()))));
					   choose("charizard");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(charizardImg, charizardPanel.getWidth(), charizardPanel.getHeight()))));
					   choose("charizard");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(charizardImg, charizardPanel.getWidth(), charizardPanel.getHeight()))));
					   choose("charizard");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(charizardImg, charizardPanel.getWidth(), charizardPanel.getHeight()))));
					   choose("charizard");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(charizardImg, charizardPanel.getWidth(), charizardPanel.getHeight()))));
					   choose("charizard");
				   }
			   }
		   }
		
		
		else if (panelClicked == charmanderPanel) 
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(charmanderImg, charmanderPanel.getWidth(), charmanderPanel.getHeight()))));
					   choose("charmander");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(charmanderImg, charmanderPanel.getWidth(), charmanderPanel.getHeight()))));
					   choose("charmander");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(charmanderImg, charmanderPanel.getWidth(), charmanderPanel.getHeight()))));
					   choose("charmander");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(charmanderImg, charmanderPanel.getWidth(), charmanderPanel.getHeight()))));
					   choose("charmander");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(charmanderImg, charmanderPanel.getWidth(), charmanderPanel.getHeight()))));
					   choose("charmander");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(charmanderImg, charmanderPanel.getWidth(), charmanderPanel.getHeight()))));
					   choose("charmander");
				   }
			   }
		   }
		   
		
		else if (panelClicked == ivysaurPanel) 
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(ivysaurImg, ivysaurPanel.getWidth(), ivysaurPanel.getHeight()))));
					   choose("ivysaur");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(ivysaurImg, ivysaurPanel.getWidth(), ivysaurPanel.getHeight()))));
					   choose("ivysaur");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(ivysaurImg, ivysaurPanel.getWidth(), ivysaurPanel.getHeight()))));
					   choose("ivysaur");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(ivysaurImg, ivysaurPanel.getWidth(), ivysaurPanel.getHeight()))));
					   choose("ivysaur");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(ivysaurImg, ivysaurPanel.getWidth(), ivysaurPanel.getHeight()))));
					   choose("ivysaur");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(ivysaurImg, ivysaurPanel.getWidth(), ivysaurPanel.getHeight()))));
					   choose("ivysaur");
				   }
			   }
		   }
		
		
		else if (panelClicked == squirtlePanel)
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(squirtleImg, squirtlePanel.getWidth(), squirtlePanel.getHeight()))));
					   choose("Squirtle");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(squirtleImg, squirtlePanel.getWidth(), squirtlePanel.getHeight()))));
					   choose("Squirtle");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(squirtleImg, squirtlePanel.getWidth(), squirtlePanel.getHeight()))));
					   choose("Squirtle");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(squirtleImg, squirtlePanel.getWidth(), squirtlePanel.getHeight()))));
					   choose("Squirtle");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(squirtleImg, squirtlePanel.getWidth(), squirtlePanel.getHeight()))));
					   choose("Squirtle");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(squirtleImg, squirtlePanel.getWidth(), squirtlePanel.getHeight()))));
					   choose("Squirtle");
				   }
			   }
		   }
		
		
		else if (panelClicked == venusaurPanel) 
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(venusaurImg, venusaurPanel.getWidth(), venusaurPanel.getHeight()))));
					   choose("Venusaur");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(venusaurImg, venusaurPanel.getWidth(), venusaurPanel.getHeight()))));
					   choose("Venusaur");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(venusaurImg, venusaurPanel.getWidth(), venusaurPanel.getHeight()))));
					   choose("Venusaur");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(venusaurImg, venusaurPanel.getWidth(), venusaurPanel.getHeight()))));
					   choose("Venusaur");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(venusaurImg, venusaurPanel.getWidth(), venusaurPanel.getHeight()))));
					   choose("Venusaur");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(venusaurImg, venusaurPanel.getWidth(), venusaurPanel.getHeight()))));
					   choose("Venusaur");
				   }
			   }
		   }
		
		
		else if (panelClicked == vulpixPanel) 
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(vulpixImg, vulpixPanel.getWidth(), vulpixPanel.getHeight()))));
					   choose("Vulpix");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(vulpixImg, vulpixPanel.getWidth(), vulpixPanel.getHeight()))));
					   choose("Vulpix");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(vulpixImg, vulpixPanel.getWidth(), vulpixPanel.getHeight()))));
					   choose("Vulpix");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(vulpixImg, vulpixPanel.getWidth(), vulpixPanel.getHeight()))));
					   choose("Vulpix");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(vulpixImg, vulpixPanel.getWidth(), vulpixPanel.getHeight()))));
					   choose("Vulpix");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(vulpixImg, vulpixPanel.getWidth(), vulpixPanel.getHeight()))));
					   choose("Vulpix");
				   }
			   }
		   }
		
		
		else if (panelClicked == wartortlePanel) 
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(wartortleImg, wartortlePanel.getWidth(), wartortlePanel.getHeight()))));
					   choose("Wartortle");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(wartortleImg, wartortlePanel.getWidth(), wartortlePanel.getHeight()))));
					   choose("Wartortle");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(wartortleImg, wartortlePanel.getWidth(), wartortlePanel.getHeight()))));
					   choose("Wartortle");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(wartortleImg, wartortlePanel.getWidth(), wartortlePanel.getHeight()))));
					   choose("Wartortle");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(wartortleImg, wartortlePanel.getWidth(), wartortlePanel.getHeight()))));
					   choose("Wartortle");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(wartortleImg, wartortlePanel.getWidth(), wartortlePanel.getHeight()))));
					   choose("Wartortle");
				   }
			   }
		   }
		
		
		else if (panelClicked == blastoisePanel) 
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(blastoiseImg, blastoisePanel.getWidth(), blastoisePanel.getHeight()))));
					   choose("Blastoise");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(blastoiseImg, blastoisePanel.getWidth(), blastoisePanel.getHeight()))));
					   choose("Blastoise");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(blastoiseImg, blastoisePanel.getWidth(), blastoisePanel.getHeight()))));
					   choose("Blastoise");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(blastoiseImg, blastoisePanel.getWidth(), blastoisePanel.getHeight()))));
					   choose("Blastoise");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(blastoiseImg, blastoisePanel.getWidth(), blastoisePanel.getHeight()))));
					   choose("Blastoise");
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(blastoiseImg, blastoisePanel.getWidth(), blastoisePanel.getHeight()))));
					   choose("Blastoise");
				   }
			   }
		   }
		
		
		else  if (panelClicked == charmeleonPanel) 
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel(new ImageIcon(resize(charmeleonImg, charmeleonPanel.getWidth(), charmeleonPanel.getHeight()))));
					   choose("Charmeleon");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel(new ImageIcon(resize(charmeleonImg, charmeleonPanel.getWidth(), charmeleonPanel.getHeight()))));
					   choose("Charmeleon");
					   player1count++; 
				   }
				   else
				   {
					   selected3.add("Center", new JLabel(new ImageIcon(resize(charmeleonImg, charmeleonPanel.getWidth(), charmeleonPanel.getHeight()))));
					   choose("Charmeleon");
					   player1count++; 
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel(new ImageIcon(resize(charmeleonImg, charmeleonPanel.getWidth(), charmeleonPanel.getHeight()))));
					   choose("Charmeleon");
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel(new ImageIcon(resize(charmeleonImg, charmeleonPanel.getWidth(), charmeleonPanel.getHeight()))));
					   choose("Charmeleon"); 
				   }
				   else
				   {
					   selected6.add("Center", new JLabel(new ImageIcon(resize(charmeleonImg, charmeleonPanel.getWidth(), charmeleonPanel.getHeight()))));
					   choose("Charmeleon");
				   }
			   }
		   }
		   
		   
		else if (panelClicked == poliwagPanel) 
		   {
			   if(player1count < 3)
			   {
				   if(selected1.getComponentCount()==0)
				   {
					   selected1.add("Center", new JLabel (new ImageIcon(resize(poliwagImg,poliwagPanel.getWidth(), poliwagPanel.getHeight()))));
					   choose("Poliwag");
					   player1count++; 
				   }
				   else if (selected2.getComponentCount()==0) 
				   {
					   selected2.add("Center", new JLabel (new ImageIcon(resize(poliwagImg,poliwagPanel.getWidth(), poliwagPanel.getHeight()))));
					   choose("Poliwag");
					   player1count++;
				   }
				   else
				   {
					   selected3.add("Center", new JLabel (new ImageIcon(resize(poliwagImg,poliwagPanel.getWidth(), poliwagPanel.getHeight()))));
					   choose("Poliwag");
					   player1count++;
				   }
			   }
			   else
			   {
				   if(selected4.getComponentCount()==0)
				   {
					   selected4.add("Center", new JLabel (new ImageIcon(resize(poliwagImg,poliwagPanel.getWidth(), poliwagPanel.getHeight()))));
					   choose("Poliwag");					   
				   }
				   else if (selected5.getComponentCount()==0) 
				   {
					   selected5.add("Center", new JLabel (new ImageIcon(resize(poliwagImg,poliwagPanel.getWidth(), poliwagPanel.getHeight()))));
					   choose("Poliwag");		   
				   }
				   else
				   {
					   selected6.add("Center", new JLabel (new ImageIcon(resize(poliwagImg,poliwagPanel.getWidth(), poliwagPanel.getHeight()))));
					   choose("Poliwag");
				   }
			   }
		   }
		   revalidate();
		   repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
