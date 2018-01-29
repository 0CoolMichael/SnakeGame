package com.snake;

import java.util.Random;

public class Egg {
	public boolean isLive;// ���Ƿ���
	public  int x, y;
	private boolean isknock;// �Ƿ�����λ���ص�
	private Random rd;
	Snake snake1;
	Snake snake2;

	public Egg(Snake snake1, Snake snake2) {
		this.snake1 = snake1;
		this.snake2 = snake2;
		rd = new Random();
		isLive = false;
		isknock = true;
	}

	// ������ɵ���λ��
	public void setRandom() {
		int xi = rd.nextInt(19)+1;
		int yi = rd.nextInt(19)+1;
		x = xi * 30;
		y = yi * 30;
		// �������λ�ú�����ǽ�� �ϰ��ص� �����������λ��
		while (isKnock()) {
			xi = rd.nextInt(19)+1;
			yi = rd.nextInt(19)+1;
			x = xi * 30;
			y = yi * 30;
		}
		isLive=true;

	}

	// ����λ���ж��Ƿ�������ص�
	private boolean isKnock() {
		for (int i = 0; i < snake1.snakeVc.size(); i++) { // �����ߵ�XY
			int x0 = snake1.snakeVc.get(i).getX();
			int y0 = snake1.snakeVc.get(i).getY();
			// �ص�
			if (x + 21 > x0 && x < x0 + 30 && y + 30 > y0 && y < y0 + 30) {
				isknock = true;
				return true;
			}
		}
		if (GamePanel.gameMode == 2) {
			for (int i = 0; i < snake2.snakeVc.size(); i++) { // �����ߵ�XY
				int x0 = snake2.snakeVc.get(i).getX();
				int y0 = snake2.snakeVc.get(i).getY();
				// �ص�
				if (x + 21 > x0 && x < x0 + 30 && y + 30 > y0 && y < y0 + 30) {
					isknock = true;
					return true;
				}
			}
		}
		// ��ǽ�ص�
			if (x + 21 > 570 || x < 30 || y + 30 > 570 || y < 30) {
				isknock = true;
				return true;
			}
		// ���ϰ��ص�
		if (GamePanel.classes == 3)
			if (x + 21 > 150 && x < 180 && y + 30 > 150 && y < 420
					|| x + 21 > 450 && x < 480 && y + 30 > 150 && y < 420) {
				isknock = true;
				return true;
			}
		// ���ص�
		isknock = false;
		return false;

	}

	// �жϵ��Ƿ񱻳���
	public boolean haveEgg() {
		for (int i = 0; i < snake1.snakeVc.size(); i++) { // �����ߵ�XY
			int x0 = snake1.snakeVc.get(i).getX();
			int y0 = snake1.snakeVc.get(i).getY();
			// ��ײ ���򵰲���� ������
			if (x + 21 > x0 && x < x0 + 30 && y + 30 > y0 && y < y0 + 30) {
				isLive = false;
				snake1.isEategg=true;
				return false;
			}
		}
		if (GamePanel.gameMode == 2) {
			for (int i = 0; i < snake2.snakeVc.size(); i++) { // �����ߵ�XY
				int x0 = snake2.snakeVc.get(i).getX();
				int y0 = snake2.snakeVc.get(i).getY();
				// ��ײ ���򵰲���� ������
				if (x + 21 > x0 && x < x0 + 30 && y + 30 > y0 && y < y0 + 30) {
					isLive = false;
					snake2.isEategg=true;
					return false;
				}

			}
		}
		// ������Ȼ���
		isLive = true;
		return true;

	}
}
