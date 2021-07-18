/**
 * ##sentence##
 * 
 * Thanks to Lars Kaltenbach.
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 *
 * @author   ##author##
 * @modified ##date##
 * @version  ##tool.prettyVersion##
 */

package doublebrackets.tool;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import processing.app.Base;
import processing.app.tools.Tool;
import processing.app.ui.Editor;

public class DoubleBrackets implements Tool, KeyListener {
	Base base;
	BracketCloserTool bc;

	@Override
	public String getMenuTitle() {
		return "##tool.name##";
	}

	@Override
	public void init(Base base) {
		this.base = base;
		bc = new BracketCloserTool(base);
	}

	@Override
	public void run() {
		Editor editor = base.getActiveEditor();
		editor.getTextArea().addKeyListener(this);
		
		System.out.println("##tool.name## ##tool.prettyVersion## by ##author## (rework of SpeedTool for Processing 2 (osx) by Lars Kaltenbach)");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		bc.update(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
