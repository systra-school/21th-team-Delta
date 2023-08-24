<!-- login.jsp -->
<%@page contentType="text/html; charset=Shift_JIS" pageEncoding="Shift_JIS"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Expires" content="Thu, 01 Dec 1994 16:00:00 GMT">
	<html:javascript formName="loginForm" />
	<title>ログイン画面</title>
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
                    ログイン
                  </td>
                  <td id="headRight">
                    　
                  </td>
              </tr>
            </table>
        </div>


		<div id="gymBody">
		  <div align="center">
		    <div>ID・パスワードを入力してください。</div>
		    <html:form action="/login" onsubmit="return validateLoginForm(this)">
		      <html:text property="shainId" size="16" value="" />
		      <br/>
		      <html:password property="password" size="16" redisplay="false" value=""/>
		      <br/>
		      <br/>
		      <html:submit property="submit" value="ログイン" />
		      <html:reset value="リセット" />
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