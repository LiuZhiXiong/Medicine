<%--
  Created by IntelliJ IDEA.
  User: tangbo
  Date: 2020/9/26
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聚类分析</title>
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <script src="js/jquery-3.4.1.js"></script>

    <link  rel="stylesheet" type="text/css" href="layui/css/layui.css"></link>
    <script src="layui/layui.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <!-- excel转成json -->
    <script src="js/FileSaver.min.js"></script>
    <script src="js/xlsx.full.min.js"></script>
    <script src="js/echarts.min.js"></script>
<script src="js/RelateJSONToExcelConvertor.js"></script>
    <script src="js/fileJs.js"></script>
    <script>
        window.onload=layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 0,
                offset: 'auto',
                title:"聚类分析功能说明",
                skin: 'layui-layer-demo', //样式类名
                closeBtn: 0, //不显示关闭按钮
                anim: 2,
                shadeClose: true, //开启遮罩关闭
                content: '请先选择文件，1.点击药对关联按钮之后，方可输入关联度系数阈值，并获得相应数据表单以及下载表单功能；2.点击核心药物组合，选择药物组合的最小和最大数量方可得到数据结果并下载表单数据'
            });
        })
    </script>
    <style>
        .file {
            position: relative;
            display: inline-block;
            background: #31B0D5;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #ffffff;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
    </style>
</head>
<body style="background-color: #F5F5F5">
<!-- 红色的头标题 -->
<div class="title">
    <img src="image/title.jpg" class="image" width="100%" />
</div>

<div class="layui-input-block" style="text-align:center;width: 80%;margin-top: 30px">
    <button class="layui-btn layui-btn-normal" style=" background-color: #31B0D5;" id="flusterAnalysisFunctionDeclaration"> 功能使用说明</button>

    <div class="layui-input-inline" style=" border-radius: 4px;background-color: #31B0D5;">
        <a href="javascript:;" class="file layui-btn layui-btn-normal" id="fileUp"><i class="layui-icon" id="selectFile">&#xe67c;</i> 选择文件
            <input type="file" name="" id="inputS">
        </a>
</div>
    <button type="button" class="layui-btn" style=" background-color: #31B0D5;margin-left: 100px" ><a href="FlusterAnalysisRelate.jsp">获取药对关联度</a></button>
    <button type="button" class="layui-btn" style=" background-color: #31B0D5; margin-left: 100px"><a href="FlusterAnalysisCombine.jsp">获取核心药物组合</a></button>
<%--    <button type="button" class="layui-btn" style=" background-color: #31B0D5; margin-left: 100px" onclick="importfileS()">测试</button>--%>
</div>
    <div style="position: relative;margin-top: 30px;">
    <iframe id="iframeMain" src="" style="width: 100%; height: 500px" frameborder="0" scrolling="auto">

    </iframe>
</div>

<script>
    inputS.onchange = function () {
        importfile(inputS)
    }
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    });
    $(document).ready(function(){
        $("a").click(function (e) {
            if($(this).attr('id')!="fileUp"){
                e.preventDefault();
                $("#iframeMain").attr("src",$(this).attr("href"));
            }
        });

    });

    // 功能使用说明页面跳转函数
    $("#flusterAnalysisFunctionDeclaration").on('click',function () {
        window.location.href="flusterAnalysisFunctionDeclaration.html";
    })


</script>
</body>
</html>
