<%--
  Created by IntelliJ IDEA.
  User: 孟涛
  Date: 2020/9/23
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  <!--窗口宽度是设备宽度，缩放比例是1:1-->
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"></link>
    <script src="layui/layui.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>


    <script type="text/javascript" src="js/JSONToExcelConvertor.js"></script>
    <script type="text/javascript" src="js/RelateJSONToExcelConvertor.js"></script>
    <title>智能中医药数据分析系统</title>

</head>
<script>

    window.onload=layui.use('layer', function(){
        var layer = layui.layer;

        layer.open({
            fixed:true,
            offset: 'rb',
            content: '请下载标准输入文件模板和输入样例',
            btn: ['标准模板', '输入样例'],

            yes: function(index, layero){
                //按钮【按钮一】的回调
                console.log("yes");
                var title = ['编号', '处方'];
                JSONToExcelConvertor({}, "输入文件模板", title);
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
                //return false 开启该代码可禁止点击该按钮关闭
                console.log("输入样例");
                var title = ['编号', '处方'];
                var demoData=[{
                    "id":1,
                    "medicineListFre": "党参、丹参、桂枝、茯苓、桃仁、赤芍、丹皮、炒鳖甲、生牡蛎、炮穿山甲",

                },
                    {
                        "id": 2,
                        "medicineListFre": "当归、桂枝、甘草、木通、肉苁蓉、白芍、细辛、桃仁、西洋参",

                    },
                    {
                        "id": 3,
                        "medicineListFre": "当归、桂枝、木通、白芍、细辛、西洋参、丹参、大枣、火麻仁、甘草",

                    },
                    {
                        "id": 4,
                        "medicineListFre": "熟地、肉苁蓉、菟丝子、杜仲、川牛膝、木瓜、炒鹿筋、当归、桃仁、红花、炮甲、火麻仁",

                    },
                    {
                        "id": 5,
                        "medicineListFre": "熟地、肉苁蓉、菟丝子、杜仲、川牛膝、木瓜、炒鹿筋、当归、桃仁、红花、炮甲、火麻仁",

                    },
                    {
                        "id": 6,
                        "medicineListFre": "西洋参、炒白术、白芍、怀山药、柴胡、熟地、当归、杜仲、生枣仁、沙参、丹皮、肉苁蓉、火麻仁",

                    },
                    {
                        "id": 7,
                        "medicineListFre": "西洋参、炒白术、白芍、怀山药、柴胡、熟地、当归、杜仲、生枣仁、沙参、丹皮、肉苁蓉、火麻仁、黄芪、女贞子、淫羊藿、小海龙、广木香",

                    },
                    {
                        "id": 8,
                        "medicineListFre": "党参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、麦冬、甘草、香附",

                    },
                    {
                        "id": 9,
                        "medicineListFre": "党参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、麦冬、甘草、香附",

                    },
                    {
                        "id": 10,
                        "medicineListFre": "红参片、吴茱萸、川芎、当归、酒白芍、丹皮、官桂皮、法半夏、麦冬、甘草、香附、益母草",

                    },
                    {
                        "id": 11,
                        "medicineListFre": "刘寄奴、木瓜、琥珀、三棱、莪术、丹皮、官桂、延胡索、乌药、红藤、当归、酒白芍、田七片、五灵脂、炮穿山甲",

                    },
                    {
                        "id": 12,
                        "medicineListFre": "沙参、麦冬、白芍、当归、生地、枸杞子、川楝子、延胡索、琥珀、红藤、田七粉、生蒲黄、五灵脂",

                    },
                    {
                        "id": 13,
                        "medicineListFre": "西洋参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、补骨脂、小茴香、延胡索、三七、甘草",
                    },
                    {
                        "id": 14,
                        "medicineListFre": "炒枣仁、茯神、知母、川芎、熟地、怀山药、山茱萸、枸杞子、当归、杜仲、川牛膝、炒龟板、延胡索、合欢花",
                    },
                    {
                        "id": 15,
                        "medicineListFre": "知母、黄柏、生地、怀山药、泽泻、丹皮、茯苓、炒龟板、人工牛黄、熊胆粉、甘草、萹蓄",
                    },
                    {
                        "id": 16,
                        "medicineListFre": "知母、黄柏、生地、怀山药、泽泻、丹皮、茯苓、炒龟板、人工牛黄、熊胆粉",
                    },
                    {
                        "id": 17,
                        "medicineListFre": "西洋参、茯苓、炒白术、甘草、当归、白芍、熟地、川芎、荆芥炭、黄芩",
                    },
                    {
                        "id": 18,
                        "medicineListFre": "西洋参、丹参、阿胶、当归、白芍、熟地、川芎、甘草、艾叶炭、蒲黄炭",
                    },
                    {
                        "id": 19,
                        "medicineListFre": "当归、白芍、川芎、炒白术、茯苓、泽泻、黄柏、怀山药、车前子、白果、芡实、香附、浙贝、白花蛇舌草、鱼腥草、薏苡仁、煅乳香、煅没药、炒鳖甲、煅牡蛎、天葵子",
                    },
                    {
                        "id": 20,
                        "medicineListFre": "生地、白芍、当归、川芎、阿胶、艾叶炭、黄芩、黄柏、怀山药、车前子、白果、芡实、甘草、香附、浙贝、白花蛇舌草、鱼腥草",
                    },
                    {
                        "id": 21,
                        "medicineListFre": "生地、白芍、当归、川芎、阿胶、艾叶炭、荆芥炭、黄芩、黄柏、怀山药、车前子、白果、芡实、甘草",
                    },
                    {
                        "id": 22,
                        "medicineListFre": "芡实、煅龙骨、煅牡蛎、五味子、白芍、怀山药、菟丝子、麦冬、知母、黄柏、炒龟板、生地、地骨皮、炒枣仁",
                    },
                    {
                        "id": 23,
                        "medicineListFre": "芡实、煅龙骨、煅牡蛎、五味子、白芍、怀山药、菟丝子、麦冬、知母、黄柏、炒龟板、生地、地骨皮、炒枣仁",
                    },
                    {
                        "id": 24,
                        "medicineListFre": "西洋参、黄芪、炒白术、陈皮、升麻、柴胡、当归、甘草、桑螵蛸、益智仁、菟丝子、覆盆子、怀山药、白芍",
                    },
                    {
                        "id": 25,
                        "medicineListFre": "西洋参、黄芪、炒白术、陈皮、升麻、柴胡、当归、甘草、白芍、熟地、荆芥炭、黄芩、侧柏炭、地榆炭",
                    },
                    {
                        "id": 26,
                        "medicineListFre": "西洋参、黄芪、炒白术、陈皮、升麻、柴胡、当归、甘草、桑螵蛸、益智仁、菟丝子、覆盆子、白芍、木瓜",
                    },
                    {
                        "id": 27,
                        "medicineListFre": "炒枣仁、知母、川芎、茯神、丹皮、栀仁、当归、白芍、炒白术、柴胡、甘草、炒龟板",
                    },
                    {
                        "id": 28,
                        "medicineListFre": "酒大黄、陈皮、法半夏、茯苓、枳实、竹茹、甘草、苍耳子、辛夷、白芷、薄荷",
                    },
                    {
                        "id": 29,
                        "medicineListFre": "生大黄、陈皮、法半夏、茯苓、枳实、竹茹、甘草、桃仁、红花、生地、赤芍、当归、川芎",
                    },
                    {
                        "id": 30,
                        "medicineListFre": "丹皮、栀子、当归、白芍、柴胡、茯苓、炒白术、甘草、青皮、枳壳、广木香、炮甲、杜仲、延胡索、炒枣仁",
                    },

                ]
                JSONToExcelConvertor(demoData, "输入文件样例", title);
            },
            cancel: function(){
                //右上角关闭回调
                //return false 开启该代码可禁止点击该按钮关闭
            }
        });
    });
