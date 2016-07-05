package com.zbPro.seed.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2016/6/24.
 */
public class DigLog {
    Context context;

    public DigLog(Context context) {
        this.context = context;
    }

    protected void dialogSendHttpPost() {
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        builder.setMessage( "是否发送？" );
        builder.setTitle( "提示" );
        builder.setPositiveButton( "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } );
        builder.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } );
        builder.create().show();
    }

    protected void dialogSaveData() {
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        builder.setMessage( "是否保存数据？" );
        builder.setTitle( "提示" );
        builder.setPositiveButton( "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //在保存的时候获取所有View的数据

                dialog.dismiss();
            }
        } );
        builder.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } );
        builder.create().show();
    }

}
