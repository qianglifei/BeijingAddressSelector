package com.bksx.mobile.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class StaticObject {
	/**
	 * 社管通 SharePrefenc
	 */
	public static final String SHAREPREFERENC = "CySgtSharePreferenc";
	public static final String LOGTAG = "Cysgt_log";


//	/**
//	 * 获取接口数据
//	 *
//	 * @param context
//	 *            Activity.context
//	 * @param code
//	 *            业务代码
//	 * @param data
//	 *            业务数据
//	 * @return 接口返回数据
//	 */
//	public static synchronized String getMessage(Context context, String code,
//												 String data) {
//		SharedPreferences preferences = context.getSharedPreferences(
//				StaticObject.SHAREPREFERENC, Activity.MODE_PRIVATE);
//		String imsi = preferences.getString("IMSI", null);
//		if ("1".equals(Config.read(context, "isDemo"))) {
//			DemoData demoData = new DemoData();
//			return demoData.getDemoData(code);
//		}
//
//		HttpRequest hr = new HttpRequest();
//
//		String result = "";
//		try {
//			result = hr.sendString(context, code, imsi, data);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (result == null)
//			result = "";
//
//		if ("_error_bmg_00001".equals(result)) {
//			// 由于您长时间无操作，请重新登录
//			result = RequestCode.CSTR;
//
//			Intent intent = new Intent(context, LoginActivity.class);
//			intent.putExtra("tips", "conn_time_out");
//			context.startActivity(intent);
//		}
//		return result;
//	}

//	/**
//	 * 手机签到
//	 *
//	 * @param context
//	 * @param code
//	 * @param imsi
//	 */
//	public static void sjqd(final Context context) {
//		SharedPreferences preferences = context.getSharedPreferences(
//				StaticObject.SHAREPREFERENC, Activity.MODE_PRIVATE);
//		final String imsi = preferences.getString("IMSI", null);
//		// 查询表里数据是不是今天
//		String sql = "select qdsj from sjqdb";
//		SqliteHelper sh = SqliteHelper.getInstance(context);
//		Cursor c = sh.Query(sql, null);
//		// 签到日期
//		String qdrq = "";
//		if (c.getCount() > 0) {
//			c.moveToFirst();
//			qdrq = c.getString(0);
//		}
//		c.close();
//		sh.close();
//		final String dqrq = new SimpleDateFormat("yyyyMMdd").format(new Date());
//		if (!dqrq.equals(qdrq)) {
//			// 签到
//			new Timer().schedule(new TimerTask() {
//				@Override
//				public void run() {
//					SharedPreferences preference = context
//							.getSharedPreferences(StaticObject.SHAREPREFERENC,
//									Activity.MODE_PRIVATE);
//					String xzqh = preference.getString("login_xzqh", "");
//					String glyid = preference.getString("login_number", "");
//					String glyxm = preference.getString("login_admin_name", "");
//					String area = preference.getString("login_area", "");
//					HttpRequest hr = new HttpRequest();
//					String data = "{'xzqh':'" + xzqh + "','glybm':'" + glyid
//							+ "','glyxm':'" + glyxm + "','area':'" + area
//							+ "','qdrq':'" + dqrq + "'}";
//					String code = "070002";
//					String result = hr.sendString(context, code, imsi, data);
//					if (result == null)
//						result = "";
//					if (!"".equals(result)
//							&& (result.contains("09") || result.contains("90"))) {
//						String sql = "update sjqdb set qdsj = '" + dqrq + "'";
//						SqliteHelper sh = SqliteHelper.getInstance(context);
//						sh.execSQL(sql, null);
//						sh.close();
//					}
//				}
//			}, 400);
//		}
//	}

//	/**
//	 * 获取接口数据
//	 *
//	 * @param context
//	 *            Activity.context
//	 * @param code
//	 *            业务代码
//	 * @param data
//	 *            业务数据
//	 * @param filePath
//	 *            上传文件路径
//	 * @return 接口返回数据
//	 */
//	public static synchronized String getMessage(Context context, String code,
//												 String data, String filePath) {
//
//		SharedPreferences preferences = context.getSharedPreferences(
//				StaticObject.SHAREPREFERENC, Activity.MODE_PRIVATE);
//		String imsi = preferences.getString("IMSI", null);
//		if ("1".equals(Config.read(context, "isDemo"))) {
//			DemoData demoData = new DemoData();
//			return demoData.getDemoData(code);
//		}
//		HttpRequest hr = new HttpRequest();
//		String result = hr.sendFile(context, code, imsi,
//				new String[] { filePath }, data);
//		if (result == null)
//			result = "";
//		if ("_error_bmg_00001".equals(result)) {
//			// 由于您长时间无操作，请重新登录
//			result = RequestCode.CSTR;
//
//			Intent intent = new Intent(context, LoginActivity.class);
//			intent.putExtra("tips", "conn_time_out");
//			context.startActivity(intent);
//		}
//		return result;
//	}

//



//	/**
//	 * 获取接口数据
//	 *
//	 * @param context
//	 *            Activity.context
//	 * @param code
//	 *            业务代码
//	 * @param data
//	 *            业务数据
//	 * @param filePath
//	 *            上传文件路径
//	 * @return 接口返回数据
//	 */
//	public static synchronized String getMessage(Context context, String code,
//												 String data, String zpPath, String lyPath) {
//
//		SharedPreferences preferences = context.getSharedPreferences(
//				StaticObject.SHAREPREFERENC, Activity.MODE_PRIVATE);
//		String imsi = preferences.getString("IMSI", null);
//		if ("1".equals(Config.read(context, "isDemo"))) {
//			DemoData demoData = new DemoData();
//			return demoData.getDemoData(code);
//		}
//		HttpRequest hr = new HttpRequest();
//		String result = hr.sendFile(context, code, imsi, new String[] { zpPath,
//				lyPath }, data);
//		if (result == null)
//			result = "";
//		if ("_error_bmg_00001".equals(result)) {
//			// 由于您长时间无操作，请重新登录
//			result = RequestCode.CSTR;
//
//			Intent intent = new Intent(context, LoginActivity.class);
//			intent.putExtra("tips", "conn_time_out");
//			context.startActivity(intent);
//		}
//		return result;
//	}

//

//	/**
//	 * 网络请求进度条
//	 *
//	 * @param context
//	 * @param title
//	 * @param message
//	 * @param indeterminate
//	 * @return
//	 */

	public static ProgressDialog showDialog(Context context,
                                            CharSequence message) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle("进度");
		dialog.setMessage(message);
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setOnCancelListener(null);
		dialog.show();
		return dialog;
	}


	static Context mcontext;
	static Toast toast1 = null;
	static Toast toast2 = null;
	static TextView textView1;
	static TextView textView2;
