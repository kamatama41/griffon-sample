package griffon.sample

import javax.swing.BorderFactory
import javax.swing.WindowConstants
import java.awt.BorderLayout

/**
 * ビューが持つアクションを定義する
 * id          -> 固有のID
 * name        -> 名前. buttonとかのラベル名になるっぽい
 * closure     -> 実行する処理
 * mnemonic    -> ニーモニック(altと一緒に押すと実行できるためのキー)
 * accelerator -> ショートカットキー
 */
actions {
	action(
		id: 'paint',
		name: 'Paint',
		closure: controller.paintGraph,
		mnemonic: 'P',
		accelerator: 'ctrl P'
	)

	action(
		id: 'about',
		name: 'About',
		closure: controller.showAbout,
		mnemonic: 'A',
		accelerator: 'F1'
	)
}

/**
 * Viewの実体をapplicationをルートノードとするビルダーで構築する
 * title                 -> 上のバーに表示される文言
 * size                  -> パネルのサイズ
 * preferredSize         -> 好ましいサイズ
 * pack                  -> ウィンドウサイズを（表示しているコンポーネントに応じて）最適な（最小な）サイズにする
 * defaultCloseOperation -> 「×」ボタンを押したときの挙動。WindowConstants.EXIT_ON_CLOSEはSystem.exitを実行する
 * location              -> ウインドウの位置locationByPlatform=trueの場合は機能しない
 * locationByPlatform    -> ウインドウの位置をOSに任せる
 * iconImage             -> JFrameの標準的なプロパティ(フレームのトップ画像)
 * iconImages            -> プラットフォームごとに最適なアイコンを選択する(jdk1.6以上であれば、iconImageではなくこちらを利用する)
 */
application(title: 'griffon-sample',
	size: [400,400],
//	preferredSize: [320, 240],
//	pack: true,
	defaultCloseOperation:WindowConstants.EXIT_ON_CLOSE,
	location: [100,100],
//	locationByPlatform: true,
	iconImage:  imageIcon('/griffon-icon-48x48.png').image,
	iconImages:[imageIcon('/griffon-icon-48x48.png').image,
			    imageIcon('/griffon-icon-32x32.png').image,
			    imageIcon('/griffon-icon-16x16.png').image]) {

	/**
	 * メニューバーの設定
	 */
	menuBar() {
		menu(mnemonic:'A', 'Action') {
			menuItem(action: paint)
		}
		glue()
		menu(mnemonic: 'H', 'Help') {
			menuItem(action: about)
		}
	}

	/**
	 * パネルの設定
	 */
	panel(border: BorderFactory.createEmptyBorder(6,6,6,6)) {
		borderLayout()
		vbox(constraints: BorderLayout.NORTH) {
			hbox {
				hstrut(width: 10)
				label 'f(x) = '
				textField(action: paint, columns: 20, text: bind(target: model, 'function'), 'sin(x)')
				button(action: paint)
			}
		}
		vbox(constraints: BorderLayout.WEST) {
			labeledSpinner('max', 1d)
			20.times { vglue() }
			labeledSpinner('min', -1d)
		}
		vbox(constraints: BorderLayout.CENTER, border: BorderFactory.createTitledBorder('Functional Plot')) {
			panel(id: 'canvas')
		}
		hbox(constraints: BorderLayout.SOUTH) {
			hstrut(width: 10)
			labeledSpinner('from', 0d)
			10.times { hglue() }
			labeledSpinner('to', 2d*Math.PI)
		}
	}
}

def labeledSpinner(label, value) {
	this.label(label)
	spinner(value: bind(target: model, label), stateChanged: controller.paintGraph,
		model: spinnerNumberModel(value: value)
	)
}