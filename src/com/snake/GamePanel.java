package com.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.*;

/*��Ϸ���� 
 * */
//��Ϸ����
//��Ϸ״̬ δ��ʼ��Ϸ  ��Ϸ��  ��ͣ   ��Ϸ����
enum GameStates {
	NOSTART, START, PAUSE, GAMEOVER
}

public class GamePanel extends JPanel implements Runnable, KeyListener {
	// ������
	private Snake snake1 = null;
	private Snake snake2 = null;
	// private Egg egg = null;
	// private ClassesDeal classesDeal = null;
	// private Sounds sounds=null;
	private GamemodeDeal gameModedeal = null;

	public static int gameMode;// ��Ϸģʽ
	public static int mode;// 0λ����  1˫��ģʽ1 2 ˫��ģʽ2  
	private static String[] strGamemode = { "", "����", "˫��" };
	private static String[] strClasses = { "", "����", "��ͨ", "����" };// ������Ϸ����
	private static String[] strClasses2 = { "", "����", "��ʱ����" };// ˫����Ϸ����
	public static int classes;// ��Ϸ���� 1 2 3 ��ʾ���� �м� �߼�
	private static int score;// ����
	// private static int snakeLength;// �ߵó���
	private static int rank;// �ȼ�
	private static int speed;// �ߵ��˶��ٶ� �����߳������ٶ�
	boolean isSpeedup;// �Ƿ���Ҫ����
	public static GameStates gameStates;// ��Ϸ״̬
	public static int time;

	MySnakeGame main;
	Time time60;

	private Thread t;
	
	Filereadwrite fileReadwrite;
	// ��Ϸ����
	public int[] classArry = { 0, SnakeContext.CYCLE1, SnakeContext.CYCLE2, SnakeContext.CYCLE3 };

