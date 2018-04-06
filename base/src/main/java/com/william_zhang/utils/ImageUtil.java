package com.william_zhang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.DrawableRes;
import android.support.v4.content.FileProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by william_zhang on 2018/4/6.
 */

public class ImageUtil {
    /**
     * @param resources 根据资源压缩
     * @param resId
     * @param width
     * @param heigth
     * @return
     */
    public static Bitmap scaleCompress(Resources resources, int resId, int width, int heigth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //如果该 值设为true那么将不返回实际的bitmap
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        //Options中有个属性inSampleSize。我们可以充分利用它，实现缩放。
        // 如果被设置为一个值> 1,要求解码器解码出原始图像的一个子样本,返回一个较小的bitmap,以节省存储空间。
        //例如,inSampleSize = = 2，则取出的缩略图的宽和高都是原始图片的1/2，图片大小就为原始大小的1/4。
        options.inSampleSize = getInSampleSize(options, width, heigth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    /**
     * @param filePath 根据文件路径压缩
     * @param width
     * @param height
     * @return
     */
    public static Bitmap scaleCompress(String filePath, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //如果该 值设为true那么将不返回实际的bitmap
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath);
        //Options中有个属性inSampleSize。我们可以充分利用它，实现缩放。
        // 如果被设置为一个值> 1,要求解码器解码出原始图像的一个子样本,返回一个较小的bitmap,以节省存储空间。
        //例如,inSampleSize = = 2，则取出的缩略图的宽和高都是原始图片的1/2，图片大小就为原始大小的1/4。
        options.inSampleSize = getInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * @param bitmap 根据bitmap进行压缩
     * @param width
     * @param height
     * @return
     */
    public static Bitmap scaleCompress(Bitmap bitmap, int width, int height) {
        //转化成字节
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.reset();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        options.inSampleSize = getInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }


    /**
     * @param options
     * @param width   压缩宽
     * @param height  压缩高
     * @return inSampleSize
     */
    public static int getInSampleSize(BitmapFactory.Options options, int width, int height) {
        //原图的长宽以及inSampleSize
        int mWidth = options.outWidth;
        int mHeight = options.outHeight;
        //小于1都是原图的大小
        int inSampleSize = 1;
        if (mWidth > width || mHeight > height) {
            //正数：Math.round(11.5) = 12
            //负数：Math.round(-11.5) = -11
            int widthScale = Math.round((float) mWidth / (float) width);
            int heightScale = Math.round((float) mHeight / (float) height);
            inSampleSize = widthScale > heightScale ? heightScale : widthScale;
        }
        return inSampleSize;
    }

    /**
     * 质量压缩，改变的是存储空间，不改变大小
     *
     * @param bitmap
     * @param max    可以接受的最大的大小
     * @return
     */
    public static Bitmap qualityCompress(Bitmap bitmap, int max) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        //不满足大小要求继续压缩
        while ((byteArrayOutputStream.toByteArray().length / 1024) > max) {
            byteArrayOutputStream.reset();
            if (quality > 10) {
                quality -= 10;
            } else {
                quality -= 1;
            }
            if (quality == 0) {
                break;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return BitmapFactory.decodeStream(byteArrayInputStream, null, null);
    }

    /**
     * 将图片保存到文件中去
     *
     * @param file
     * @param bitmap
     * @return
     */
        public static boolean saveBitmaoToFile(String file, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            //压缩质量为85%
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 将文件转换成bitmap
     *
     * @param filePath 文件名
     * @return Bitmap
     */
    public static Bitmap fileToBitmap(String filePath) {
        return BitmapFactory.decodeFile(filePath);
    }

    /**
     * 资源文件转为bitmap
     *
     * @param context 上下文
     * @param id      资源id
     * @return bitmap
     */
    public static Bitmap resourcesToBitmap(Context context, @DrawableRes int id) {
        Resources resources = context.getResources();
        return BitmapFactory.decodeResource(resources, id);
    }

    /**
     * 根据uri转为bitmap
     *
     * @param context context
     * @param uri     uri
     * @return btimap
     */
    public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        if (context == null || uri == null) return null;

        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 图片直接处理实现模糊
     *
     * @param sentBitmap
     * @param radius
     * @param canReuseInBitmap
     * @return
     */
    public static Bitmap blurBitmap(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {

        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    // 图片缩放比例(即模糊度)
    private static final float BITMAP_SCALE = 0.4f;

    /**
     * 使用android api实现模糊
     *
     * @param context    上下文
     * @param image      待处理文件
     * @param blurRadius 最大25f
     * @return
     */
    public static Bitmap blurBitmap(Context context, Bitmap image, float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);
        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }


    public static final int REQ_PHOTO_CAMERA = 110; // 拍照
    public static final int REQ_PHOTO_ALBUM = 120; // 相册
    public static final int REQ_PHOTO_CROP = 130; // 裁剪

    /**
     * 调用手机摄像头拍照 在onActivityResult中获得图片uri
     *
     * @param activity
     * @param uri
     */
    public static void getImageFromCamera(Activity activity, Uri uri) {
        if (Build.VERSION.SDK_INT < 24) {
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(openCameraIntent, REQ_PHOTO_CAMERA);
        } else {//7.0系统
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//将拍取的照片保存到指定URI
            activity.startActivityForResult(intent, REQ_PHOTO_CAMERA);
        }
    }

    /**
     * 从相册中获得图片  在onActivityResult中获得图片
     *
     * @param activity
     */
    public static void getImageFromAlbums(Activity activity) {
        Intent openAlbumIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(openAlbumIntent, REQ_PHOTO_ALBUM);
    }

    /**
     * 拍照的照片进行裁剪
     *
     * @param context
     * @param providerAuthority
     * @param file
     */
    public static void cameraImageCrop(Context context, String providerAuthority, File file) {
        if (Build.VERSION.SDK_INT < 24) {
            startImageCrop(context, file);
        } else {
            //7.0系统，通过FileProvider创建一个content类型的Uri
            Uri imageUri = FileProvider.getUriForFile(context, providerAuthority, file);
            startImageCropAPI24(context, file);
        }
    }

    /**
     * 相册的照片进行裁剪
     *
     * @param context
     * @param file
     */
    public static void albumImageCrop(Context context, File file) {
        if (Build.VERSION.SDK_INT < 24) {
            startImageCrop(context, file);
        } else {
            //7.0系统，通过FileProvider创建一个content类型的Uri
            startImageCropAPI24(context, file);
        }
    }

    /**
     * 低于sdk24
     *
     * @param context
     * @param file
     */
    private static void startImageCrop(Context context, File file) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(file), "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("ic_return-data", true);
        intent.putExtra("noFaceDetection", true);
        ((Activity) context).startActivityForResult(intent, REQ_PHOTO_CROP);
    }

    /**
     * sdk 24之后
     *
     * @param context
     * @param file
     */
    private static void startImageCropAPI24(Context context, File file) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(Uri.fromFile(file), "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("ic_return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        ((Activity) context).startActivityForResult(intent, REQ_PHOTO_CROP);
    }
}