</script>
<body>
<!-- 红色的头标题 -->
<div class="title">
    <img src="image/title.jpg" class="image" width="100%"/>
</div>
<!-- 	  6个图标 -->
<div class="tab1">
    <!--  中间第二部分 -->


    <div class="container">
        <div class="title" style="position: absolute;top:13%; left: 37%">
            <h2 class="tab-h2" style="font-family: 'arial rounded mt bold' ;color: #226FBE;">「 智能中医药数据分析系统 」</h2>
            <p style="left: 10px;text-align: center">致力于服务中医药，以中医药为基础开发的在线网站系统</p>
        </div>

        <!-- 第一排图标 -->
        <div class="row" id="row1" style="margin-top: 100px">
            <!-- 第一个图标 -->
            <div class="col-md-4 " onclick="window.open('FrequencyAnalysis.jsp')">
                <div class="media">
                    <div class="media-left">
                        <img src="image/row11.png"/>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">频次分析</h4>

                        <p class="text-muted"> 将处方中单味药、药对、三元组的出现频次进行统计，并从大到小排序。
                            （单味药、药对、三元组用户可自己选择）</p>

                    </div>
                </div>
            </div>
            <!-- 第二个图标 -->
            <div class="col-md-4 col " onclick="window.open('AssociationAnalysis.jsp')">
                <div class="media">
                    <div class="media-left">
                        <img src="image/row12.png"/>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">关联分析 </h4>
                        <p class="text-muted"> 可自主设置最小支持度计数阈值和最小置信度阈值，依据Apriori关联分析算法生成强关联规则表和图</p>


                    </div>
                </div>
            </div>
            <!-- 第三个图标 -->
            <div class="col-md-4 col" onclick="window.open('FlusterAnalysis.jsp')">
                <div class="media">
                    <div class="media-left">
                        <img src="image/row13.png"/>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">聚类分析</h4>
                        <p class="text-muted"> 可自主设置最小关联度阈值，生成按照关联度系数从大到小排序后的药物排名表或得到处方中核心药物组合表。</p>

                    </div>

                </div>
            </div>
        </div>

        <!-- 第二排图标 -->
        <div class="row" id="row2" style="margin-top: 10%" onclick="window.open('NLPAnalysis.jsp')">
            <!-- 第一个图标 -->
            <div class="col-md-4 ">
                <div class="media">
                    <div class="media-left">
                        <img src="image/row21.png"/>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">文本情感分析</h4>

                        <p class="text-muted"> 可输入一段文字，系统将给出这段文字表达的情感是正面的还是负面的判断</p>

                    </div>
                </div>
            </div>
            <!-- 第二个图标 -->
            <div class="col-md-4 col " onclick="window.open('tongueAnalysis.jsp')">
                <div class="media">
                    <div class="media-left" >
                        <img src="image/tongue_img.png" style="width: 128px; height: 128px;"/>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">中医舌诊图像诊断</h4>
                        <p class="text-muted"> 上传舌头图片，图片名不能为中文，系统将分析病状并给出药物调养建议</p>


                    </div>
                </div>
            </div>
            <!-- 第三个图标 -->
            <div class="col-md-4 col" >
                <div class="media">
                    <div class="media-left">
                        <img src="image/robot_img.png" style="width: 128px; height: 128px;"/>
                    </div>
                    <div class="media-body" >
                        <h4 class="media-heading">中医古籍翻译</h4>
                        <p> 待实现</p>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
