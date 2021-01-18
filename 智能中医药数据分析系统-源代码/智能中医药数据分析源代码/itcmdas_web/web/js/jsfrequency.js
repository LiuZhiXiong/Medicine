document.write('<script src="js/echarts.min.js"></script>');
document.write('<script src="js/jquery-3.4.1.js"></script>');
document.write('<script src="js/jquery-3.4.1.js"></script>');
document.write('<script src="js/showBo.js"></script>');
document.write('<link rel="stylesheet" type="text/css" href="css/showBo.css"></link>');
//用于存放解析出来的值
var jsonNums = new Array();
var jsonIds = new Array();
var jsonMedicine = new Array();

var data;
var wb;
var sel_val;
var sel_num
var data1 = {
    "array": data
}

//    layUI表格数据获取函数
var limitcount = 10;
var curnum = 1;
var ids = new Array();
var medicine = new Array();
var testdiv = new Array();
var dateee;
var myChart;
var frequencyName;
function linksParse(source, target) {
    var o = new Object();
    o.source = source;
    o.target = target;
    return o;
}

function namesParse(name) {
    var o = new Object();
    o.name = name;
    return o;
}

/**
 * 频次分析
 * 展示echart图表
 * @param myChart
 */

function echartsFre(myChart) {

    $.ajax({
        type: "post",
        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "FrequencyAnalysisServlet?method=frequencyAnalysisEchartsData",
        success: function (data) {
            dateee = eval("(" + data + ")"); // 解析数据成模型
            console.log(dateee)
            for (var i = 0; i < dateee.data.length; i++) {
                jsonMedicine.push(dateee.data[i].medicineListFre)
                jsonNums.push(dateee.data[i].medicineNumFre)
            }
            testdiv=dateee.data
            console.log("testdiv")
            console.log(testdiv)

            let option = {
                toolbox: {
                    show: true,
                    feature: {
                        dataZoom: {
                            yAxisIndex: "none"
                        },
                        dataView: {
                            readOnly: false
                        },
                        magicType: {
                            type: ["line", "bar"]
                        },
                        restore: {},
                        saveAsImage: {}
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },

                grid: {
                    width: 'auto',
                    height: 'auto',
                    left: '3%',
                    right: '4%',
                    bottom: '5%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    data: jsonMedicine.reverse()
                },
                series: [
                    {
                        name: '药物频度',
                        type: 'bar',
                        data: jsonNums.reverse(),
                        itemStyle: {//柱图背景色
                            color: '#1E9FFF'
                        },
                    },
                ],

            };


            // 使用刚指定的配置项和数据显示图表。

            myChart.setOption(option)
            jsonNums = []
            jsonMedicine = []
            jsonIds = []

        }
    });


}


/**
 * 频次分析
 * 表格（带分页的）
 */

function getFrenLayUIData() {
    layui.use('table', function () {
        var table = layui.table;
        var laypage = layui.laypage;
        //第一个实例
        table.render({
            elem: '#divtable'
            , height: 686
            , type: 'post'
            , url: "FrequencyAnalysisServlet?method=frequencyAnalysisLayuiTableData"//数据接口

            , page: true //开启分页
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 60, sort: true, fixed: 'left'}
                , {field: 'medicineListFre', title: '药物', width: 300}
                , {field: 'medicineNumFre', title: '频度', width: 360}
            ]]
            , done: function (data, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                laypage.render({
                    elem: 'laypage'
                    , count: count
                    , curr: curnum
                    , limit: limitcount
                    , layout: ['prev', 'page', 'next', 'skip', 'count', 'limit']
                    , jump: function (obj, first) {
                        if (!first) {
                            curnum = obj.curr;
                            limitcount = obj.limit;
                            productGroupId = count;
                            getFormLimitData(productGroupId,curnum,limitcount);
                        }
                    }
                })
            }

        });

    });
}

var flag=0;

/**
 * 下载文件
 */
function downloadFile() {
    //此数据需要后台获取
    var medicineJson = testdiv
    var  excelName=""
    //自定义标题栏
    var title = ['编号', '药物', '频次统计']
    //自定义导出
    if (medicineJson == null || medicineJson.length == 0) {

        Showbo.Msg.alert('暂时无数据，不可导出')

    } else {
        const sel_val = $('#select').val();
        const sel_num = $('#frequencySelect').val();
        console.log(flag+"flag")

        if(flag==0){
            console.log(sel_val)
            if(sel_val==1){
                excelName=frequencyName.replace(".xls","" )+ "频次分析Excel表-单味药" + "降序前" + sel_num+"药物排名表"
            }else if(sel_val==2){
                excelName=frequencyName.replace(".xls","" )+ "频次分析Excel表-药对组"+ "降序前" + sel_num+"药物排名表"
            }else {
                excelName=frequencyName.replace(".xls","" )+ "频次分析Excel表-三元组" + "降序前" + sel_num+"药物排名表"
            }
            JSONToExcelConvertor(medicineJson, excelName, title);
        }

    }
}



/**
 * 进行药对，排名进行判断，并将excel转为json厚的数据发送给servlet
 */
function excel_to_json() {
    console.log("excel_to_json")
    if (data == undefined) {
        Showbo.Msg.alert('文件错误", "请上传文件')
    } else {

        //还需判断药对
        select_val();
        //判断数据量是否选择
        select_num();
        medicine = []
        for (var i = 0; i < data.length; i++) {
            ids.push(data[i].编号)
            medicine.push(data[i].处方)
        }

    }
}

/**
 * 导入文件
 * @param obj
 */

function importfile(obj) {
    var myChart = echarts.init(document.getElementById('echarts_bar'));
    frequencyName = obj.files[0].name;

    var flag = obj.files[0].name
    // 判断是否是excel表单
    if (flag.indexOf(".xls") == -1) {
        // mizhu.alert('文件格式有误', '文件错误，请重新选择')
        Showbo.Msg.alert('文件格式有误,文件错误，请重新选择')
        return
    } else {
        $('#selectFile').text(flag);
        var reader = new FileReader(),
            file = obj.files[0];

        reader.onload = function (e) {
            data = e.target.result;

            wb = XLSX.read(data, {
                type: 'binary'
            });

            data = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]) //转成 json 数据
            const selectValue = $('#select').val();
            const frequencySelect = $('#frequencySelect').val();
            console.log(selectValue+"---"+frequencySelect)
            medicine = []
            for (var i = 0; i < data.length; i++) {
                ids.push(data[i].编号)
                medicine.push(data[i].处方)
            }
            console.log(medicine)
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
                    medicine=[]
                    ids=[]
                    // 异步加载数据
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    //传入失败时的操作
                }
            });

        }

    };

    reader.readAsBinaryString(file) //以二进制方式读取



}
/**
 * 功能介绍
 */
function functionDescription() {
    console.log("功能介绍")
    window.location.href="FrequencyFunctionDeclaration.html"

}
