document.write('<script src="js/CombineJSONToExcelConvertor.js" type="text/javascript" charset="utf-8"></script>');
// /**
document.write('<script src="js/showBo.js"></script>');

document.write('<link rel="stylesheet" type="text/css" href="css/showBo.css"></link>');
let sourceArr = new Array();
let targetArr = new Array();
let categoryArr = new Array();
let fileArr = new Array();
let CombineArr = new Array();
let CombineFile = new Array();

/**
 * 下载转过来的json数据
 */
function downloadJson(dataS, filename) {
    var blob = new Blob([JSON.stringify(dataS)], {type: "text/plain;charset=utf-8"});
    saveAs(blob, filename || 'data.json');
}


/**
 * //下载文件
 */
function downloadFile() {
    //自定义标题栏
    var title = ['编号', '药物', '频次统计']
    //自定义过滤栏（不需要导出的行）
    // var filter=['id','logins']
    medicineJson = null
    // 原始导出
    JSONToExcelConvertor(medicineJson, "report");
    // 自定义导出
    if (medicineJson == null || medicineJson.length == 0) {
        alert("暂时无数据，不可导出")
    } else {
        console.log("12515215125125125125125")
        JSONToExcelConvertor(medicineJson, "频次统计Excel表"+getMinAndMax, title);
        console.log("12515215125125125125125")
        // JSONToExcelConvertor(medicineJson,"report");
    }
}


/**
 * 请输入药对关联度系数阈值
 * 将excel转为json数据
 */
function excel_to_json() {
    if (dataS == undefined) {
        // alert('请上传文件');
        Showbo.Msg.alert('请上传文件!')
        // console.log(sel_val)
    } else {
        console.log("-----")
        //console.log(dataS)
        send_json_to_servlet()
        console.log("++++")

    }
}

/**
 * 将excel转为的json发送给后台
 * ids 为编号
 * medicine为药物处方
 * @type {any[]}
 */
var ids = new Array();
var medicineStr = new Array();
var nameArr = new Array();
var linksArr = new Array();

/**
 * 聚类分析
 * 将文件的json传给servlet
 */
function send_json_to_servlet() {
    medicineStr = []
    for (var i = 0; i < dataS.length; i++) {
        ids.push(dataS[i].编号)
        medicineStr.push(dataS[i].处方)
    }

    // 异步加载数据
    $.ajax(
        {
            url: "FlusterAnalysis?method=saveDataToSession",
            type: "post",
            traditional: true,//如果需要传入数组，一定将此值赋值true！！
            datatype: "json",
            data: {
                "minCorrelationCoefficient": document.getElementById("minCorrelationCoefficient").value,
                "medicineStr": this.medicineStr,
                "dataNum": 5
            },
            //-----------------------------------
            success: function () {

                //设置表格信息
                setTableForm();
//设置图表信息
                setEcharts();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //传入失败时的操作
            }
        });
}

/**
 * 聚类分析
 * 获取药对关联度功能
 * ajax将最大值和最小值传给后端
 */
function excel_to_jsonCore() {
    if (dataS == undefined) {
        // alert('请上传文件');
        Showbo.Msg.alert('请上传文件!')
        // console.log(sel_val)
    } else {
        console.log("---------")
        //console.log(dataS)
        send_json_to_servletCore()
        console.log("++++")

    }
}

function send_json_to_servletCore() {
    medicineStr = []
    for (var i = 0; i < dataS.length; i++) {
        ids.push(dataS[i].编号)
        //console.log(data[i].编号)
        //  console.log(data[i].处方)
        medicineStr.push(dataS[i].处方)
    }
    console.log(ids)
    console.log(medicineStr)

    // var id = [];
    var name = [];
    var nameArr = new Array();
    var linksArr = new Array();
    // var minCorrelationCoefficient =getValue();
    //alert(minCorrelationCoefficient);
    // 异步加载数据
    console.log(document.getElementById("MinId").value);
    console.log(typeof (document.getElementById("MinId").value))

    $.ajax(
        {
            url: "CoreMedicineListServlet?method=saveDataToSession",
            type: "post",
            traditional: true,//如果需要传入数组，一定将此值赋值true！！
            datatype: "json",
            data: {
                "minMedicineNum": document.getElementById("MinId").value,
                "maxMedicineNum": document.getElementById("MaxId").value,
                "medicineStrCoreMedicine": this.medicineStr,
            },
            //-----------------------------------
            success: function () {
                LayUICombine();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //传入失败时的操作
                console.log(textStatus + "++" + errorThrownS)
            }
        });
}


/**
 * 导入文件
 * @param obj
 */
var jsonParseJson = sessionStorage.getItem('key');
var dataS = JSON.parse(jsonParseJson);
var wbS;


