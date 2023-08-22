<!-- shiftHanrei.jsp -->
<%@page import="constant.CommonConstant.DayOfWeek"%>
<%
/**
* ファイル名：kihonShiftMstMnt.jsp
*
* 変更履歴
* 1.0  2010/11/13 新規作成 楢木 一矢
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
    <script type="text/javascript" src="/kikin/pages/js/common.js">
    </script>
    <script type="text/javascript" src="/kikin/pages/js/checkCommon.js">
    </script>
    <script type="text/javascript" src="/kikin/pages/js/message.js">
    </script>
    <script type="text/javascript" language="Javascript1.1">
    </script>

    <title>
      シフト凡例
    </title>
    <link href="/kikin/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <div id="wrapper">
      <div id="header">
        <table>
          <tr>
            <td id="headLeft">
            </td>
            <td id="headCenter">
              シフト凡例
            </td>
            <td id="headRight">
            </td>
          </tr>
        </table>
      </div>
      <div id="gymBody">
        <html:form>
          <%-- 凡例 --%>
          <div id="hanrei" style="width:470px; margin-left:15px; float:left">
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
                <logic:iterate id="shiftHanreiBeanList" name="shiftHanreiForm"  property="shiftHanreiBeanList" indexId="idx">
                  <tr>
                    <td width="100px" align="center">
                      <bean:write property="shiftName" name="shiftHanreiBeanList"/>
                    </td>
                    <td width="150px" align="center">
                      <bean:write property="symbol" name="shiftHanreiBeanList"/>
                    </td>
                    <td width="150px" align="center">
                      <bean:write property="timeZone" name="shiftHanreiBeanList" filter="false"/>
                    </td>
                    <td width="50px" align="center">
                      <bean:write property="kyukei" name="shiftHanreiBeanList"/>
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
            </td>
          </tr>
        </table>
      </div>
    </div>
  </body>
</html>
