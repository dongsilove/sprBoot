package com.mindone.sms;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindone.dbtable.TableRepository;


@Controller
public class SmsController {

	@Autowired
	TableRepository tableRepository;
	
	@RequestMapping("/sms")
	public String tablelayout(ModelMap model,HttpServletResponse response) throws Exception {
		return "sms";
	}
	
	@RequestMapping("/sendSms")
	public void sendSms(@RequestParam Map<String,Object> paraMap,ModelMap model,HttpServletResponse response) throws Exception {
		String phoneNumber = (String)paraMap.get("phoneNumber");
		String msg = (String)paraMap.get("msg");
		paraMap.forEach((k,v)->System.out.println("key : "+k+" value : " + v));
		com.mindone.okch.common.utils.SmsSendClient.sendSms(phoneNumber,msg);
	}
	

    
}