function importfile(obj) {
    console.log(obj.files[0].name)
    var flag = obj.files[0].name
    // 判断是否是excel表单
    if (flag.indexOf(".xls") == -1) {
        // mizhu.alert('文件格式有误', '文件错误，请重新选择')
        Showbo.Msg.alert('文件错误，请重新选择!')
        // alert('文件错误，请重新选择');
        return
    } else {
        $('#selectFile').text(flag);
        var reader = new FileReader(),
            file = obj.files[0];

        reader.onload = function (e) {
            dataS = e.target.result;

            wbS = XLSX.read(dataS, {
                type: 'binary'
            });

            dataS = XLSX.utils.sheet_to_json(wbS.Sheets[wbS.SheetNames[0]]) //转成 json 数据
            console.log(dataS)
            var tempIndex = JSON.stringify(dataS);
            console.log("tempIndex"+tempIndex)
            sessionStorage.setItem('key', tempIndex)

        }

    }
    ;

    reader.readAsBinaryString(file) //以二进制方式读取

}

/**
 * 获取阈值
 */
function getValue() {
    var a = document.getElementById("minCorrelationCoefficient").value;
    return a
    //  alert(a);
}

/**
 * 获取组合药物的最大和最小值
 */
function getMinAndMax() {
    var MinId = document.getElementById("MinId").value;
    var MaxId = document.getElementById("MaxId").value;
    return "--药物最小数量"+MinId+"--药物最大数量"+MaxId;
}


/**
 * 连接连个药物关联
 * @param source
 * @param target
 * @param correlationCoefficient
 * @returns {Object}
 */
function linksParse(source, target, correlationCoefficient) {
    var o = new Object();
    o.source = source;
    o.target = target;
    o.category = correlationCoefficient;
    return o;
}

/**
 * 将药材转为对象
 * @param name
 * @returns {Object}
 */
function namesParse(name) {
    var o = new Object();
    o.name = name;
    return o;
}

/**
 * 设置echarts图表数据信息
 */
