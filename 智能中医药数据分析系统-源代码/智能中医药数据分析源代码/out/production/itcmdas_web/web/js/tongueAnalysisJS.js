let flag = 0;
let onesystom;
let onesuggest;
let twosystom;
let towsuggest;
let threesystom
let threesuggest;
let medicine;
let imageUrl;
var tongueJSON;
var suggest;
var symptom;

/**
 * 用于将图片显示在图片界面上
 * @type {HTMLElement}
 */

/**
 * 样例一
 */
function exampeOne() {
    console.log("样例一")
    onesystom = "苔色：黄苔,厚苔,腻苔,剥苔\n" +
        "\n" +
        "苔质：无齿痕,有点刺,有裂纹,燥苔\n" +
        "\n" +
        "舌色：淡白舌\n" +
        "\n" +
        "舌型：胖大舌\n" +
        "\n" +
        "体质类型：阳虚体质\n" +
        "\n" +
        "证型：寒痰阻肺,虚喘证,血虚不润,湿热较盛,胃热炽盛,"
    onesuggest = " \"当归生姜羊肉汤。制作方法：当归20克，生姜30克洗净，切成片，羊肉500克，剔去筋膜，剁成小块放入沸水中焯去血水。在沙锅中加入适量清水，放入当归片、羊肉块、生姜片、料酒，用大火煮沸，去浮沫，改用小火煲至羊肉熟烂，加盐调味。主要用于补益补身汤。有温中补血，祛寒止痛，特别适用于冬天食用。\",\n" +
        "      \"韭菜炒胡桃仁。制作方法：胡桃仁50克开水浸泡去皮，沥干备用，韭菜200克摘洗干净，切段备用，麻油倒入沙锅，烧至七成热时，加入胡桃仁，炸至焦黄，再加入韭菜、食盐，翻炒至熟。本品有补肾助阳、温暖腰膝的作用，适用于肾阳不足、腰膝冷痛者。\",\n" +
        "      \"合适的食物。羊肉、狗肉、肌肉、河虾、海虾、海参、核桃仁、蜂王浆、雄蚕蛾、胡椒、干姜、荔枝、冬虫夏草\",\n" +
        "      \"少吃的食物。梨、李子、西瓜、香蕉、琵琶、马蹄、柿子、黄瓜、苦瓜、蚕豆、绿豆、百合、甲鱼、鸭肉、田螺、绿茶。\"";

    flag = 1;
    let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
    let suggestText = document.getElementById('content2');
    symptomText.innerHTML = onesystom;
    suggestText.innerHTML = onesuggest;
}

/**
 *样例二
 * */
function exampeTwo() {
    console.log("样例二")
    twosystom = "苔色：黄苔,厚苔,腻苔,非剥苔\n" +
        "\n" +
        "苔质：无齿痕,有点刺,无裂纹,燥苔\n" +
        "\n" +
        "舌色：淡白舌\n" +
        "\n" +
        "舌型：胖大舌\n" +
        "\n" +
        "体质类型：阳虚体质\n" +
        "\n" +
        "证型：寒痰阻肺,湿热较盛,胃热炽盛,湿浊不化,"
    towsuggest = " \"凉拌马齿苋。新鲜马齿苋100g清水洗净，切断，用少许酱油、麻油拌均食用。\",\n" +
        "      \"泥鳅炖豆腐。将泥鳅500g去鳃及内脏，洗净；豆腐250g切块；泥鳅入锅，加盐3g、水适量，置武火上，炖至五成熟，加入豆腐，再炖至泥鳅熟烂即可。有清热利湿的作用。\",\n" +
        "      \"合适的食物。薏米、莲子、茯苓、红小豆、绿豆、冬瓜、丝瓜、葫芦、苦瓜、黄瓜、西瓜、白菜、芹菜、卷心菜、莲藕、空心菜、苋菜等\",\n" +
        "      \"少吃的食物。胡桃仁、鹅肉、羊肉、狗肉、鳝鱼、香菜、辣椒、酒、饴糖、胡椒、花椒等甘酸滋腻之品及火锅、烹炸、烧烤等辛温助热食品。\""

    flag = 2;
    let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
    let suggestText = document.getElementById('content2');
    symptomText.innerHTML = twosystom;
    suggestText.innerHTML = towsuggest;
}

