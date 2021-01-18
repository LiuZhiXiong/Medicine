<%--
  Created by IntelliJ IDEA.
  User: 唐波
  Date: 2020/10/8
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关联分析</title>
    <script src="js/jquery-3.4.1.js"></script>

    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"></link>
    <script src="layui/layui.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <!-- excel转成json -->
    <script src="js/FileSaver.min.js"></script>
    <script src="js/xlsx.full.min.js"></script>
    <script src="js/echarts.min.js"></script>

<%--    <script src="js/AssociationFileJs.js"></script>--%>
    <script src="js/AssociationJsonToExcelConvertor.js"/>
    <%--//美化alert--%>
    <link rel="stylesheet" type="text/css" href="css/showBo.css"></link>
    <script src="js/showBo.js"></script>
    <style>
        .EchartsGraphOne {
            position: relative;
            margin-left: 5%;
            margin-top: 10%;
        }

        .EchartsGraphTwo {
            position: relative;
            margin-left: 3%;
            margin-top: 10%;
        }
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
        textarea {
            vertical-align: top;
            /*width: 80%;*/
            /*border: none;*/
            /*resize: none;*/
            /*cursor: pointer;*/

            border: 0;
            border-radius: 5px;
            background-color: rgba(241, 241, 241, .98);
            width: 300px;
            height: 160px;
            padding: 10px;
            resize: none;

        }
        #labelColor{
            background-color: rgba(241, 241, 241, .98);
        }
        .EchartsStyle{
            background-color: rgba(241, 241, 241, .98);
        }
    </style>
    <script>
        window.onload=layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 0,
                offset: 'auto',
                title:"关联分析功能说明",
                skin: 'layui-layer-demo', //样式类名
                closeBtn: 0, //不显示关闭按钮
                anim: 2,
                shadeClose: true, //开启遮罩关闭
                content: '请先选择文件，并输入置信度以及支持度。点击确认按钮之后方可得到药物排名以及数据表单，同时可以下载得到excel表格\n'

            });
        })
    </script>
</head>
<body style="height: 500px">
<!-- 红色的头标题 -->
<div class="title">
    <img src="image/title.jpg" class="image" width="100%" />
</div>

<div class="layui-input-block" style="text-align:center;width: 90%;margin-top: 20px">
    <button class="layui-btn layui-btn-normal" style=" background-color: #31B0D5;" id="associationFunctionDescription"> 功能使用说明</button>
    <div class="layui-input-inline" style=" border-radius: 4px;background-color: #31B0D5;">
        <a href="javascript:;" class="file layui-btn layui-btn-normal" id="fileUp"><i class="layui-icon" id="selectFile">&#xe67c;</i> 选择文件
            <input type="file" name="" id="associationInput">
        </a>
    </div>
    <%--    <input value="UNSAVED" autoComplete="Off" type="text" οnfοcus="if(this.s!='t'){this.value='';this.s='t';}" οnblur="if(this.value ==''){this.value='UNSAVED';this.s='';}" />--%>
    <%--    <div class="layui-input-block" style="text-align:center;width: 90%;margin-top: 30px">--%>
    <label style="font-size: medium;margin: 10px">请输入置信度阈值：</label>
    <input style="border-radius: 30px; margin-left: 5px;padding: 10px"  value="0.8" name="Confidence" id="Confidence">
    <label style="font-size: medium;margin: 10px">支持数阈值：</label>
    <input style="border-radius: 30px; margin-left: 5px;padding: 10px" placeholder="请输入非零整数" value="16" name="Support" id="Support">
    <button type="button" class="layui-btn" id="submit" style=" background-color: #31B0D5;margin-left: 20px"
            onclick="assocation_excel_to_json()">确定
    </button>
    <button type="button" class="layui-btn" style=" background-color: #31B0D5;margin-left: 10px"
            onclick="downloadAssociation()">下载
    </button>
    <%--    </div>--%>
</div>

