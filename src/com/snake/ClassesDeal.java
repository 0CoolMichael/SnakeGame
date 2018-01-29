package com.snake;

import java.awt.Graphics;

/*
 * ��Ϸģʽ������  */
public class ClassesDeal {
	Snake snake1;
	Snake snake2;

	public ClassesDeal(Snake snake1, Snake snake2) {
		this.snake1 = snake1;
		this.snake2 = snake2;
	}

	// ��Ϸ�������
	public void dieDeal() {
		switch (GamePanel.classes) {
		// ����
		case 1:
			// ���� ����
			low();
			break;
		// ����
		case 2:
			// ���� ��ͨ
			middle(snake1);
			// ˫��ģʽ ����ģʽ
			if (GamePanel.mode == 1) {
				robFood();
			}
			if (GamePanel.mode == 2) {
				eatEachother();
			}
			break;
		// ����
		case 3:
			// ���� ����ģʽ
			middle(snake1);
			// �������ϰ�������
			for (int i = 150; i <= 390; i += 30) {
				if (snake1.getHead().getX() == 150 && snake1.getHead().getY() == i) {
					snake1.isLive = false;
					GamePanel.gameStates = GameStates.GAMEOVER;
				}
				if (snake1.getHead().getX() == 450 && snake1.getHead().getY() == i) {
					snake1.isLive = false;
					GamePanel.gameStates = GameStates.GAMEOVER;
				}
			}
			break;
		}
	}

	// ���� ����
	private void low() {
		for (int i = 0; i < snake1.snakeVc.size(); i++) {
			// �߿��Դ�ǽ
			if (snake1.snakeVc.get(i).getX() >540)
				{snake1.snakeVc.get(i).setX(30);}
			else if (snake1.snakeVc.get(i).getY() >540)
				{snake1.snakeVc.get(i).setY(30);}
			else if (snake1.snakeVc.get(i).getX() <30)
				{snake1.snakeVc.get(i).setX(540);}
			else if (snake1.snakeVc.get(i).getY() <30)
			{	snake1.snakeVc.get(i).setY(540);}
			// �ж��Ƿ����β �ߵĽ�������5�ǲ��ܳԵ��Լ�
			for (int j = 4; j < snake1.snakeVc.size(); j++) {
				if (snake1.getHead().getX() == snake1.snakeVc.get(j).getX()
						&& snake1.getHead().getY() == snake1.snakeVc.get(j).getY()) {
					snake1.isLive = false;
					GamePanel.gameStates = GameStates.GAMEOVER;
				}

			}
		}
	}

	// ���� ��ͨ
	private void middle(Snake sanke) {
		// �ж��Ƿ����β �ߵĽ�������5�ǲ��ܳԵ��Լ�
		for (int j = 4; j < sanke.snakeVc.size(); j++) {
			if (sanke.getHead().getX() == sanke.snakeVc.get(j).getX()
					&& sanke.getHead().getY() == sanke.snakeVc.get(j).getY()) {
				sanke.isLive = false;
				GamePanel.gameStates = GameStates.GAMEOVER;
			}

		}
		// ������ǽ�ͻ���
		if (sanke.getHead().getX() >= 570) {
			sanke.isLive = false;
			GamePanel.gameStates = GameStates.GAMEOVER;
		} else if (sanke.getHead().getY() >= 570) {
			sanke.isLive = false;
			GamePanel.gameStates = GameStates.GAMEOVER;
		} else if (sanke.getHead().getX() < 30) {
			sanke.isLive = false;
			GamePanel.gameStates = GameStates.GAMEOVER;
		} else if (sanke.getHead().getY() < 30) {
			sanke.isLive = false;
			GamePanel.gameStates = GameStates.GAMEOVER;
		}
	}

