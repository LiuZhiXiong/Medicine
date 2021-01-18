/**
 * 选择药对
 */
function select_val() {
    //获取select的值
   // sel_val = $("#select").find("option:selected").val()
    sel_val = $("#select").find("option:selected").val()
    console.log("你选择了----")
    console.log(sel_val)
    var res=1;

    if (sel_val == "defalut") {
        res=0;
        Showbo.Msg.alert('请选择药对组个数')
        // alert("请选择药对组个数")
    }
    return res;
}

/**
 * 选择数据量
 */
function select_num() {
    //获取select的值
    sel_num = $("#frequencySelect").find("option:selected").val()
console.log(sel_num)
    var res=1;

    if (sel_num == '数据量') {
        res=0;
        // alert("请选择需要生成的药对数量")
        Showbo.Msg.alert('请选择需要生成的药对数量')
    }
    return res;
}
