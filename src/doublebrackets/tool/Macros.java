/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doublebrackets.tool;

import processing.app.ui.Editor;
import processing.core.PApplet;

/**
 *
 * @author dahjon / Robocraft999
 */
public class Macros {

    protected String key;
    protected String code;
    protected int carBack;
    protected boolean requiresKey;
    protected char req_key;
    

    private static Macros[] macrosList = {
    	new Macros("(", ")", 1),
    	new Macros("[", "]", 1),
    	new Macros("{", "}", 1),
    	new Macros("\"", "\"", 1),
    	new Macros("'", "'", 1),
    	new Macros("<", ">", 1),
    	new Macros("{", "  \n", 3, PApplet.ENTER),
    	new Macros("(", "  \n", 3, PApplet.ENTER)
    };

    public Macros(String key, String code, int carBack) {
        this.key = key;
        this.code = code;
        this.carBack = carBack;
        this.requiresKey = false;
    }
    
    public Macros(String key, String code, int carBack, char req_key) {
        this.key = key;
        this.code = code;
        this.carBack = carBack;
        this.requiresKey = true;
        this.req_key = req_key;
    }

    protected int getNumbersOfLineBreaks(String str){
        int nr=0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)=='\n'){
                nr++;
            } 
        }
        return nr;
    }

    public static Macros find(Editor editor,String sstr) {
        for (int i = 0; i < macrosList.length; i++) {
            Macros m = macrosList[i];
            if (m.stringIsThisMacro(editor, sstr)) {
                return m;
            }
            if(m.requiresKey && sstr.endsWith(""+m.req_key)) {
            	return m;
            }

        }
        return null;
    }

    public  boolean stringIsThisMacro(Editor editor, String sstr) {
        return key.equals(sstr);
    }

    public void insert(Editor editor, int indent) {
        int nr = getNumbersOfLineBreaks(code.substring(code.length()-carBack));
        String indentStr = new String(new char[indent]).replace('\0', ' ');
        String str = code.replaceAll("\n", "\n" + indentStr);
        editor.insertText(str);
        //System.out.println("nr = " + nr);
        int carPos=editor.getCaretOffset()-carBack-nr*indent;
        editor.getTextArea().setCaretPosition(carPos);
        
    }
    
    public boolean requiresKey() {
    	return requiresKey;
    }


}
