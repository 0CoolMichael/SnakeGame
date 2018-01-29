package com.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;
/*
 * ��������*/
public class SnakeNode {
	private int x,y;
	private Direction dir;
	//public Vector<Integer> passedTurn;//�洢�Ѿ�����ת��
	public Vector<Point> turnXy;//�洢ת���x��y
	public Vector<Direction> turnDir;//�洢ת��ķ���
	private int size=SnakeContext.SNAKE_SIZE;
	
	public SnakeNode(int x,int y){
		this.x=x;
		this.y=y;
		//�洢ת���x��y
		turnXy=new Vector<Point>();
		//�洢ת��ķ���
		turnDir=new Vector<Direction>();
	}
	
	//��������
	public void draw(Graphics g){
		g.fill3DRect(x, y, size, size, false);
	}
		
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	public Direction getDir() {
		return dir;
	}
}
