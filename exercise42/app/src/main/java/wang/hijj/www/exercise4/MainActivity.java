package wang.hijj.www.exercise4;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, android.hardware.Camera.PreviewCallback {

    SurfaceHolder surfaceHolder;
    Camera camera;
    ImageView tv;
    Boolean flag = true;
    Boolean flag1 = false;
    Boolean flag2 = false;
    int k=0;
    int index=0;
    int[][] shu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (ImageView) findViewById(R.id.tv);
        SurfaceView view = (SurfaceView) findViewById(R.id.surface_view);
        view.getHolder().addCallback(this);
        view.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try {
            camera = Camera.open();
            camera.setPreviewDisplay(holder);
            Camera.Parameters parameters = camera.getParameters();
            parameters.setPreviewSize(352, 288);
            camera.setParameters(parameters);
            camera.startPreview();
            camera.setPreviewCallback(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) camera.release();
        camera = null;
    }

    @Override
    public void onPreviewFrame(byte[] data, android.hardware.Camera camera) {
        FaceTask mFaceTask = new FaceTask(data);
        mFaceTask.execute((Void) null);

    }

    private class FaceTask extends AsyncTask<Void, Void, Void> {
        private byte[] mData;

        FaceTask(byte[] data) {
            this.mData = data;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Camera.Size size = camera.getParameters().getPreviewSize();
            final int w = size.width;
            final int h = size.height;
            final YuvImage image = new YuvImage(mData, ImageFormat.NV21, w, h, null);
            ByteArrayOutputStream os = new ByteArrayOutputStream(mData.length);
            if (!image.compressToJpeg(new Rect(0, 0, w, h), 100, os)) {
                return null;
            }
            byte[] tmp = os.toByteArray();
            Bitmap bmp = BitmapFactory.decodeByteArray(tmp, 0, tmp.length);
            if (bmp == null) {
                return null;
            }
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            if (width == 0 | height == 0) {
                return null;
            }
            int[] pixels = new int[width * height];
            int cha=1;
            if(flag==true) {
                shu = new int[3][width * height];
                flag=false;
            }

            bmp.getPixels(pixels, 0, width, 0, 0, width, height);
            int alpha = 0xFF << 24;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int grey = pixels[width * i + j];
                    int red = (grey >> 16) & 0xff;
                    int green = (grey >> 8) & 0xff;
                    int blue = grey & 0xff;
                    grey = (int) ((float) red * 0.299 + (float) green * 0.587 + (float) blue * 0.114);
                    shu[index][width*i+j]=grey;

                }
            }
            if (!flag1) {
                flag1 = true;
                index=(index+1)%3;
                return null;
            } else if (!flag2) {
                flag2 = true;
                index=(index+1)%3;
                return null;
            } else {
                int sum1;
                int sum2;
                int left=(index+2)%3;
                int right=(index+1)%3;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        sum1=Math.abs(shu[left][i*width+j]-shu[right][i*width+j]);
                        sum2=Math.abs(shu[left][i*width+j]-shu[index][width*i+j]);
                        if(Math.abs(sum1-sum2)>30)
                            cha=0;
                        else cha=254;
                        pixels[width * i + j] = alpha | (cha << 16) | (cha << 8) | cha;
                    }
                }
                index=(index+1)%3;

            }

            Bitmap bt = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            bt.setPixels(pixels, 0, width, 0, 0, width, height);
            int rotateDegree = 0;
            Matrix matrix = new Matrix();
            matrix.postRotate((float) rotateDegree);
            Bitmap rotaBitmap = Bitmap.createBitmap(bt, 0, 0, bt.getWidth(), bt.getHeight(), matrix, false);
            Message msg = new Message();
            msg.obj = rotaBitmap;
            msg.what = 0;
            handler.sendMessage(msg);

            return null;
        }


    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            tv.setImageBitmap((Bitmap) msg.obj);
        }
    };

}
