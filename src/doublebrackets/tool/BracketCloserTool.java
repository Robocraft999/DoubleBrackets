/**
 * 
 * Lars Kaltenbach, 2013
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
 * @author		Lars Kaltenbach
 * @modified	15/07/13
 * @version		001
 */

package doublebrackets.tool;

import processing.app.Base;

public class BracketCloserTool {
	Base base;
	
	//needed to remove double brackets (when typing too fast) 
	char lastChar;
	
	// define which characters should get closed
	char[] openingChar = { '(', '[', '{', '"', '\'' };
	char[] closingChar = { ')', ']', '}', '"', '\'' }; 
	
	public BracketCloserTool() {   
	} 
	  
	public BracketCloserTool(Base _base) { 
		base = _base;
	}
   
	public void update( char key ) {
		// loop through array of opening brackets to trigger completion
		for(int i = 0; i < openingChar.length; i++ ) {
			// if nothing is selected just add closing bracket, else wrap brackets around selection  
			if( !base.getActiveEditor().isSelectionActive() ) {
				if( key == openingChar[i] ) addClosingChar(i); 
				else if( key == closingChar[i] && lastChar == openingChar[i] ) removeClosingChar(i);
			} else if( key == openingChar[i] && base.getActiveEditor().isSelectionActive() ) addClosingChar(i, base.getActiveEditor().getSelectionStart(), base.getActiveEditor().getSelectionStop() );
		}
	}
	
	//add closing bracket and set caret inside brackets
	private void addClosingChar(int positionOfChar) {
		base.getActiveEditor().insertText( Character.toString( closingChar[positionOfChar] ) );
		
		int cursorPos = base.getActiveEditor().getCaretOffset();
		base.getActiveEditor().setSelection(cursorPos-1, cursorPos-1);
		lastChar = openingChar[positionOfChar] ;
	}
	
	//if something is selected wrap closing brackets around selection
	private void addClosingChar(int positionOfChar, int startSelection, int endSelection) {
		base.getActiveEditor().setSelection(endSelection, endSelection);
		base.getActiveEditor().insertText( Character.toString( closingChar[positionOfChar] ));
		base.getActiveEditor().setSelection(startSelection, startSelection);
		lastChar = openingChar[positionOfChar] ;
	}
	
	//prevents something like ()) when typing too fast
	private void removeClosingChar(int positionOfChar) {
		//return if character is ' or "
		if(closingChar[positionOfChar] == '\'' || closingChar[positionOfChar] == '"' ) return;
		
		String sketchContent = base.getActiveEditor().getText();
        int cursorPos = base.getActiveEditor().getCaretOffset();

        String newContent1 = sketchContent.substring(0, cursorPos);
        String newContent2 = sketchContent.substring(cursorPos+1, sketchContent.length());

        base.getActiveEditor().setText(newContent1 + newContent2);
        base.getActiveEditor().setSelection(cursorPos, cursorPos);
        
        lastChar = closingChar[positionOfChar]; 
	}
	
}
