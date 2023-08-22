<%
/**
 * ファイル名：kihonShtRegist.jsp
 *
 * 変更履歴
 * 1.0  2010/10/05 Satoshi.Sugi
 */
%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@page import="business.logic.utils.CheckUtils"%>
<%@page import="form.mst.ShainMstMntForm"%>
<%@page import="java.util.List"%>
<%@page import="form.mst.ShainMstMntBean"%>
<%@page contentType="text/html; charset=Shift_JIS" pageEncoding="Shift_JIS"%>
<%@ page import="constant.RequestSessionNameConstant"%>
<%@ page import="constant.CommonConstant"%>
<%
  // 一覧のサイズを取得する
  ShainMstMntForm shainMstMntFormSession = (ShainMstMntForm) session.getAttribute("shainMstMntForm");
  List<ShainMstMntBean> shainMstMntBeanLista = shainMstMntFormSession.getShainMstMntBeanList();

  int shainMstMntBeanListSize = 0;

  if (CheckUtils.isEmpty(shainMstMntBeanLista)) {
      shainMstMntBeanListSize = 0;
  } else {
      shainMstMntBeanListSize = shainMstMntBeanLista.size();
  }

%>
<html>
  <head>
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
        var deleteShainId = document.forms[0].elements.deleteShainId;
        var isCheck = false;

        if (deleteShainId.length > 1) {
            isCheck = document.forms[0].elements.deleteShainId[index].checked;
        } else {
            isCheck = deleteShainId.checked;
        }

        document.forms[0].elements.namedItem('shainMstMntBeanList['+ index +'].deleteFlg').value = isCheck;
    }

    /**
     * 新規登録画面へ
     */
    function shainMstMntRegistInit() {
        document.forms[0].action = "/kikin/shainMstMntRegistInit.do";
        document.forms[0].submit();
    }

    /**
     * 更新処理を行う
     */
    function shainMstMntUpdate() {
        // 一覧のサイズ
        var listSize = <%= shainMstMntBeanListSize %>;

        // パスワードエラーメッセージ
        var passwordErrorMsg = '';
        // 社員名カナエラーメッセージ
        var shainmeiKanaErrorMsg = '';
        var errorMsg = '';

        with(document.forms[0].elements) {
            for (var i = 0; i < listSize; i++) {
                // パスワードチェック
                if (!passwordErrorMsg) {
                    // パスワードを取得する。
                    var password = namedItem('shainMstMntBeanList['+ i +'].password').value;
                    if (!checkRequired(password)) {
                        var strArr = ['パスワード'];
                        passwordErrorMsg = getMessage('E-MSG-000001', strArr);
                    }
                }
                // 社員名カナチェック
                if (!shainmeiKanaErrorMsg) {
                    // パスワードを取得する。
                    var shainNameKana = namedItem('shainMstMntBeanList['+ i +'].shainNameKana').value;
                    if (!checkHankakuKana(shainNameKana)) {
                        var strArr = ['社員名カナ'];
                        shainmeiKanaErrorMsg = getMessage('E-MSG-000003', strArr);
                    }
                }

                if (passwordErrorMsg && shainmeiKanaErrorMsg) {
                    // パスワード 、社員名カナが共にエラーの場合
                    break;
                }
            }
        }
        // エラーメッセージ
        errorMsg = passwordErrorMsg + shainmeiKanaErrorMsg;

        if (errorMsg) {
            alert(errorMsg);
            // エラー
            return false;
        }

        document.forms[0].submit();
    }

    -->
    </script>

    <title>社員マスタメンテナンス画面</title>

    <link href="/kikin/pages/css/common.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <div id="header">
      <div style="display: inline;">
        社員マスタメンテナンス
      </div>

      <div class="backBtn">
        <input value="戻る" type="button" class="smlButton"  onclick="doSubmit('/kikin/menu.do')" />
      </div>

      <div class="logoutBtn">
        <input value="ログアウト" type="button" class="smlButton"  onclick="logout()" />
      </div>

    </div>
    <html:form action="">
      <div id="gymBody">
        <div style="overflow:hidden; width:100%; height:100%;text-align:center;">
          <div style="width: 630px;text-align: left;">
            <table class="tblHeader" border="1" cellpadding="0" cellspacing="0">
              <tr>
                <td width="100px" align="center">
                  シフト名
                </td>
                <td width="100px" align="center">
                  シンボル
                </td>
                <td width="130px" align="center">
                  時間
                </td>
                <td width="130px" align="center">
                  休憩
                </td>
              </tr>
            </table>
          </div>
          <div style="overflow: auto; height: 81%; width: 630px;text-align: left; ">
            <table class="tblBody" border="1" cellpadding="0" cellspacing="0">
              <logic:iterate indexId="idx" id="shainMstMntBeanList" name="shainMstMntForm"  property="shainMstMntBeanList">
              <bean:define id="shainId" name= "shainMstMntBeanList" property="shainId" type="java.lang.String"/>
              <bean:define id="selectKengenId" name= "shainMstMntBeanList" property="kengenId" type="java.lang.String"/>
                <tr>
                  <td width="100px"  align="center">
                    <bean:write property="shainId" name="shainMstMntBeanList"/>
                    <html:hidden property="shainId" name="shainMstMntBeanList" indexed="true"/>
                  </td>
                  <td width="100px"  align="center">
                    <html:password property="password" name="shainMstMntBeanList"  size="10" maxlength="6" indexed="true" />
                  </td>
                  <td width="130px"  align="center">
                    <html:text property="shainName" name="shainMstMntBeanList" size="20" maxlength="10" indexed="true" />
                  </td>
                  <td width="130px"  align="center">
                    <html:text property="shainNameKana" name="shainMstMntBeanList"  size="20" maxlength="10" indexed="true" />
                  </td>
                </tr>
              </logic:iterate>
            </table>
          </div>
        </div>
      </div>
    </html:form>
    <div id="footer">
      <div style="position: absolute; top: 10px; right: 120px">
        <input value="新規登録" type="button" class="smlButton"  onclick="shainMstMntRegistInit()" />
      </div>
      <div style="position: absolute; top: 10px; right: 10px">
        <input value="更新" type="button" class="smlButton"  onclick="shainMstMntUpdate()" />
      </div>
    </div>
  </body>
</html>