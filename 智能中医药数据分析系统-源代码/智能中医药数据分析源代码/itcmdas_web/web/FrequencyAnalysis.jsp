<%--
  Created by IntelliJ IDEA.
  User: 孟涛
  Date: 2020/9/23
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>频次分析</title>
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <!-- excel转成json -->
    <script src="js/FileSaver.min.js"></script>
    <script src="js/xlsx.full.min.js"></script>

    <link rel="stylesheet" href="css/fraquencyAnalysis.css">
    <script src="js/jsfrequency.js"></script>
    <%--    导入echarts--%>
    <script src="js/echarts.min.js"></script>

    <script type="text/javascript" src="js/JSONToExcelConvertor.js"></script>
    <script type="text/javascript" src="js/RelateJSONToExcelConvertor.js"></script>
    <script type="text/javascript" src="js/selectVal.js"></script>
    <%--    <script type="text/javascript" src="js/fileJs.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"></link>
    <script src="layui/layui.js"></script>
    <script src="js/exampleJs.js"></script>
    <%--    美化alert--%>
    <link rel="stylesheet" type="text/css" href="css/showBo.css"></link>
    <script src="js/showBo.js"></script>

    <%--改变table表格的每一项高度--%>
    <style>
        .layui-table-cell {
            height: 50px;
            line-height: 25px;
        }
    </style>

    <%--改为手动输入--%>
    <script>
        $.fn.editable = function (config) {

            $(this).each(function (i, t) {

                $(t).change(function () {
                    var me = $(this);
                    me.find('.customval').remove();
                    if (-1 == me.val()) {
                        var ed = $("<input type=\"text\" class=\"form-control\" style=\"width: 90px;\" />");
                        me.after(ed).hide();
                        ed.blur(function () {
                            var v = ed.val();
                            if (null === v || v.length == 0) {
                                ed.remove();
                                me.val(null).show();
                            } else {
                                me.append("<option value=\"" + v + "\" class=\"customval\" selected>" + v + "</option>").show();
                                ed.remove();
                            }
                        }).focus();
                    }
                })
            });
        }

        $(document).ready(function (e) {
            $(".frequencySelect").editable(e);
        });


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
<body style="background: #F5F5F5 ">
<div class="layui-fluid">
    <!-- 红色的头标题 -->
    <div class="title">
        <img src="image/title.jpg" class="image" width="100%"/>
    </div>
    <div class="layui-row" style="background: #FFFFFF;  border: #0f0f0f 1px solid;height: 120px;">

        <%--&lt;%&ndash;    右侧一些按钮&ndash;%&gt;--%>
        <div class="layui-col-md12" style="height: 130px; margin-top:2%;">
            <%--            右侧需要布局的--%>
            <div style="display: flex;flex-direction: row ;margin-left: 50px">
                <button class="layui-btn layui-btn-normal" id="description"
                        style="margin-left: 150px;background-color: #31B0D5;">
                    功能使用说明
                </button>
                <%--            文件上传--%>
                <div class="layui-input-inline"
                     style="border-radius: 30px; background-color: #31B0D5;margin-left: 20px;width: 50px ;height: 30px">
                    <a href="javascript:;" class="file layui-btn layui-btn-normal" id="fileUp"><i class="layui-icon"
                                                                                                  id="selectFile">&#xe67c;</i>
                        选择文件
                        <input type="file" name="" id="input">
                    </a>
                </div>

                <div style="margin-left: 290px">
                    <%--药对个数--%>
                    <label style="font-size: medium;">药物数：</label>
                    <select name="select" id="select" class="select"
                            style="border-radius: 30px; padding: 5px; height: 40px;width:120px ">
                        <option value="1">单味药</option>
                        <option value="2">药对</option>
                        <option value="3">三元组</option>

                    </select>
                </div>


                <div style="margin-left: 50px;">
                    <%--  数据量--%>
                    <label style="font-size: medium;">降序排序显示前n：</label>
                    <select name="frequencySelect" id="frequencySelect" class="frequencySelect"
                            style="border-radius: 30px; width: 120px; height:40px;padding: 5px;">
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="-1">手动输入</option>
                    </select>
                </div>
                <div style="margin-left: 50px">
                    <button class="layui-btn layui-btn-normal" id="submitFre" style="background-color: #31B0D5;">
                        确定
                    </button>
                    <button type="button" class="layui-btn layui-btn-normal"
                            style="margin-left:45px;background-color: #31B0D5;"
                            onclick="downloadFile()">下载
                    </button>
                </div>

            </div>
            <%--            <hr class="layui-bg-black">--%>

            <%--右侧下面部分--%>
            <%--                <div style="margin-left: 14%;display: flex;margin-top: 1px ;direction: revert  ">--%>
            <%--                    <label style="font-size: medium;margin-top: 5px;">样例处方:</label>--%>
            <%--                    <button type="button" class="layui-btn layui-btn-normal" id="submitExample1"--%>
            <%--                            style="margin-left: 70px">妇科病证--%>
            <%--                    </button>--%>

            <%--                    <button type="button" class="layui-btn layui-btn-normal" style="margin-left: 300px"--%>
            <%--                            id="submitExample2">肺系处方1--%>
            <%--                    </button>--%>

            <%--                    <button type="button" class="layui-btn layui-btn-normal" style="margin-left: 230px"--%>
            <%--                            id="submitExample3">肺系处方2--%>
            <%--                    </button>--%>
            <%--                    <button type="button" class="layui-btn layui-btn-normal" style="margin-left:45px"--%>
            <%--                            onclick="downloadFile()">下载--%>
            <%--                    </button>--%>
            <%--                </div>--%>

        </div>

    </div>


    <%--图表显示--%>
    <div class="layui-row">
        <div class="layui-col-md6" style="height: 680px;background: #FFFFFF;">
            <%--显示echarts图标--%>
            <div class="layui-card">
                <div class="layui-card-body" id="echarts_bar" style="width:98%;height:700px;margin: 0 auto;">

                </div>

            </div>

        </div>
        <div class="layui-col-md6" style="height:  700px;background: #FFFFFF;">
            <div class="layui-card" id="divtable">

            </div>
        </div>
    </div>
