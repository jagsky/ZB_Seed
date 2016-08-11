package com.zbPro.seed.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;

import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.dao.FarmaerDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FarmerBasedataActiivty extends BaseActivity {
    String dk;
    FarmaerDao farmaerDao;
    FarmerBean farmerBean;
    List<HashMap<String, Object>> beanList = new ArrayList<HashMap<String, Object>>();
    List lineData = new ArrayList();
    @Bind(R.id.farmer_name)
    EditText farmerName;
    @Bind(R.id.farmer_Number_et)
    EditText farmerNumberEt;
    @Bind(R.id.farmer_type_et)
    EditText farmerTypeEt;
    @Bind(R.id.farmer_base_et)
    EditText farmerBaseEt;
    @Bind(R.id.farmer_house_et)
    EditText farmerHouseEt;
    @Bind(R.id.farmer_idcard_et)
    EditText farmerIdcardEt;
    @Bind(R.id.farmer_temperature_et)
    EditText farmerTemperatureEt;
    @Bind(R.id.farmer_voyages_et)
    EditText farmerVoyagesEt;
    @Bind(R.id.farmer_manure_et)
    EditText farmerManureEt;
    @Bind(R.id.farmer_troopsName_et)
    EditText farmerTroopsNameEt;
    @Bind(R.id.farmer_yield_et)
    EditText farmerYieldEt;
    @Bind(R.id.farmer_aera_et)
    EditText farmerAeraEt;
    @Bind(R.id.farmer_call)
    Button farmerCall;
    public static final int REQUESTCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_basedata_actiivty);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        dk = intent.getStringExtra("dk");
        System.out.println(dk);
        //查询farmer表中的数据
        queryFarmerDatabase();
        FarmerBean farmerBean = new FarmerBean();


    }

    //查询farmer表中的数据
    private void queryFarmerDatabase() {
        farmaerDao = new FarmaerDao(FarmerBasedataActiivty.this);
        farmerBean = farmaerDao.queryFarmerLine(dk);
       /* HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("姓名", farmerBean.getName());
        map.put("地块号", farmerBean.getdKNumber());
        map.put("种类", farmerBean.getType());
        map.put("基地名称", farmerBean.getBaseName());
        map.put("联系地址", farmerBean.getHouse());
        map.put("身份证", farmerBean.getIdCard());
        map.put("区域温度", farmerBean.getTemperature());
        map.put("村委会配合程度", farmerBean.getVoyages());
        map.put("土地管理模式", farmerBean.getManure());
        map.put("所在生产队", farmerBean.getTroopsName());
        map.put("地块往年产量", farmerBean.getYield());
        map.put("地块面积", farmerBean.getAera());
        beanList.add(map);
        SimpleAdapter simpleAdapter = new SimpleAdapter(FarmerBasedataActiivty.this,beanList,R.layout.farmerline_data,
                new String[]{"姓名"},new int[]{});*/
        farmerName.setText(farmerBean.getFarmerName());
        farmerNumberEt.setText(farmerBean.getdKnumber());
        farmerTypeEt.setText(farmerBean.getType());
        farmerBaseEt.setText(farmerBean.getBaseName());
        farmerHouseEt.setText(farmerBean.getHouse());
        farmerIdcardEt.setText(farmerBean.getIdcard());
        farmerTemperatureEt.setText(farmerBean.getTemperature());
        farmerVoyagesEt.setText(farmerBean.getFarmer_voyages());
        farmerManureEt.setText(farmerBean.getFarmer_manure());
        farmerTroopsNameEt.setText(farmerBean.getFarmer_troopsname());
        farmerYieldEt.setText(farmerBean.getFarmer_yield());
        farmerAeraEt.setText(farmerBean.getFarmer_area());

    }

    @OnClick(R.id.farmer_call)
    public void onClick() {
        //检查权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {

                new AlertDialog.Builder(FarmerBasedataActiivty.this)
                        .setMessage("app需要开启权限才能使用此功能")
                        .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create()
                        .show();
            } else {

                //申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUESTCODE);
            }

        } else {
            //已经拥有权限进行拨打
            call();
        }

    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + farmerBean.getHouse());
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUESTCODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意了授权
                    call();

                } else {
                    //sssssssss
                    //用户拒绝了授权
                    // Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }

    }


}
