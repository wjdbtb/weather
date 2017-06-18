package com.example.mydetqyb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.itcast.weather.R;


public class MainActivity extends Activity implements OnClickListener {
private TextView select_city, select_tianq, select_wend, select_fong,
select_pm,select_shid,select_jiany;
private Map<String, String> map;
private List<Map<String, String>> list;
private String wend, tianq, minz, pm, fong,shid,jiany;
private ImageView icon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ʼ���ı��ؼ�
				select_city = (TextView) findViewById(R.id.select_city);
				select_tianq = (TextView) findViewById(R.id.select_tianq);
				select_wend = (TextView) findViewById(R.id.wend);
				select_fong = (TextView) findViewById(R.id.fong);
				select_pm = (TextView) findViewById(R.id.pm);
				icon = (ImageView) findViewById(R.id.icon);
				select_shid=(TextView) findViewById(R.id.shid);
				select_jiany=(TextView) findViewById(R.id.jiany);

				findViewById(R.id.city_sh).setOnClickListener(this);
				findViewById(R.id.city_bj).setOnClickListener(this);
				findViewById(R.id.city_cs).setOnClickListener(this);
				findViewById(R.id.city_ld).setOnClickListener(this);

				try {
					// �����ϱ�д�õĽ�������,tianq.xml�������Ŀ¼�£�ʹ������������м���
					// infos����ÿ�����е�������Ϣ���ϣ��������������Ҫ���������ݡ�
					List<WeatherInfo> infos = WeatherService
							.getWeatherInfos(MainActivity.class.getClassLoader()
									.getResourceAsStream("weather.xml"));
					// ѭ����ȡinfos�е�ÿһ������
					list = new ArrayList<Map<String, String>>();
					for (WeatherInfo info : infos) {
						map = new HashMap<String, String>();
						map.put("wend", info.getWend());
						map.put("tianq", info.getTianq());
						map.put("minz", info.getMinz());
						map.put("pm", info.getPm());
						map.put("fong", info.getFong());
						map.put("shid", info.getShid());
						map.put("jiany", info.getJiany());
						list.add(map);
					}
					// ��ʾ������Ϣ���ı��ؼ���
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(this, "������Ϣʧ��", 0).show();
				}

				getMap(1, R.drawable.sun);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.city_sh:
			getMap(0, R.drawable.cloud_sun);
			break;
		case R.id.city_bj:
			getMap(1, R.drawable.sun);
			break;
		case R.id.city_cs:
			getMap(2, R.drawable.clouds);
			break;
		case R.id.city_ld:
			getMap(3, R.drawable.clouds);
			break;
		}
	}

	private void getMap(int number, int iconNumber) {
		Map<String, String> bjMap = list.get(number);
		wend = bjMap.get("wend");
		tianq = bjMap.get("tianq");
		minz = bjMap.get("minz");
		pm = bjMap.get("pm");
		fong = bjMap.get("fong");
		shid = bjMap.get("shid");
		jiany = bjMap.get("jiany");
		select_city.setText(minz);
		select_tianq.setText(tianq);
		select_wend.setText("" + wend);
		select_fong.setText("����  : " + fong);
		select_pm.setText("pm: " + pm);
		select_shid.setText("ʪ��" +shid);
		select_jiany.setText("����" +jiany);
		icon.setImageResource(iconNumber);
	}

}
