var all=new Array();
var professions_huaNan=new Array();
var professions_huaShi=new Array();
var professions_jida=new Array();
var professions_guanwai=new Array();
var professions_shangda=new Array();
var professions_guangcai=new Array();

all[0]=professions_huaNan;  all[1]=professions_huaShi;  all[2]=professions_jida;
all[3]=professions_guanwai; all[4]=professions_shangda; all[5]=professions_guangcai;

professions_huaNan[0]="计算机科学与技术";professions_huaNan[1]="土木工程";professions_huaNan[2]="工程管理";
professions_huaNan[3]="数字媒体艺术";professions_huaNan[4]="汽车服务工程";

professions_huaShi[0]="心理健康教育";professions_huaShi[1]="人力资源管理";professions_huaShi[2]="工商管理";
professions_huaShi[3]="汉语言文学";professions_huaShi[4]="视觉传达设计";professions_huaShi[5]="艺术教育";
professions_huaShi[6]="学前教育";professions_huaShi[7]="汽车服务工程";

professions_jida[0]="金融学";professions_jida[1]="新闻学";professions_jida[2]="会计学";professions_jida[3]="会展经济与管理";
professions_jida[4]="物业管理";professions_jida[5]="食品卫生与营养学";professions_jida[6]="公共事业管理";

professions_guanwai[0]="国际经济与贸易";professions_guanwai[1]="市场营销";professions_guanwai[2]="商务英语";
professions_guanwai[3]="采购管理";professions_guanwai[4]="秘书学";professions_guanwai[5]="日语";
professions_guanwai[6]="网络工程";

professions_shangda[0]="人力资源管理";professions_shangda[1]="旅游管理";professions_shangda[2]="财务会计与审计";
professions_shangda[3]="公共事业管理";professions_shangda[4]="艺术设计";professions_shangda[5]="商务秘书";
professions_shangda[6]="行政管理";

professions_guangcai[0]="行政管理";professions_guangcai[1]="市场营销";professions_guangcai[2]="工商管理";
professions_guangcai[3]="电子政务";professions_guangcai[4]="税收学";professions_guangcai[5]="法学";
professions_guangcai[6]="环境设计";

function getAcademy(academy) {
    switch(academy) {
        case 0:return "华南理工大学";
        case 1:return "华南师范大学";
        case 2:return "暨南大学";
        case 3:return "广东外语外贸大学";
        case 4:return "汕头大学";
        case 5:return "广东财经大学";
        default:return "";
    }
}
// function getProfession(profession) {
//     switch(profession) {
//         case 0:return "计算机科学与技术";
//         default:return "";
//     }
// }
function getProfession(academy,profession) {
    if(academy>=all.length||profession>=all[academy].length)
        return "";
    return all[academy][profession];
}
function getLevel(level) {
    switch(level) {
        case 0 : return "专科";
        case 1 : return "专升本";
        default : return "";
    }
}
function getType(type) {
    switch(type) {
        case 0 : return "自学考试";
        case 1 : return "远程教育";
        case 2 : return "成人高考";
        default : return "";
    }
}
function getParameter(href,key) {
    var parameterString=href.split("?")[1];
    if(parameterString==null)
        return "";
    var parameters=parameterString.split("&");
    for(var i=parameters.length-1;i>=0;i--) {
        if(parameters[i].split("=")[0]==key)
            return parameters[i].split("=")[1];
    }
    return "";
}