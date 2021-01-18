function AssociationJSONToExcelConvertor(JSONData, FileName,title,filter) {
    //JSONData, FileName,title,filter
    console.log("AssociationJSONToExcelConvertor")
    console.log(JSONData)
    if(!JSONData)
        return;
    //转化json为object
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
    console.log(arrData)
    console.log(arrData.length)
    var excel = "<table>";

    //设置表头
    var row = "<tr>";

    if(title)
    {
        //使用标题项
        for (var i in title) {
            row += "<th align='center'>" + title[i] + '</th>';
        }

    }
    else{
        //不使用标题项
        for (var i in arrData[0]) {
            row += "<th align='center'>" + i + '</th>';
        }
    }

    excel += row + "</tr>";
let id=0;
let medicineArr1=new Array();
let medicineArr2 =new Array();
    //设置数据
    for (var i = 0; i < arrData.length; i++) {
        var row = "<tr>";

        id++;
        medicineArr1 =arrData[i].medicine1
        medicineArr2=arrData[i].medicine2
        // console.log(medicineArr1[i]+"----"+medicineArr2[i])
        var value = medicineArr1 == null ? "" :  medicineArr1;
        var value2 = medicineArr2 == null ? "" : medicineArr2;
        console.log(value+"-----"+value2+"------"+id)
        row += "<td align='center'>" + id + "</td>";
        row += "<td align='center'>" +'[' + value+ ']' + '->' + '[' +  value2  +  ']'+ "</td>";

        excel += row + "</tr>";
    }

    excel += "</table>";

    var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
    excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
    excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel';
    excelFile += '; charset=UTF-8">';
    excelFile += "<head>";
    excelFile += "<!--[if gte mso 9]>";
    excelFile += "<xml>";
    excelFile += "<x:ExcelWorkbook>";
    excelFile += "<x:ExcelWorksheets>";
    excelFile += "<x:ExcelWorksheet>";
    excelFile += "<x:Name>";
    excelFile += "{worksheet}";
    excelFile += "</x:Name>";
    excelFile += "<x:WorksheetOptions>";
    excelFile += "<x:DisplayGridlines/>";
    excelFile += "</x:WorksheetOptions>";
    excelFile += "</x:ExcelWorksheet>";
    excelFile += "</x:ExcelWorksheets>";
    excelFile += "</x:ExcelWorkbook>";
    excelFile += "</xml>";
    excelFile += "<![endif]-->";
    excelFile += "</head>";
    excelFile += "<body>";
    excelFile += excel;
    excelFile += "</body>";
    excelFile += "</html>";


    var uri = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(excelFile);

    var link = document.createElement("a");
    link.href = uri;

    link.style = "visibility:hidden";
    // link.download = FileName + ".xls";
    link.download = FileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}