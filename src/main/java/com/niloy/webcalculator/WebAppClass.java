package com.niloy.webcalculator;
/**
 * Created by user on 5/10/2017.
 */


import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.regex.Pattern;


public class WebAppClass {

    MainActivity webActivity = null;
    WebView webView=null;
    String curOperator=null;
    String[] digit=new String[0];
    boolean arithExp=false;
    float finalSum;


    WebAppClass(Context context ,WebView webviewObj)
    {
        this.webActivity= (MainActivity) context;
        this.webView=webviewObj;
    }

    @JavascriptInterface
    public void showToast(String str)
    {
        Toast.makeText(webActivity,str,Toast.LENGTH_LONG).show();
    }
    @JavascriptInterface
    public void addNum()
    {
        this.arithExp=false;
    }
    @JavascriptInterface
    public void addOperator(String op)
    {
        this.curOperator=op;
        this.arithExp=false;

    }

    @JavascriptInterface
    public String getResult(String exp)
    {
        String numPat="^(-)?[0-9]+([.][0-9]+)?[+*/-][0-9]+([.][0-9]+)?$";
        if(Pattern.matches(numPat,exp))
        {

            this.doSum(exp);
            if(this.arithExp)
            {
                Toast.makeText(webActivity,"Can't Devided by 0",Toast.LENGTH_LONG).show();
                return exp;
            }
            else
            {
                String res=String.valueOf(this.finalSum);
                return res;
            }
        }
        else
        {

            Toast.makeText(webActivity,"Invalid Expression",Toast.LENGTH_LONG).show();
            return exp;
        }
    }

    private void doSum(String exp)
    {
        switch (this.curOperator)
        {
            case "+":
            {
                this.digit=exp.split(Pattern.quote("+"));
                this.finalSum=Float.parseFloat(digit[0])+Float.parseFloat(digit[1]);
                break;
            }
            case "-":
            {
                this.digit=exp.split("-");
                this.finalSum=Float.parseFloat(digit[0])-Float.parseFloat(digit[1]);
                break;
            }
            case "*":
            {
                this.digit=exp.split(Pattern.quote("*"));
                this.finalSum=Float.parseFloat(digit[0])*Float.parseFloat(digit[1]);
                break;
            }
            case "/":
            {
                this.digit=exp.split("/");
                if(Float.parseFloat(digit[1])!=0)
                {
                    this.finalSum=Float.parseFloat(digit[0])/Float.parseFloat(digit[1]);
                }
                else
                {
                    this.arithExp=true;
                    Toast.makeText(this.webActivity,"Excp: "+String.valueOf(arithExp),Toast.LENGTH_LONG).show();
                }

                break;
            }
            default:{
                break;
            }

        }
    }


}
