package com.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;
/*
 * ����  ����ʵ���ߵ��ƶ�  ת�� ��*/

//ö�� ������������
enum Direction{UP,DOWN,LEFT,RIGHT}

public class Snake {
	public  Vector<SnakeNode> snakeVc;
	private  Direction dir;//����
	public  Vector<Direction> newdirVc;//�洢���·���
	private boolean moveflag;//�жϸı䷽����Ƿ��ƶ�  �ƶ��˲����ٴθı䷽��
	public  boolean isLive;//�Ƿ��� ����Ϸ�Ƿ����
	public boolean isEategg;//�Ƿ���˵�
	public int life;
	private int theSecond;//�����ж��Ƿ�����2
	private int speed=SnakeContext.SPEED;//һ���ߵĸ����� 
	
	//
	public Snake(){
		snakeVc=new Vector<SnakeNode>();
		newdirVc=new Vector<Direction>();
		dir=Direction.RIGHT;
		moveflag=false;
		isLive=true;
		isEategg=false;
		life=10;
		if(GamePanel.mode==0||GamePanel.mode==1){
		//��ʼ���ߵĽ���
		//����ģʽ  ��˫��ģʽ1  ��ʼ����1
		for(int i=0;i<SnakeContext.SNAKE_LENGTH;i++)
		{
			SnakeNode sn;
			sn=new SnakeNode(SnakeContext.SNAKE1_X-i*SnakeContext.SNAKE_SIZE,SnakeContext.SNAKE1_Y);
			sn.setDir(this.dir);
			snakeVc.add(sn);
			
		}
		}
		//˫��ģʽ ģʽ2 ��ʼ����1
		if(GamePanel.mode==2){
			for(int i=0;i<SnakeContext.SNAKE_MODE2_LENGTH;i++)
			{
				SnakeNode sn;
				sn=new SnakeNode(SnakeContext.SNAKE1_MODE2_X-i*SnakeContext.SNAKE_SIZE,SnakeContext.SNAKE1_MODE2_Y);
				sn.setDir(this.dir);
				snakeVc.add(sn);
				
			}
		}
	}
	//��2
	public Snake(int gameMode){
		this.theSecond=gameMode;
		snakeVc=new Vector<SnakeNode>();
		newdirVc=new Vector<Direction>();
		dir=Direction.LEFT;
		moveflag=false;
		isLive=true;
		isEategg=false;
		life=10;
		if(GamePanel.mode==1){
		//��ʼ���ߵĽ���
		//˫��ģʽ ģʽ1 ��ʼ����2
		for(int i=0;i<SnakeContext.SNAKE_LENGTH;i++)
		{
			SnakeNode sn;
			sn=new SnakeNode(SnakeContext.SNAKE2_X+i*SnakeContext.SNAKE_SIZE,SnakeContext.SNAKE2_Y);
			sn.setDir(this.dir);
			snakeVc.add(sn);
			
		}
		}
		//˫��ģʽ ģʽ2 ��ʼ����2
		if(GamePanel.mode==2) {
			for(int i=0;i<SnakeContext.SNAKE_MODE2_LENGTH;i++)
			{
				SnakeNode sn;
				sn=new SnakeNode(SnakeContext.SNAKE2_MODE2_X+i*SnakeContext.SNAKE_SIZE,SnakeContext.SNAKE2_MODE2_Y);
				sn.setDir(this.dir);
				snakeVc.add(sn);
				
			}
		}
	}
	
	//����
	public void drawSanke(Graphics g){
		
		for(int i=0;i<snakeVc.size();i++)
		{
			if(i==0) {if(theSecond!=2) {g.setColor(Color.red);}
			else {g.setColor(Color.blue);}}
			else {
					g.setColor(Color.green);
					}
			snakeVc.get(i).draw(g);
		}
	}
	

	//�洢�·��� ���ж��Ƿ�Ҫ�ı䷽�� 
	private boolean isChangeDir(Direction newdir){
		if(moveflag==true){
		if(!(newdirVc.size()==0)){
			
			//�෴���� ���ɸı�
			if(!(dir==Direction.UP&&newdirVc.get(0)==Direction.DOWN||
			dir==Direction.DOWN&&newdirVc.get(0)==Direction.UP||
			dir==Direction.RIGHT&&newdirVc.get(0)==Direction.LEFT||
			dir==Direction.LEFT&&newdirVc.get(0)==Direction.RIGHT))
				{
				//�����ڷ�����ڵ�һ���·���
				dir=newdirVc.get(0);
				//�洢��ͷ��XY  ��ת��xy
				Point Xy=new Point(snakeVc.get(0).getX(),snakeVc.get(0).getY());
				//���� ����ģʽ  �����ǽת������
				if(GamePanel.gameMode==1&&GamePanel.classes==1){
					if(Xy.y>540) {
						Xy.y=30;
					}
					else if(Xy.y<30){
						Xy.y=540;
					}
					else if(Xy.x>540){
						Xy.x=30;
					}
					else if(Xy.x<30){
						Xy.x=540;
					}
					}
				
				for(int i=1;i<snakeVc.size();i++){
				//�洢ת���
				snakeVc.get(i).turnXy.add(Xy);
				//�洢ת��ķ���
				snakeVc.get(i).turnDir.add(dir);
				
				}
				return true;
				}
				else newdirVc.remove(newdirVc.size()-1);
				
				}
		}
		return false;
	}
	
