//登陆画面各种图片设置
$().ready(function(){
    //定位验证码图片框
    var oVCode = $("#sys0101S01Form_validateCd");
    var pos = getAbsPosition(oVCode);
    var left = pos.left;
    var top = pos.top + oVCode[0].offsetHeight + 2;
    $("div.validateCode").css("top", top).css("left", left);
    
    //设置验证码图片框显隐
    showHideVcodeImg();
    
    //绑定验证码图片框单击事件实现更换验证码
    $("div.validateCode").click(function(event) {
        changeCode();
        event.stopPropagation();
    });
});

//设置验证码图片框显隐
function showHideVcodeImg() {
    $("#sys0101S01Form_userId,#sys0101S01Form_pwd ,#loginEventBtn").click(function(){
        $("div.validateCode").css("display", "none");
    }).focus(function() {
        $("div.validateCode").css("display", "none");
    });
    
    $("#sys0101S01Form_validateCd").click(function(event) {
        $("div.validateCode").css("display", "block");
        event.stopPropagation();
    }).focus(function() {
        $("div.validateCode").css("display", "block");
    });
    
    $(document).click(function() {
        $("div.validateCode").css("display", "none");
    });
}

//获得对象绝对坐标位置
function getAbsPosition(object) {
    object = $(object);
    if (object.length == 0) {
        return false;
    }
    object = object[0];
    var left, top;
    left = object.offsetLeft;
    top = object.offsetTop;
    while (object = object.offsetParent) {
        left += object.offsetLeft;
        top += object.offsetTop;
    }
    return {
        left: left,
        top: top
    };
}


//更新验证码图片
function changeCode() {
    $("#imgValidateCode").attr("src", "./servlet/getValidateImageCode?time=" + new Date().getTime());
}
