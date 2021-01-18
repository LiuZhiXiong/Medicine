<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/17
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>舌诊识别</title>
</head>
<script src="js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
<script src="layui/layui.js"></script>
<script src="js/MD5.js"></script>
<script src="js/showBo.js"></script>
<link rel="stylesheet" type="text/css" href="css/showBo.css"/>
<link rel="stylesheet" type="text/css" href="css/tongueAnalysisCss.css"/>
<script src="js/tongueAnalysisJS.js"></script>

<%--//上传文件--%>

<!-- 引用控制层插件样式 -->
<link rel="stylesheet" href="control/css/zyUpload.css" type="text/css">
<!-- 引用核心层插件 -->
<script type="text/javascript" src="core/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script type="text/javascript" src="control/js/zyUpload.js"></script>
<!-- 引用初始化JS -->
<script type="text/javascript" src="demo.js"></script>
<body>
<!-- 红色的头标题 -->
<div class="title">
    <img src="image/title.jpg" class="image" width="100%"/>
</div>
<div class="title" style="position: absolute;top:13%; left: 37%;margin-top: 20px;">
    <h2 class="tab-h2" style="font-family: 'arial rounded mt bold' ;color: #226FBE;">「 舌 诊 图 像 识 别 」</h2>
    <p style="left: 10px;text-align: center">分析舌头，给出病症并推荐治疗方案</p>
</div>

<div class="layui-fluid " style="margin-top: 100px">
    <div class="layui-row layui-col-space20">
        <div class="layui-col-md7  ">
            <div style=" border: #0f0f0f 1px solid;height: 670px">
                <div id="demo" class="demo" style="margin-left: 20px">
                    <div id="button" style="margin-left: 30px">
                    </div>
                </div>


                <%--图片样例--%>
                <img src="image/tongueImage1.png" style="width: 200px;height: 200px;margin-left: 45px;margin-top: 45px"
                     onclick="exampeOne()">
                <img src="image/tongueImage2.png" style="width: 200px;height: 200px;margin-left: 45px;margin-top: 45px"
                     onclick="exampeTwo()">
                <img src="image/tongueImage2.png" style="width: 200px;height: 200px;margin-left: 45px;margin-top: 45px"
                     onclick="exampeThree()">

            </div>
        </div>
        <div class="layui-col-md5">
            <div style=" border: #0f0f0f 1px solid;height: 650px">
                <div class="layui-tab">
                    <ul class="layui-tab-title">
                        <li onclick="SymptomOn()">症状识别</li>
                        <li onclick="adviceOnclick()"> 药物调养建议</li>
                        <button id="menu" type="button" class="layui-btn layui-btn-normal" style="margin-left: 20px">
                            功能使用说明
                        </button>
                        <button id="sumbit" type="button" class="layui-btn layui-btn-normal" style="margin-left: 150px">
                            结果分析
                        </button>
                    </ul>
                    <div class="layui-tab-content">

                        <div class="layui-tab-item layui-show" id="content1"
                             style="width: 550px;height: 350px;font-size: medium;font-family: STZhongsong">
                            请上传图片点击分析结果获取症状
                        </div>
                        <div class="layui-tab-item" id="content2"
                             style="width: 550px;height: 350px;font-size: medium;font-family: STZhongsong">
                            请上传图片点击分析结果获取药物建议
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<script>

    $("#menu").on('click', function () {

        window.location.href = "TongueAnalysisFuctionDeclaration.html"
    });
    $("#sumbit").on('click', function () {
        console.log("分析结果")
        symptomOnclick();

    });
    //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function () {
        var element = layui.element;

    });

</script>
</html>
