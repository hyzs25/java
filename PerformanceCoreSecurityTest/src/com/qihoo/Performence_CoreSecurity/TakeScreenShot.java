package com.qihoo.Performence_CoreSecurity;

import java.io.File;
import java.io.FileOutputStream;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;

public class TakeScreenShot {

public static String baseDIR = Environment.getExternalStorageDirectory()
.getAbsolutePath();
public static String subDIR = "/heyang";
public static String SCREEN_SHOTS_LOCATION = baseDIR + subDIR;

/*
* public static void takeScreenShot(View view) throws Exception {
* takeScreenShot(view, "default"); }
*/
public static void takeScreenShot(View view, String name) throws Exception {

System.out.println("ϵͳ·����" + baseDIR);// ��ȡϵͳ��SD��·��

view.setDrawingCacheEnabled(true);
view.buildDrawingCache();
Bitmap b = view.getDrawingCache();
FileOutputStream fos = null;
try {
File sddir = new File(SCREEN_SHOTS_LOCATION);
if (!sddir.exists()) {
sddir.mkdirs();
}

fos = new FileOutputStream(name + ".jpg");
System.out.println("����ͼƬ��·����" + SCREEN_SHOTS_LOCATION + name
+ ".jpg");
if (fos != null) {
b.compress(Bitmap.CompressFormat.JPEG, 90, fos);
fos.close();
}
} catch (Exception e) {
}
}


}