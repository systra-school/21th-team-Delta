<!-- kinmuJissekiNyuryokuKakunin.jsp -->
<%@page import="constant.CommonConstant.DayOfWeek"%>
<%
/**
 * �t�@�C�����FkinmuJissekiNyuryokuKakunin.jsp
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
     * hh:mm�`���ƋΖ��J�n���ԁ��Ζ��I�����Ԃ̃`�F�b�N��G���[������������o�^����
     */
     function regist() {
    	// �ꗗ�̃T�C�Y
	    var listSize = <%= kinmuJissekiNyuryokuKakuninListSize %>;
      	// �J�n���ԃG���[���b�Z�[�W
     	var startTimeErrMsg = '';
     	// �I�����ԃG���[���b�Z�[�W
     	var endTimeErrMsg = '';
     	// �x�e���ԃG���[���b�Z�[�W
     	var breakTimeErrMsg = '';
     	// From - To �G���[���b�Z�[�W
     	var fromToErrMsg = '';
     	// �G���[���b�Z�[�W
     	var errorMsg = '';
 
     	
		with(document.forms[0].elements) {
			for (var i = 0; i < listSize; i++) {
				// �J�n���Ԃ��擾����B
	     	    var startTime = namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].startTime').value;
	     	    // �I�����Ԃ��擾����B
	     	    var endTime = namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].endTime').value;
	     	    // �x�e���Ԃ��擾����B
	     	    var breakTime = namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].breakTime').value;

	     	    // �w�i�F���N���A����
	     	    namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].startTime').style.backgroundColor = 'white';
	     	    namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].endTime').style.backgroundColor = 'white';
	     	    namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].breakTime').style.backgroundColor = 'white';

	     	    // ���ԃ`�F�b�N
	     	    if (!startTimeErrMsg) {	
	     	    	if (startTime != '' && !checkTime(startTime)) {
	     	        	var strArr = ['�J�n����'];
	     	            startTimeErrMsg = getMessage('E-MSG-000004', strArr);
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].startTime').style.backgroundColor = 'red';
	     	     	}
	     	     }
	     	     if (!endTimeErrMsg) {
	     	     	if (endTime != '' &&!checkTime(endTime)) {
	     	        	var strArr = ['�I������'];
	     	            endTimeErrMsg = getMessage('E-MSG-000004', strArr);
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].endTime').style.backgroundColor = 'red';
	     	        }
	     	     }
	     	     if (!breakTimeErrMsg) {
	     	     	if (breakTime != '' &&!checkTime(breakTime)) {
	     	        	var strArr = ['�x�e����'];
	     	            breakTimeErrMsg = getMessage('E-MSG-000004', strArr);
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].breakTime').style.backgroundColor = 'red';
	     	        }
	     	     }

	     	     // from - to �̃`�F�b�N
	     	     if (!checkTimeCompare(startTime, endTime)) {
	     	      	if (checkTime(startTime) && checkTime(endTime)) {
	     	        	fromToErrMsg = getMessageCodeOnly('E-MSG-000005');
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].startTime').style.backgroundColor = 'red';
	     	            namedItem('kinmuJissekiNyuryokuKakuninList['+ i +'].endTime').style.backgroundColor = 'red';
	     	      	}
	     	     }
	     	     // �G���[���b�Z�[�W
	     	     errorMsg = startTimeErrMsg + endTimeErrMsg + breakTimeErrMsg + fromToErrMsg;
	     	    
	     	     
	     	     if (errorMsg) {
	     	      	alert(errorMsg);
	     	        // �G���[
	     	      	return false;
	     	     }
			}
			
			if(!errorMsg) {
				doSubmit('/kikin_test/kinmuJissekiNyuryokuKakuninRegist.do');
			}
     	}	
    }
    /**
     * ����
     */
    function submitSearch() {
        doSubmit('/kikin_test/kinmuJissekiNyuryokuKakuninSearch.do');
    }
    
    
    </script>
    <title>�Ζ����ѓ��͉��</title>

    <link href="/kikin_test/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
  <body >
    <div id="wrapper">
      <div id="header">
        <table>
          <tr>
            <td id="headLeft">
              <input value="�߂�" type="button" class="smlButton"  onclick="doSubmit('/kikin_test/kinmuJissekiNyuryokuKakuninBack.do')" />
            </td>
            <td id="headCenter">
              �Ζ����ѓ���
            </td>
            <td id="headRight">
                <input value="���O�A�E�g" type="button" class="smlButton"  onclick="logout()" />
              </td>
          </tr>
        </table>
      </div>
      <div id="gymBody">
        <html:form action="/shainMstMntRegist" >
          <div style="float: left; width: 100%;">
            <div style="float: left; width: 804px; text-align: left; margin-left:100px;">
              �\���N���F
              <html:select name="kinmuJissekiNyuryokuKakuninForm" property="yearMonth" onchange="submitSearch()">
              <html:optionsCollection name="kinmuJissekiNyuryokuKakuninForm"
                                      property="yearMonthCmbMap"
                                      value="key"
                                      label="value"/>
              </html:select>
            </div>
            <div style="float: left; width: 284px; text-align: left;">
              �Ј�ID&nbsp;<bean:write name="kinmuJissekiNyuryokuKakuninForm" property="shainId"/>
              �F�Ј���&nbsp;<bean:write name="kinmuJissekiNyuryokuKakuninForm" property="shainName"/>
            </div>
          </div>
          <div>
            <div style="overflow: hidden; width: 1080px; margin-left:80px;">
            <table border="1" cellpadding="0" cellspacing="0">
              <tr class="tblHeader" style="overflow: hidden; width: 1080px; margin-left:80px;">
                <td width="80px" align="center">
                  ���t
                </td>
                <td width="50px" align="center">
                  �j
                </td>
                <td width="100px" align="center">
                  �V�t�g
                </td>
                <td width="100px" align="center">
                  �J�n����
                </td>
                <td width="100px" align="center">
                  �I������
                </td>
                <td width="100px" align="center">
                  �x�e
                </td>
                <td width="100px" align="center">
                  ��������
                </td>
                <td width="100px" align="center">
                  ���ԊO
                </td>
                <td width="100px" align="center">
                  �x��
                </td>
                <td width="220px" align="center">
                  ���l
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
                  <bean:define id="shukujitsuFlg" name="kinmuJissekiNyuryokuKakuninList" property="shukujitsuFlg"/>

                  <%
                  if (DayOfWeek.SATURDAY.getRyaku().equals(youbi)) {
                      color = "fontBlue";
                  } else if (DayOfWeek.SUNDAY.getRyaku().equals(youbi)) {
                      color = "fontRed";
                  } else if((boolean)shukujitsuFlg) {
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
                �@
              </td>
              <td id="footCenter">
                �@
              </td>
              <td id="footRight">
                <input value="�o�^"  type="button" class="smlButton"  onclick="regist()" />
              </td>
          </tr>
        </table>
    </div>
    </div>
  </body>
</html>