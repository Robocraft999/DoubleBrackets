/**
 * ##sentence##
 * 
 * Thanks to Lars Kaltenbach and dahjon;
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
import java.util.Arrays;
import java.util.List;

import processing.app.Base;
import processing.app.tools.Tool;
import processing.app.ui.Editor;
import processing.core.PApplet;

public class DoubleBrackets implements Tool, KeyListener {
	Base base;
    Editor editor;
	List<Character> openingChar = Arrays.asList( '(', '[', '{', '"', '\'', '<' );

	@Override
	public String getMenuTitle() {
		return "##tool.name##";
	}

	@Override
	public void init(Base base) {
		this.base = base;
	}

	@Override
	public void run() {
		editor = base.getActiveEditor();
		editor.getTextArea().addKeyListener(this);
		
		System.out.println("##tool.name## ##tool.prettyVersion## by ##author## (rework of SpeedTool for Processing 2 (osx) by Lars Kaltenbach "
				+ "with the help of Macros from ExtendedCodeCompletion by dahjon)");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		if(openingChar.contains(c) || c == PApplet.ENTER) {
			e.consume();
			//String txt = getTextBeforeCaret();
			//System.out.println("txt = '" + txt + "'");
			String txt = ""+c;
			int indent = getSpacesBeforeText(txt.length());
			//System.out.println("indent = " + indent);
			Macros m = Macros.find(editor, txt);
			if (m != null) {
				m.insert(editor, indent);
			}
		}
	}
	
    private int getSpacesBeforeText(int textLen) {
        int start = editor.getCaretOffset() - 1 - textLen;
        int i = start;
        String edtext = editor.getText();
        if (start >= 0) {
            char c = edtext.charAt(start);
            while (c == ' ' && i >= 0) {
                //System.out.println("i = " + i+" c = " + c);
                i--;
                if (i >= 0) {
                    c = editor.getText().charAt(i);
                }
            }
        }

        return start - i;
    }

    private String getTextBeforeCaret() {
        int start = editor.getCaretOffset() - 1;
        if (start >= 0) {
            //System.out.println("start = " + start);
            int i = start;
            String edtext = editor.getText();
            System.out.println("edtext = " + edtext);
            //System.out.println("edtext.length() = " + edtext.length());
            char c = edtext.charAt(start);
            while (!openingChar.contains(c) && i >= 0) {
                i--;
                if (i >= 0) {
                    c = editor.getText().charAt(i);
                }
            }
            i++;
            return editor.getText().substring(i, start + 1);
        } else {
            return "";
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
