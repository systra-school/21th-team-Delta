<!-- shainMstMntRegist.jsp -->
<%
/**
 * ファイル名：shainMstMntRegist.jsp
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
     * チェックボックスがチェックされたら true、されていなければ false
     * param index 対象行番号
     */
    function checkDeleteFlg(index) {
      var isCheck = document.forms[0].elements.deleteShainId[index].checked;

      document.forms[0].elements.deleteFlg[index].value = isCheck;
    }

    /**
     * 新規登録画面へ
     */
    function shainMstMntRegist() {

        with (document.forms[0]) {
            // パスワード
            var passwordVar = password.value;
            // 社員名カナ
            var shainNameKanaVar = shainNameKana.value;
            // エラーメッセージ
            var errorMsg = '';

            // 背景色をクリアする
            password.style.backgroundColor = 'white';
            shainNameKana.style.backgroundColor = 'white';

            // パスワード
            if (!checkRequired(passwordVar)) {
                // エラー有り
                var strArr = ['パスワード'];
                errorMsg += getMessage('E-MSG-000001', strArr);
                password.style.backgroundColor = 'red';
            }
            // 社員名カナ
            if (!checkHankakuKana(shainNameKanaVar)) {
                // エラー有り
                var strArr = ['社員名カナ'];
                errorMsg += getMessage('E-MSG-000003', strArr);
                shainNameKana.style.backgroundColor = 'red';
            }

            if (errorMsg) {
                alert(errorMsg);
                // エラー
                return false;
            }
        }

        // サブミット
        doSubmit('/kikin/shainMstMntRegist.do');
    }

    -->
    </script>
    <title>社員マスタメンテナンス画面</title>

    <link href="/kikin/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <div id="wrapper">
      <div id="header">
        <table>
          <tr>
            <td id="headLeft">
              <input value="戻る" type="button" class="smlButton"  onclick="doSubmit('/kikin/shainMstMntRegistBack.do')" />
            </td>
            <td id="headCenter">
              社員マスタメンテナンス画面（新規登録）
            </td>
            <td id="headRight">
              <input value="ログアウト" type="button" class="smlButton"  onclick="logout()" />
            </td>
          </tr>
        </table>
      </div>
      <div id="gymBody">
        <html:form action="/shainMstMntRegist" >
          <div style="width: 650px;text-align: left; margin-left:300px;">
            <table class="tblHeader" border="1" cellpadding="0" cellspacing="0">
              <tr>
                <td width="150px" align="center">
                  パスワード
                </td>
                <td width="200px" align="center">
                  社員名
                </td>
                <td width="200px" align="center">
                  社員名カナ
                </td>
                <td width="100px" align="center">
                  権限
                </td>
              </tr>
            </table>
          </div>
          <div style="overflow: auto; height: 440px; width: 650px; margin-left:300px; ">
            <table class="tblBody" border="1" cellpadding="0" cellspacing="0">
              <tr>
                <td width="150px"  align="center">
                  <html:text property="password"  value="" size="10" />
                </td>
                <td width="200px"  align="center">
                  <html:text property="shainName" value="" size="20" />
                </td>
                <td width="200px"  align="center">
                  <html:text property="shainNameKana" value="" size="20" />
                </td>
                <td width="100px" align="center">
                  <html:select property="kengenId" value="01">
                    <html:optionsCollection name="shainMstMntForm"
                                            property="kengenCmbMap"
                                            value="key"
                                            label="value"/>
                  </html:select>
                </td>
              </tr>
            </table>
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
              <input value="登録" type="button" class="smlButton"  onclick="shainMstMntRegist()" />
            </td>
          </tr>
        </table>
      </div>
    </div>
  </body>
</html>