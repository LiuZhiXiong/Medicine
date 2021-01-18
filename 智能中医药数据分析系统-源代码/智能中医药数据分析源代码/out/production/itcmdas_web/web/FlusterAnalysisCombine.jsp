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
    <title>FlusterAnalysisCombine</title>
    <script src="js/jquery-3.4.1.js"></script>

    <link  rel="stylesheet" type="text/css" href="layui/css/layui.css"></link>
    <script src="layui/layui.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <!-- excel转成json -->
    <script src="js/FileSaver.min.js"></script>
    <script src="js/xlsx.full.min.js"></script>
    <script src="js/echarts.min.js"></script>

    <script src="js/fileJs.js"></script>
    <%--    美化alert--%>
    <link  rel="stylesheet" type="text/css" href="css/alertstyle.css"></link>
    <script src="js/ui.js"></script>
</head>
<body>
<%--药物核心表--%>
<div id="Combination" class="layui-input-block" style="position:relative;text-align:center;width: fit-content;margin-top: 20px">
    <label style="font-size: medium;margin: 10px">请输入药物组合的药物最小数量：</label>
    <select name="MinNums" id="MinId" lay-verify="" style="border-radius: 30px; margin-left: 5px;padding: 10px">
        <option value="">请选择药对组最小数量</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
    </select>
    <label style="font-size: medium;margin: 10px">最大数量：</label>
    <select name="MaxNums" id="MaxId" lay-verify="" style="border-radius: 30px; margin-left: 5px;padding: 10px">
        <option value="">请选择药对组最大数量</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
    </select>
    <button type="button" class="layui-btn" style=" background-color: #31B0D5;margin-left: 10px" onclick="excel_to_jsonCore()">确定</button>
    <button type="button" class="layui-btn" style=" background-color: #31B0D5;margin-left: 10px" onclick="downloadCombineFile()">下载核心药物组合表</button>
</div>

<script type="text/html" id="xuhao">
    {{d.LAY_TABLE_INDEX+1}}
</script>

<div style=" margin-left: 30%;margin-top: 20px;width: fit-content">
    <div id="combinediv">
        <table id="demo2" lay-filter="test"></table>
    </div>
    <script>
        var limitcount = 10;
        var curnum = 1;
        function getFormLimitData(productGroupId,curnum,count) {
            layui.use('table',function () {
                var table = layui.table;
                var laypage = layui.laypage;
                //第一个实例
                table.render({
                    elem: '#demo2'
                    ,height: 312
                    ,url: 'FlusterAnalysis?method=coreMedicineListsData' //数据接口
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'id',title: 'ID', width:80, sort: true, fixed: 'left'}
                        ,{field: 'coreMedicineList', title: '核心药物组合', width:500}
                    ]]
                });

            });
        }
        function LayUICombine(){
            layui.use('table', function () {
                var table = layui.table;
                var laypage = layui.laypage;
                //第一个实例
                //第一个实例
                table.render({
                    elem: '#demo2'
                    ,height: 500
                    ,url: 'CoreMedicineListServlet?method=coreMedicineListsData' //数据接口
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {field: 'id',title: 'ID', width:80, sort: true, fixed: 'left'}
                        ,{field: 'coreMedicineList', title: '核心药物组合', width:500}
                    ]]
                });
            });

        }

    </script>
</div>

</body>
<%--改变table表格的每一项高度--%>
<style>
    .layui-table-cell{
        height:40px;
        line-height: 31.5px;
    }
    .layui-table-cell {
        height:auto;
    }

</style>
</html>
