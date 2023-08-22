/**
 * ファイル名：KinmuJissekiNyuryokuKakuninForm.java
 *
 * 変更履歴
 * 1.0  2010/11/04 Kazuya.Naraki
 */
package form.bse;

import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import form.bse.KihonShiftMstMntBean;

/**
 * 説明：基本シフト入力確認フォーム
 * @author naraki
 *
 */
public class KihonShiftMstMntForm extends ActionForm {

    /**
	 *シリアル
	 */
	private static final long serialVersionUID = 1483629197030517493L;

    /** 社員ID */
    private String shainId;
    /** 社員名 */
    private String shainName;

	/** 設定済み基本シフトBeanList */
    private List<KihonShiftMstMntBean> kihonShiftMstMntBeanList;
    /** 基本シフトコンボ */
    private Map<String, String> shiftCmbMap;
    /** 基本シフト凡例一覧 */
    private List<KihonShiftHanreiBean> kihonShiftHanreiBeanList;

    /**
	 * @return kihonShiftMstMntBeanList
	 */
	public List<KihonShiftMstMntBean> getKihonShiftMstMntBeanList() {
		return kihonShiftMstMntBeanList;
	}
	/**
	 * @param kihonShiftMstMntBeanList セットする kihonShiftMstMntBeanList
	 */
	public void setKihonShiftMstMntBeanList(
			List<KihonShiftMstMntBean> kihonShiftMstMntBeanList) {
		this.kihonShiftMstMntBeanList = kihonShiftMstMntBeanList;
	}

	/**
	 * @return kihonShiftHanreiBeanList
	 */
	public List<KihonShiftHanreiBean> getKihonShiftHanreiBeanList() {
		return kihonShiftHanreiBeanList;
	}
	/**
	 * @param kihonShiftHanreiBeanList セットする kihonShiftHanreiBeanList
	 */
	public void setKihonShiftHanreiBeanList(
			List<KihonShiftHanreiBean> kihonShiftHanreiBeanList) {
		this.kihonShiftHanreiBeanList = kihonShiftHanreiBeanList;
	}
	/**
	 * @return shiftCmbMap
	 */
	public Map<String, String> getShiftCmbMap() {
		return shiftCmbMap;
	}
	/**
	 * @param shiftCmbMap セットする shiftCmbMap
	 */
	public void setShiftCmbMap(Map<String, String> shiftCmbMap) {
		this.shiftCmbMap = shiftCmbMap;
	}
    /**
	 * @return shainId
	 */
	public String getShainId() {
		return shainId;
	}
	/**
	 * @param shainId セットする shainId
	 */
	public void setShainId(String shainId) {
		this.shainId = shainId;
	}
	/**
	 * @return shainName
	 */
	public String getShainName() {
		return shainName;
	}
	/**
	 * @param shainName セットする shainName
	 */
	public void setShainName(String shainName) {
		this.shainName = shainName;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer(256);
		for (KihonShiftMstMntBean bean : this.kihonShiftMstMntBeanList) {
			ret.append("\n");
			ret.append("■■■■■■■■■■■■");
			ret.append("getShainId()           :").append(bean.getShainId()).append("\n");
			ret.append("getShainName()         :").append(bean.getShainName()).append("\n");
			ret.append("getShiftIdOnMonday()   :").append(bean.getShiftIdOnMonday()).append("\n");
			ret.append("getShiftIdOnTuesday()  :").append(bean.getShiftIdOnTuesday()).append("\n");
			ret.append("getShiftIdOnWednesday():").append(bean.getShiftIdOnWednesday()).append("\n");
			ret.append("getShiftIdOnThursday() :").append(bean.getShiftIdOnThursday()).append("\n");
			ret.append("getShiftIdOnFriday()   :").append(bean.getShiftIdOnFriday()).append("\n");
			ret.append("getShiftIdOnSaturday() :").append(bean.getShiftIdOnSaturday()).append("\n");
			ret.append("getShiftIdOnSunday()   :").append(bean.getShiftIdOnSunday()).append("\n");
		}

		return ret.toString();

	}
}