<%--表格--%>
<div class="layui-input-inline " id="EchartsGraph" style="display: flex;flex-direction: row;margin-top: 2%;">
    <div style="position: relative;top:10%;margin-left: 4px; width: 27%;float: left">
        <div>
            <table id="demo" lay-filter="test"></table>
        </div>
        <script>
            // 上传文件之后,调用importfile
            associationInput.onchange = function () {
                console.log("++++")
                importfile(associationInput)


                console.log("----")
                setTimeout("test()","200");
            }
            // $('#')
           function test(){
                assocation_send_json_to_servlet()
               $('#submit').trigger("click");

               $("#description").append("<div style=\"margin-top: 5%\">\n" +
                   "            <label id=\"medicineNameLabel\">药名：</label>\n" +
                   "            <span id=\"medicineName\">百部(示例药物，请点击药物关联规则图节点查看相关药物介绍)</span>\n" +
                   "        </div>\n" +
                   "        <div style=\"margin-top: 8%\">\n" +
                   "            <label id=\"medicineCharacterLabel\">功效：</label>\n" +
                   "            <textarea id=\"medicineCharacter\" readonly=\"\">润肺止咳，杀虫灭虱。属化痰止咳平喘药下分类的止咳平喘药。</textarea>\n" +
                   "        </div>\n" +
                   "        <div style=\"margin-top: 8%\">\n" +
                   "            <label id=\"medicineDescriptionLabel\">性状：</label>\n" +
                   "            <textarea id=\"medicineDescription\" readonly=\"\">纺锤形或长条形，长8～24厘米，直径0.8～2厘米。表面淡黄棕色或灰棕色，具浅纵皱纹或不规则纵槽。质坚实，断面黄白色或暗棕色，中柱较大，中心类白色。</textarea>\n" +
                   "        </div>");

            }

        </script>
        <script type="text/html" id="xuhao">
            {{d.LAY_TABLE_INDEX+1}}
        </script>
        <script type="text/html" id="formMedincine">
            {{d.medicines}}
        </script>


    </div>
    <%--    Echarts--%>
    <div id="main" class="" style="width:45%; height:470px;float: left;margin-left: 4px;margin-top:8px;background-color: rgba(241, 241, 241, .98);"></div>
    <div id="description" style="float: left;margin-left: 2%; margin-top:5px;height:400px;">
    </div>

