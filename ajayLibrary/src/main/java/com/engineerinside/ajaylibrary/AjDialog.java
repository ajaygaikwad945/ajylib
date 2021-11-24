package com.engineerinside.ajaylibrary;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AjDialog extends Dialog {

        private String message="";
        private String title="";
        private String btYesText="";
        private String btNoText="";
        private boolean cancelable=false;
        private int icon=0;
        private View.OnClickListener btYesListener=null;
        private View.OnClickListener btNoListener=null;
        private View.OnClickListener btnClose=null;

        public AjDialog(Context context) {
            super(context,R.style.CustomAlertDialog);
        }

        public AjDialog(Context context, int themeResId) {
            super(context, R.style.CustomAlertDialog);
        }

        protected AjDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.custom_alert_dialog_new);

            TextView tvmessage = (TextView) findViewById(R.id.tvCustomTxt);
            ImageView iv_close = (ImageView) findViewById(R.id.iv_close);
            TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
            Button btYes = (Button) findViewById(R.id.buttonOK);
            Button btNo = (Button) findViewById(R.id.buttonCancel);

            tvmessage.setText(getMessage());
            tvTitle.setText(getTitle());
            btYes.setText(btYesText);
            btNo.setText(btNoText);
            btYes.setOnClickListener(btYesListener);
            btNo.setOnClickListener(btNoListener);
            iv_close.setOnClickListener(btnClose);
            //show();

            if(cancelable){
               setCancelable(true);
            }else{
                setCancelable(false);
            }

            if(message.equals("")){
                tvmessage.setVisibility(View.GONE);
            }else{
                tvmessage.setVisibility(View.VISIBLE);
            }
            if(title.equals("")){
                tvTitle.setVisibility(View.GONE);
            }else{
                tvTitle.setVisibility(View.VISIBLE);
            }
            if(btYesText.equals("")){
                btYes.setVisibility(View.GONE);
            }else{
                btYes.setVisibility(View.VISIBLE);
            }
            if(btNoText.equals("")){
                btNo.setVisibility(View.GONE);
            }else{
                btNo.setVisibility(View.VISIBLE);
            }

            if(btnClose!=null){
                iv_close.setVisibility(View.VISIBLE);
            }else{
                iv_close.setVisibility(View.GONE);
            }

        }



        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public int getIcon() {
            return icon;
        }



        public void setPositveButton(String yes, View.OnClickListener onClickListener) {
            dismiss();
            this.btYesText = yes;
            this.btYesListener = onClickListener;


        }

    public boolean isCancelable() {
        return cancelable;
    }

    public void Cancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public void setNegativeButton(String no, View.OnClickListener onClickListener) {
            dismiss();
            this.btNoText = no;
            this.btNoListener = onClickListener;

        }

    public void setBtnClose( View.OnClickListener onClickListener) {
            //dismiss();
            this.btnClose = onClickListener;

        }
        
        
    }

