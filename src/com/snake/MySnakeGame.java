package com.snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*SakejmGame������
 * */
public class MySnakeGame extends JFrame implements ActionListener, MouseListener {
	// �������
	GamePanel mp = null;
	JPanel rightPanel = null;
	public static JPanel scorePanel = null;
	JPanel statementPanel = null;
	// �˵�
	private JMenuBar jmb;
	private JMenu jmGame;
	private JMenuItem jmtStart;
	private JMenuItem jmtRestart;
	private JMenuItem jmtPause;
	private JMenuItem jmtQuit;
	// �ڶ��в˵� �ȼ�
	private JMenu jmMode;
	private JMenu jmOnesnake;
	private JMenuItem jmtSimple1;
	private JMenuItem jmtMiddle1;
	private JMenuItem jmtHeight1;
	private JMenu jmTwosnake;
	private JMenuItem jmtTwoMode1;
	private JMenuItem jmtTwoMode2;
	// �����в˵� ��Ч
	private JMenu jmSound;
	private JMenu jmBgm;
	private JMenuItem jmtOpenbgm;
	private JMenuItem jmtClosebgm;
	private JMenu jmGameSound;
	private JMenuItem jmtOpenGamesound;
	private JMenuItem jmtCloseGamesound;
	// �����в˵� ���а�
	private JMenu jmRank;
	private JMenuItem jmtSimplerank;
	private JMenuItem jmtMiddlerank;
	private JMenuItem jmtHeightrank;

	// ��Ϸ����
	private JMenu jmHelp;
	// �����в˵� ����
	private JMenu jmAbout;

	public static JLabel jlMode;// ��Ϸģʽ
	public static JLabel jlClass;// �ٶȼ���
	public static JLabel jlScore;// �Ʒ�
	public static JLabel jlSnakeNode;// �߳���
	// public static JLabel JlLife;//����
	public static JLabel jlRank;// �ȼ�
	public static JLabel jlPlayer;// ���
	// ��������
	public static JLabel jlUp;
	public static JLabel jlDown;
	public static JLabel jlLeft;
	public static JLabel jlRight;
	// ��ʱ ˫�� ģʽ2�õ�
	public static JLabel jlTime;

	// ��������
	private Music bgm = null;
	public static Music gameSound = null;
	public static boolean isHaveSound;// �Ƿ�Ҫ��Ϸ��Ч
	MyJDialog myJd;// �Ի���

	// �߳���
	Time time60;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySnakeGame mysnkejmGame = new MySnakeGame();
	}

	public MySnakeGame() {
		// ��Ϸ���
		mp = new GamePanel(this);
		this.add(mp);
		this.addKeyListener(mp);

		Snake snake = new Snake();

		// �˵�
		jmb = new JMenuBar();
		// ��һ�в˵� ��Ϸ
		jmGame = new JMenu("��Ϸ(G)");
		jmGame.setMnemonic('G');
		jmtStart = new JMenuItem("��ʼ");
		jmtStart.addActionListener(this);
		jmtStart.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));// ���ÿ�ݷ�ʽ
		jmtRestart = new JMenuItem("���¿�ʼ");
		jmtRestart.addActionListener(this);
		jmtRestart.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
		jmtPause = new JMenuItem("��ͣ");
		jmtPause.addActionListener(this);
		jmtPause.setAccelerator(KeyStroke.getKeyStroke('P', Event.CTRL_MASK));
		jmtQuit = new JMenuItem("�˳�");
		jmtQuit.addActionListener(this);
		jmtQuit.setAccelerator(KeyStroke.getKeyStroke('Q', Event.CTRL_MASK));
		// ���
		jmGame.add(jmtStart);
		jmGame.add(jmtRestart);
		jmGame.add(jmtPause);
		jmGame.add(jmtQuit);
		// �ڶ��в˵� �ȼ�
		jmMode = new JMenu("��Ϸģʽ(M)");
		jmMode.setMnemonic('M');
		jmOnesnake = new JMenu("����ģʽ(O)");
		jmOnesnake.setMnemonic('O');
		jmtSimple1 = new JMenuItem("����");
		jmtSimple1.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.Event.CTRL_MASK));
		jmtSimple1.addActionListener(this);
		jmtMiddle1 = new JMenuItem("��ͨ");
		jmtMiddle1.setAccelerator(KeyStroke.getKeyStroke('M', java.awt.Event.CTRL_MASK));
		jmtMiddle1.addActionListener(this);
		jmtHeight1 = new JMenuItem("����");
		jmtHeight1.setAccelerator(KeyStroke.getKeyStroke('H', java.awt.Event.CTRL_MASK));
		jmtHeight1.addActionListener(this);

		jmTwosnake = new JMenu("˫��ģʽ");
		jmTwosnake.setMnemonic('T');
		jmtTwoMode1 = new JMenuItem("����ģʽ");
		jmtTwoMode1.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.Event.CTRL_MASK));
		jmtTwoMode1.addActionListener(this);
		jmtTwoMode2 = new JMenuItem("��ʱ����ģʽ");
		jmtTwoMode2.setAccelerator(KeyStroke.getKeyStroke('M', java.awt.Event.CTRL_MASK));
		jmtTwoMode2.addActionListener(this);

		// ���
		jmMode.add(jmOnesnake);
		jmOnesnake.add(jmtSimple1);
		jmOnesnake.add(jmtMiddle1);
		jmOnesnake.add(jmtHeight1);
		jmMode.add(jmTwosnake);
		jmTwosnake.add(jmtTwoMode1);
		jmTwosnake.add(jmtTwoMode2);

		// �����в˵� ��Ч
		jmSound = new JMenu("��Ч(S)");
		jmSound.setMnemonic('S');
		jmBgm = new JMenu("��������(B)");
		jmBgm.setMnemonic('B');
		jmtOpenbgm = new JMenuItem("��");
		jmtOpenbgm.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.Event.CTRL_MASK));
		jmtOpenbgm.addActionListener(this);
		jmtClosebgm = new JMenuItem("��");
		jmtClosebgm.setAccelerator(KeyStroke.getKeyStroke('C', java.awt.Event.CTRL_MASK));
		jmtClosebgm.addActionListener(this);
		jmGameSound = new JMenu("��Ϸ��Ч(M)");
		jmGameSound.setMnemonic('M');
		jmtOpenGamesound = new JMenuItem("��");
		jmtOpenGamesound.setAccelerator(KeyStroke.getKeyStroke('N', java.awt.Event.CTRL_MASK));
		jmtOpenGamesound.addActionListener(this);
		jmtCloseGamesound = new JMenuItem("��");
		jmtCloseGamesound.setAccelerator(KeyStroke.getKeyStroke('M', java.awt.Event.CTRL_MASK));
		jmtCloseGamesound.addActionListener(this);
		// ���
		jmBgm.add(jmtOpenbgm);
		jmBgm.add(jmtClosebgm);
		jmGameSound.add(jmtOpenGamesound);
		jmGameSound.add(jmtCloseGamesound);
		jmSound.add(jmBgm);
		jmSound.add(jmGameSound);

		// �����в˵� ���а�
		jmRank = new JMenu("���а�(R)");
		jmRank.setMnemonic('R');
		jmRank.addMouseListener(this);
