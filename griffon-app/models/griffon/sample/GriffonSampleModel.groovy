package griffon.sample

/**
 * 関数描画アプリケーションのモデル
 */
class GriffonSampleModel {
	/*
	 * @Bindableを付けるとviewからそれぞれの値を変更できるようになる
	 */

	/** 関数(式)を表す文字列 */
	@Bindable String function

	/** x座標の最小値 */
	@Bindable double from
	/** x座標の最大値 */
	@Bindable double to
	/** y座標の最小値 */
	@Bindable double min
	/** y座標の最大値 */
	@Bindable double max
}