	//�ı䷽��
	public void changeDir(Direction newdir){
		if(isChangeDir(newdir)) {
			
			//�ı���ͷ�ķ���
			snakeVc.get(0).setDir(newdirVc.get(0));
			
			//������ת��δ�ƶ�
			moveflag=false;
			//ת�����Ƴ�����
			
			if(!(newdirVc.size()==0))
				newdirVc.remove(0);
		} 
	}
	//�����ͷ
	public  SnakeNode getHead(){
		return snakeVc.get(0);
	}
	//˫��  ��2ģʽʹ��
	//��õڶ���
	public SnakeNode getNode2(){
		return snakeVc.get(1);
	}
	//�Ƴ����һ��
	public void removeLast(){
		snakeVc.remove(snakeVc.size()-1);
	}
	//����ߵĳ���
	public int getsnakeSize(){
		return snakeVc.size();
	}
	
	//���˶�
	public void Snakemove(){
		for(int i=0;i<snakeVc.size();i++){
		int x=snakeVc.get(i).getX();
		int y=snakeVc.get(i).getY();
		//����ת��
		turn(i,x,y);
		
		switch(snakeVc.get(i).getDir()){
		case UP:y-=speed;SnakeNodemove(i,x,y);break;
		case DOWN:y+=speed;SnakeNodemove(i,x,y);break;
		case LEFT:x-=speed;SnakeNodemove(i,x,y);break;
		case RIGHT:x+=speed;SnakeNodemove(i,x,y);break;
		}

		
		}
		//����Ϊת����ѳɹ��ƶ�
		moveflag=true;

	}
	//ÿһ�ڵ������ƶ�
	
	private void SnakeNodemove(int Node,int x,int y){
		
			switch(snakeVc.get(Node).getDir())
		{
		case UP:
	
			snakeVc.get(Node).setX(x);
			snakeVc.get(Node).setY(y);
		
			break;
		case DOWN:
			snakeVc.get(Node).setX(x);
			snakeVc.get(Node).setY(y);
		
			break;
		case LEFT:
			
			snakeVc.get(Node).setX(x);
			snakeVc.get(Node).setY(y);
		
			break;
		case RIGHT:
			snakeVc.get(Node).setX(x);
			snakeVc.get(Node).setY(y);	
			break;
				
		}
		
	}
	
	//ת�䴦��
	private void turn(int Node,int x,int y){
		if(!(Node==0)){
			
 			if(!(snakeVc.get(Node).turnXy.size()==0)){
			if(x==snakeVc.get(Node).turnXy.get(0).getX()&&y==snakeVc.get(Node).turnXy.get(0).getY())
				{
				snakeVc.get(Node).setDir(snakeVc.get(Node).turnDir.get(0));
				
				//ת�������Ƴ�
				snakeVc.get(Node).turnXy.remove(0);
				snakeVc.get(Node).turnDir.remove(0);
	
				}
			}
			
		}
	} 
	
	public void addSnakenode(){
		int x = 0, y = 0;
		// �Ե������ӵ����� ��ʼ���õ����ߵ�����
		// ����Ϊ ���һ���ߵķ���
		Direction dir = snakeVc.get(snakeVc.size() - 1).getDir();
		// ������һ�ڵ����ߵ� δ ת��ķ���� δת��ĵ������
		int dircount = snakeVc.get(snakeVc.size() - 1).turnDir.size();
		int turnxycount = snakeVc.get(snakeVc.size() - 1).turnXy.size();
		// �趨�����ߵ�X��Y
		switch (dir) {
		case UP:
			x = snakeVc.get(snakeVc.size() - 1).getX();
			y = snakeVc.get(snakeVc.size() - 1).getY() + 30;
			// addDir=Direction.UP;
			break;
		case DOWN:
			x = snakeVc.get(snakeVc.size() - 1).getX();
			y = snakeVc.get(snakeVc.size() - 1).getY() - 30;
			// addDir=Direction.DOWN;
			break;
		case LEFT:
			x = snakeVc.get(snakeVc.size() - 1).getX() + 30;
			y = snakeVc.get(snakeVc.size() - 1).getY();
			// addDir=Direction.LEFT;
			break;
		case RIGHT:
			x = snakeVc.get(snakeVc.size() - 1).getX() - 30;
			y = snakeVc.get(snakeVc.size() - 1).getY();
			// addDir=Direction.RIGHT;
			break;

		}
		// �����µĵ�����
		SnakeNode snakenode = new SnakeNode(x, y);
		snakenode.setDir(dir);
		// ������һ�ڵ����� ��δת��� �� �����Щֵ��ֵ�� �����ĵ�������
		if (dircount != 0 && turnxycount != 0) {
			Vector<Direction> turnDir = new Vector<Direction>();
			for (int i = 0; i < dircount; i++) {
				turnDir.add(snakeVc.get(snakeVc.size() - 1).turnDir.get(i));
			}
			Vector<Point> turnXy = new Vector<Point>();
			for (int i = 0; i < turnxycount; i++) {
				turnXy.add(snakeVc.get(snakeVc.size() - 1).turnXy.get(i));
			}

			snakenode.turnDir = turnDir;
			snakenode.turnXy = turnXy;
		}
		snakeVc.add(snakenode);
	}
	
	//��÷���
	public Direction getDirection() {
		return dir;
	}

	//��÷���
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	/*//����·���
	public void setnewDirection(Direction newdir) {
		this.newdir = newdir;
	}*/


	 
}