</div>
<script type="text/html" id="xuhao">
    {{d.LAY_TABLE_INDEX+1}}
</script>

</body>

<script>

    $(document).ready(function () {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                type: 0,
                offset: 'auto',
                title: "频次分析",
                skin: 'layui-layer-demo', //样式类名
                closeBtn: 0, //不显示关闭按钮
                anim: 2,
                shadeClose: true, //开启遮罩关闭
                content: '请先选择文件，并输入药物数以及排序显示前n个。点击确认按钮之后方可得到药物排名以及数据表单，同时可以下载得到excel表格\n'

            });
        });

        var myChart = echarts.init(document.getElementById('echarts_bar'));

        //确定按钮实现
        $("#submitFre").on('click', function () {
            //获取文件数据
            // excel_to_json();
            const selectValue = $('#select').val();
            const frequencySelect = $('#frequencySelect').val();
            console.log("确定按钮实现")
            console.log(selectValue + "确定按钮实现" + frequencySelect)
            console.log(data)
            medicine = []

            for (var i = 0; i < data.length; i++) {
                ids.push(data[i].编号)
                medicine.push(data[i].处方)
            }
            // 异步加载数据
            $.ajax({
                url: "FrequencyAnalysisServlet?method=saveDataToSession",
                type: "post",
                traditional: true,//如果需要传入数组，一定将此值赋值true！！
                datatype: "json",
                data: {
                    "sortedNum": selectValue,
                    "medicineNum": frequencySelect,
                    "medicineStrFre": medicine
                },
                //-----------------------------------
                success: function () {

                    getFrenLayUIData();
                    echartsFre(myChart);
                    // 异步加载数据
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    //传入失败时的操作
                }
            });
        });

        //妇科病症样例实现
        $("#submitExample1").on('click', function () {
            flag = 1
            //判断药对
            select_val();
            //判断数据量是否选择
            select_num();

            const selectValue = $('#select').val();
            const frequencySelect = $('#frequencySelect').val();


            if (selectValue === "defalut" || frequencySelect === "defalut") {
                return;
            } else {
                // 异步加载数据
                $.ajax({
                    url: "FrequencyAnalysisServlet?method=saveDataToSession",
                    type: "post",
                    traditional: true,//如果需要传入数组，一定将此值赋值true！！
                    datatype: "json",
                    data: {
                        "sortedNum": selectValue,
                        "medicineNum": frequencySelect,
                        "medicineStrFre": medicines1
                    },
                    //-----------------------------------
                    success: function () {
                        getFrenLayUIData();

                        echartsFre(myChart);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        //传入失败时的操作
                    }
                });
            }
        });

        //肺系处方1样例实现
        $("#submitExample2").on('click', function () {
            flag = 2
            //判断药对
            select_val();
            //判断数据量是否选择
            select_num();

            const selectValue = $('#select').val();
            const frequencySelect = $('#frequencySelect').val();

            if (selectValue === "defalut" || frequencySelect === "defalut") {
                return;
            } else {
                // 异步加载数据
                $.ajax({
                    url: "FrequencyAnalysisServlet?method=saveDataToSession",
                    type: "post",
                    traditional: true,//如果需要传入数组，一定将此值赋值true！！
                    datatype: "json",
                    data: {
                        "sortedNum": selectValue,
                        "medicineNum": frequencySelect,
                        "medicineStrFre": medicines2
                    },
                    //-----------------------------------
                    success: function () {
                        getFrenLayUIData();

                        echartsFre(myChart);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        //传入失败时的操作
                    }
                });
            }
        });

        //肺系处方2样例实现
        $("#submitExample3").on('click', function () {
            flag = 3
            //判断药对
            select_val();
            //判断数据量是否选择
            select_num();

            const selectValue = $('#select').val();
            const frequencySelect = $('#frequencySelect').val();

            if (selectValue === "defalut" || frequencySelect === "defalut") {
                return;
            } else {

                // 异步加载数据
                $.ajax({
                    url: "FrequencyAnalysisServlet?method=saveDataToSession",
                    type: "post",
                    traditional: true,//如果需要传入数组，一定将此值赋值true！！
                    datatype: "json",
                    data: {
                        "sortedNum": selectValue,
                        "medicineNum": frequencySelect,
                        "medicineStrFre": medicines3
                    },
                    //-----------------------------------
                    success: function () {
                        getFrenLayUIData();

                        echartsFre(myChart);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        //传入失败时的操作
                    }
                });
            }
        });
    });
    //功能介绍
    $("#description").on('click', function () {
        functionDescription()

    })

    input.onchange = function () {
        importfile(input)

    }


</script>
<style>
    .layui-table-cell {
        height: 48px;
        line-height: 35px;
    }
</style>
</html>