	public GamePanel(MySnakeGame main) {
		this.main=main;
		// ������Ϸ���ش�С
		this.setSize(600, 600);
		// ��ʼ����Ϸ����Ϊ����
		classes = 1;
		// ��ʼ����ϷģʽΪ����ģʽ
		gameMode = 1;
		mode = 0;//0λ����  1˫��ģʽ1 2 ˫��ģʽ2
		// ��Ϸģʽ
		gameModedeal = new GamemodeDeal(this);
		// ʵ��������
		gameModedeal.instantiate();
		snake1 = gameModedeal.snake1;
		snake2 = gameModedeal.snake2;
		// ��ʼ���ٶ�
		speed = classArry[classes];
		isSpeedup = false;
		// �Ʒְ�
		// ��ʼ����Ϸ�Ʒ�����
		score = 0;
		rank = 1;

		time = 60;

		// ��ʼ����Ϸ״̬
		gameStates = GameStates.NOSTART;
		//�߳���
		time60 = new Time(this);
		
		//��ȡ���а���
		fileReadwrite=new Filereadwrite();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 600);
		// ��ǽ ����ͨ�͸��ּ����У�
		drawWall(g);
		// ���ϰ�(���ּ�����)
		drawBarrier(g);
		gameModedeal.paint(g);
		// ���� ��ʼ������ͣʱ ������
		if (gameStates == GameStates.START || gameStates == GameStates.PAUSE || gameStates == GameStates.GAMEOVER) {
			//˫��ģʽ2�� ����Ҫ����
			if(mode!=2) {g.drawImage(new ImageIcon("images/egg.png").getImage(), gameModedeal.egg.x, gameModedeal.egg.y, this);}
		}
		// System.out.println(gameModedeal.egg1.x);
		this.repaint();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (gameStates == GameStates.START) {
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// �Ʒְ�
			MySnakeGame.jlMode.setText("��Ϸģʽ��" + strGamemode[gameMode]);
			MySnakeGame.jlClass.setText("��Ϸ����:" + strClasses[classes]);

			// snakeLength = Snake.snakeVc.size();
			// gameModedeal.threadDeal();
			gameModedeal.threadDeal();
			// ����ģʽ
			if (gameMode == 1) {
				MySnakeGame.jlSnakeNode.setText("�߳���:" + gameModedeal.snakeLength1);
				MySnakeGame.jlSnakeNode.setFont(new Font("����", Font.PLAIN, 20));
				MySnakeGame.jlScore.setText("����:" + score);
				MySnakeGame.jlScore.setFont(new Font("����", Font.PLAIN, 20));
				// ������һ�ٷ� �ٶȼӿ�10���� ���Ϊ10����
				if (score / 100 - rank == 1) {
					isSpeedup = true;
				}
				// ��Ҫ���� �����
				if (speed != 10) {
					if (isSpeedup) {
						speed -= 10;
						isSpeedup = false;
					}
				}
				// ��������Ӧ�ĵȼ�
				rank = score / 100;
				MySnakeGame.jlRank.setText("�ȼ�:" + rank);

			}
		
		// ˫��ģʽ
		if (gameMode == 2) {
			MySnakeGame.jlClass.setText("ģʽ:" + strClasses2[mode]);
			MySnakeGame.jlSnakeNode
					.setText("��1����:" + gameModedeal.snakeLength1 + " " + "��2����:" + gameModedeal.snakeLength2);
			MySnakeGame.jlSnakeNode.setFont(new Font("����", Font.PLAIN, 15));
			MySnakeGame.jlScore.setText("��1����:" + gameModedeal.life1 + " " + "��2����:" + gameModedeal.life2);
			MySnakeGame.jlScore.setFont(new Font("����", Font.PLAIN, 15));

			if (mode == 2) {
				MySnakeGame.jlScore.setText("��1����:" + "��" + " " + "��2����:" + "��");

			}
		}

		// �ж��Ƿ���Ϸ����
		isGameover();
		}
	}

	// �Ե�
	public void eatEgg() {
		score += 10;
		gameModedeal.eatEgg();

	}

	// ��ǽ
	private void drawWall(Graphics g) {
		g.setColor(new Color(245, 245, 245));
		for (int i = 0; i < 20; i++) {
			g.fill3DRect(0, i * 30, 30, 30, false);
			g.fill3DRect(i * 30, 0, 30, 30, false);
			g.fill3DRect(570, i * 30, 30, 30, false);
			g.fill3DRect(i * 30, 570, 30, 30, false);
		}
	}

	// �����ϰ���
	private void drawBarrier(Graphics g) {
		if (classes == 3)
			for (int i = 150; i < 420; i += 90) {
				// ���ϰ�
				g.setColor(new Color(70, 130, 180));
				g.fill3DRect(150, i, 30, 30, false);
				g.setColor(new Color(30, 144, 255));
				g.fill3DRect(150, i + 30, 30, 30, false);
				g.setColor(new Color(135, 206, 235));
				g.fill3DRect(150, i + 60, 30, 30, false);
				// ���ϰ�
				g.setColor(new Color(70, 130, 180));
				g.fill3DRect(450, i, 30, 30, false);
				g.setColor(new Color(30, 144, 255));
				g.fill3DRect(450, i + 30, 30, 30, false);
				g.setColor(new Color(135, 206, 235));
				g.fill3DRect(450, i + 60, 30, 30, false);
			}

	}

	// �Ƿ�����
	private void isGameover() {
		// �ж�����
		if (gameStates == GameStates.GAMEOVER) {
			if (gameMode == 1) {
					fileReadwrite.readRank(classes);
					if(fileReadwrite.isInRank(score)){
						String name=JOptionPane.showInputDialog(this, "���������Ĵ���",
								"��ϲ��������˵�"+fileReadwrite.num+""
								+ "��");
						fileReadwrite.name[fileReadwrite.num]=name;
						fileReadwrite.writeInRank(classes);
						MyJDialog rankDialog=new MyJDialog(main,"rank");
						rankDialog.setVisible(true);
					}
					else {JOptionPane.showMessageDialog(null, "��Ϸ����!��δ�ܽ���Ӣ�۰񣡼���Ŭ����");}
				
			}
			if (gameMode == 2) {
				if (mode == 1) {
					if (snake1.life == 0) {
						JOptionPane.showMessageDialog(null, "���2ʤ��");
					}
					if (snake2.life == 0) {
						JOptionPane.showMessageDialog(null, "���1ʤ��");
					}
					if (snake1.isLive == false && snake2.isLive == false) {
						if (snake1.life > snake2.life) {
							JOptionPane.showMessageDialog(null, "���1ʤ��");
						} else if (snake1.life < snake2.life) {
							JOptionPane.showMessageDialog(null, "���2ʤ��");
						} else if (snake1.life == snake2.life) {
							JOptionPane.showMessageDialog(null, "ͬ���ھ��񣡱���ͬ����������̫����");
						}
					}
					whoWin();
				}
				if (mode == 2) {
					if (time == 0) {
						if (snake1.getsnakeSize() > snake2.getsnakeSize()) {
							JOptionPane.showMessageDialog(null, "���1ʤ��");
						} else if (snake1.getsnakeSize() < snake2.getsnakeSize()) {
							JOptionPane.showMessageDialog(null, "���ʤ��");
						} else if (snake1.getsnakeSize() == snake2.getsnakeSize()) {
							JOptionPane.showMessageDialog(null, "�Գ�ƽ�֣�");
						}

					}
					whoWin();
				}
			}
		}
	}

	// �ж������Ӯ
	public void whoWin() {
		if (snake1.isLive == false && snake2.isLive == true) {
			JOptionPane.showMessageDialog(null, "���2ʤ��");
		} else if (snake1.isLive == true && snake2.isLive == false) {
			JOptionPane.showMessageDialog(null, "���1ʤ��");
		}
		if(mode==2){
		if(snake1.isLive == false && snake2.isLive == false){
			JOptionPane.showMessageDialog(null, "ͬ���ھ���");
		}
		}
	}

	// ���¿�ʼ��Ϸ
	public void reStart() {
		// ��ʼ���Ʒְ�����
		score = 0;
		rank = 0;
		speed = classArry[classes];
		gameStates = GameStates.NOSTART;
		time = 60;

		MySnakeGame.jlScore.setText("����:" + 0);
		MySnakeGame.jlScore.setFont(new Font("����", Font.PLAIN, 20));
		MySnakeGame.jlMode.setText("��Ϸģʽ��" + strGamemode[gameMode]);
		MySnakeGame.jlClass.setText("��Ϸ����:" + strClasses[classes]);
		MySnakeGame.jlTime.setText("");

		// ��Ϸģʽ����
		gameModedeal.restart();
		// �߳���
		MySnakeGame.jlSnakeNode.setText("�߳���:" + gameModedeal.snakeLength1);
		MySnakeGame.jlSnakeNode.setFont(new Font("����", Font.PLAIN, 20));
		if (gameMode == 2) {

			MySnakeGame.jlClass.setText("ģʽ:" + strClasses2[mode]);
			MySnakeGame.jlSnakeNode
					.setText("��1����:" + gameModedeal.snakeLength1 + " " + "��2����:" + gameModedeal.snakeLength2);
			MySnakeGame.jlSnakeNode.setFont(new Font("����", Font.PLAIN, 15));
			MySnakeGame.jlScore.setText("��1����:" + gameModedeal.life1 + " " + "��2����:" + gameModedeal.life2);
			MySnakeGame.jlScore.setFont(new Font("����", Font.PLAIN, 15));
			MySnakeGame.jlTime.setText("");
			if (mode == 2) {
				MySnakeGame.jlScore.setText("��1����:" + "��" + " " + "��2����:" + "��");
				MySnakeGame.jlTime.setText("ʱ��:" + "   " + 60);
			}

		}
		snake1 = gameModedeal.snake1;
		snake2 = gameModedeal.snake2;
		this.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// ��Ϸ��ʼʱ �߲��ܱ�����
		if (gameStates == GameStates.START) {
			// �ı䷽�� ʱ ���·�����ӵ�������
			if (e.getKeyCode() == KeyEvent.VK_W) {
				snake1.newdirVc.add(Direction.UP);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				snake1.newdirVc.add(Direction.DOWN);
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				snake1.newdirVc.add(Direction.LEFT);
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				snake1.newdirVc.add(Direction.RIGHT);
			}
			if (gameMode == 2) {
				// �ı䷽�� ʱ ���·�����ӵ�������
				if (e.getKeyCode() == KeyEvent.VK_UP) {

					snake2.newdirVc.add(Direction.UP);
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					snake2.newdirVc.add(Direction.DOWN);
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					snake2.newdirVc.add(Direction.LEFT);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					snake2.newdirVc.add(Direction.RIGHT);
				}

			}

		}
		// ���
		// CTR+'+' ���ٶ�
		if (e.getKeyCode() == KeyEvent.VK_EQUALS) {
			if (speed != 10) {
				speed -= 10;
			}
		}
		// CTR+'-' ���ٶ�
		if (e.getKeyCode() == KeyEvent.VK_MINUS) {
			speed += 10;
		}
		// ��ʼ��Ϸ ��ͣ��Ϸ SpaceΪ��ݼ�
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (gameStates == GameStates.NOSTART || gameStates == GameStates.PAUSE) {
				gameStates = GameStates.START;
				if (gameMode == 2 && mode == 2) {
					Thread thread60 = new Thread(time60);
					thread60.start();
				}
			} else if (gameStates == GameStates.START) {
				gameStates = GameStates.PAUSE;
			}
			if (gameStates == GameStates.START) {
				t = new Thread(this);
				t.start();
			}
		}

		// �˳���Ϸ
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
