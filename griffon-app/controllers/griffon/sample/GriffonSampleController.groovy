package griffon.sample

import javax.swing.JOptionPane
import java.awt.Color

class GriffonSampleController {
    // modelとviewはGriffonによってインジェクションされる
    def model
    def view

    // void mvcGroupInit(Map args) {
	//		// このメソッドはモデルとビューがインジェクションされる前に呼ばれる
    // }

    // void mvcGroupDestroy() {
    //    // このメソッドはグループが停止されるときに呼ばれる
    // }

	/**
	 * グラフの描画処理
	 */
	def paintGraph = { evt = null ->
		def calc = new Dynamo(model.function)
		def canvas = view.canvas
		def g = canvas.graphics
		int w = canvas.size.width
		int h = canvas.size.height

		g.color = new Color(255,255,150)
		g.fillRect(0, 0, w, h)
		g.color = Color.BLUE

		def dx = (model.to - model.from) / w
		def dy = h / (model.max - model.min)
		int ceiling = h + model.min * dy
		int lastY = calc.f(model.from) * dy
		for(x in (1..w)) {
			int y = calc.f(model.from + x * dx) * dy
			g.drawLine(x-1, ceiling - lastY, x, ceiling-y)
			lastY = y
		}
	}

	/**
	 * Aboutの表示処理
	 */
    def showAbout = { evt = null ->
		JOptionPane.showMessageDialog(app.windowManager.windows[0]
				, '''\
A Function Plotter
that services as a SwingBuilder example for
Groovy in Action
''')
    }
}

class Dynamo {
	static final GroovyShell SHELL = new GroovyShell()
	Script funtionScript

	Dynamo(String function) {
		this.funtionScript = SHELL.parse('import static java.lang.Math.*;' + function)
	}

	Object f(x) {
		funtionScript.x = x
		return funtionScript.run()
	}

}
