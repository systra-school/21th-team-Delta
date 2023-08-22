<!-- kinmuJissekiKakunin.jsp -->
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
<%
String color = "";
%>
<html>
  <head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
    <script type="text/javascript" src="/kikin/pages/js/common.js"></script>
    <script type="text/javascript" src="/kikin/pages/js/checkCommon.js"></script>
    <script type="text/javascript" src="/kikin/pages/js/message.js"></script>
    <script type="text/javascript" language="Javascript1.1">
    <!--

    /**
     * 検索
     */
    function submitSearch() {
        doSubmit('/kikin/kinmuJissekiKakuninSearch.do');
    }
    -->
    </script>
    <title>勤務実績確認画面</title>

    <link href="/kikin/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <div id="wrapper">
      <div id="header">
        <table width="100%">
          <tr>
            <td id="headLeft">
              <input value="戻る" type="button" class="smlButton"  onclick="doSubmit('/kikin/kinmuJissekiKakuninBack.do')" />
            </td>
            <td id="headCenter">
              勤務実績確認
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
            <div style="float: left; width: 844px; text-align: left; margin-left:100px;">
              表示年月：
              <html:select name="kinmuJissekiKakuninForm" property="yearMonth" onchange="submitSearch()">
              <html:optionsCollection name="kinmuJissekiKakuninForm"
                                      property="yearMonthCmbMap"
                                      value="key"
                                      label="value"/>
              </html:select>
            </div>
            <div style="float: left; width: 244px; text-align: left;">
              社員名：
              <html:select name="kinmuJissekiKakuninForm" property="shainId" onchange="submitSearch()">
              <html:optionsCollection name="kinmuJissekiKakuninForm"
                                      property="shainCmbMap"
                                      value="key"
                                      label="value"/>
              </html:select>
            </div>
          </div>
          <div style="width: 1088px; margin-left:100px;">
            <table class="tblHeader" border="1" cellpadding="0" cellspacing="0">
              <tr>
                <td width="50px" align="center">
                  日付
                </td>
                <td width="30px" align="center">
                  曜
                </td>
                <td width="50px" align="center">
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
                <td width="320px" align="center">
                  備考
                </td>
              </tr>
            </table>
          </div>
          <div style="overflow: auto; height: 400px; width: 1088px; margin-left:100px;" >
            <logic:iterate id="kinmuJissekiNyuryokuKakuninList" name="kinmuJissekiKakuninForm" property="kinmuJissekiNyuryokuKakuninList" indexId="idx">
              <table class="tblBody" border="1" cellpadding="0" cellspacing="0">
                <tr>
                  <html:hidden name="kinmuJissekiNyuryokuKakuninList" property="shainId" />
                  <td width="50px" align="center">
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

                  <td width="30px" align="center" class='<%=color %>'">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="youbi" /><br>
                  </td>
                  <td width="50px" align="center" style="vertical-align: middle;">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="symbol" /><br>
                  </td>
                  <td width="100px" align="center">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="startTime" /><br>
                  </td>
                  <td width="100px" align="center">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="endTime" /><br>
                  </td>
                  <td width="100px" align="center">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="breakTime" /><br>
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
                  <td width="320px" align="left">
                    <bean:write name="kinmuJissekiNyuryokuKakuninList" property="bikou" /><br>
                  </td>
                </tr>
              </table>
            </logic:iterate>
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
                　
              </td>
          </tr>
        </table>
    </div>
  </div>
  </body>
</html>