</div>
    <script type="text/javascript">
        var ids = new Array();
        var medicineStr = new Array();

        var AssociationData;

        var myChart = echarts.init(document.getElementById('main'));
        $(document).ready(function() {


            $("#submit").on('click',function () {
                    const supportValue=$('#Support').val();
                    const ConfidenceValue = $('#Confidence').val();
                    console.log("hdfhiduhiu"+medicineStr);
                    if(examData(ConfidenceValue,supportValue)) {
                        echartsGetMedicine(myChart);
                        // 异步加载数据
                        $.ajax(
                            {
                                url: "AssociationAnalysisServlet?method=saveData",
                                // url: "AssociationAnalysisServlet?method=saveData&Support="+supportValue+"&Confidence="+ConfidenceValue,
                                type: "post",
                                traditional: true,//如果需要传入数组，一定将此值赋值true！！
                                datatype: "json",
                                data: {
                                    "Support": supportValue,
                                    "Confidence": ConfidenceValue,
                                    "medicineStr": medicineStr
                                },
                                //-----------------------------------
                                success: function () {
                                    getLayUIData();

                                    // 异步加载数据
                                    $.ajax({
                                        type: "post",
                                        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                                        url: "AssociationAnalysisServlet?method=echartsData",
                                        success: function (data) {
                                            console.log("sasf");
                                            console.log(data)
                                            var myData = JSON.parse(data).data
                                            relateEchartsData(myData, myChart);
                                            AssociationData = myData;

                                        }
                                    });

                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    //传入失败时的操作
                                }
                            });


                };
            });
        });



        function downloadAssociation() {
            console.log("下载中-------------");
            //自定义标题栏
            var title = ['编号', '药物']
            if (AssociationData == null || AssociationData.length == 0) {
                Showbo.Msg.alert('暂时无数据，不可导出')
                // Showbo.Msg.alert('暂时无数据，不可导出')
                // alert("暂时无数据，不可导出" );
            } else {
                var ConfidenceNum=$("#Confidence").val();
                var SupportNum=$("#Support").val();
                console.log("---"+ConfidenceNum);
                console.log(AssocationName)
                console.log(AssociationData)
                AssociationJSONToExcelConvertor(AssociationData, AssocationName+"关联分析Excel表-置信阈值"+ConfidenceNum+"支持度"+SupportNum, title);
            }
            console.log("******************+++++++*******");

        }
        /////////////////////////////////////////

        /**
         * 导入文件
         * @param obj
         */

        var dataS;
        var wbS;
        var flag="";

        function importfile(obj) {
            console.log( "kkk");
            flag = obj.files[0].name;
            AssocationName=flag
            console.log(flag)
            console.log(AssocationName)
            // 判断是否是excel表单
            if (flag.indexOf(".xls") == -1) {
                // mizhu.alert('文件格式有误','文件错误，请重新选择')
                Showbo.Msg.alert('文件格式有误，文件错误，请重新选择')
                // alert('文件错误，请重新选择');
                return
            } else {
                $('#selectFile').text(flag);
                var reader = new FileReader(),
                    file = obj.files[0];
            }
                reader.onload = function (e) {
                    dataS = e.target.result;

                    wbS = XLSX.read(dataS, {
                        type: 'binary'
                    });

                    dataS = XLSX.utils.sheet_to_json(wbS.Sheets[wbS.SheetNames[0]]) //转成 json 数据
                    console.log(dataS)
                }


            reader.readAsBinaryString(file) //以二进制方式读取




        }


        /**
         *
         * 将excel转为json数据
         */
        function assocation_excel_to_json() {
            if (dataS == undefined) {
                // alert('请上传文件');
                // Showbo.Msg.alert('请上传文件')
                Showbo.Msg.alert('请上传文件')
                // console.log(sel_val)
            } else {
                console.log("-----")
                //console.log(dataS)
                assocation_send_json_to_servlet()
                console.log("++++")

            }
        }


        /**
         * 将excel转为的json发送给后台
         * ids 为编号
         * medicine为药物处方
         * @type {any[]}
         */

        function assocation_send_json_to_servlet() {
            medicineStr = []
            for (var i = 0; i < dataS.length; i++) {
                ids.push(dataS[i].编号)
                //console.log(data[i].编号)
                //  console.log(data[i].处方)
                medicineStr.push(dataS[i].处方)
            }
            console.log(ids)
            console.log(medicineStr)
            console.log("AssocitionFileJs")




        }
        //Echarts数据格式转换函数
        function linksParse(source,target) {
            var o = new Object();
            o.source = source;
            o.target = target;
            return o;
        }

        function namesParse(name) {
            var o = new Object();
            o.name = name;
            o.value=50;
            return o;
        }
        //    分页函数
        function getFormLimitData(productGroupId,curnum,count) {
            layui.use('table',function () {
                var table = layui.table;
                var laypage = layui.laypage;
                //第一个实例
                table.render({
                    elem: '#demo'
                    , height: 450
                    ,type:'post'
                    , url: "AssociationAnalysisServlet?method=formData"//数据接口

                    , page: true //开启分页
                    , cols: [[ //表头
                        {templet: '#xuhao', title: 'ID', width: 80, sort: true, fixed: 'left'}
                        , {templet: '#formMedincine', title: '关联规则', width: 620}
                    ]]
                    ,done: function(data, curr, count){
                        //如果是异步请求数据方式，res即为你接口返回的信息。
                        //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                        laypage.render({
                            elem:'laypage'
                            ,count:count
                            ,curr:curnum
                            ,limit:limitcount
                            ,layout: ['prev', 'page', 'next', 'skip','count','limit']
                            ,jump:function (obj,first) {
                                if(!first){
                                    curnum = obj.curr;
                                    limitcount= obj.limit;
                                    productGroupId=count;
                                    getFormLimitData(productGroupId,curnum,limitcount);
                                }
                            }
                        })
                    }

                });

            });
        }
        //    Echarts药材说明函数
        function echartsGetMedicine(myChart) {
            // 显示标题，图例和空的坐标轴

            myChart.on('click', {dataType: 'node'}, function (params) {
                $("#EchartsGraph").attr('class', "layui-input-inline EchartsGraphTwo");
                $("#description").text("");
                $("#description").append(" <div style=\"margin-top: 5%\">\n" +
                    "            <label id=\"medicineNameLabel\">加载中......</label>\n" +
                    "            <span id=\"medicineName\"></span>\n" +
                    "        </div>\n" +
                    "        <div style=\"margin-top: 8%\">\n" +
                    "            <label id=\"medicineCharacterLabel\"></label>\n" +
                    "            <textarea id=\"medicineCharacter\"  readonly></textarea>\n" +
                    "        </div>\n" +
                    "        <div style=\"margin-top: 8%\">\n" +
                    "            <label id=\"medicineDescriptionLabel\"></label>\n" +
                    "            <textarea id=\"medicineDescription\"  readonly></textarea>\n" +
                    "        </div>");
                $.ajax({
                    type: "post",
                    async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                    url: "medicineServlet?method=medicineDescription&medicine=" + params.name,    //请求发送到TestServlet处
                    success: function (data) {
                        console.log(data);
                        var medicineData = JSON.parse(data).data;
                        $("#medicineNameLabel").text("药名：");
                        $("#medicineName").text(medicineData.medicineName);
                        $("#medicineCharacterLabel").text("功效：");
                        $("#medicineCharacter").text(medicineData.efficacy);
                        $("#medicineDescriptionLabel").text("性状：");
                        $("#medicineDescription").text(medicineData.properties);
                    }
                });
                // $("#description").text(params.name);
                // 控制台打印数据的名称 if(params.dataType === 'node') {
                // console.log(params.name);
            });
        }
        //    layUI表格数据获取函数
        var limitcount = 10;
        var curnum = 1;
        function getLayUIData() {


            layui.use('table', function () {
                var table = layui.table;
                var laypage = layui.laypage;
                //第一个实例
                table.render({
                    elem: '#demo'
                    , height: 500
                    ,type:'post'
                    , url: "AssociationAnalysisServlet?method=formData"//数据接口

                    , page: true //开启分页
                    , cols: [[ //表头
                        {field: 'id', title: 'ID', width: 60, sort: true, fixed: 'left'}
                        , {templet: '#formMedincine', title: '关联规则', width: 300}
                    ]]
                    ,done: function(data, curr, count){
                        //如果是异步请求数据方式，res即为你接口返回的信息。
                        //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                        laypage.render({
                            elem:'laypage'
                            ,count:count
                            ,curr:curnum
                            ,limit:limitcount
                            ,layout: ['prev', 'page', 'next', 'skip','count','limit']
                            ,jump:function (obj,first) {
                                if(!first){
                                    curnum = obj.curr;
                                    limitcount= obj.limit;
                                    productGroupId=count;
                                    getFormLimitData(productGroupId,curnum,limitcount);
                                }
                            }
                        })
                    }

                });

            });
        }
        //Echarts数据关联转换显示函数
        function relateEchartsData(myData,myChart) {
            var name = [];
            var nameArr = new Array();
            var linksArr = new Array();
            for(var i=0;i<myData.length;i++){
                var list1= new Array();
                list1=myData[i].medicine1;
                var list2=new Array();
                list2=myData[i].medicine2;
                var correlationCoefficients=1;
                for(var j=0;j<list1.length;j++){
                    var temp = list1[j];
                    var bigNum1=name.indexOf(temp);
                    if(bigNum1==-1){
                        name.push(temp);
                        nameArr.push(namesParse(temp));
                    }
                    console.log(list2);
                    for(var z=0;z<list2.length;z++){
                        var temp2=list2[z];
                        var bigNum2=name.indexOf(temp2);
                        if(bigNum2==-1){
                            name.push(temp2);
                            nameArr.push(namesParse(temp2));
                        }else {
                            nameArr[bigNum2].value+=2.5;
                        }
                        linksArr.push(linksParse(temp,temp2))
                    }
                }
            }
// alert(JSON.parse(data).data.length);
// console.log("*************************");
// console.log(linksArr);
// console.log("*************************");
            myChart.setOption({
                title: {
                    text: '药物关联规则图'
                },
                // tooltip: {},
                // toolbox: {
                //     show: true,
                //     right:50,
                //     feature: {
                //         saveAsImage: {
                //             show:true,
                //             name:flag+"_药物关联规则图",
                //             type:'png',
                //             title:"保存为图片",
                //             pixelRatio:2
                //         }
                //     }
                // },
                toolbox: {
                    show: true,
                    itemSize: 20,
                    itemGap: 30,
                    right: 10,
                    feature: {
                        dataView: {show:true},
                        saveAsImage: {
                            show:true,
                            //excludeComponents :['toolbox'],
                            pixelRatio: 2
                        }
                    }
                },
                animationDurationUpdate: 1500,
                animationEasingUpdate: 'quinticInOut',
                series: [
                    {
                        type: 'graph',
                        layout: 'force',
                        roam: true,
                        draggable : true,
                        force : {
                            //initLayout: ,//力引导的初始化布局，默认使用xy轴的标点
                            repulsion : 10000,//节点之间的斥力因子。支持数组表达斥力范围，值越大斥力越大。
                            gravity : 2,//节点受到的向中心的引力因子。该值越大节点越往中心点靠拢。
                            edgeLength :95,//边的两个节点之间的距离，这个距离也会受 repulsion。[10, 50] 。值越小则长度越长
                            layoutAnimation : false
                            //因为力引导布局会在多次迭代后才会稳定，这个参数决定是否显示布局的迭代动画，在浏览器端节点数据较多（>100）的时候不建议关闭，布局过程会造成浏览器假死。
                        },
                        label: {
                            show: true,
                            text:"hhh"
                        },
                        symbolSize:(value,params)=>{
                            console.log("=====params:"+params.name+"vlauesss==="+value);
                            return value;

                        },
                        focusNodeAdjacency : true,
                        edgeSymbol: ['circle', 'arrow'],
                        edgeSymbolSize: [4, 10],
                        edgeLabel: {


                        },
                        itemStyle: {//配置节点的颜色已及尺寸
                            // normal: {
                            //     // （1） 直接写一个颜色，这样的结果是所有节点都是同一个颜色
                            //     color: '#000000'
                            // }
                            },
                        data:nameArr,
                        links:linksArr,
                        lineStyle: {
                            opacity: 0.9,
                            width: 2,
                            curveness: 0
                        }
                    }]
            });
        }
        //数据校验
        function examData(confinData,suppData) {
            if(confinData>=1||confinData<0){
                alert("置信度阈值非法，请输入小于1的正整数！");
                return false;
            }else {
                var y = String(suppData).indexOf(".") + 1;//获取小数点的位置
                console.log("小数点"+y);
                // var count = String(confinData).length - y;//获取小数点后的个数
                if(y > 0||suppData<0) {
                    alert("支持度阈值非法，请输入正整数！");
                    return false;
                }
            }
            return true;
        }


        // 功能使用说明页面跳转函数
        $("#associationFunctionDescription").on('click',function () {
            window.location.href="associationFunctionDescription.html";
        })

    </script>



</body>
</html>
