package com.snake;

import java.applet.AudioClip; 

import java.io.*; 
import java.applet.Applet;  
import java.net.MalformedURLException; 
import java.net.URL; 


public class Music  { 
	
private  AudioClip aau; 
@SuppressWarnings("deprecation")

public  void musicstart(String musicFile){
	try { 
		 URL cb; 
		 File f = new File(musicFile); //�������ļ�
		 cb = f.toURL(); 
		 aau = Applet.newAudioClip(cb); 
       }catch (MalformedURLException e) { 
	         e.printStackTrace(); 
	 }
}
	//ѭ������
	public void musicLoopplay(){
		aau.loop();
	}
	
	public void musicPlay(){
		aau.play();//���� aau.play()				
	}
	
	public  void musicStop(){
		
		aau.stop();
	}


}
