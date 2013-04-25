/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import gui.FrmTabulator;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author pojbuitrago
 */
public class ControlFrmTabulator {
    FrmTabulator gui;
    
    public ControlFrmTabulator(FrmTabulator aThis) {
        this.gui = aThis;
    }

    public void tabule() {
        String initText = gui.txtInitValue.getText();
        gui.txtEndValue.setText("");
        int tabSize = Integer.parseInt(gui.spTabSize.getValue() + "");
        
        String[] textSplited = initText.split("\n");
        String tabString = getTabString(tabSize);
        for(int i = 0 ; i < textSplited.length ; i++) {
            gui.txtEndValue.append( tabString + textSplited[i] + "\n");
        }
    }

    private String getTabString(int tabSize) {
        StringBuilder tabString = new StringBuilder("");
        for(int i = 0 ; i < tabSize ; i++) tabString.append(" ");
        
        return tabString.toString();            
    }

    public void removeSpaces() {
        String initText = gui.txtInitValue.getText();
        gui.txtEndValue.setText("");
        int tabSize = Integer.parseInt(gui.spTabSize.getValue() + "");
        String tabString = getTabString(tabSize);
        if (tabSize > 0) {
            String[] textSplited = initText.split("\n");
            for(int i = 0 ; i < textSplited.length ; i++) {
                if (textSplited[i].trim().length() > 0) {
                    if (textSplited[i].length() < tabSize) textSplited[i] = textSplited[i] + tabString; //Si el texto es menor a los espacios que se quieren remover, agrego espacios a la derecha para poder realizar operacon
                    int blankSpaces = getBlankSpaces(textSplited[i]);
                    int beginIndex = tabSize;
                    
                    if (tabSize > blankSpaces) beginIndex = blankSpaces; //Si se intenta quitar mas espacios en blancos de los que hay
                        
                    gui.txtEndValue.append( textSplited[i].substring(beginIndex, textSplited[i].length()) + "\n");
                } else {
                    gui.txtEndValue.append("\n");
                }
            }
        } else {
            gui.txtEndValue.setText(initText);
        }
    }

    private int getBlankSpaces(String text) {
        int cant = 0;
        boolean letterFound = false;
        char[] charArrayText = text.toCharArray();
        
        for(int i = 0 ; i < charArrayText.length && !letterFound ; i++) {
            if (charArrayText[i] == ' ') 
                cant++;
            else 
                letterFound = true;
        }
        
        return cant;
    }

    public void copyEndValueToClipBoard() {
        String endValue = gui.txtEndValue.getText();
        StringSelection stringSelection = new StringSelection(endValue);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    
}
