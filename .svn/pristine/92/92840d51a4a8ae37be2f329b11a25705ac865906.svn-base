/**
 * 
 */
$(function(){
    function setCookie(c_name,value,expiredays)
    {
        var exdate=new Date();
        exdate.setDate(exdate.getDate()+expiredays);
        document.cookie=c_name+ "=" +escape(value)+
            ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
    }
    var $password = $('#password');
    var $username = $('#username');
    var $button = $('#button');
    var $form = $('form');
    var $loginMessage = $('.login-message');
    var $capId = $('#validateCode');

    $password.keydown(function(event){
        if(event.keyCode == 13) {
        	$button.trigger('click');
        }
    });

    $username.keydown(function (event) {
        if (event.keyCode == 13) {
        	$button.trigger('click');
        }
    });

    var loginUrl = Config.root + "/login.do";

    
    $button.click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        var username = $username.val();
        var password = $password.val();
        var capId = $capId.val();
        if (!username) {
            showMessage('info-circle', '用户名不能为空');
            //$username.focus();
            return;
        }
        if (!password) {
            showMessage('info-circle', '密码不能为空');
            //$password.focus();
            return;
        }
        if (!capId) {
            showMessage('info-circle', '验证码不能为空');
            //$password.focus();
            return;
        }
        $button.prop('disabled', true);
        $button.val("正在验证···");
        var params = $("#loginForm").serialize();
        postData(loginUrl, params, true, 
        	function(result){
	        	if(result.success){
	        		location.href = Config.root + "/main.html";
	        		return;
				}else{
					var $verify=$("#verify");
					$verify.attr("src",Config.root+"/verify?c=login&t="+new Date().getTime());
					var message = result.message;
					showMessage('info-circle', message);
					$button.prop('disabled', false);
				}
        	},
        	function (XMLHttpRequest, textStatus, errorThrown){
        		$button.val("登录");
        		$button.prop('disabled', false);
        	}
        );
    });
   
    /*
    function showMessage(type, content) {
        $loginMessage.find(".error-icon").addClass("fa fa-" + type);
        $loginMessage.find(".error-info").html(content);
    }*/
});
