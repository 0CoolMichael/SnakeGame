package com.snake;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Filereadwrite {
	//���а�����  
		public String[] name=new String[4];
		String[] score=new String[4];
		private File file;
		private FileReader fr;
		private FileWriter fw;
		private BufferedReader cout;
		private BufferedWriter cin;
		public int num;//�������а������
		//��ȡ���а�
		public void readRank(int classes){
			if(classes==1){
				file=new File("rank/simpleRank.txt");
				if(!file.exists())
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			else if(classes==2){
				file=new File("rank/middleRank.txt");
				if(!file.exists())
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			else if(classes==3){
				file=new File("rank/heightRank.txt");
				if(!file.exists())
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			//���ļ�
			try {
				//���ļ�
				fr=new FileReader(file);
				cout=new BufferedReader(fr);
				int num=1;//����
				int row=1;//����
				while(true){
					String str=cout.readLine();
					if(str==null) {break;}
					if(row%2==0){
						score[num]=str;
						num++;
						row++;
					}
					else {
						name[num]=str;
						row++;
					}
					
				}
				
				
				cout.close();
				fr.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
		} 
		
		//�Ƿ��ܽ������а�
		public boolean isInRank(int scoreNow){
			for(num=1;num<4;num++)
			{	String score0=score[num];
				if(scoreNow>=Integer.parseInt(score0)) {
					if(num==1){
						score[num+2]=score[num+1];
						score[num+1]=score0;
						score[num]=String.valueOf(scoreNow);
						return true;
					}
					else if(num==2){
						score[num+1]=score[num];
						score[num]=String.valueOf(scoreNow);
						return true;
					}
					else if(num==3){
						score[num]=String.valueOf(scoreNow);
						return true;
					}
					
				}
			}
			return false;
		}

		//д�����а�
		public void writeInRank(int classes){
			if(classes==1){
				file=new File("rank/simpleRank.txt");
				if(!file.exists())
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			else if(classes==2){
				file=new File("rank/middleRank.txt");
				if(!file.exists())
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			else if(classes==3){
				file=new File("rank/heightRank.txt");
				if(!file.exists())
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			try {
				fw=new FileWriter(file);
				cin=new BufferedWriter(fw);
				for(int i=1;i<4;i++){
					cin.write(name[i]);
					cin.newLine();
					cin.write(score[i]);
					cin.newLine();
				}
				cin.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
