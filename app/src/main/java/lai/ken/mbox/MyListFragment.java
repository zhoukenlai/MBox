package lai.ken.mbox;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(),
                R.layout.listviewitem,
                new String[]{"img","title","info"},
                new int[]{R.id.img,R.id.title,R.id.info});
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        System.out.println(l.getChildAt(position));
        //HashMap<String, Object> view= (HashMap<String, Object>) l.getItemAtPosition(position);
        Toast.makeText(getActivity(), "XXXX", Toast.LENGTH_LONG).show();
    }

    private List<Map<String, Object>> getData() {
        List<Map<String ,Object>> list = new ArrayList<>();
        String[] strs = {"主题1","主题2","主题3","主题4","主题5"};
        for (String str : strs) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", R.drawable.b111);
            map.put("title", str);
            map.put("info", str+"数据过滤，生成运营商字段，根据系统ID和运营商，通过表TB_DISPATCH_CONFIG可以定位到发送通道ID（多条时，按权重来）");
            list.add(map);
        }

        return list;
    }
}
