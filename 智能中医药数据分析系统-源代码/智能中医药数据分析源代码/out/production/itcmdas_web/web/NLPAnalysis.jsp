<%--
  Created by IntelliJ IDEA.
  User: 唐波
  Date: 2020/11/11
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>情感文本分析</title>
    <script src="js/jquery-3.4.1.js"></script>

    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"></link>
    <script src="layui/layui.js"></script>
    <!-- excel转成json -->
    <script src="js/echarts.min.js"></script>

    <%--//美化alert--%>
    <link  rel="stylesheet" type="text/css" href="css/alertstyle.css"></link>
    <script src="js/ui.js"></script>
    <style>
        .NLPOne {
            position: relative;
            margin-left: 30%;
            margin-top: 10%;
        }

        .NLPTwo {
            position: relative;
            margin-left: 10%;
            margin-top: 10%;
        }

        textarea {
            vertical-align: top;
            /*width: 40%;*/
            /*border: none;*/
            /*resize: none;*/
            /*cursor: pointer;*/

            border: 0;
            border-radius: 5px;
            background-color: rgba(241, 241, 241, .98);
            width: 555px;
            height: 270px;
            padding: 10px;
            resize: none;
        }
    </style>
    <script>
        window.onload=layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 0,
                offset: 'auto',
                title:"文本情感分析功能说明",
                skin: 'layui-layer-demo', //样式类名
                closeBtn: 0, //不显示关闭按钮
                anim: 2,
                shadeClose: true, //开启遮罩关闭
                content: '请在文本框内输入一段文字，点击确定后，系统将会给出判断！   ^.^  \n'

            });
        })
    </script>
</head>
<body>
<!-- 红色的头标题 -->
<div class="title">
    <img src="image/title.jpg" class="image" width="100%" />
</div>
<div id="NLPDiv" class=""  style="display: flex;flex-direction: row;margin-top: 50px;">

    <div style="float: left;margin-left: 60px;" >
        <div style="text-align: center">
            <label style="font-size: medium;">请输入文本：</label>
        </div>
        <textarea id="NLPText" class="" style="margin-top: 20px;"></textarea>
    </div>

    <div id="NLPResultDiv" style="margin-left: 60px;text-align: center"></div>
</div>
<div style="text-align:center;">
    <button type="button" class="layui-btn" id="submit" style=" background-color: #31B0D5; width: 200px;margin-top: 20px;">确定  </button>
</div>

<script>
    function firstData() {
        const NLPText=$('#NLPText').val();
        // $("#NLPDiv").attr('class', "layui-input-inline NLPTwo");
        $("#NLPResultDiv").text("");
        $("#NLPResultDiv").append("<div style='text-align: center'><label style=\"font-size: medium;\">分析结果：</label></div>\n" +
            "        <textarea id=\"NLPResult\" style=\"margin-top: 20px;\" readonly></textarea>");
        console.log(NLPText);
        // 异步加载数据
        $.ajax(
            {
                url: "HanlpServlet?method=hanlpResult",
                // url: "AssociationAnalysisServlet?method=saveData&Support="+supportValue+"&Confidence="+ConfidenceValue,
                type: "post",
                // traditional: true,//如果需要传入数组，一定将此值赋值true！！
                datatype: "json",
                data: {
                    "text": NLPText
                },
                //-----------------------------------
                success: function (data) {
                    var NLPResultData = JSON.parse(data);
                    console.log(NLPResultData.result)
                    $('#NLPResult').text("“ "+NLPText+" ” 的情感极性是“ "+NLPResultData.result+" ”的");
                    // 异步加载数据


                },
                error: function (jqXHR, textStatus, errorThrown) {
                    //传入失败时的操作
                }
            });



    }
function test(){
        // $('#submit').trigger("click");
    $('#NLPText').text("示例：你好坏，可我好喜欢");
    }
    $(document).ready(function() {
        //你好坏，可我好喜欢
        $('#NLPText').text("你好坏，可我好喜欢");
        firstData();
        setTimeout("test()","200");


        $("#submit").click(function () {
               firstData()
        })
    });
</script>
</body>
</html>
