package com.td.baseapp.ui;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.td.baseapp.R;
import com.td.baseapp.bean.UserBean;
import com.td.baseapp.db.GreenDaoManager;
import com.td.baseapp.gen.UserBeanDao;


import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class DBActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;

    private UserBeanDao userBeanDao;
    private List<UserBean> userBeens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
        userBeanDao = GreenDaoManager.getInstance().getmDaoSession().getUserBeanDao();
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions .request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean granted) throws Exception {
                        if (granted) { // Always true pre-M
                            Log.e("===","授权成功");
                        } else {
                            Log.e("===","授权失败");
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                userBeens = new ArrayList<>();
                userBeens.add(new UserBean("12","lalal"));
                userBeens.add(new UserBean("13","lalal2"));
                userBeens.add(new UserBean("12","lalal3"));

                saveNLists(userBeens);
                break;
            case R.id.button2:
              //  userBeanDao.loadAll();
              //  Log.e("===", Arrays.toString(userBeanDao.loadAll().toArray()) + "  ");
               // querydataByRaw();
                userBeanDao.queryBuilder().where(UserBeanDao.Properties.UserId.eq("12")).unique();
                Log.e("===",userBeanDao.queryBuilder().where(UserBeanDao.Properties.UserId.eq("12"),UserBeanDao.Properties.UserName.eq("lalal3")).unique().toString() + "  ");
                break;
            case R.id.button3:
              userBeanDao.deleteByKeyInTx("13");
                break;
        }
    }

    /**
     * 批量插入或修改用户信息
     *
     * @param list 用户信息列表
     */
    public void saveNLists(final List<UserBean> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        userBeanDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    UserBean user = list.get(i);
                    userBeanDao.insertOrReplace(user);
                }
            }
        });

    }

    private void querydataBy() {////查询条件
        Query<UserBean> nQuery = userBeanDao.queryBuilder()
//                .where(UserDao.Properties.Name.eq("user1"))//.where(UserDao.Properties.Id.notEq(999))
                .orderAsc(UserBeanDao.Properties.UserId)//.limit(5)//orderDesc
                .build();
        List<UserBean> users = nQuery.list();
        Log.i("tag", "当前数量：" + users.size());
    }

    private void querydataByRaw() {////查询条件
        List<UserBean> been = userBeanDao.queryRaw("where _id=?", new String[]{"1"});
        Log.e("===", been.size() + "ttttttttttt");
    }

    /**
     * 删除所有数据
     */
    public void deleteAllNote() {
        userBeanDao.deleteAll();
    }

    /**
     * 根据用户类,删除信息
     *
     * @param user 用户信息类
     */
    public void deleteNote(UserBean user) {
        userBeanDao.delete(user);
    }
}