//
	/**
	 * 显示Toast
	 */
	public static void showToast(Context context, String msg) {

		if (toast1 == null || !context.equals(mcontext)) {
			mcontext = context;
			toast1 = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			LayoutInflater inflater = LayoutInflater.from(context
					.getApplicationContext());
			View view = inflater.inflate(R.layout.my_toasts, null);
			textView1 = (TextView) view.findViewById(R.id.toast_text);
			toast1.setView(view);
			toast1.setGravity(Gravity.CENTER,0,-200);
		}
		textView1.setHeight(DensityUtil.dip2px(context,93.3f));
		textView1.setText(msg);
		textView1.setTextColor(Color.parseColor("#ffffff"));
		toast1.show();
	}

    public static void showToasts(Context context, String msg) {

        if (toast2 == null || !context.equals(mcontext)) {
            mcontext = context;
            toast2 = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            LayoutInflater inflater = LayoutInflater.from(context.getApplicationContext());
            View view = inflater.inflate(R.layout.my_toasts, null);
            textView2 = (TextView) view.findViewById(R.id.toast_text);
            toast2.setView(view);
            toast2.setGravity(Gravity.FILL_HORIZONTAL, 0, -390);
        }
        textView2.setHeight(60);
        textView2.setText(msg);
        textView2.setTextColor(Color.BLACK);
        toast2.show();
    }
	/**
	 * 列出特定路径下的文件名以startWith开头的文件,并将其删除
	 *
=======
//	public static ProgressDialog showDialog(Context context,
//											CharSequence message) {
//		ProgressDialog dialog = new ProgressDialog(context);
//		dialog.setTitle("进度");
//		dialog.setMessage(message);
//		dialog.setIndeterminate(true);
//		dialog.setCancelable(true);
//		dialog.setOnCancelListener(null);
//		dialog.show();
//		return dialog;
//	}
//
//	static Toast toast;
//	static Context mcontext;
//	static TextView textView = null;

//	/**
//	 * 显示Toast
//	 */
//	public static void showToast(Context context, String msg) {
//
//		if (toast == null || !context.equals(mcontext)) {
//			mcontext = context;
//			toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
//			LayoutInflater inflater = LayoutInflater.from(context
//					.getApplicationContext());
//			View view = inflater.inflate(R.layout.my_toast, null);
//			textView = (TextView) view.findViewById(R.id.toast_text);
//			toast.setDuration(Toast.LENGTH_LONG);
//			toast.setView(view);
//			toast.setGravity(Gravity.CENTER, 0, -300);
//		}
//		textView.setText(msg);
//		toast.show();
//
//	}

	/**
	 * 列出特定路径下的文件名以startWith开头的文件,并将其删除
	 * 

	 * @param directoryName
	 *            路径名
	 */
	public static void deletes(String directoryName, final String startWith) {
		File dir = new File(directoryName);
		// 确定该路径指向一个目录
		if (dir.exists() && dir.isDirectory()) {
			// 列出所有以sfz_tmp开头的文件
			File[] files = dir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith(startWith);
				}
			});

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile() && files[i].exists())
					files[i].delete();
			}
		}
	}


