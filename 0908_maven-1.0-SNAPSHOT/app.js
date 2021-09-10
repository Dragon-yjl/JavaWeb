function load() {
    alert("load OK")

}
$(function () {//jquery的方式：body加载完之后执行的代码
    alert("load OK")
    //下面发起一个网络请求
    //jquery的ajax方法，异步的方式发起http网络请求
    //方法的传入参数，是json格式对象

    let data = {
        username: "abc",
        password: "123"
    };

    $.ajax({//里面的内容以键值对形式出现
        type: "POST",//请求方法
        url: "some.php",//请求路径
        contentType: "application/json",//请求的数据类型，(默认:"application/x-www/form-urlencoded")
        data: JSON.stringify(data),//请求数据,如果数据类型是json，需要将json对象转换为字符串
        success: function (msg) {
            alert("Data Saved:" + msg)

        },error: function (XMLHttpRequest, textStatus, errorThrown) {
            //通常textStatus 和 errorThrown 之中
            //只有一个会包含信息
            this; //调用本次AJAX请求时传递的option参数

        }

    });
});