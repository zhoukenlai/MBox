package lai.ken.mbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

public class MainActivity extends FragmentActivity
{
    private List<Fragment> mTabContents = new ArrayList<>();

    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("查看", "登记", "报表");

    private ViewPagerIndicator mIndicator;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.vp_indicator);

        initView();
        initDatas();

        //设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);

        //打开或创建test.db数据库
        db = openOrCreateDatabase("MBox.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MBOX(_ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE VARCHAR, NOTE VARCHAR, OPTTIME VARCHAR, PIC VARCHAR)");
        db.close();
    }

    private void initDatas()
    {
        MyListFragment fragment1 = new MyListFragment();
        mTabContents.add(fragment1);

        MyInsertFragment fragment2 = new MyInsertFragment();
        mTabContents.add(fragment2);

        MyReportFragment fragment3 = new MyReportFragment();
        mTabContents.add(fragment3);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
    }

    private void initView()
    {
        mViewPager = (ViewPager) findViewById(R.id.id_vp);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
    }

}
