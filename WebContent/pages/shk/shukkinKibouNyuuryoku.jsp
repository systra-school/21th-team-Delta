<!-- shukkinKibouNyuuryoku.jsp -->
<%@page import="constant.CommonConstant.DayOfWeek"%>
<%@page import="business.logic.utils.CheckUtils"%>
<%@page import="form.common.DateBean"%>
<%@page import="java.util.List"%>
<%@page import="form.mth.TsukibetsuShiftNyuuryokuForm"%>
<%
/**
 * �t�@�C�����FshukkinKibouNyuuryoku.jsp
 *
 * �ύX����
 * 1.0  2010/09/13 Kazuya.Naraki
 */
%>
<%@page contentType="text/html; charset=Shift_JIS" pageEncoding="Shift_JIS"%>
<%@ page import="constant.RequestSessionNameConstant"%>
<%@ page import="constant.CommonConstant"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<bean:size id="dateBeanListSize" name="tsukibetsuShiftNyuuryokuForm" property="dateBeanList"/>
<bean:define id="color" value="" type="java.lang.String"/>
<bean:define id="shainId" name="loginUserDto" property="shainId" type="java.lang.String" />
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
     * �o�Ί�]���f
     */
    function submitShukkinKibou() {
        // �T�u�~�b�g
        doSubmit('/kikin_test/tsukibetsuShiftNyuuryokuShukkinKibou.do');
    }

    /**
     * �o�^
     */
    function submitRegist() {
        // �T�u�~�b�g
        doSubmit('/kikin_test/syukkinKibouNyuuryokuRegist.do');
    }

    /**
     * ����
     */
    function submitSearch() {
        doSubmit('/kikin_test/shukkinKibouNyuuryokuSearch.do');
    }

    /**
     * �T�u�E�B���h�E���J��
     */
    function openWindow(){
        window.open("/kikin_test/shiftHanrei.do?param=", null, "menubar=no, toolbar=no, scrollbars=auto, resizable=yes, width=520px, height=650px");
    }
    
    </script>
    <title>�o�Ί�]������</title>

    <link href="/kikin_test/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <div id="wrapper">
      <div id="header">
        <table>
          <tr>
            <td id="headLeft">
              <input value="�߂�" type="button" class="smlButton"  onclick="doSubmit('/kikin_test/tsukibetsuShiftNyuuryokuBack.do')" />
            </td>
            <td id="headCenter">
              �o�Ί�]������
            </td>
            <td id="headRight">
              <input value="���O�A�E�g" type="button" class="smlButton"  onclick="logout()" />
            </td>
          </tr>
        </table>
      </div>
      <div id="gymBody" style="overflow: hidden;">
        <html:form action="/tsukibetsuShiftNyuuryokuInit.do" >
          <div style="margin-left:25px;">
            <div style="height: 25px;">
              �\���N���F
              <bean:define id="sessionYearMonth" name="tsukibetsuShiftNyuuryokuForm" property="yearMonth" type="String"/>
              <html:select property="yearMonth" name="tsukibetsuShiftNyuuryokuForm"  onchange="submitSearch()">
              <html:optionsCollection name="tsukibetsuShiftNyuuryokuForm"
                                      property="yearMonthCmbMap"
                                      value="key"
                                      label="value"/>
              </html:select>
            </div>
            
            <!-- ��������V�K -->
            <table width="1100px" cellpadding="0" cellspacing="0" border="1">
				<tr class="tblHeader">
					<td width="80px" style="min-width:80px;"></td>
					<td width="40px" align="center">1</td>
					<td width="40px" align="center">2</td>
					<td width="40px" align="center">3</td>
					<td width="40px" align="center">4</td>
					<td width="40px" align="center">5</td>
					<td width="40px" align="center">6</td>
					<td width="40px" align="center">7</td>
					<td width="40px" align="center">8</td>
					<td width="40px" align="center">9</td>
					<td width="40px" align="center">10</td>
					<td width="40px" align="center">11</td>
					<td width="40px" align="center">12</td>
					<td width="40px" align="center">13</td>
					<td width="40px" align="center">14</td>
					<td width="40px" align="center">15</td>
					<td width="40px" align="center">16</td>
					<td width="40px" align="center">17</td>
					<td width="40px" align="center">18</td>
					<td width="40px" align="center">19</td>
					<td width="40px" align="center">20</td>
					<td width="40px" align="center">21</td>
					<td width="40px" align="center">22</td>
					<td width="40px" align="center">23</td>
					<td width="40px" align="center">24</td>
					<td width="40px" align="center">25</td>
					<td width="40px" align="center">26</td>
					<td width="40px" align="center">27</td>
