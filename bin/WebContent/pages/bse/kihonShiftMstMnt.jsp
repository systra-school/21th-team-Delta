<!-- kihonShiftMstMnt.jsp -->
<%@page import="constant.CommonConstant.DayOfWeek"%>
<%
/**
* ファイル名：kihonShiftMstMnt.jsp
*
* 変更履歴
* 1.0  2010/11/13 新規作成 西岡孝太郎
*/
%>
<%@ page contentType="text/html; charset=Shift_JIS" pageEncoding="Shift_JIS"%>
<%@ page import="constant.RequestSessionNameConstant"%>
<%@ page import="constant.CommonConstant"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<% String color = ""; %>
<html>
  <head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
    <script type="text/javascript" src="/kikin_test/pages/js/common.js">
    </script>
    <script type="text/javascript" src="/kikin_test/pages/js/checkCommon.js">
    </script>
    <script type="text/javascript" src="/kikin_test/pages/js/message.js">
    </script>
    <script type="text/javascript" language="Javascript1.1">
    /**
     * 登録へ
     */
    function regist() {
        // サブミット
        doSubmit('/kikin/kihonShiftRegist.do');
    }
    </script>

    <title>
    基本シフト登録
    </title>
    <link href="/kikin_test/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <div id="wrapper">
      <div id="header">
        <table>
          <tr>
            <td id="headLeft">
              <input value="戻る" type="button" class="smlButton"  onclick="doSubmit('/kikin/kihonShiftBack.do')" />
            </td>
            <td id="headCenter">
              基本シフト登録
            </td>
            <td id="headRight">
              <input value="ログアウト" type="button" class="smlButton"  onclick="logout()" />
            </td>
          </tr>
        </table>
      </div>
      <div id="gymBody">
        <html:form action="/kihonShiftRegist" >
          <%-- 凡例 --%>
          <div id="hanrei" style="width:476px; margin-left:80px; float:left">
            <div>
              <table class="tblHeader" border="1" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="100px" align="center">
                    シフト名
                  </td>
                  <td width="150px" align="center">
                    シンボル
                  </td>
                  <td width="150px" align="center">
                    時間
                  </td>
                  <td width="50px" align="center">
                    休憩
                  </td>
                </tr>
              </table>
            </div>
            <div style="height:450px;overflow:auto">
              <table class="tblBody" border="1" cellpadding="0" cellspacing="0">
                <logic:iterate id="kihonShiftHanreiBeanList" name="kihonShiftMstMntForm"  property="kihonShiftHanreiBeanList" indexId="idx">
                  <tr>
                    <td width="100px" align="center">
                      <bean:write property="shiftName" name="kihonShiftHanreiBeanList"/>
                    </td>
                    <td width="150px" align="center">
                      <bean:write property="symbol" name="kihonShiftHanreiBeanList"/>
                    </td>
                    <td width="150px" align="center">
                      <bean:write property="timeZone" name="kihonShiftHanreiBeanList" filter="false"/>
                    </td>
                    <td width="50px" align="center">
                      <bean:write property="kyukei" name="kihonShiftHanreiBeanList"/>
                    </td>
                  </tr>
                </logic:iterate>
              </table>
            </div>
          </div>
          <div id="data" style="width:614px; margin-left:20px; float:left">
            <div>
              <table class="tblHeader" border="1" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="230px" align="center">
                    社員名
                  </td>
                  <td width="50px" align="center">
                    月
                  </td>
                  <td width="50px" align="center">
                    火
                  </td>
                  <td width="50px" align="center">
                    水
                  </td>
                  <td width="50px" align="center">
                    木
                  </td>
                  <td width="50px" align="center">
                    金
                  </td>
                  <td width="50px" align="center" style="color:blue;">
                    土
                  </td>
                  <td width="50px" align="center" style="color:red;">
                    日
                  </td>
                </tr>
              </table>
            </div>
            <div style="height:450px;overflow:auto">
              <table class="tblBody" border="1" cellpadding="0" cellspacing="0">
                <logic:iterate id="kihonShiftMstMntBeanList" name="kihonShiftMstMntForm" property="kihonShiftMstMntBeanList" indexId="idx">
                  <tr>
                    <html:hidden name="kihonShiftMstMntBeanList" property="shainId" />
                    <td width="230px" align="center">
                      <bean:write property="shainName" name="kihonShiftMstMntBeanList"/>
                    </td>
                    <td width="50px" align="center">
                      <html:select property="shiftIdOnMonday" name="kihonShiftMstMntBeanList" indexed="true">
                      <html:optionsCollection name="kihonShiftMstMntForm" property="shiftCmbMap" value="key" label="value"/>
                      </html:select>
                    </td>
                    <td width="50px" align="center">
                      <html:select property="shiftIdOnTuesday" name="kihonShiftMstMntBeanList" indexed="true">
                      <html:optionsCollection name="kihonShiftMstMntForm" property="shiftCmbMap" value="key" label="value"/>
                      </html:select>
                    </td>
                    <td width="50px" align="center">
                      <html:select property="shiftIdOnWednesday" name="kihonShiftMstMntBeanList" indexed="true">
                      <html:optionsCollection name="kihonShiftMstMntForm" property="shiftCmbMap" value="key" label="value"/>
                      </html:select>
                    </td>
                    <td width="50px" align="center">
                      <html:select property="shiftIdOnThursday" name="kihonShiftMstMntBeanList" indexed="true">
                      <html:optionsCollection name="kihonShiftMstMntForm" property="shiftCmbMap" value="key" label="value"/>
                      </html:select>
                    </td>
                    <td width="50px" align="center">
                      <html:select property="shiftIdOnFriday" name="kihonShiftMstMntBeanList" indexed="true">
                      <html:optionsCollection name="kihonShiftMstMntForm" property="shiftCmbMap" value="key" label="value"/>
                      </html:select>
                    </td>
                    <td width="50px" align="center">
                      <html:select property="shiftIdOnSaturday" name="kihonShiftMstMntBeanList" indexed="true">
                      <html:optionsCollection name="kihonShiftMstMntForm" property="shiftCmbMap" value="key" label="value"/>
                      </html:select>
                    </td>
                    <td width="50px" align="center">
                      <html:select property="shiftIdOnSunday" name="kihonShiftMstMntBeanList" indexed="true">
                      <html:optionsCollection name="kihonShiftMstMntForm" property="shiftCmbMap" value="key" label="value"/>
                      </html:select>
                    </td>
                  </tr>
                </logic:iterate>
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