//	/**
//	 * 设置TextView为选择框
//	 *
//	 * @param context
//	 * @param strs
//	 *            选择数组
//	 * @param tv
//	 *            要显示的组件
//	 */
//	public static void setSelectView(final Context context,
//									 final String[] strs, final TextView tv) {
//		tv.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				new AlertDialog.Builder(context)
//						.setIcon(android.R.drawable.ic_dialog_info)
//						.setTitle("请选择")
//						.setItems(strs, new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								tv.setText(strs[which]);
//							}
//						}).show();
//			}
//		});
//
//	}
//

	/**
	 * 设置TextView为选择框
	 * 
	 * @param context
	 * @param strs
	 *            选择数组
	 * @param tv
	 *            要显示的组件
	 */
	public static void setSelectView(final Context context,
                                     final String[] strs, final TextView tv) {
		tv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(context)
						.setIcon(android.R.drawable.ic_dialog_info)
						.setTitle("请选择")
						.setItems(strs, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
                                                int which) {
								tv.setText(strs[which]);
							}
						}).show();
			}
		});

	}


	/**
	 * 打印消息
	 */
	public static void print(Object msg) {
		if (msg != null) {
			Log.i(LOGTAG, msg.toString());
		}
	}

	/**
	 * MD5加密算法

	 * @param plainText
	 * @return
	 */
	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

//	/**
//	 * 图标图片名称转ID出错
//	 *
//	 * @param icon_name
//	 * @return
//	 */
//	public static int getIconId(String icon_name) {
//		try {
//			return R.drawable.class.getDeclaredField(icon_name).getInt(null);
//		} catch (Exception e) {
//			StaticObject.print("图标图片名称转ID出错");
//			e.printStackTrace();
//			return R.drawable.icon_tz;
//		}
//	}

//	/**
//	 * 名称转ID出错
//	 *
//	 * @param view_name
//	 * @return
//	 */
//	public static int getViewId(String view_name) {
//		try {
//			return R.id.class.getDeclaredField(view_name).getInt(null);
//		} catch (Exception e) {
//			StaticObject.print("VIEW名称转ID出错");
//			e.printStackTrace();
//			return R.drawable.icon_tz;
//		}
//	}