/**
 * 样例三
 */
function exampeThree() {
    console.log("样例三")
    threesystom = "苔色：黄苔,厚苔,腐苔,非剥苔\n" +
        "\n" +
        "苔质：有齿痕,有点刺,无裂纹,燥苔\n" +
        "\n" +
        "舌色：淡红舌\n" +
        "\n" +
        "舌型：胖大舌\n" +
        "\n" +
        "体质类型：气虚体质,undefined\n" +
        "\n" +
        "证型：肝胃不和,脾虚或气虚,痰浊阻滞,湿热熏蒸,胃热炽盛,"
    threesuggest = " \"黄芪童子鸡。将童子鸡1只洗净，用纱布袋包好生黄芪9克，取一根细线，一端扎紧袋口，置于锅内，另一端则绑在锅柄上。在锅中加入葱姜蒜盐酒等调料，用文火慢炖1小时，食肉喝汤。有补中益气，增强机体抗病能力的作用，适用于体质虚弱、易患风寒感冒者。\",\n" +
        "      \"山药粥。将山药30g及粳米180g一起加入锅中，适量清水煮粥，煮熟即可。具有补中益气、固肺涩精的作用。\",\n" +
        "      \"合适的食物。粳米、小米、黄米、大麦、莜麦、黄豆、白扁豆、豇豆、蚕豆、豌豆、土豆、白薯、山药、胡萝卜、香菇、鲫鱼、鹌鹑、鹅肉、鸽蛋、鸽肉、羊心、羊肚、莲子、菱角、猴头菇、蘑菇、芡实、红薯、栗子、人参、黄鳝、虾等。\",\n" +
        "      \"少吃的食物。荞麦、柚子、生萝卜、柑橘、槟榔、空心菜等。\""
    let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
    let suggestText = document.getElementById('content2');
    symptomText.innerHTML = threesystom;
    suggestText.innerHTML = threesuggest;
    flag = 3;
}


//将时间转为yyyymmddhhmmss
function generateTimeReqestNumber() {
    var date = new Date();
    return date.getFullYear().toString() + pad2(date.getMonth() + 1) + pad2(date.getDate()) + pad2(date.getHours()) + pad2(date.getMinutes()) + pad2(date.getSeconds());
}

function pad2(n) {
    return n < 10 ? '0' + n : n
}

