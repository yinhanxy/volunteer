package com.topstar.volunteer.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.topstar.volunteer.common.Constant;
import com.topstar.volunteer.util.VerifyCodeUtils;

/**  
 * 类说明：生成验证码
 */
public class VerifyServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        
        //验证码类型
        String codeType = request.getParameter("c");
        
        String verifyName = "";
        if(StringUtils.isBlank(codeType)){
        	verifyName ="ERROR";
        }else{
        	verifyName =  Constant.VERIFY_NAME + "_"+ codeType;
        }
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        session.setAttribute(verifyName, verifyCode.toLowerCase());
        //生成图片
        int w = 136, h = 41;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}
