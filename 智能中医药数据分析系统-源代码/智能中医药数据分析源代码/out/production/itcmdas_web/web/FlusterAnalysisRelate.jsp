<%--
  Created by IntelliJ IDEA.
  User: 唐波
  Date: 2020/10/8
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FlusterAnalysisRelate</title>
    <script src="js/jquery-3.4.1.js"></script>

    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"></link>
    <script src="layui/layui.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <!-- excel转成json -->
    <script src="js/FileSaver.min.js"></script>
    <script src="js/xlsx.full.min.js"></script>
    <script src="js/echarts.min.js"></script>
    <script src="js/RelateJSONToExcelConvertor.js"></script>
    <script src="js/fileJs.js"></script>
    <%--    美化alert--%>
    <link  rel="stylesheet" type="text/css" href="css/showBo.css"></link>
    <script src="js/showBo.js"></script>
    <style>
        .EchartsGraphOne {
            position: relative;
            margin-left: 30%;
            margin-top: 50px;
        }

        .EchartsGraphTwo {
            position: relative;
            margin-left: 15%;
            margin-top: 50px;
        }
        textarea {
            vertical-align: top;

            border: 0;
            border-radius: 5px;
            background-color: rgba(241, 241, 241, .98);
            width: 355px;
            height: 130px;
            padding: 10px;
            resize: none;
        }

    </style>
</head>
<body style="background-color: #F5F5F5">

<div class="layui-fluid" style="background-color: #F5F5F5">

    <div class="layui-row">
        <div style="display: flex ;flex-direction: row">

            <label style="font-size: medium;margin: 10px">请输入药对关联度系数阈值：</label><input style="border-radius: 30px; padding: 10px" name="yuZhi"  value="0.4" id="minCorrelationCoefficient">
            <button type="button" class="layui-btn" id="" style=" background-color: #31B0D5;margin-left: 205px;width: 150px"
                    onclick="excel_to_json()">确定</button>
            <button type="button" class="layui-btn" style=" background-color: #31B0D5;margin-left: 100px ;width: 150px"
                    onclick="downloadRelateFile()">下载药对关联度
            </button>
        </div>
    </div>
<%--    -------%>
    <div class="layui-row">
        <div class="layui-col-md5" style="width:500px; height:450px;border: #0f0f0f 1px solid;background-color: #FFFFFF" >
            <div id="tablediv" >
            </div>
        </div>
        <div class="layui-col-md4" style="border: #0f0f0f 1px solid ;background-color: #FFFFFF; height:450px;">
            <div id="main" style="width:500px; height:500px;float: left"></div>
        </div>
        <div class="layui-col-md3" style="width:450px; height:450px; margin-top: 5px;    border: #0f0f0f 1px solid ;background-color: #FFFFFF">
            <div id="description" style="float: left;margin-left: 60px;"></div>
        </div>
    </div>




</div>


</body>
</html>