//舌诊症状
function symptomOnclick() {
    flag = 0;
    // 获取网络图片
    $.ajax({
        type: "post",
        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "RedirectServlet?method=getUrl",
        datatype: "json",
        success: function (res) {

            resJson = eval("(" + res + ")"); // 解析数据成模型
            console.log(typeof (resJson) + "jsonUrl")
            console.log(resJson.data)

            let time = generateTimeReqestNumber()
            console.log(time)
            //将地址赋值给imageUrl
            imageUrl="http://115.29.204.107:8080/itcmdas/upload/"+resJson.data;
            // imageUrl = "http://115.29.204.107:8080/itcmdas/upload/0177e4db-5ac1-4224-93ca-39f95db24565/2.png"
            console.log(imageUrl);
            console.log(resJson);
            console.log("imageUrl");
            if (escape(imageUrl).indexOf("%u") != -1) {
                Showbo.Msg.alert('图片名不能为中文')
            } else if (imageUrl != null) {
                $.ajax({
                    type: "POST",
                    url: "http://www.aibayes.cn/api/analysis",
                    traditional: true,//如果需要传入数组，一定将此值赋值true！！
                    data: {
                        timestamp: time,
                        app_id: '941c9b2baa9b4e83',
                        version: '1.0',
                        method: '9tiAnalysis',
                        imgpath: imageUrl,
                        sign: hex_md5(hex_md5(time).toUpperCase() + 'fe40d5fc22074e94ba456929d6949f0c').toUpperCase()
                    },
                    success: function (msg) {

                        console.log(msg)

                        medicine = msg.data;
                        // console.log("对象类型" + typeof (medicine))
                        dateee = eval("(" + msg.data + ")"); // 解析数据成模型
                        console.log("解析出来的结果如下：")
                        console.log(dateee)
                        let len = dateee.char.length;
                        let taise;
                        let shiliao;
                        let j = 0;
                        let shiliaoLen = dateee.tiaoli.shiliao.length;
                        for (let i = 0; i < shiliaoLen; i++) {
                            if (dateee.tiaoli.shiliao[i] != "") {
                                shiliao += ((++j) + ":" + dateee.tiaoli.shiliao[i])
                            } else {
                                shiliao += (dateee.tiaoli.shiliao[i])
                            }
                        }
                        for (let i = 0; i < shiliaoLen; i++) {
                            shiliao += ((++j) + ":" + dateee.tiaoli.shiliao[i])
                        }
                        let tizhi = dateee.tiaoli.tizhi_name;
                        let suggest = shiliao;
                        let symptom = taise + " " + tizhi;
                        let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
                        let suggestText = document.getElementById('content2');
                        symptomText.innerHTML = symptom.replace("undefined", "");
                        suggestText.innerHTML = suggest.replace("undefined", "");

                    }
                });

            } else {
                Showbo.Msg.alert('请上传图片')
            }
        }

    });


}


/**
 * 症状识别
 */
function adviceOnclick() {
    console.log("adviceOnclick")
    console.log("flag::" + flag)
    console.log(symptom)
    console.log(suggest)
    if (flag === 1) {
        let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
        let suggestText = document.getElementById('content2');
        symptomText.innerHTML = onesystom.replace("undefined", "");
        suggestText.innerHTML = onesuggest.replace("undefined", "");
    } else if (flag === 2) {
        let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
        let suggestText = document.getElementById('content2');
        symptomText.innerHTML = twosystom.replace("undefined", "");
        suggestText.innerHTML = towsuggest.replace("undefined", "");
    } else if (flag === 3) {
        let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
        let suggestText = document.getElementById('content2');
        symptomText.innerHTML = threesystom.replace("undefined", "");
        suggestText.innerHTML = threesuggest.replace("undefined", "");
    }
    if (flag === 0) {
        console.log("默认flage==0")
        console.log(symptom)
        console.log(suggest)
        let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
        let suggestText = document.getElementById('content2');
        symptomText.innerHTML = symptom.replace("undefined", "");
        suggestText.innerHTML = suggest.replace("undefined", "");
    }


}

/**
 * 药物建议
 */
function SymptomOn() {
    console.log("symptom：：" + flag)
    if (flag === 1) {
        let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
        let suggestText = document.getElementById('content2');
        symptomText.innerHTML = onesystom.replace("undefined", "");
        suggestText.innerHTML = onesuggest.replace("undefined", "");
    } else if (flag === 2) {
        let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
        let suggestText = document.getElementById('content2');
        symptomText.innerHTML = twosystom.replace("undefined", "");
        suggestText.innerHTML = towsuggest.replace("undefined", "");
    } else if (flag === 3) {
        let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
        let suggestText = document.getElementById('content2');
        symptomText.innerHTML = threesystom.replace("undefined", "");
        suggestText.innerHTML = threesuggest.replace("undefined", "");
    } else {
        let symptomText = document.getElementById('content1');//获取HTML中div的ID，从而得到div中的内容
        let suggestText = document.getElementById('content2');
        symptomText.innerHTML = symptom.replace("undefined", "");
        suggestText.innerHTML = suggest.replace("undefined", "");
    }
}
