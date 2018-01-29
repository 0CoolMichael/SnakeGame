package com.snake;

import java.awt.Color;
import java.awt.Graphics;

//��Ϸģʽ����
public class GamemodeDeal {
	private GamePanel gamePanel;
	public static Snake snake1 = null;
	public static Snake snake2 = null;
	public static Egg egg = null;
	private static ClassesDeal classesDeal = null;
	public static int snakeLength1;// ��1�ĳ���
	public static int snakeLength2;// ��2�ĳ���
	public static int life1;// ��1����
	public static int life2;// ��2����

	public GamemodeDeal(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	// ʵ�������� ��GamePanel������
	public static void instantiate() {

		snake1 = new Snake();
		snake2 = new Snake(GamePanel.gameMode);
		egg = new Egg(snake1, snake2);
		classesDeal = new ClassesDeal(snake1, snake2);
		snakeLength1 = snake1.snakeVc.size();// ��1�ĳ���
		snakeLength2 = snake2.snakeVc.size();// ��2�ĳ���
		life1 = snake1.life;
		life2 = snake2.life;
	}

	// �ػ淽��
	public void paint(Graphics g) {

		// ����
		snake1.drawSanke(g);
		// if (GamePanel.gameStates == GameStates.START) {

		// }
		// �����Լ���β���� �ػ���ͷ ������ԭ�� ������β��������ͷ
		if (snake1.isLive == false) {
			g.setColor(Color.red);
			g.fill3DRect(snake1.getHead().getX(), snake1.getHead().getY(), 30, 30, false);
		}
		if (GamePanel.gameMode == 2) {
			// ����
			snake2.drawSanke(g);
			// �����Լ���β���� �ػ���ͷ ������ԭ�� ������β��������ͷ
			if (snake2.isLive == false) {
				g.setColor(Color.blue);
				g.fill3DRect(snake2.getHead().getX(), snake2.getHead().getY(), 30, 30, false);
			}
		}
		// �����������ɵ�
		if (egg.isLive == false) {
			egg.setRandom();
		}
	}

	// �߳���Ĵ���
	public void threadDeal() {

		snakeLength1 = snake1.snakeVc.size();

		// �ߴ�����˶�
		if (GamePanel.gameStates != GameStates.GAMEOVER) {
			snake1.Snakemove();
		}
		// ���µķ��� ����з�����
		if (snake1.newdirVc.size() > 0) {
			snake1.changeDir(snake1.newdirVc.get(0));
		}
		// û�е��� ����������
		if (!egg.haveEgg()) {
			gamePanel.eatEgg();
		}
		if (GamePanel.gameMode == 2) {
			// �ߴ�����˶�
			if (GamePanel.gameStates != GameStates.GAMEOVER) {
				snake2.Snakemove();
			}
			// ���µķ��� ����з�����
			if (snake2.newdirVc.size() > 0) {
				snake2.changeDir(snake2.newdirVc.get(0));
			}
			snakeLength2 = snake2.snakeVc.size();
			life1 = snake1.life;
			life2 = snake2.life;
		}
		// ��������
		classesDeal.dieDeal();

	}

	// �Ե�����
	public void eatEgg() {
		if (GamePanel.gameMode == 1) {
			if (snake1.isEategg == true) {
				snake1.addSnakenode();
				snake1.isEategg = false;
			}
		}
		if (GamePanel.gameMode == 2) {
			if (snake1.isEategg == true) {
				snake1.addSnakenode();
				snake2.life -= 1;
				snake1.isEategg = false;
			}
			if (snake2.isEategg == true) {
				snake2.addSnakenode();
				snake1.life -= 1;
				snake2.isEategg = false;
			}
		}
		if (MySnakeGame.isHaveSound == true) {
			MySnakeGame.gameSound.musicPlay();
		}

	}

	// ���¿�ʼ��Ϸ����
	public void restart() {
		snake1 = new Snake();
		snakeLength1 = snake1.snakeVc.size();
		if (GamePanel.gameMode == 2) {
			snake2 = new Snake(GamePanel.gameMode);
			snakeLength2 = snake2.snakeVc.size();
			life1 = snake1.life;
			life2 = snake2.life;
		}

		egg = new Egg(snake1, snake2);
		classesDeal = new ClassesDeal(snake1, snake2);
	}

}
