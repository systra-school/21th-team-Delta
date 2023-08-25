<!-- kinmuJissekiNyuryokuKakunin.jsp -->
<%@page import="constant.CommonConstant.DayOfWeek"%>
<%
/**
 * ファイル名：kinmuJissekiNyuryokuKakunin.jsp
 *
 * 変更履歴
 * 1.0  2010/09/13 Kazuya.Naraki
 */
%>
<%@page contentType="text/html; charset=Shift_JIS" pageEncoding="Shift_JIS"%>
<%@ page import="constant.RequestSessionNameConstant"%>
<%@ page import="constant.CommonConstant"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<bean:define id="color" value="" type="java.lang.String"/>
<bean:size id="dateBeanListSize" name="kinmuJissekiNyuryokuKakuninForm"  property="dateBeanList"/>
<bean:size id="kinmuJissekiNyuryokuKakuninListSize" name="kinmuJissekiNyuryokuKakuninForm"  property="kinmuJissekiNyuryokuKakuninList"/>
<html>
  <head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
    <script type="text/javascript" src="/kikin_test/pages/js/common.js"></script>
    <script type="text/javascript" src="/kikin_test/pages/js/checkCommon.js"></script>
    <script type="text/javascript" src="/kikin_test/pages/js/message.js"></script>
    <script type="text/javascript" language="Javascript1.1">
    /**
     * hh:mm形式と勤務開始時間＜勤務終了時間のチェック後エラーが無かったら登録する
     */
     function regist() {
    	// 一覧のサイズ
	    var listSize = <%= kinmuJissekiNyuryokuKakuninListSize %>;
      	// 開始時間エラーメッセージ
     	var startTimeErrMsg = '';
     	// 終了時間エラーメッセージ
     	var endTimeErrMsg = '';
     	// 休憩時間エラーメッセージ
     	var breakTimeErrMsg = '';
     	// From - To エラーメッセージ
     	var fromToErrMsg = '';
     	// エラーメッセージ
     	var errorMsg = '';
 
     	
		with(document.forms[0].elements) {
			for (var i = 0; i < listSize; i++) {
				// 開始時間を取得する。
	     	    var startTime = namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].startTime').value;
	     	    // 終了時間を取得する。
	     	    var endTime = namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].endTime').value;
	     	    // 休憩時間を取得する。
	     	    var breakTime = namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].breakTime').value;

	     	    // 背景色をクリアする
	     	    namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].startTime').style.backgroundColor = 'white';
	     	    namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].endTime').style.backgroundColor = 'white';
	     	    namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].breakTime').style.backgroundColor = 'white';

	     	    // 時間チェック
	     	    if (!startTimeErrMsg) {	
	     	    	if (startTime != '' && !checkTime(startTime)) {
	     	        	var strArr = ['開始時間'];
	     	            startTimeErrMsg = getMessage('E-MSG-000004', strArr);
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].startTime').style.backgroundColor = 'red';
	     	     	}
	     	     }
	     	     if (!endTimeErrMsg) {
	     	     	if (endTime != '' &&!checkTime(endTime)) {
	     	        	var strArr = ['終了時間'];
	     	            endTimeErrMsg = getMessage('E-MSG-000004', strArr);
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].endTime').style.backgroundColor = 'red';
	     	        }
	     	     }
	     	     if (!breakTimeErrMsg) {
	     	     	if (breakTime != '' &&!checkTime(breakTime)) {
	     	        	var strArr = ['休憩時間'];
	     	            breakTimeErrMsg = getMessage('E-MSG-000004', strArr);
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].breakTime').style.backgroundColor = 'red';
	     	        }
	     	     }

	     	     // from - to のチェック
	     	     if (!checkTimeCompare(startTime, endTime)) {
	     	      	if (checkTime(startTime) && checkTime(endTime)) {
	     	        	fromToErrMsg = getMessageCodeOnly('E-MSG-000005');
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].startTime').style.backgroundColor = 'red';
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].endTime').style.backgroundColor = 'red';
	     	      	}
	     	     }
	     	     // エラーメッセージ
	     	     errorMsg = startTimeErrMsg + endTimeErrMsg + breakTimeErrMsg + fromToErrMsg;
	     	    
	     	     
	     	     if (errorMsg) {
	     	      	alert(errorMsg);
	     	        // エラー
	     	      	return false;
	     	     }
			}
			
			if(!errorMsg) {
				doSubmit('/kikin_test/kinmuJissekiNyuryokuKakuninRegist.do');
			}
     	}	
    }
    /**
     * 検索
     */
    function submitSearch() {
        doSubmit('/kikin_test/kinmuJissekiNyuryokuKakuninSearch.do');
    }
    
    
    </script>
    <title>勤務実績入力画面</title>

    <link href="/kikin_test/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
  <body >
    <div id="wrapper">
      <div id="header">
        <table width="100%">
          <tr>
            <td id="headLeft">
              <input value="戻る" type="button" class="smlButton"  onclick="doSubmit('/kikin_test/kinmuJissekiNyuryokuKakuninBack.do')" />
            </td>
            <td id="headCenter">
              勤務実績入力
            </td>
            <td id="headRight">
                <input value="ログアウト" type="button" class="smlButton"  onclick="logout()" />
              </td>
          </tr>
        </table>
      </div>
      <div id="gymBody">
        <html:form action="/shainMstMntRegist" >
          <div style="float: left; width: 100%;">
            <div style="float: left; width: 804px; text-align: left; margin-left:100px;">
              表示年月：
              <html:select name="kinmuJissekiNyuryokuKakuninForm" property="yearMonth" onchange="submitSearch()">
              <html:optionsCollection name="kinmuJissekiNyuryokuKakuninForm"
                                      property="yearMonthCmbMap"
                                      value="key"
                                      label="value"/>
              </html:select>
            </div>
            <div style="float: left; width: 284px; text-align: left;">
              社員ID&nbsp;<bean:write name="kinmuJissekiNyuryokuKakuninForm" property="shainId"/>
              ：社員名&nbsp;<bean:write name="kinmuJissekiNyuryokuKakuninForm" property="shainName"/>
            </div>
          </div>
          <div>
            <div style="overflow: hidden; width: 1080px; margin-left:80px;">
            <table border="1" cellpadding="0" cellspacing="0">
              <tr class="tblHeader" style="overflow: hidden; width: 1080px; margin-left:80px;">
                <td width="80px" align="center">
                  日付
                </td>
                <td width="50px" align="center">
                  曜
                </td>
                <td width="100px" align="center">
                  シフト
                </td>
                <td width="100px" align="center">
                  開始時刻
                </td>
                <td width="100px" align="center">
                  終了時刻
                </td>
                <td width="100px" align="center">
                  休憩
                </td>
                <td width="100px" align="center">
                  実働時間
                </td>
                <td width="100px" align="center">
                  時間外
                </td>
                <td width="100px" align="center">
                  休日
                </td>
                <td width="220px" align="center">
                  備考
                </td>
              </tr>
            <logic:iterate id="kinmuJissekiNyuryokuKakuninList" name="kinmuJissekiNyuryokuKakuninForm" property="kinmuJissekiNyuryokuKakuninList" indexId="idx">
                  <div>
                  <tr class="tblBody" >
                  <html:hidden name="kinmuJissekiNyuryokuKakuninList" property="shainId" />
                  <td width="80px" align="center">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="kadouDayDisp" /><br>
                  </td>
                  <bean:define id="youbi" name="kinmuJissekiNyuryokuKakuninList" property="youbi"/>

                  <%
                  if (DayOfWeek.SATURDAY.getRyaku().equals(youbi)) {
                      color = "fontBlue";
                  } else if (DayOfWeek.SUNDAY.getRyaku().equals(youbi)) {
                      color = "fontRed";
                  } else {
                      color = "fontBlack";
                  }
                  %>

                  <td width="50px" align="center" class="<%=color %>">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="youbi" /><br>
                  </td>
                  <td width="100px" align="center">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="symbol" /><br>
                  </td>
                  <td width="100px" align="center">
                    <html:text style="text-align:center" size="10" maxlength="5" name="kinmuJissekiNyuryokuKakuninList" property="startTime" indexed="true"></html:text><br>
                  </td>
                  <td width="100px" align="center">
                    <html:text style="text-align:center" size="10" maxlength="5" name="kinmuJissekiNyuryokuKakuninList" property="endTime" indexed="true"></html:text><br>
                  </td>
                  <td width="100px" align="center">
                    <html:text style="text-align:center" size="10" maxlength="5" name="kinmuJissekiNyuryokuKakuninList" property="breakTime" indexed="true"></html:text><br>
                  </td>
                  <td width="100px" align="center">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="jitsudouTime" /><br>
                  </td>
                  <td width="100px" align="center">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="jikangaiTime" /><br>
                  </td>
                  <td width="100px" align="center">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="kyuujitsuTime" /><br>
                  </td>
                  <td width="220px" align="left">
                    <html:text style="text-align:left" size="40" name="kinmuJissekiNyuryokuKakuninList" property="bikou" indexed="true"></html:text><br>
                  </td>
                </tr>
            </logic:iterate>
            </div>
            </table>
          </div>
          </div>
        </html:form>
      </div>
      <div id="footer">
        <table>
          <tr>
              <td id="footLeft">
                　
              </td>
              <td id="footCenter">
                　
              </td>
              <td id="footRight">
                <input value="登録"  type="button" class="smlButton"  onclick="regist()" />
              </td>
          </tr>
        </table>
    </div>
    </div>
  </body>
</html>