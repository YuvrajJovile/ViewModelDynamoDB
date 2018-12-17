package sai.com.viewmodeljava.utils;

import android.content.Context;
import android.widget.Toast;

public class CodeSnipet {

    private static CodeSnipet mCodeSnipet;


    public static CodeSnipet getCodeSnipet(){
        if(mCodeSnipet==null)
            mCodeSnipet = new CodeSnipet();
        return mCodeSnipet;
    }


    public void showMessage(Context pContext, String pMessage){
        Toast.makeText(pContext, pMessage, Toast.LENGTH_SHORT).show();
    }
}
