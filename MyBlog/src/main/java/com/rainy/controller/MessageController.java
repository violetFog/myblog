package com.rainy.controller;

import com.rainy.model.Message;
import com.rainy.repository.MessageRepository;
import com.rainy.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/7/20.
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/write",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> writeMessage(HttpServletRequest request){
        String content = StringUtil.null2Str(request.getParameter("content"));
        String ip = StringUtil.null2Str(request.getParameter("ip"));
        Map<String,Object> result = new HashMap<>();
        if(StringUtil.isNullStr(content)){
            result.put("error","内容为空");
            return result;
        }
        Message message = new Message();
        message.setIP(ip);
        message.setContent(content);
        Message newMessage = messageRepository.saveAndFlush(message);
        result.put("newMessage",newMessage);
        return result;
    }

    @RequestMapping(value = "/showMessage",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> showMessage(){
        List<Message> messageList = messageRepository.findAll();
        long count = messageRepository.count();
        Map<String,Object> result = new HashMap<>();
        result.put("messages",messageList);
        result.put("count",count);
        return result;
    }
}