//		jmtSimplerank = new JMenuItem("�������а�");
//		jmtSimplerank.addActionListener(this);
//		jmtMiddlerank = new JMenuItem("��ͨ���а�");
//		jmtMiddlerank.addActionListener(this);
//		jmtHeightrank = new JMenuItem("�������а�");
//		jmtHeightrank.addActionListener(this);
//		// ���
//		jmRank.add(jmtSimplerank);
//		jmRank.add(jmtMiddlerank);
//		jmRank.add(jmtHeightrank);

		// ��Ϸ����
		jmHelp = new JMenu("��Ϸ����(H)");
		jmHelp.setMnemonic('H');
		jmHelp.addMouseListener(this);

		// �����в˵� ����
		jmAbout = new JMenu("����(A)");
		jmAbout.setMnemonic('A');
		jmAbout.addMouseListener(this);

		// ��ӵ�JMenuBar
		jmb.add(jmGame);
		jmb.add(jmMode);
		jmb.add(jmSound);
		jmb.add(jmRank);
		jmb.add(jmHelp);
		jmb.add(jmAbout);
		// this.add(jmb); ����Ҫ�ⲽ

		// �ұ����
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(2, 1));
		this.add(rightPanel, BorderLayout.EAST);

		// �Ʒְ�
		scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(6, 1));
		jlMode = new JLabel("��Ϸģʽ:", JLabel.CENTER);
		jlMode.setFont(new Font("����", Font.BOLD, 20));
		jlClass = new JLabel("��Ϸ����:");
		jlClass.setFont(new Font("����", Font.PLAIN, 20));
		jlScore = new JLabel("����:" + 0);
		jlScore.setFont(new Font("����", Font.PLAIN, 20));
		jlSnakeNode = new JLabel("�߳��ȣ�3");
		jlSnakeNode.setFont(new Font("����", Font.PLAIN, 20));
		jlRank = new JLabel("�ȼ�:" + 0);
		jlRank.setFont(new Font("����", Font.PLAIN, 20));
		jlTime = new JLabel();
		jlTime.setFont(new Font("����", Font.BOLD, 20));
		// JlLife=new JLabel();
		// ���
		scorePanel.add(jlMode);
		scorePanel.add(jlClass);
		scorePanel.add(jlRank);
		scorePanel.add(jlScore);
		scorePanel.add(jlSnakeNode);
		scorePanel.add(jlTime);
		// scorePanel.add(JlLife);

		rightPanel.add(scorePanel);
		// ���ñ߿�
		scorePanel.setBorder(new LineBorder(Color.black, 1));

		// ��Ϸ����˵�����
		statementPanel = new JPanel();
		statementPanel.setLayout(new GridLayout(7, 1));
		JLabel jlContorl = new JLabel("��Ϸ����", JLabel.CENTER);
		jlContorl.setFont(new Font("����", Font.BOLD, 20));
		JLabel jlStart = new JLabel("��ʼ/��ͣ��Space");
		jlStart.setFont(new Font("����", Font.PLAIN, 20));
		jlPlayer = new JLabel();
		jlPlayer.setFont(new Font("����", Font.PLAIN, 20));
		jlUp = new JLabel();
		jlUp.setFont(new Font("����", Font.PLAIN, 20));
		jlDown = new JLabel();
		jlDown.setFont(new Font("����", Font.PLAIN, 20));
		jlLeft = new JLabel();
		jlLeft.setFont(new Font("����", Font.PLAIN, 20));
		jlRight = new JLabel();
		jlRight.setFont(new Font("����", Font.PLAIN, 20));
		MySnakeGame.jlPlayer.setText("���1" + "    " + "���2");
		MySnakeGame.jlUp.setText("��:W" + "      " + "��");
		MySnakeGame.jlDown.setText("��:S" + "      " + "��");
		MySnakeGame.jlLeft.setText("��:A" + "      " + "��");
		MySnakeGame.jlRight.setText("��:D" + "      " + "��");
		statementPanel.add(jlContorl);
		statementPanel.add(jlStart);
		statementPanel.add(jlPlayer);
		statementPanel.add(jlUp);
		statementPanel.add(jlDown);
		statementPanel.add(jlLeft);
		statementPanel.add(jlRight);
		rightPanel.add(statementPanel);
		// ���ñ߿�
		statementPanel.setBorder(new LineBorder(Color.black, 1));

		// ��������
		bgm = new Music();
		bgm.musicstart("sounds/bgm.wav");
		bgm.musicLoopplay();
		// ��Ϸ��Ч
		gameSound = new Music();
		gameSound.musicstart("sounds/eat.wav");
		isHaveSound = true;// ��Ϸ��ЧĬ��Ϊ��

		// ����JFrame
		//���ڼ���
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(JOptionPane.showConfirmDialog(MySnakeGame.this, "�����Ҫ�˳���","ȷ���˳�",JOptionPane.OK_CANCEL_OPTION)
				==JOptionPane.OK_OPTION){
			System.exit(0);}
			}
			
		});
		this.setTitle("̰ʳ��");
		this.setIconImage(new ImageIcon("images/icon.png").getImage());
		this.setSize(768, 654);
		this.setJMenuBar(jmb);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(this); // ���ھ���
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// �˵�����
		// ��ʼ
		if (e.getSource() == this.jmtStart) {
			if(GamePanel.gameStates!=GameStates.START&&GamePanel.gameStates!=GameStates.GAMEOVER){
			GamePanel.gameStates = GameStates.START;
			Thread t = new Thread(mp);
			t.start();
			if (GamePanel.gameMode == 2 && GamePanel.mode == 2) {
				Thread thread60 = new Thread(time60);
				thread60.start();
			}
			}
		}
		// ��ͣ
		if (e.getSource() == this.jmtPause)
			GamePanel.gameStates = GameStates.PAUSE;
		// ���¿�ʼ
		if (e.getSource() == this.jmtRestart)
			mp.reStart();
		// �˳���Ϸ
		if (e.getSource() == this.jmtQuit)
			System.exit(0);
		// ����ģʽ
		// ����
		if (e.getSource() == this.jmtSimple1) {
			GamePanel.gameMode = 1;
			GamePanel.mode = 0;
			GamePanel.classes = 1;
			GamemodeDeal.instantiate();
			mp.reStart();
		}
		// ��ͨ
		if (e.getSource() == this.jmtMiddle1) {
			GamePanel.gameMode = 1;
			GamePanel.mode = 0;
			GamePanel.classes = 2;
			GamemodeDeal.instantiate();
			mp.reStart();
		}
		// ����
		if (e.getSource() == this.jmtHeight1) {
			GamePanel.gameMode = 1;
			GamePanel.mode = 0;
			GamePanel.classes = 3;
			GamemodeDeal.instantiate();
			mp.reStart();
		}
		// ˫��ģʽ
		// ����ģʽ
		if (e.getSource() == this.jmtTwoMode1) {
			GamePanel.gameMode = 2;
			GamePanel.mode = 1;
			GamePanel.classes = 2;
			GamemodeDeal.instantiate();
			mp.reStart();
		}
		// ��ʱ ����ģʽ
		if (e.getSource() == this.jmtTwoMode2) {
			GamePanel.gameMode = 2;
			GamePanel.mode = 2;
			GamePanel.classes = 2;
			GamemodeDeal.instantiate();
			mp.reStart();
		}
