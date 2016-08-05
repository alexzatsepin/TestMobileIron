package com.example.alexanderzatsepin.testmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
	private static final String debugTag = MainActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String dataDirectory = getApplicationContext().getExternalCacheDir().getPath();
		String mountFile = dataDirectory + "/mounts.txt";
		fileCopy("/proc/mounts", mountFile);
	}

	/*
  * This method copies a file to a new location.
  *
  * Parameters:
  *     src: Source file name.
  *     dst: Destination file name.
  */
	private static void fileCopy(String src, String dst)
	{
		FileInputStream in  = null;
		FileOutputStream out = null;

		File srcFile = new File(src);
		File dstFile = new File(dst);

		try
		{
			in  = new FileInputStream(srcFile);
			out = new FileOutputStream(dstFile);

			int count;
			while((count = in.read()) != -1) {
				out.write(count);
			}
		}
		catch (IOException e)
		{
			Log.d(debugTag, "fileCopy: Error copying '" + src +
					"' to '" + dst + "':" + e.getMessage() );
		}
		finally
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException e)
			{
				Log.d(debugTag, "fileCopy: Error closing '" + src +
						"':" + e.getMessage() );
			}
			try {
				if (out != null)
				{
					out.close();
				}
			}
			catch (IOException e)
			{
				Log.d(debugTag, "fileCopy: Error closing '" + dst +
						"':" + e.getMessage() );
			}
		}
	}
}