<%
if (dateBeanListSize >= 28) {
%>
					<td width="40px" align="center">28</td>
<%
}
if (dateBeanListSize >= 29) {
%>
					<td width="40px" align="center">29</td>
<%
}
if (dateBeanListSize >= 30) {
%>
					<td width="40px" align="center">30</td>
<%
}
if (dateBeanListSize == 31) {
%>
					<td width="40px" align="center">31</td>
<%
}
%>
				</tr>
				<tr class="tblHeader">
					<td width="150px" align="center">�Ј���</td>
					<logic:iterate
						id="dateBeanList"
						name="tsukibetsuShiftNyuuryokuForm"
						property="dateBeanList"
					>
						<bean:define id="youbi" name="dateBeanList" property="youbi" />
						<bean:define id="shukujitsuFlg" name="dateBeanList" property="shukujitsuFlg"/>
						<%
						if (DayOfWeek.SATURDAY.getRyaku().equals(youbi)) {
							color = "fontBlue";%>
							<td width="40px" align="center" class="<%=color%>">
							<bean:write property="youbi" name="dateBeanList" />
							</td>
						<%} else if (DayOfWeek.SUNDAY.getRyaku().equals(youbi)) {
							color = "fontRed";%>
							<td width="40px" align="center" class="<%=color%>">
							<bean:write property="youbi" name="dateBeanList" />
							</td>
						<%} else if((boolean)shukujitsuFlg) {
                          	color = "fontRed";%>
                          	<td width="40px" align="center" class="<%=color%>">
							�j
							</td>
						<%} else {
							color = "fontBlack";%>
							<td width="40px" align="center" class="<%=color%>">
							<bean:write property="youbi" name="dateBeanList" />
							</td>
						<%}
						%>
					</logic:iterate>
				</tr>
				<logic:iterate
					offset="offset"
					id="tsukibetsuShiftNyuuryokuBeanList"
					name="tsukibetsuShiftNyuuryokuForm"
					property="tsukibetsuShiftNyuuryokuBeanList"
				>
					<logic:equal value="${shainId }" name="tsukibetsuShiftNyuuryokuBeanList" property="shainId">
					<html:hidden name="tsukibetsuShiftNyuuryokuBeanList" property="registFlg" value="true" indexed="true"/>
						<tr height="20px" class="tblBody">
							<td width="150px" align="center">
								<bean:write property="shainName" name="tsukibetsuShiftNyuuryokuBeanList" />
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId01"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId02"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId03"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId04"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId05"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId06"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId07"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId08"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId09"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId10"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId11"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId12"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId13"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId14"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId15" 
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection 
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap" 
										value="key" 
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId16"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId17"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId18"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId19"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId20"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId21"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId22"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId23"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId24"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId25"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId26"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId27"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
<%
if (dateBeanListSize >= 28) {
%>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId28"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
<%
}
if (dateBeanListSize >= 29) {
%>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId29"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
<%
}
if (dateBeanListSize >= 30) {
%>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId30"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
<%
}
if (dateBeanListSize >= 31) {
%>
							<td width="40px" align="center" valign="middle">
								<html:select
									property="shiftId31"
									name="tsukibetsuShiftNyuuryokuBeanList"
									indexed="true">
									<html:optionsCollection
										name="tsukibetsuShiftNyuuryokuForm"
										property="shiftCmbMap"
										value="key"
										label="value" />
								</html:select>
							</td>
<%
}
%>
						</tr>
					</logic:equal>
				</logic:iterate>
			</table>
          </div>
        </html:form>
        <div style="margin-left:50px;">
          <input value="�}��\��" type="button" class="lngButton"  onclick="openWindow()" />
        </div>
      </div>
      <div id="footer">
        <table>
          <tr>
            <td id="footLeft">
            </td>
            <td id="footCenter" style="text-align: right;">
				
            </td>
            <td id="footRight">
            	<input value="�o�Ί�]���Q��" type="button" class="lngButton" onclick="window.open('./shukkinKibouKakuninInit.do')" />
            	<input value="�o�^" type="button" class="smlButton"  onclick="submitRegist()" />
            </td>
          </tr>
        </table>
      </div>
    </div>
  </body>
</html>