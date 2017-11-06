<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2017/6/3
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <%request.setAttribute("ctx", request.getContextPath()); %>
    <head>
        <title>登录</title>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="${ctx}/resources/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" href="${ctx}/resources/css/login.css">

        <script src="${ctx}/resources/js/jquery-3.1.1.min.js"></script>
        <script src="${ctx}/resources/bootstrap/js/bootstrap.js"></script>
        <style type="text/css">
            #code
            {
                float: right;
                height: 34px;
                width: 85px;
                margin: auto 15px;
                font-family:Arial;
                font-style:italic;
                font-weight:bold;
                font-size: 16px;
                border:0;
                letter-spacing:2px;
                color:blue;
            }
        </style>

        <script>

            var code ; //在全局定义验证码
            //产生验证码
             function createCode(){
                code = "";
                var codeLength = 4;//验证码的长度
                var checkCode = document.getElementById("code");
                var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
                    'S','T','U','V','W','X','Y','Z');//随机数
                for(var i = 0; i < codeLength; i++) {//循环操作
                    var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）
                    code += random[index];//根据索引取得随机数加到code上
                }
                checkCode.value = code;//把code值赋给验证码
            }
            //校验验证码

            function validate(){
               var inputCode = document.getElementById("checkCode").value.toUpperCase(); //取得输入的验证码并转化为大写
               if(inputCode.length <= 0) { //若输入的验证码长度为0
                   alert("请输入验证码！"); //则弹出请输入验证码
               }
               else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时
                   alert("验证码输入错误！@_@"); //则弹出验证码输入错误
                   createCode();//刷新验证码
                   document.getElementById("checkCode").value = "";//清空文本框
               }
               else { //输入正确时
                  $("form").submit();
               }
           }
        </script>
    </head>
    <body onload="createCode()" >
            <div class="top">
                <p class="top-head "> 武汉准择科技有限公司</p>
            </div>
            <div class="col-md-4 col-md-offset-4 text-center">

                    <form class="form-horizontal login-form " action="/toLogin.action" method="POST">
                        <h1 class="heading">欢迎登录</h1>
                        <div class="form-group">
                           <div class="label">
                               <label class="w3" for="username">用户名</label>
                           </div>
                          <input class="login-input"  type="text" id="username"  name="username">
                    </div>
                        <div class="form-group">
                            <div class="label">
                                <label class="w2" for="password">密码</label>
                            </div>
                            <input class="login-input"  type="password" id="password"  name="password">
                        </div>
                        <div class="form-group">
                            <div class="label">
                                <label class="w3" for="checkCode">验证码</label>
                            </div>
                            <%--<img class="login-input-check-img" id="captchaImage" src="captcha.form"/>--%>
                            <input type = "button" id="code" onclick="createCode();" >
                            <input class="login-input-check"  type="text" id="checkCode"  name="checkCode">
                        </div>
                        <div class="form-group">
                            <span class="label-warning ">${msg}</span>
                        </div>
                        <div class="form-group">
                            <div class="submit">
                                <input type="button" class="submit-input" value="登录" onclick="validate()">
                            </div>
                            <a href="/toHome.action">返回首页</a>

                        </div>
                        <%--用户名:<input type="text" name="username"><br/>--%>
                        <%--密码:  <input type="password" name="password"><br/>--%>
                        <%--<span class="label label-warning col-lg-offset-2">${msg}</span>--%>
                        <%--<input type="submit" value="登录">--%>
                    </form>

            </div>
        </div>

            <%--<script type="text/javascript">--%>
                <%--var code;//声明一个变量用于存储生成的验证码--%>
                <%--document.getElementById("code").onclick=changeImg;--%>
                <%--function changeImg(){--%>
                    <%--//alert("换图片");--%>
                    <%--var arrays=new Array(--%>
                        <%--'1','2','3','4','5','6','7','8','9','0',--%>
                        <%--'a','b','c','d','e','f','g','h','i','j',--%>
                        <%--'k','l','m','n','o','p','q','r','s','t',--%>
                        <%--'u','v','w','x','y','z',--%>
                        <%--'A','B','C','D','E','F','G','H','I','J',--%>
                        <%--'K','L','M','N','O','P','Q','R','S','T',--%>
                        <%--'U','V','W','X','Y','Z'--%>
                    <%--);--%>
                    <%--code='';//重新初始化验证码--%>
                    <%--//alert(arrays.length);--%>
                    <%--//随机从数组中获取四个元素组成验证码--%>
                    <%--for(var i=0;i<4;i++){--%>
                        <%--//随机获取一个数组的下标--%>
                        <%--var r=parseInt(Math.random()*arrays.length);--%>
                        <%--code+=arrays[r];--%>
                        <%--//alert(arrays[r]);--%>
                    <%--}--%>
                    <%--//alert(code);--%>
                    <%--document.getElementById('code').innerHTML=code;//将验证码写入指定区域--%>
                <%--}--%>

                <%--//效验验证码(表单被提交时触发)--%>
                <%--function check(){--%>
                    <%--//获取用户输入的验证码--%>
                    <%--var input_code=document.getElementById('vcode').value;--%>
                    <%--//alert(input_code+"----"+code);--%>
                    <%--if(input_code.toLowerCase()==code.toLowerCase())--%>
                    <%--{--%>
                        <%--//验证码正确(表单提交)--%>
                        <%--return true;--%>
                    <%--}--%>
                    <%--alert("请输入正确的验证码!");--%>
                    <%--//验证码不正确,表单不允许提交--%>
                    <%--return false;--%>
                <%--}--%>
            <%--</script>--%>
            <%--<script>--%>

                <%--$('#captchaImage').click(function()--%>
                <%--{--%>
                    <%--$('#captchaImage').attr("src", "captcha.form?timestamp=" + (new Date()).valueOf());--%>
                <%--});--%>
            <%--</script>--%>
    </body>
</html>