//
	/**
	 * 将一个file读成一个String类型
	 *


	/**
	 * 将一个file读成一个String类型
	 * 

	 * @param file
	 *            源文件
	 * @return 返回file内容的String类型
	 */
	public static String RFTString(File file) {
		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream fis;
			fis = new FileInputStream(file);
			byte[] b = new byte[1024];
			int rlength = 0;
			while ((rlength = fis.read(b, 0, b.length)) != -1) {
				for (int i = 0; i < rlength; i++) {
					// 字节的占位长度与char的占位长度相同，且String中可存放char
					sb.append((char) b[i]);
				}
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 让EditText变成可编辑状态

	 *

	 * 

	 * @param edit
	 */
	public static void Unlock(EditText edit) {
		edit.setFocusable(true);
		edit.setFocusableInTouchMode(true);
	}

	/**
	 * 让EditText变成不可编辑状态

	 *

	 * 

	 * @param edit
	 */
	public static void lock(EditText edit) {
		edit.setFocusable(false);
		edit.setFocusableInTouchMode(false);
	}
	/**
	 * 让EditText变成不可编辑状态

	 *

	 *

	 * @param tv
	 */
	public static void lockText(TextView tv) {
		tv.setClickable(false);
	}
	public static void unLockText(TextView tv) {
		tv.setClickable(true);
	}
//
//	/**
//	 * 把 VO 中所有属性为 null 的转为 ""
//	 *
//	 * @throws ApplicationException
//	 */
//	public static void nullConverNullString(Object obj) {
//		if (obj != null) {
//
//			Class<? extends Object> classz = obj.getClass();
//			// 获取所有该对象的属性值
//			Field fields[] = classz.getDeclaredFields();
//
//			// 遍历属性值，取得所有属性为 null 值的
//			for (Field field : fields) {
//				try {
//					Type t = field.getGenericType();
//					if (!t.toString().equals("boolean")) {
//						Method m = classz.getMethod("get"
//								+ change(field.getName()));
//						Object name = m.invoke(obj);// 调用该字段的get方法
//						if (name == null) {
//
//							Method mtd = classz.getMethod(
//									"set" + change(field.getName()),
//									new Class[] { String.class });// 取得所需类的方法对象
//							mtd.invoke(obj, new Object[] { "" });// 执行相应赋值方法
//						}
//					}
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	/**
//	 * @param src
//	 *            源字符串
//	 * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
//	 */
//	public static String change(String src) {
//		if (src != null) {
//			StringBuffer sb = new StringBuffer(src);
//			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
//			return sb.toString();
//		} else {
//			return null;
//		}
//	}

	public static String readString(File file)

	{

		//FileInputStream 用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用 FileReader。

		FileInputStream inStream = null;
		try {
			inStream = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024 * 8];

			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {

				bos.write(buffer, 0, length);

				// .write方法 SDK 的解释是 Writes count bytes from the byte array buffer starting at offset index to this stream.

				//  当流关闭以后内容依然存在
				return bos.toString();
			}

			bos.close();

			inStream.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 为什么不一次性把buffer得大小取出来呢？为什么还要写入到bos中呢？ return new(buffer,"UTF-8") 不更好么?

		// return new String(bos.toByteArray(),"UTF-8");

		return null;
	}
	/**
	 * 把 VO 中所有属性为 null 的转为 ""
	 * 
	 * @throws
	 */
	public static void nullConverNullString(Object obj) {
		if (obj != null) {

			Class<? extends Object> classz = obj.getClass();
			// 获取所有该对象的属性值
			Field fields[] = classz.getDeclaredFields();

			// 遍历属性值，取得所有属性为 null 值的
			for (Field field : fields) {
				try {
					Type t = field.getGenericType();
					if (!t.toString().equals("boolean")) {
						Method m = classz.getMethod("get"
								+ change(field.getName()));
						Object name = m.invoke(obj);// 调用该字段的get方法
						if (name == null) {

							Method mtd = classz.getMethod(
									"set" + change(field.getName()),
									new Class[] { String.class });// 取得所需类的方法对象
							mtd.invoke(obj, new Object[] { "" });// 执行相应赋值方法
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param src
	 *            源字符串
	 * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
	 */
	public static String change(String src) {
		if (src != null) {
			StringBuffer sb = new StringBuffer(src);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}

	}
}
