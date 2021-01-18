package com.itcmdas.test;

import com.itcmdas.service.MedincineStrGeneration;
import com.itcmdas.vo.DataList;

import java.util.ArrayList;

/**
 * @Classname Test
 * @Description TODO
 * @Author liubo
 * @Date 2020/10/13 0:00
 * @Version 1.0
 */
public class Test {
    /**
     * 测试数据
     */

    public static void main(String[] args){
        String[] medicineStr=new String[44];
        medicineStr[0] = new String();/**/
        medicineStr[0] = "党参、丹参、桂枝、茯苓、桃仁、赤芍、丹皮、炒鳖甲、生牡蛎、炮穿山甲";
        medicineStr[1] = new String();
        medicineStr[1] = "党参、丹参、桂枝、茯苓、桃仁、赤芍、丹皮、炒鳖甲、生牡蛎、炮穿山甲";
        medicineStr[2] = new String();
        medicineStr[2] = "当归、桂枝、甘草、木通、肉苁蓉、白芍、细辛、桃仁、西洋参";
        medicineStr[3] = new String();
        medicineStr[3] = "当归、桂枝、木通、白芍、细辛、西洋参、丹参、大枣、火麻仁、甘草";
        medicineStr[4] = new String();
        medicineStr[4] = "熟地、肉苁蓉、菟丝子、杜仲、川牛膝、木瓜、炒鹿筋、当归、桃仁、红花、炮甲、火麻仁";
        medicineStr[5] = new String();
        medicineStr[5] = "西洋参、炒白术、白芍、怀山药、柴胡、熟地、当归、杜仲、生枣仁、沙参、丹皮、肉苁蓉、火麻仁";
        medicineStr[6] = new String();
        medicineStr[6] = "西洋参、炒白术、白芍、怀山药、柴胡、熟地、当归、杜仲、生枣仁、沙参、丹皮、肉苁蓉、火麻仁、黄芪、女贞子、淫羊藿、小海龙、广木香";
        medicineStr[7] = new String();
        medicineStr[7] = "党参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、麦冬、甘草、香附";
        medicineStr[8] = new String();
        medicineStr[8] = "党参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、麦冬、甘草、香附";
        medicineStr[9] = new String();
        medicineStr[9] = "红参片、吴茱萸、川芎、当归、酒白芍、丹皮、官桂皮、法半夏、麦冬、甘草、香附、益母草";
        medicineStr[10] = new String();
        medicineStr[10] = "刘寄奴、木瓜、琥珀、三棱、莪术、丹皮、官桂、延胡索、乌药、红藤、当归、酒白芍、田七片、五灵脂、炮穿山甲";
        medicineStr[11] = new String();
        medicineStr[11] = "沙参、麦冬、白芍、当归、生地、枸杞子、川楝子、延胡索、琥珀、红藤、田七粉、生蒲黄、五灵脂";
        medicineStr[12] = new String();
        medicineStr[12] = "西洋参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、补骨脂、小茴香、延胡索、三七、甘草";
        medicineStr[13] = new String();
        medicineStr[13] = "炒枣仁、茯神、知母、川芎、熟地、怀山药、山茱萸、枸杞子、当归、杜仲、川牛膝、炒龟板、延胡索、合欢花";
        medicineStr[14] = new String();
        medicineStr[14] = "西洋参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、补骨脂、小茴香、延胡索、三七、甘草";
        medicineStr[15] = new String();
        medicineStr[15] = "黄芩、黄连、生大黄、生地、白芍、当归、川芎、水牛角片、丹皮、白茅根、甘草";
        medicineStr[16] = new String();
        medicineStr[16] = "黄芩、黄连、生大黄、生地、白芍、当归、川芎、水牛角片、丹皮、白茅根、甘草";
        medicineStr[17] = new String();
        medicineStr[17] = "黄芩、黄连、生大黄、生地、白芍、当归、川芎、水牛角片、丹皮、甘草";
        medicineStr[18] = new String();
        medicineStr[18] = "桃仁、红花、生地、赤芍、当归、香附、莪术、广木香、川木通、炮甲、甘草";
        medicineStr[19] = new String();
        medicineStr[19] = "生石膏、栀子、藿香、防风、桃仁、丹皮、当归、赤芍、生大黄、炮甲、连翘、甘草";
        medicineStr[20] = new String();
        medicineStr[20] = "桃仁、红花、生地、赤芍、当归、川芎、炒枣仁、茯神、知母、生大黄、甘草";
        medicineStr[21] = new String();
        medicineStr[21] = "熟地、菟丝子、白芍、当归、怀山药、茯苓、柴胡、香附";
        medicineStr[22] = new String();
        medicineStr[22] = "西洋参、黄芪、白芍、葛根、蔓荆子、当归、川芎、生地、杜仲、续断、黄柏、甘草";
        medicineStr[23] = new String();
        medicineStr[23] = "当归、白芍、熟地、川芎、阿胶、艾叶炭、党参、炒白术、甘草、怀山药、芡实、白果、车前子、黄柏";
        medicineStr[24] = new String();
        medicineStr[24] = "当归、白芍、川芎、生地、黄芩、荆芥炭、地榆炭、黄柏、芡实、怀山药、白果、车前子、甘草、炒龟板";
        medicineStr[25] = new String();
        medicineStr[25] = "桃仁、红花、熟地、赤芍、当归、川芎、香附、莪术、官桂、广木香、川木通、炮甲、甘草";
        medicineStr[26] = new String();
        medicineStr[26] = "当归、白芍、柴胡、茯苓、炒白术、甘草、小海龙、炒鹿筋";
        medicineStr[27] = new String();
        medicineStr[27] = "西洋参、炒白术、茯苓、当归、川芎、白芍、熟地、甘草、巴戟天、淫羊藿";
        medicineStr[28] = new String();
        medicineStr[28] = "玄参、生地、麦冬、天冬、丹皮、地骨皮、白芍、阿胶、川芎、白芷、天麻、葛根、甘草";
        medicineStr[29] = new String();
        medicineStr[29] = "玄参、生地、麦冬、天冬、丹皮、地骨皮、白芍、阿胶、甘草";
        medicineStr[30] = new String();
        medicineStr[30] = "生地、白芍、当归、川芎、栀子炭、黄芩、阿胶、炒龟板、煅龙骨、甘草";
        medicineStr[31] = new String();
        medicineStr[31] = "生地、白芍、当归、川芎、荆芥炭、黄芩、地榆炭、甘草";
        medicineStr[32] = new String();
        medicineStr[32] = "当归、赤芍、丹皮、桃仁、酒大黄、红花、炮甲、甘草、连翘";
        medicineStr[33] = new String();
        medicineStr[33] = "厚朴、陈皮、苍术、甘草、法半夏、炒菜菔子、炮甲、路路通、小海龙";
        medicineStr[34] = new String();
        medicineStr[34] = "厚朴、陈皮、苍术、甘草、法半夏、炒菜菔子、炮甲、路路通、小海龙、山茱萸";
        medicineStr[35] = new String();
        medicineStr[35] = "陈皮、法半夏、茯苓、甘草、砂仁、黄芩、炒白术";
        medicineStr[36] = new String();
        medicineStr[36] = "熟地、白芍、当归、山茱萸、巴戟天、小海龙、炮甲";
        medicineStr[37] = new String();
        medicineStr[37] = "熟地、白芍、当归、山茱萸、巴戟天、小海龙、肉桂";
        medicineStr[38] = new String();
        medicineStr[38] = "熟地、白芍、当归、山茱萸、巴戟天、小海龙、炒鹿筋、桂枝、甘草、细辛、川木通";
        medicineStr[39] = new String();
        medicineStr[39] = "黄芪、当归、西洋参、通草、王不留行、麦冬、炮甲";
        medicineStr[40] = new String();
        medicineStr[40] = "黄芪、西洋参、当归、通草、王不留行、麦冬、炮甲、桔梗";
        medicineStr[41] = new String();
        medicineStr[41] = "西洋参、当归、酒白芍、熟地、川芎、阿胶、艾叶炭、侧柏炭、地榆炭、甘草";
        medicineStr[42] = new String();
        medicineStr[42] = "西洋参、当归、酒白芍、熟地、川芎、阿胶、艾叶炭、甘草";
        medicineStr[43] = new String();
        medicineStr[43] = "西洋参、黄芪、独活、秦艽、防风、细辛、熟地、白芍、当归、川芎、桂枝、茯苓、杜仲、川牛膝、续断、甘草";

        MedincineStrGeneration common=new MedincineStrGeneration();
        DataList data=new DataList();

        ArrayList<String> datalist=new ArrayList<>();
        ArrayList<String> data1=new ArrayList<>();
        ArrayList<String> data2=new ArrayList<>();
        ArrayList<Double> con=new ArrayList<>();
        int support=5;
        double confidence=0.9;
//        String json="麻黄、杏仁、生石膏、桑白皮、葶苈子、炙紫菀、百部、白前、陈皮、荆芥、川贝、桔梗、甘草, 炒瓜壳、桃仁、川贝、桑白皮、白前、法半夏、杏仁、桔梗、炙紫菀、百部、荆芥、薄荷、甘草、矮地茶, 桑叶、杏仁、沙参、麦冬、生石膏、阿胶、炙枇杷叶、甘草、桃仁、炒瓜壳、川贝、天花粉, 杏仁、桔梗、炙紫菀、百部、白前、陈皮、薄荷、矮地茶、甘草、桑白皮、川贝、青黛粉、海蛤粉, 麻黄、杏仁、炙冬花、法半夏、苏子、黄芩、白果、桑白皮、白芥子、炒菜菔子、甘草, 桑白皮、地骨皮、杏仁、炒瓜蒌壳、生大黄、生石膏、川贝、甘草, 麻黄、桂枝、白芍、细辛、五味子、干姜、法半夏、甘草, 芦根、桃仁、生薏苡仁、炒冬瓜子、黄连、法半夏、炒瓜壳、白及片、鱼腥草、野天麻、葛根、浙贝、防风, 百合、生地、熟地、玄参、浙贝、桔梗、麦冬、白芍、天冬、甘草、炙枇杷叶、白及片、丹皮、栀子炭、白茅根, 黄连、法半夏、炒瓜蒌壳、苇茎、薏苡仁、桃仁、椒目、葶苈、大枣、枳实, 香薷、厚朴、扁豆花、银花、连翘、黄芩、柴胡、法半夏、杏仁、甘草, 麻黄、杏仁、生石膏、桑白皮、葶苈子、炙紫菀、百部、白前、陈皮、川贝、桔梗、甘草、法半夏, 黄芪、炒白术、防风、炒瓜壳、川贝、杏仁、桔梗、炙紫菀、百部、白前、陈皮、法半夏、甘草、矮地茶, 桑叶、杏仁、沙参、麦冬、生石膏、阿胶珠、炙枇杷叶、甘草、桃仁、花粉, 麻黄、杏仁、炙冬花、法半夏、苏子、黄芩、白果、桑白皮、葶苈子、川贝、白芥子、炒菜菔子、厚朴、甘草, 麻黄、杏仁、甘草、苏子、桑白皮、川贝、生石膏、生大黄、炒瓜壳, 麻黄、射干、细辛、法半夏、炙紫菀、炙冬花、五味子、茯苓、大枣、生姜, 芦根、生薏苡仁、炒瓜壳、炒冬瓜子、杏仁、桔梗、炙紫菀、百部、陈皮、甘草、鱼腥草、浙贝、红藤, 百合、生地、熟地、玄参、浙贝、桔梗、麦冬、白芍、天冬、甘草、炙枇杷叶、白及片, 炒瓜壳、白芥子、川贝、椒目、茯苓、泽泻、猪苓、滑石、车前子、杏仁、枳实, 麻黄、杏仁、甘草、苏子、桑白皮、川贝、生石膏、炒瓜壳, 芦根、生薏苡仁、炒瓜壳、炒冬瓜子、杏仁、桔梗、炙紫菀、百部、陈皮、甘草、葶苈子、鱼腥草、浙贝、蒲公英、白及片、桑白皮、大枣, 西洋参、茯苓、炒白术、陈皮、法半夏、甘草、百合、百部、白及片, 鱼腥草、炒瓜壳、白芥子、川贝、椒目、茯苓、泽泻、猪苓、滑石、车前子、杏仁、枳实、甘草, 鱼腥草、炒瓜壳、白芥子、椒目、茯苓、泽泻、猪苓、滑石、车前子、杏仁、甘草、陈皮、法半夏, 芦根、生薏苡仁、炒瓜壳、炒冬瓜子、杏仁、桔梗、灸紫菀、百部、陈皮、甘草、鱼腥草、浙贝";
        try {
            datalist=(ArrayList<String>) common.getMedicalTableStr(medicineStr);
            data1=data.getmediacine1(datalist,support,confidence);
            data2=data.getmediacine2(datalist,support,confidence);
            con=data.getConfidence(datalist,support,confidence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(datalist);
        System.out.println(data1);
        System.out.println(data2);
        System.out.println(con);
    }
}