function setEcharts() {
    var name = [];
//     var nameArr = new Array();
//     var linksArr = new Array();
    let myChart = echarts.init(document.getElementById('main'));
    $.ajax(
        {
            url: "FlusterAnalysis?method=correlationCoefficientEchartsData",
            type: "post",
            //-----------------------------------
            success: function (data) {
                console.log("*************************")
                fileArr = JSON.parse(data)
                console.log(fileArr.data)
                console.log("*************************")

                var relateData = JSON.parse(data)
                console.log(typeof (relateData))
                console.log(relateData)
                console.log(relateData.count)
                for (let i = 0; i < relateData.count; i++) {
                    let temp = relateData.data[i].medicine1;
                    console.log(temp)
                    let temp2 = relateData.data[i].medicine2;
                    console.log(temp2 + "2")
                    let correlationCoefficients = relateData.data[i].correlationCoefficient;
                    if (name.indexOf(temp) == -1) {
                        name.push(temp);
                        nameArr.push(namesParse(temp));
                    }
                    if (name.indexOf(temp2) == -1) {
                        name.push(temp2);
                        nameArr.push(namesParse(temp2));
                    }
                    linksArr.push(linksParse(temp, temp2, correlationCoefficients));
                }
                console.log(linksArr)
                myChart.setOption({
                    title: {
                        text: '网络图'
                    },
                    tooltip: {
                        formatter: function (x) {
                            if (x.data.category == undefined) {
                                return;
                            }
                            return "关联系数：" + x.data.category;
                        }
                    },
                    animationDurationUpdate: 1500,
                    animationEasingUpdate: 'quinticInOut',
                    series: [
                        {
                            type: 'graph',
                            layout: 'force',
                            symbolSize: 50,
                            roam: true,
                            draggable: true,
                            force: {
                                //initLayout: ,//力引导的初始化布局，默认使用xy轴的标点
                                repulsion: 1000,//节点之间的斥力因子。支持数组表达斥力范围，值越大斥力越大。
                                gravity: 0.8,//节点受到的向中心的引力因子。该值越大节点越往中心点靠拢。
                                edgeLength: 190,//边的两个节点之间的距离，这个距离也会受 repulsion。[10, 50] 。值越小则长度越长
                                layoutAnimation: false
                                //因为力引导布局会在多次迭代后才会稳定，这个参数决定是否显示布局的迭代动画，在浏览器端节点数据较多（>100）的时候不建议关闭，布局过程会造成浏览器假死。
                            },
                            label: {
                                show: true
                            },
                            focusNodeAdjacency: true,
                            edgeSymbol: ['circle', 'line'],
                            edgeSymbolSize: [4, 10],
                            edgeLabel: {
                                fontSize: 20
                            },
                            data: nameArr,
                            links: linksArr,
                            lineStyle: {
                                opacity: 0.9,
                                width: 2,
                                curveness: 0
                            }
                        }]
                });

            },
            error: function (jqXHR, textStatus, errorThrown) {
                //传入失败时的操作
            }
        });



    myChart.on('click', {dataType: 'node'}, function (params) {
        $("#description").text("");
        // $("#EchartsGraph").attr('class', "layui-input-inline EchartsGraphTwo");
        $("#description").append(" <div style=\"margin-top: 5px; background-color: rgba(241, 241, 241, 0.98);\">\n" +
            "            <label id=\"medicineNameLabel\">加载中......</label>\n" +
            "            <span id=\"medicineName\"></span>\n" +
            "        </div>\n" +
            "        <div style=\"margin-top: 10%\">\n" +
            "            <label id=\"medicineCharacterLabel\"></label>\n" +
            "            <textarea id=\"medicineCharacter\" readonly></textarea>\n" +
            "        </div>\n" +
            "        <div style=\"margin-top: 10%\">\n" +
            "            <label id=\"medicineDescriptionLabel\"></label>\n" +
            "            <textarea id=\"medicineDescription\" readonly></textarea>\n" +
            "        </div>")

        $.ajax({
            type: "get",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "medicineServlet?method=medicineDescription&medicine=" + params.name,    //请求发送到TestServlet处
            success: function (data) {
                var medicineData = JSON.parse(data).data;
                $("#medicineNameLabel").text("药名：");
                $("#medicineNameLabel").attr('class', "labelColor");
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

/**
 * 设置Table表单
 * */
let html = '';
var limitcount = 10;
var curnum = 1;
function getFormLimitData(productGroupId,curnum,count) {
    layui.use('table',function () {
        var table = layui.table;
        var laypage = layui.laypage;
        //第一个实例
        table.render({
            elem: '#tablediv'
            , height: 450
            ,type:'post'
            , url: "FlusterAnalysis?method=correlationCoefficientTableData"//数据接口

            , page: true //开启分页
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                , {field: 'medicine1', title: '中药', width: 150}
                , {field: 'medicine2', title: '被关联中药', width: 150}
                , {field: 'correlationCoefficient', title: '关联度系数', width: 120}
            ]]
            ,done: function(data, curr, count){
                console.log(data)
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
function setTableForm() {


        layui.use('table', function () {
            var table = layui.table;
            var laypage = layui.laypage;
            //第一个实例
            table.render({
                elem: '#tablediv'
                , height: 450
                ,type:'post'
                , url: "FlusterAnalysis?method=correlationCoefficientTableData"//数据接口

                , page: true //开启分页
                , cols: [[ //表头
                    {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                    , {field: 'medicine1', title: '中药', width: 150}
                    , {field: 'medicine2', title: '被关联中药', width: 150}
                    , {field: 'correlationCoefficient', title: '关联度系数', width: 120}
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

/**
 * 下载excel
 * 关联度
 */
function downloadRelateFile() {
    console.log("下载中++++++++++++++++")
    //此数据需要后台获取
    var RelatemedicineJson = fileArr.data
    console.log(RelatemedicineJson)
    console.log("下载中++++++++++++++++")
//console.log(medicineJson)
    //自定义标题栏
    var title = ['中药', '关联中药', '关联度系数']
    // console.log(RelatemedicineJson.length + "-------------")
    //自定义导出
    if (RelatemedicineJson == null || RelatemedicineJson.length == 0) {

        Showbo.Msg.alert('暂时无数据，不可导出!')

    } else {
        var value = getValue();
        console.log(value)
        RelateJSONToExcelConvertor(RelatemedicineJson, "药物关联度系数排名Excel表-关联系数系数阈值"+value, title)
    }
}

/**
 * 下载excel
 *
 * 核心药物组
 */
let CombineMmedicineJson;
function downloadCombineFile() {

    console.log("测试中")
    $.ajax(
        {
            url: "CoreMedicineListServlet?method=saveDataToSession",
            type: "post",
            traditional: true,//如果需要传入数组，一定将此值赋值true！！
            datatype: "json",
            data: {
                "minMedicineNum": document.getElementById("MinId").value,
                "maxMedicineNum": document.getElementById("MaxId").value,
                "medicineStrCoreMedicine": this.medicineStr,
            },
            //-----------------------------------
            success: function () {
                $.ajax(
                    {
                        url: "CoreMedicineListServlet?method=coreMedicineListsDataDown",
                        type: "post",
                        traditional: true,//如果需要传入数组，一定将此值赋值true！！
                        datatype: "json",

                        //-----------------------------------
                        success: function (data) {
                            coreJson = eval("(" + data + ")"); // 解析数据成模型
                            coreJsonData=coreJson.data
                            // console.log(coreJson)
                            // console.log(coreJsonData)

                            CombineMmedicineJson=coreJsonData
                            //自定义标题栏
                            var title = ['编号', '核心药物组']
                            //自定义导出
                            if (CombineMmedicineJson == null || CombineMmedicineJson.length == 0) {
                                Showbo.Msg.alert('暂时无数据，不可导出!')

                            } else {
                                CombineJSONToExcelConvertor(CombineMmedicineJson, "核心药物组Excel表"+getMinAndMax(), title)
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            //传入失败时的操作
                            console.log(textStatus + "++" + errorThrownS)
                        }
                    });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //传入失败时的操作
                console.log(textStatus + "++" + errorThrownS)
            }
        });



}
