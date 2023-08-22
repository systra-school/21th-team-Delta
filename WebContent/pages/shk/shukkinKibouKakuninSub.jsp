<!-- shukkinKibouKakuninSub.jsp -->
<%@page import="constant.CommonConstant.DayOfWeek"%>
<%@page import="business.logic.utils.CheckUtils"%>
<%@page import="form.common.DateBean"%>
<%@page import="java.util.List"%>
<%@page import="form.shk.ShukkinKibouKakuninForm"%>
<%
/**
 * ファイル名：shukkinKibouNyuuryoku.jsp
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

<bean:size id="dateBeanListSize" name="shukkinKibouKakuninForm"  property="dateBeanList"/>
<bean:define id="offset" name="shukkinKibouKakuninForm" property="offset" />
<%
String showLength = "18";
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
        doSubmit('/kikin/shukkinKibouKakuninSubSearch.do');
    }
    -->
    </script>
    <title>出勤希望確認画面</title>

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
              出勤希望確認
            </td>
            <td id="headRight">
              　
            </td>
          </tr>
        </table>
      </div>
      <div id="gymBody" style="overflow: auto;">
        <div style="margin-left:70px;">
          <html:form action="/shukkinKibouKakuninInit" >
            表示年月：
            <html:select name="shukkinKibouKakuninForm" property="yearMonth" onchange="submitSearch()">
            <html:optionsCollection name="shukkinKibouKakuninForm"
                                    property="yearMonthCmbMap"
                                    value="key"
                                    label="value"/>
            </html:select>
            <html:link href="/kikin/shukkinKibouKakuninSubPage.do?paging=back">前へ</html:link>
            <html:link href="/kikin/shukkinKibouKakuninSubPage.do?paging=next">次へ</html:link>
            <bean:write name="shukkinKibouKakuninForm" property="cntPage"/>/
            <bean:write name="shukkinKibouKakuninForm" property="maxPage"/>

              <table width="950px" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="150px" valign="top">
                    <table border="1" cellpadding="0" cellspacing="0">
                      <tr class="tblHeader" >
                        <td width="150px" align="center">
                          &nbsp;
                        </td>
                      </tr>
                      <tr class="tblHeader" >
                        <td width="150px" align="center">
                        社員名
                        </td>
                      </tr>
                      <logic:iterate offset="offset" length="<%=showLength %>" id="shukkinKibouKakuninBeanList" name="shukkinKibouKakuninForm" property="shukkinKibouKakuninBeanList">
                        <tr class="tblBody" >
                          <td width="150px" align="center">
                            <bean:write property="shainName" name="shukkinKibouKakuninBeanList"/><br>
                          </td>
                        </tr>
                      </logic:iterate>
                    </table>
                  </td>
                  <td>
                    <div style="overflow-x: auto;overflow-y: hidden; width:800px;height: 100%; ">
                      <table border="1" cellpadding="0" cellspacing="0">
                        <tr class="tblHeader" >
                          <td width="40px" align="center" valign="middle">
                            1
                          </td>
                          <td width="40px" align="center" valign="middle">
                            2
                          </td>
                          <td width="40px" align="center" valign="middle">
                            3
                          </td>
                          <td width="40px" align="center" valign="middle">
                            4
                          </td>
                          <td width="40px" align="center" valign="middle">
                            5
                          </td>
                          <td width="40px" align="center" valign="middle">
                            6
                          </td>
                          <td width="40px" align="center" valign="middle">
                            7
                          </td>
                          <td width="40px" align="center" valign="middle">
                            8
                          </td>
                          <td width="40px" align="center" valign="middle">
                            9
                          </td>
                          <td width="40px" align="center" valign="middle">
                            10
                          </td>
                          <td width="40px" align="center" valign="middle">
                            11
                          </td>
                          <td width="40px" align="center" valign="middle">
                            12
                          </td>
                          <td width="40px" align="center" valign="middle">
                            13
                          </td>
                          <td width="40px" align="center" valign="middle">
                            14
                          </td>
                          <td width="40px" align="center" valign="middle">
                            15
                          </td>
                          <td width="40px" align="center" valign="middle">
                            16
                          </td>
                          <td width="40px" align="center" valign="middle">
                            17
                          </td>
                          <td width="40px" align="center" valign="middle">
                            18
                          </td>
                          <td width="40px" align="center" valign="middle">
                            19
                          </td>
                          <td width="40px" align="center" valign="middle">
                            20
                          </td>
                          <td width="40px" align="center" valign="middle">
                            21
                          </td>
                          <td width="40px" align="center" valign="middle">
                            22
                          </td>
                          <td width="40px" align="center" valign="middle">
                            23
                          </td>
                          <td width="40px" align="center" valign="middle">
                            24
                          </td>
                          <td width="40px" align="center" valign="middle">
                            25
                          </td>
                          <td width="40px" align="center" valign="middle">
                            26
                          </td>
                          <td width="40px" align="center" valign="middle">
                            27
                          </td>
                          <% if (dateBeanListSize >= 28) { %>
                          <td width="40px" align="center" valign="middle">
                            28
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 29) { %>
                          <td width="40px" align="center" valign="middle">
                            29
                          </td>
                          <% } %>
                          <% if (dateBeanListSize >= 30) { %>
                          <td width="40px" align="center" valign="middle">
                            30
                          </td>
                          <% } %>
                          <% if (dateBeanListSize == 31) { %>
                          <td width="40px" align="center" valign="middle">
                            31
                          </td>
                          <% } %>
                        </tr>
                        <tr class="tblHeader" >
                          <logic:iterate id="dateBeanList" name="shukkinKibouKakuninForm" property="dateBeanList">
                              <bean:define id="youbi" name="dateBeanList" property="youbi"/>
                              <%
                              if (DayOfWeek.SATURDAY.getRyaku().equals(youbi)) {
                                  color = "fontBlue";
                              } else if (DayOfWeek.SUNDAY.getRyaku().equals(youbi)) {
                                  color = "fontRed";
                              } else {
                                  color = "fontBlack";
                              }
                              %>

                              <td width="40px" align="center" valign="middle" class="<%=color %>">
                                <bean:write property="youbi" name="dateBeanList"/>
                              </td>
                          </logic:iterate>
                        </tr>
                        <logic:iterate offset="offset"  length="<%=showLength %>" id="shukkinKibouKakuninBeanList" name="shukkinKibouKakuninForm" property="shukkinKibouKakuninBeanList">
                          <tr class="tblBody" >
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol01" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol02" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol03" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol04" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol05" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol06" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol07" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol08" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol09" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol10" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol11" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol12" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol13" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol14" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol15" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol16" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol17" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol18" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol19" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol20" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol21" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol22" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol23" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol24" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol25" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol26" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol27" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <% if (dateBeanListSize >= 28) { %>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol28" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <% } %>
                            <% if (dateBeanListSize >= 29) { %>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol29" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <% } %>
                            <% if (dateBeanListSize >= 30) { %>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol30" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <% } %>
                            <% if (dateBeanListSize >= 31) { %>
                            <td width="40px" align="center" valign="middle">
                              <bean:write property="symbol31" name="shukkinKibouKakuninBeanList" />
                            </td>
                            <% } %>
                          </tr>
                        </logic:iterate>
                      </table>
                    </div>
                  </td>
                </tr>
              </table>
          </html:form>
        </div>
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