//		// �������а�
//		if (e.getSource() == this.jmtSimplerank) {
//			myJd = new MyJDialog(this, "simpleRank");
//			myJd.setVisible(true);
//			
//		}
//		// ��ͨ���а�
//		if (e.getSource() == this.jmtMiddlerank) {
//			myJd = new MyJDialog(this, "middleRank");
//			myJd.setVisible(true);
//
//		}
//		// �������а�
//		if (e.getSource() == this.jmtHeightrank) {
//			myJd = new MyJDialog(this, "heightRank");
//			myJd.setVisible(true);
//
//		}
		// ��Ч
		if (e.getSource() == this.jmtClosebgm) {
			bgm.musicStop();
		}
		if (e.getSource() == this.jmtOpenbgm) {
			bgm.musicLoopplay();
		}
		if (e.getSource() == this.jmtCloseGamesound) {
			isHaveSound = false;
		}
		if (e.getSource() == this.jmtOpenGamesound) {
			isHaveSound = true;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.jmRank) {
			myJd = new MyJDialog(this, "rank");
			myJd.setVisible(true);
		}
		//����
		if (e.getSource() == this.jmHelp) {
			myJd = new MyJDialog(this, "help");
			myJd.setVisible(true);
		}
		//����
		if (e.getSource() == this.jmAbout) {
			myJd = new MyJDialog(this, "about");
			myJd.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
