/**
 * ファイル名：LogicInterface.java
 *
 * 変更履歴
 * 1.0  2010/07/19 Kazuya.Naraki
 */
package business.logic;

/**
 * 説明：ロジックのインターフェース
 * @author naraki
 *
 */
public interface LogicInterface {
	/**
	 * ロジックのメイン処理。
	 * @param プロセス
	 * @return フォワードキー
	 * @author Kazuya.Naraki
	 */
	abstract public String executeProc(String proc);
}
