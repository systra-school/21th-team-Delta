<!-- tsukibetsuShiftKakunin.jsp -->
<%@page import="constant.CommonConstant.DayOfWeek"%>
<%@page import="business.logic.utils.CheckUtils"%>
<%@page import="form.common.DateBean"%>
<%@page import="java.util.List"%>
<%@page import="form.mth.TsukibetsuShiftKakuninForm"%>
<%
/**
 * ファイル名：tsukibetsuShiftKakunin.jsp
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

<bean:size id="dateBeanListSize" name="tsukibetsuShiftKakuninForm" property="dateBeanList"/>
<bean:size id="listSize" name="tsukibetsuShiftKakuninForm" property="tsukibetsuShiftKakuninBeanList"/>
<bean:define id="showLength" value="17" type="java.lang.String"/>
<bean:define id="offset" name="tsukibetsuShiftKakuninForm" property="offset" />
<bean:define id="color" value="" type="java.lang.String"/>
<bean:define id="cntPage" name="tsukibetsuShiftKakuninForm" property="cntPage" type="java.lang.Integer"/>
<bean:define id="maxPage" name="tsukibetsuShiftKakuninForm" property="maxPage" type="java.lang.Integer"/>

<%
final int heightSize = 22;

int intShowLength = Integer.parseInt(showLength);

// 表示しているリストサイズの調整
if (cntPage.intValue() == maxPage.intValue()) {
    listSize = listSize % intShowLength;
}

if (listSize > intShowLength) {
    listSize = intShowLength;
}

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
     * 登録
     */
    function submitPrint() {
        // サブミット
        doSubmit('/kikin/tsukibetsuShiftKakuninPrint.do');
    }

    /**
     * 検索
     */
    function submitSearch() {
        doSubmit('/kikin/tsukibetsuShiftKakuninSearch.do');
    }
    /**
     * サブウィンドウを開く
     */
    function openWindow(){
        window.open("/kikin/shiftHanrei.do?param=", null, "menubar=no, toolbar=no, scrollbars=auto, resizable=yes, width=520px, height=650px");
    }
    -->
    </script>
    <title>月別シフト確認画面</title>

    <link href="/kikin/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <div id="wrapper">
      <div id="header">
        <table>
          <tr>
            <td id="headLeft">
              <input value="戻る" type="button" class="smlButton"  onclick="doSubmit('/kikin/tsukibetsuShiftKakuninBack.do')" />
            </td>
            <td id="headCenter">
              月別シフト確認
            </td>
            <td id="headRight">
              <input value="ログアウト" type="button" class="smlButton"  onclick="logout()" />
            </td>
          </tr>
        </table>
      </div>
      <div id="gymBody" style="overflow:auto; height: 470px;">
        <html:form action="/shukkinKibouKakuninInit" >
          <div style="margin-left:50px;">
            <div style="height: 25px;">
              表示年月：
              <bean:define id="sessionYearMonth" name="tsukibetsuShiftKakuninForm" property="yearMonth" type="String"/>
              <html:select property="yearMonth" name="tsukibetsuShiftKakuninForm"  onchange="submitSearch()">
              <html:optionsCollection name="tsukibetsuShiftKakuninForm"
                                      property="yearMonthCmbMap"
                                      value="key"
                                      label="value"/>
              </html:select>
              <html:link href="/kikin/tsukibetsuShiftKakuninPage.do?paging=back">前へ</html:link>
              <html:link href="/kikin/tsukibetsuShiftKakuninPage.do?paging=next">次へ</html:link>
              <bean:write name="tsukibetsuShiftKakuninForm" property="cntPage"/>/
              <bean:write name="tsukibetsuShiftKakuninForm" property="maxPage"/>
            </div>
            <table width="1100px" cellpadding="0" cellspacing="0">
              <tr>
                <td width="150px" valign="top">
                  <table class="tblHeader" border="1" cellpadding="0" cellspacing="0">
                    <tr height="<%=heightSize %>px">
                      <td width="150px" align="center">
                        &nbsp;
                      </td>
                    </tr>
                    <tr height="<%=heightSize %>px">
                      <td width="150px" align="center">
                      社員名
                      </td>
                    </tr>
                    <logic:iterate offset="offset" length="<%=showLength %>"  id="tsukibetsuShiftKakuninBeanList" name="tsukibetsuShiftKakuninForm" property="tsukibetsuShiftKakuninBeanList">
                      <tr  class="tblBody"  height="<%=heightSize %>px">
                        <td width="150px" align="center">
                          <bean:write property="shainName" name="tsukibetsuShiftKakuninBeanList"/><br>
                        </td>
                      </tr>
                    </logic:iterate>
                  </table>
                </td>
                <td>
                	<div style="overflow-x: auto;overflow-y: auto; width:985px; text-align:center;">
                    <table class="tblHeader" border="1" cellpadding="0" cellspacing="0">
                      <tr height="<%=heightSize %>px">
                        <td width="40px" align="center">
                          1
                        </td>
                        <td width="40px" align="center">
                          2
                        </td>
                        <td width="40px" align="center">
                          3
                        </td>
                        <td width="40px" align="center">
                          4
                        </td>
                        <td width="40px" align="center">
                          5
                        </td>
                        <td width="40px" align="center">
                          6
                        </td>
                        <td width="40px" align="center">
                          7
                        </td>
                        <td width="40px" align="center">
                          8
                        </td>
                        <td width="40px" align="center">
                          9
                        </td>
                        <td width="40px" align="center">
                          10
                        </td>
                        <td width="40px" align="center">
                          11
                        </td>
                        <td width="40px" align="center">
                          12
                        </td>
                        <td width="40px" align="center">
                          13
                        </td>
                        <td width="40px" align="center">
                          14
                        </td>
                        <td width="40px" align="center">
                          15
                        </td>
                        <td width="40px" align="center">
                          16
                        </td>
                        <td width="40px" align="center">
                          17
                        </td>
                        <td width="40px" align="center">
                          18
                        </td>
                        <td width="40px" align="center">
                          19
                        </td>
                        <td width="40px" align="center">
                          20
                        </td>
                        <td width="40px" align="center">
                          21
                        </td>
                        <td width="40px" align="center">
                          22
                        </td>
                        <td width="40px" align="center">
                          23
                        </td>
                        <td width="40px" align="center">
                          24
                        </td>
                        <td width="40px" align="center">
                          25
                        </td>
                        <td width="40px" align="center">
                          26
                        </td>
                        <td width="40px" align="center">
                          27
                        </td>
                        <% if (dateBeanListSize >= 28) { %>
                        <td width="40px" align="center">
                          28
                        </td>
                        <% } %>
                        <% if (dateBeanListSize >= 29) { %>
                        <td width="40px" align="center">
                          29
                        </td>
                        <% } %>
                        <% if (dateBeanListSize >= 30) { %>
                        <td width="40px" align="center">
                          30
                        </td>
                        <% } %>
                        <% if (dateBeanListSize == 31) { %>
                        <td width="40px" align="center">
                          31
                        </td>
                        <% } %>
                      </tr>
                      <tr height="<%=heightSize %>px">
                      <logic:iterate id="dateBeanList" name="tsukibetsuShiftKakuninForm" property="dateBeanList">
                      <bean:define id="youbiEnum" name="dateBeanList" property="youbiEnum"/>
                           <%
                              if (DayOfWeek.SATURDAY.equals(youbiEnum)) {
                                  color = "fontBlue";
                              } else if (DayOfWeek.SUNDAY.equals(youbiEnum)) {
                                  color = "fontRed";
                              } else {
                                  color = "fontBlack";
                              }
                              %>

                          <td width="40px" align="center" class="<%=color %>">
                            <bean:write property="youbi" name="dateBeanList"/><br>
                          </td>
                      </logic:iterate>
                      </tr>
                      <logic:iterate offset="offset" length="<%=showLength %>" id="tsukibetsuShiftKakuninBeanList" name="tsukibetsuShiftKakuninForm" property="tsukibetsuShiftKakuninBeanList">
                        <html:hidden name="tsukibetsuShiftKakuninBeanList" property="registFlg" value="true" indexed="true"/>
                        <tr  class="tblBody"  height="<%=heightSize %>px">
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol01"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol02"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol03"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol04"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol05"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol06"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol07"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol08"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol09"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol10"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol11"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol12"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol13"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol14"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol15"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol16"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol17"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol18"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol19"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol20"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol21"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol22"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol23"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol24"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol25"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol26"/><br>
                          </td>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol27"/><br>
                          </td>
                          <% if (dateBeanListSize >= 28) { %>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol28"/><br>
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 29) { %>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol29"/><br>
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 30) { %>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol30"/><br>
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 31) { %>
                          <td width="40px" align="center" valign="middle">
                            <bean:write name="tsukibetsuShiftKakuninBeanList" property="symbol31"/><br>
                          </td>
                          <% } %>
                        </tr>
                      </logic:iterate>
                    </table>
                </div>
                </td>
              </tr>
            </table>
          </div>
        </html:form>
      </div>
      <div id="footer">
        <div style="margin-left:50px;">
          <input value="凡例表示" type="button" class="lngButton"  onclick="openWindow()" />
       </div>

        <table>
          <tr>
            <div style="margin-left:50px;">
            <p>
              <input value="印刷" type="button" class="smlButton"  onclick="submitPrint()" />
        	</p>
              </div>
            <td id="footCenter" style="text-align: right;">
            </td>
            <td id="footRight">
            </td>
          </tr>
        </table>
      </div>
    </div>
  </body>
</html>