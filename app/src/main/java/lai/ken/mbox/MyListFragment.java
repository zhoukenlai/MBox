package lai.ken.mbox;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListFragment extends ListFragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v;
        v = inflater.inflate(R.layout.query, container, false);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(),
                    R.layout.listviewitem,
                    new String[]{"img","title","info","opttime"},
                    new int[]{R.id.img,R.id.title,R.id.info,R.id.opttime});
            setListAdapter(adapter);
        }
//        else {
//            //相当于Fragment的onPause
//        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        try {
            HashMap<String, Object> view = (HashMap<String, Object>) l.getItemAtPosition(position);
            Toast.makeText(getActivity(), view.get("title").toString(), Toast.LENGTH_LONG).show();
        }
        catch( Exception e) {
            Log.e("HashMap",e.toString() );
        }
    }

    private List<Map<String, Object>> getData() {
        List<Map<String ,Object>> list = new ArrayList<>();
        // 从数据库中取
        SQLiteDatabase db;
        db = getActivity().openOrCreateDatabase("MBox.db", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM MBOX ORDER BY OPTTIME DESC LIMIT 10 ",null);
        while (c.moveToNext()) {
            String stitle = c.getString(c.getColumnIndex("TITLE"));
            String snote = c.getString(c.getColumnIndex("NOTE"));
            String sopttime = c.getString(c.getColumnIndex("OPTTIME"));
            String spic = c.getString(c.getColumnIndex("PIC"));

            Map<String, Object> map = new HashMap<>();
            map.put("img", R.drawable.b111);
            map.put("title", stitle);
            map.put("info", snote);
            map.put("opttime", sopttime);

            list.add(map);
        }
        c.close();
        db.close();

        return list;
    }
}
