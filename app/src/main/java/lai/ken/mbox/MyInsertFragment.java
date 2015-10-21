package lai.ken.mbox;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyInsertFragment extends Fragment
{
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v;
        v = inflater.inflate(R.layout.insert,container, false);

        Button mLeftMenu = (Button) v.findViewById(R.id.b1);
        final EditText etitle = (EditText) v.findViewById(R.id.etitle);
        final EditText enote = (EditText) v.findViewById(R.id.enote);
        final ImageView ii = (ImageView) v.findViewById(R.id.im);
        ii.setImageResource(R.drawable.b111);

        mLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stitle = etitle.getText().toString();
                String snote = enote.getText().toString();

                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String sTime = formatter.format(curDate);

                db = getActivity().openOrCreateDatabase("MBox.db", Context.MODE_PRIVATE, null);
                db.execSQL("INSERT INTO MBOX VALUES (NULL, ?, ?, ?, NULL)", new Object[]{stitle, snote, sTime});
                db.close();

                Toast.makeText(getActivity(), "保存成功！", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}