	// ˫�� ����ģʽ
	private void robFood() {
		middle(snake2);
		// ��2ײ��1
		for (int i = 0; i < snake1.snakeVc.size(); i++) {
			if (snake2.getHead().getX() == snake1.snakeVc.get(i).getX()
					&& snake2.getHead().getY() == snake1.snakeVc.get(i).getY()) {
				if (i == 0) {
					snake1.isLive = false;
					snake2.isLive = false;
					GamePanel.gameStates = GameStates.GAMEOVER;
				} // ��2��
				else {
					snake2.isLive = false;
					GamePanel.gameStates = GameStates.GAMEOVER;
				}
			}
		}
		// ��1ײ��2
		for (int i = 0; i < snake2.snakeVc.size(); i++) {
			if (snake1.getHead().getX() == snake2.snakeVc.get(i).getX()
					&& snake1.getHead().getY() == snake2.snakeVc.get(i).getY()) {
				if (i == 0) {
					snake1.isLive = false;
					snake2.isLive = false;
					GamePanel.gameStates = GameStates.GAMEOVER;
				} else {
					snake1.isLive = false;
					GamePanel.gameStates = GameStates.GAMEOVER;
				}
			}
		}
		// ����ֵΪ0 ��Ϸ����
		if (snake1.life == 0 || snake2.life == 0) {
			GamePanel.gameStates = GameStates.GAMEOVER;
		}
	}

	//��ʱ ����ģʽ
	private void eatEachother() {
		middle(snake2);
		//ʱ��Ϊ0 ��Ϸ����
		if(GamePanel.time==0) {GamePanel.gameStates = GameStates.GAMEOVER;}
		// ��2 �� ��1
		for (int i = 0; i < snake1.snakeVc.size(); i++) {
			if (snake2.getHead().getX() == snake1.snakeVc.get(i).getX()
					&& snake2.getHead().getY() == snake1.snakeVc.get(i).getY()) {
				// ײ��ͷ
				if (i == 0) {
					// ����ͷ
					if (snake1.snakeVc.size() == 1) {
						snake1.isLive = false;
						GamePanel.gameStates = GameStates.GAMEOVER;
					}
//					// ����ײ��ͷ �ߵڶ��ڵ�X��Y�������Ϊ����
//					if (snake2.getNode2().getX() != snake1.getNode2().getX()
//							&& snake2.getNode2().getY() != snake1.getNode2().getY()) {
//						snake1.isLive = false;
//						GamePanel.gameStates = GameStates.GAMEOVER;
//					}
				}

				// ��2β���� ��1����
				else {
					snake2.addSnakenode();
					if (MySnakeGame.isHaveSound == true) {
						MySnakeGame.gameSound.musicPlay();
					}
					if (snake1.snakeVc.size() != 1) {
						snake1.removeLast();
					}
				}
			}
		}

		// ��1 �� ��2
		for (int i = 0; i < snake2.snakeVc.size(); i++) {
			if (snake1.getHead().getX() == snake2.snakeVc.get(i).getX()
					&& snake1.getHead().getY() == snake2.snakeVc.get(i).getY()) {
				// ײ��ͷ
				if (i == 0) {
					// ����ͷ
					if (snake2.snakeVc.size() == 1) {
						snake2.isLive = false;
						GamePanel.gameStates = GameStates.GAMEOVER;
					}
//					// ����ײ��ͷ �ߵڶ��ڵ�X��Y�������Ϊ����
//					if (snake1.getNode2().getX() != snake2.getNode2().getX()
//							&& snake1.getNode2().getY() != snake2.getNode2().getY()) {
//						snake2.isLive = false;
//						GamePanel.gameStates = GameStates.GAMEOVER;
//						// ��1β���� ��2����
//					}
				} else {
					snake1.addSnakenode();
					if (MySnakeGame.isHaveSound == true) {
						MySnakeGame.gameSound.musicPlay();
					}
					if (snake2.snakeVc.size() != 1) {
						snake2.removeLast();
					}
				}
			}
		}
	}
}
