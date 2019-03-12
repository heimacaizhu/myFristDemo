/*
 * 检查姓名,只由字符或者汉字组成
 * 验证成功返回true，否则返回false
 */
function cheackName(name){
	var Expression = /^[A-Za-z\u4e00-\u9fa5]+$/;
	var objExp = new RegExp(Expression);
	if (objExp.test(name)) {
		return true;
	} else{
		return false;
	}
}

/*
 * 验证昵称,可以由数字，汉字，英文字母，下划线以及-组成
 * 验证成功返回true,否则返回false
 */
function cheackNickName(nickName){
	var expression  = /^[A-Za-z0-9_\-\u4e00-\u9fa5]+$/;
	var objExp = new RegExp(expression);
	if (objExp.test(nickName)) {
		return true;
	} else{
		return false;
	}
}

/*
 * 验证密码，密码只能由英文字母，数字，组成，且不能包含相关非法字符串,且密码长度只能是6到16位
 * 验证成功返回true,否则返回false
 */
function cheackPwd(pwd){
	//检查密码长度
	if(pwd.length<6||pwd.length>16){
		return false;
	}
	//验证是否是只由英文字母，数字组成
	var expression1 = /^[A-Za-z0-9]+$/;
	var objExp1 = new RegExp(expression1);
	if(!objExp1.test(pwd)){
		return false;
	}
	//验证是否包含非法字符串
	var expression2 = /(script)|(insert)|(select)|(update)|(delete)|(create)|(src)|(url)|(href)|(drop)/g;
	var pwdTemp = pwd.replace(expression2,"b");
	if (pwd==pwdTemp) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检查非法字符串
 * 验证成功返回true,否则返回false
 */
function cheackStr(str){
	var expression = /(script)|(insert)|(select)|(update)|(delete)|(create)|(src)|(url)|(href)|(drop)/g;
	var strTemp = str.replace(expression,"b");
	if (str==strTemp) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检查正整数和正一位浮点数
 * 符合返回true,否则返回false
 */
function cheackNumber1(num){
	var expression = /^[1-9]\d{0,}.\d|^[1-9]\d{0,}$/;
	var objExp = new RegExp(expression);
	if (objExp.test(num)) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检查正整数
 */
function cheackNumber2(num){
	var expression = /^[1-9]\d{1,}$|^[1-9]$/;
	var objExp = new RegExp(expression);
	if (objExp.test(num)) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检测正整数和正两位浮点数
 */
function cheackNumber3(num){
	var expression = /^[1-9]\d{0,}.\d{1,2}$|^[1-9]\d{0,}$/;
	var objExp = new RegExp(expression);
	if (objExp.test(num)) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检查商品折扣
 */
function cheachDiscount(discount){
	var expression = /^[1-9]\d{0,}.\d|^[1-9]\d{0,}$/;
	var objExp = new RegExp(expression);
	if (!objExp.test(discount)) {
		return false;
	}
	discount = parseFloat(discount);
	if (discount>10) {
		return false;
	}
	return true;
}


/*
 * 检测英文字母，数字组成的4位验证码
 */
function cheackCode(code){
	var expression = /^[0-9a-zA-Z]{4}$/;
	var objExp = new RegExp(expression);
	if (objExp.test(code)) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检查日期 格式2016-10-04,并且按照闰年和平年严格判断，且不是过去的时间
 * 符合要求返回true,否则返回false
 */
function cheackDate(datet){
	//普通判断
	var expression = /^\d{4}(\-)\d{1,2}\1\d{1,2}$/;
	var objExp = new RegExp(expression);
	if (!objExp.test(datet)) {
		return false;
	}
	return true;
	/*//根据是否是闰年来判断
	var yearFlag = false;//闰年标志
	var year = datet.substring(0,4);
	year = parseInt(year);
	if (isNaN(year)) {
		//判断是不是闰年
		if((year%4==0&&year%100!=0)||(year%400==0)){
			yearFlag = true;
		}
	}
	var month = datet.substring(5,6);
	//判断月份是否小于10,如果小于10直接截取下一位，否则截取两位
	if (month=="0") {
		//月份小于10，直接截取第二位
		month = datet.substring(6,7);
	} else if (month=="1") {
		month = datet.substring(5,7);
	}else{
		//不是正确的月份表示方法
		return false;
	}
	month = parseInt(month);
	//判断月份转换过后是不是数字或者不在范围之内，不是返回false
	if (isNaN(month)||month>12||month<=0) {
		return false;
	}
	//截取当前天数,先截取天数的第一位，判断是不是0，如果是截取下一位，否则截取两位
	var day = datet.substring(8,9);
	if(day=="0"){
		day = datet.substring(9,10);
	}else if (day=="1"||day=="2"||day=="3") {
		day = datet.substring(8,10);
	}else{
		return false;
	}
	if (isNaN(day)) {
		return false;
	}
	//获取当前月份的最大天数
	var dayTemp = 30;
	switch (month){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
		    dayTemp = 31;
			break;
		case 2:
		    if (yearFlag) {
		    	dayTemp = 29;
		    }else{
		    	dayTemp = 28;
		    }
			break;
	}
	//判断日期是否小于0或者大于最大天数
	if (day<=0||day>dayTemp) {
		return false;
	}
	//获取当前的时间，判断驶入的日期是否是过去的日期，如果是返回false，否则返回true
	var d = new Date();
	//年份
	var y = d.getFullYear();
	//月份
	var m = d.getMonth()+1;
	//day
	var dd = d.getDate();
	if (year<y) {
		return false;
	} else{
		if (month<m) {
			return false;
		} else{
			if (day<dd) {
				return false;
			} else{
				return true;
			}
		}
	}*/
}

/*
 * 检测正整数
 * 符合要求返回true,否则返回false
 */
function cheackPositiveInteger(num){
	var  expression = /^[1-9]\d{1,}$/;
	var objExp = new RegExp(expression);
	if (objExp.test(num)) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检测手机号
 * 符合要求返回true,否则返回false
 */
function cheackPhone(phone){
	var expression=/0?(13|14|15|18)[0-9]{9}/;
	var objExp = new RegExp(expression);
	if (objExp.test(phone)) {
		return true;
	} else{
		return false;
	}
}

/*
 *检查身份证号 
 */
function cheackIdCard(idCard){
	//检测身份证号码
	//15位和18位身份证号码的正则表达式
     var Expression=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
     var objExp = new RegExp(Expression);
    //如果通过该验证，说明身份证格式正确，但准确性还需计算
    if(objExp.test(idCard)){
        if(idCard.length==18){
            var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
            var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
            var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
            for(var i=0;i<17;i++){
                idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
            }
            var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
            var idCardLast=idCard.substring(17);//得到最后一位身份证号码
             //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if(idCardMod==2){
                if(idCardLast=="X"||idCardLast=="x"){
                	return true;
                }else{
                	return false;
                }
            }else{
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if(idCardLast==idCardY[idCardMod]){
                	return true;
                }else{
                	return false;
                }
            }
        } 
    }else{
    	return false;
    }
}

/*
 * 检测订单号
 */
function cheackOrder(od){
	var expression=/^2\d{23}$/;
	var objExp = new RegExp(expression);
	if (objExp.test(od)) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检测邮政编码
 */
function cheackYZCode(code){
	var expression = /^[1-9][0-9]{5}$/;
	var objExp = new RegExp(expression);
	if (objExp.test(code)) {
		return true;
	} else{
		return false;
	}
}


/*
 *检测邮箱账号 
 */
function cheackEmail(email){
	//检验邮箱
	var  Expression = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	var objExp = new RegExp(Expression);
	if (objExp.test(email)==true) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检测24位订单号
 */
function cheackOrders(oid){
	var  Expression = /^\d{24}$/;
	var objExp = new RegExp(Expression);
	if (objExp.test(oid)==true) {
		return true;
	} else{
		return false;
	}
}

/*
 * 检测输入的账号
 * */
function cheackLoginAccout(accout){
	var  Expression = /^2\d{12}$/;
	var objExp = new RegExp(Expression);
	if (objExp.test(accout)==true) {
		return true;
	} else{
		return false;
	}
}
