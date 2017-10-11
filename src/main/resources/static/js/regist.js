function regist(){
    var user = {
        userName : "刘涛",
            password : "123456",
            sysUserName : "lt",
            mobilePhone : "1874954831",
            email : "tao.8384@qq.com"
    };
    $.ajax({
        url : "http://localhost:8090/auth/register",
        contentType : "application/json;",
        method:"post",
        type : "json",
        data : JSON.stringify(user),
        success : function(data){
           console.log(data);
        }
    });
}