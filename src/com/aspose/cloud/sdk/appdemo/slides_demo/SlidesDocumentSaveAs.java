package com.aspose.cloud.sdk.appdemo.slides_demo;

import org.apache.log4j.BasicConfigurator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.slides.Document;
import com.aspose.cloud.sdk.slides.SaveFormat;

public class SlidesDocumentSaveAs extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg1;
	private Spinner function_arg2;
	private TextView result;
	private Button btnSubmit;
	protected SaveFormat saveFormat;
	protected String outputPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slides_document_saveas);
		init();

		function_arg2
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub
						saveFormat = (SaveFormat) function_arg2
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(SlidesDocumentSaveAs.this,
								"Please Select a Save Format",
								Toast.LENGTH_LONG).show();
					}
				});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							SlidesDocumentSaveAs.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					outputPath = function_arg1.getText().toString();
					Document obj = new Document(constructor_arg1.getText()
							.toString());
					obj.saveAs(outputPath, saveFormat);
					result.append("file is converted and saved in your sdcard");
				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.slides_document_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.slides_document_saveas_arg1);
		function_arg2 = (Spinner) findViewById(R.id.slides_document_saveas_arg2);
		function_arg2.setAdapter(new ArrayAdapter<SaveFormat>(this,
				android.R.layout.simple_list_item_1, SaveFormat.values()));
		result = (TextView) findViewById(R.id.txt_result);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		String app_sid = sp.getString("app_sid", "");
		String app_key = sp.getString("app_key", "");
		if (app_sid.equals("") || app_key.equals("")) {
			Toast.makeText(this,
					"No App Key or AppSid Define. Please Define Them First",
					Toast.LENGTH_LONG).show();
			this.finish();
		} else {
			AsposeApp.setAppInfo(app_key, app_sid);
			Product.setBaseProductUri("http://api.aspose.com/v1.1");
		}
	}
}
