package com.amos.study.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.amos.study.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: amos
 * @date: 2021/3/17 13:54
 * @description:
 */
public class FileStudyActivity extends BaseActivity {
    private static final String TAG = FileStudyActivity.class.getSimpleName();

    public static void launch(Context context) {
        context.startActivity(new Intent(context, FileStudyActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getFilesDir();
        getDir();
        getFileStreamPath();
        getExternalFilesDir();
        getExternalFilesDirs();
        getNoBackupFilesDir();
        getCacheDir();
        getCodeCacheDir();
        getDataDir();
        getObbDir();
        getObbDirs();
        getExternalMediaDirs();
        getExternalCacheDir();*/

        //TestInternal.INSTANCE.testMethod$commom_component_app();

        File file = getFilesDir();
        String absolutePath = file.getAbsolutePath();


        String path = file.getPath();
        Log.e(TAG, "getFilesDir  ==   absolutePath = " + absolutePath + "  path = " + path);
        String filename = "myfile";
        String fileContents = "Hello world!";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(fileContents.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //this.deleteDatabase()

        //Proxy.newProxyInstance()

    }

   /* public static void main(String[] args){

    }*/
}
