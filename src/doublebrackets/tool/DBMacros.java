package doublebrackets.tool;

import ecc.Macros;
import processing.app.ui.Editor;

public class DBMacros extends Macros{
	
	private static Macros[] macrosList = {
			new Macros("{", "\n   \n}", 7)
	};

	public DBMacros(String key, String code, int carBack) {
		super(key, code, carBack);
	}
	
	public static Macros find(Editor editor,String sstr) {
        for (int i = 0; i < macrosList.length; i++) {
            Macros m = macrosList[i];
            if (m.stringIsThisMacro(editor, sstr)) {
                return m; 
            } 

        }
        return null;
    }

}
