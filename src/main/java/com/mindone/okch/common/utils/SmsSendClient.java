package com.mindone.okch.common.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("smsSendClient")
public class SmsSendClient {
	
	@Value("${Globals.smsServerIp}")
	private static String SMS_SERVER_IP;
	@Value("${Globals.smsServerPort}")
	private static int SMS_SERVER_PORT;
	
    @Value("${Globals.smsServerIp}")
    private void setSmsServerIp(String smsServerIp){
    	SMS_SERVER_IP = smsServerIp;
    }
    
    @Value("${Globals.smsServerPort}")
    private void setSmsServerPort(String smsServerPort){
    	SMS_SERVER_PORT = Integer.parseInt(smsServerPort);
    }
    
    public static String sendSms(String phoneNumber,String msg) {
    	String returnMsg = null;
    	DatagramSocket socket = null;
    	try{     
			socket = new DatagramSocket();
    		InetAddress ia = InetAddress.getByName(SMS_SERVER_IP);
            String str = "CID:"+phoneNumber+",MSG:"+msg;
            
            DatagramPacket sendPacket = new  DatagramPacket(str.getBytes("euc-kr"), str.getBytes("euc-kr").length,ia,SMS_SERVER_PORT);
            
        	socket.send(sendPacket);
            //socket.setSoTimeout(10000);  
            
        	//DatagramPacket receivePacket = new DatagramPacket(str.getBytes(),str.getBytes().length);
            //socket.receive(receivePacket);
            //returnMsg = new String(receivePacket.getData(), 0,receivePacket.getData().length);//CID:01039971416,SMS:OK
    	}catch(SocketTimeoutException e){
    		System.out.println("Timeout reached!!! " + e);
            socket.close();
        }catch(IOException ioe){
            ioe.printStackTrace();          
        }finally {
        	if(socket != null)
        		socket.close();
		}
    	return returnMsg;
    }
    
    
    public static String sendUserSms(List<HashMap<String, Object>> userList,String msg){
    	String returnMsg = null;
    	try{
    		for(int i=0;i<userList.size();i++){
    			if(userList.get(i) != null){
    				String hp = (String)userList.get(i).get("HP");
    				if(hp!=null&&!hp.equals("")) {
    					returnMsg = sendSms(hp, msg);
    				}
    			}
    		}
    	}catch(Exception ioe){
        }finally {
		}
    	return returnMsg;
	}
}
