package de.mrapp.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class BitmapUtil {
    private static final int COMPLETE_ARC_ANGLE = 360;

    private static int getSampleSize(Pair<Integer, Integer> pair, int i, int i2) {
        Condition.ensureNotNull(pair, "The image dimensions may not be null");
        int i3 = 1;
        Condition.ensureAtLeast(i, 1, "The maximum width must be at least 1");
        Condition.ensureAtLeast(i2, 1, "The maximum height must be at least 1");
        int intValue = ((Integer) pair.first).intValue();
        int intValue2 = ((Integer) pair.second).intValue();
        if (intValue > i || intValue2 > i2) {
            int i4 = intValue / 2;
            int i5 = intValue2 / 2;
            while (i4 / i3 > i && i5 / i3 > i2) {
                i3 *= 2;
            }
        }
        return i3;
    }

    private BitmapUtil() {
    }

    public static Bitmap clipCircle(Bitmap bitmap) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        return clipCircle(bitmap, bitmap.getWidth() >= bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth());
    }

    public static Bitmap clipCircle(Bitmap bitmap, int i) {
        Bitmap clipSquare = clipSquare(bitmap, i);
        int width = clipSquare.getWidth();
        float f = ((float) width) / 2.0f;
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawCircle(f, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(clipSquare, new Rect(0, 0, width, width), new Rect(0, 0, width, width), paint);
        return createBitmap;
    }

    public static Bitmap clipCircle(Bitmap bitmap, int i, int i2) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        return clipCircle(bitmap, bitmap.getWidth() >= bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth(), i, i2);
    }

    public static Bitmap clipCircle(Bitmap bitmap, int i, int i2, int i3) {
        Condition.ensureAtLeast(i2, 0, "The border width must be at least 0");
        Bitmap clipCircle = clipCircle(bitmap, i);
        Bitmap createBitmap = Bitmap.createBitmap(clipCircle.getWidth(), clipCircle.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) i2;
        float f2 = f / 2.0f;
        canvas.drawBitmap(clipCircle, new Rect(0, 0, clipCircle.getWidth(), clipCircle.getHeight()), new RectF(f2, f2, ((float) createBitmap.getWidth()) - f2, ((float) createBitmap.getHeight()) - f2), (Paint) null);
        if (i2 > 0 && Color.alpha(i3) != 0) {
            Paint paint = new Paint();
            paint.setFilterBitmap(false);
            paint.setAntiAlias(true);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(f);
            paint.setColor(i3);
            canvas.drawArc(new RectF(f2, f2, ((float) createBitmap.getWidth()) - f2, ((float) createBitmap.getWidth()) - f2), 0.0f, 360.0f, false, paint);
        }
        return createBitmap;
    }

    public static Bitmap clipSquare(Bitmap bitmap) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        return clipSquare(bitmap, bitmap.getWidth() >= bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth());
    }

    public static Bitmap clipSquare(Bitmap bitmap, int i) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        Condition.ensureAtLeast(i, 1, "The size must be at least 1");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            bitmap = Bitmap.createBitmap(bitmap, (width / 2) - (height / 2), 0, height, height);
        } else if (bitmap.getWidth() < bitmap.getHeight()) {
            bitmap = Bitmap.createBitmap(bitmap, 0, (bitmap.getHeight() / 2) - (width / 2), width, width);
        }
        return bitmap.getWidth() != i ? resize(bitmap, i, i) : bitmap;
    }

    public static Bitmap clipSquare(Bitmap bitmap, int i, int i2) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        return clipSquare(bitmap, bitmap.getWidth() >= bitmap.getHeight() ? bitmap.getHeight() : bitmap.getWidth(), i, i2);
    }

    public static Bitmap clipSquare(Bitmap bitmap, int i, int i2, int i3) {
        Condition.ensureAtLeast(i2, 0, "The border width must be at least 0");
        Bitmap clipSquare = clipSquare(bitmap, i);
        Bitmap createBitmap = Bitmap.createBitmap(clipSquare.getWidth(), clipSquare.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) i2;
        float f2 = f / 2.0f;
        canvas.drawBitmap(clipSquare, new Rect(0, 0, clipSquare.getWidth(), clipSquare.getHeight()), new RectF(f2, f2, ((float) createBitmap.getWidth()) - f2, ((float) createBitmap.getHeight()) - f2), (Paint) null);
        if (i2 > 0 && Color.alpha(i3) != 0) {
            Paint paint = new Paint();
            paint.setFilterBitmap(false);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(f);
            paint.setColor(i3);
            canvas.drawRect(new RectF(f2, f2, ((float) createBitmap.getWidth()) - f2, ((float) createBitmap.getWidth()) - f2), paint);
        }
        return createBitmap;
    }

    public static Bitmap resize(Bitmap bitmap, int i, int i2) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        Condition.ensureAtLeast(i, 1, "The width must be at least 1");
        Condition.ensureAtLeast(i2, 1, "The height must be at least 1");
        return Bitmap.createScaledBitmap(bitmap, i, i2, false);
    }

    public static Pair<Bitmap, Bitmap> splitHorizontally(Bitmap bitmap) {
        return splitHorizontally(bitmap, bitmap.getHeight() / 2);
    }

    public static Pair<Bitmap, Bitmap> splitHorizontally(Bitmap bitmap, int i) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        Condition.ensureGreater(i, 0, "The split point must be greater than 0");
        int height = bitmap.getHeight();
        Condition.ensureSmaller(i, height, "The split point must be smaller than " + bitmap.getHeight());
        return new Pair<>(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), i), Bitmap.createBitmap(bitmap, 0, i, bitmap.getWidth(), bitmap.getHeight() - i));
    }

    public static Pair<Bitmap, Bitmap> splitVertically(Bitmap bitmap) {
        return splitVertically(bitmap, bitmap.getWidth() / 2);
    }

    public static Pair<Bitmap, Bitmap> splitVertically(Bitmap bitmap, int i) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        Condition.ensureGreater(i, 0, "The split point must be greater than 0");
        int width = bitmap.getWidth();
        Condition.ensureSmaller(i, width, "The split point must be smaller than " + bitmap.getWidth());
        return new Pair<>(Bitmap.createBitmap(bitmap, 0, 0, i, bitmap.getHeight()), Bitmap.createBitmap(bitmap, i, 0, bitmap.getWidth() - i, bitmap.getHeight()));
    }

    public static Bitmap tile(Bitmap bitmap, int i, int i2) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        Condition.ensureAtLeast(i, 1, "The width must be at least 1");
        Condition.ensureAtLeast(i2, 1, "The height must be at least 1");
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        for (int i3 = 0; i3 < i; i3 += width) {
            for (int i4 = 0; i4 < i; i4 += height) {
                int i5 = i - i3;
                if (i5 >= width) {
                    i5 = width;
                }
                int i6 = i2 - i4;
                if (i6 >= height) {
                    i6 = height;
                }
                canvas.drawBitmap(bitmap, new Rect(0, 0, i5, i6), new Rect(i3, i4, i5 + i3, i6 + i4), (Paint) null);
            }
        }
        return createBitmap;
    }

    public static Bitmap tint(Bitmap bitmap, int i) {
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(i);
        canvas.drawRect(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), paint);
        return bitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        Condition.ensureNotNull(drawable, "The drawable may not be null");
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap textToBitmap(Context context, int i, int i2, int i3, CharSequence charSequence, float f, int i4) {
        return textToBitmap(context, i, i2, i3, charSequence, f, i4, (Typeface) null);
    }

    public static Bitmap textToBitmap(Context context, int i, int i2, int i3, CharSequence charSequence, float f, int i4, Typeface typeface) {
        Condition.ensureNotNull(context, "The context may not be null");
        Condition.ensureNotNull(charSequence, "The text may not be null");
        Condition.ensureNotEmpty(charSequence, "The text may not be empty");
        Condition.ensureAtLeast(f, 1.0f, "The text size must be at least 1");
        Bitmap colorToBitmap = colorToBitmap(i, i2, i3);
        Canvas canvas = new Canvas(colorToBitmap);
        Paint paint = new Paint(1);
        paint.setColor(i4);
        paint.setTextSize(f * DisplayUtil.getDensity(context));
        paint.setTextAlign(Paint.Align.CENTER);
        if (typeface != null) {
            paint.setTypeface(typeface);
        }
        canvas.drawText(charSequence.toString(), (float) (colorToBitmap.getWidth() / 2), (float) ((int) (((float) (colorToBitmap.getHeight() / 2)) - ((paint.descent() + paint.ascent()) / 2.0f))), paint);
        return colorToBitmap;
    }

    public static Bitmap colorToBitmap(int i, int i2, int i3) {
        Condition.ensureAtLeast(i, 1, "The width must be at least 1");
        Condition.ensureAtLeast(i2, 1, "The height must be at least 1");
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(i3);
        canvas.drawRect(0.0f, 0.0f, (float) i, (float) i2, paint);
        return createBitmap;
    }

    public static Pair<Integer, Integer> getImageDimensions(File file) throws IOException {
        Condition.ensureNotNull(file, "The file may not be null");
        Condition.ensureFileIsNoDirectory(file, "The file must exist and must not be a directory");
        String path = file.getPath();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i != -1 && i2 != -1) {
            return Pair.create(Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IOException("Failed to decode image \"" + path + "\"");
    }

    public static Pair<Integer, Integer> getImageDimensions(Context context, int i) throws IOException {
        Condition.ensureNotNull(context, "The context may not be null");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), i, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (i2 != -1 && i3 != -1) {
            return Pair.create(Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IOException("Failed to decode image resource with id " + i);
    }

    public static Bitmap loadThumbnail(File file, int i, int i2) throws IOException {
        int sampleSize = getSampleSize(getImageDimensions(file), i, i2);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        String absolutePath = file.getAbsolutePath();
        Bitmap decodeFile = BitmapFactory.decodeFile(absolutePath, options);
        if (decodeFile != null) {
            return decodeFile;
        }
        throw new IOException("Failed to decode image \"" + absolutePath + "\"");
    }

    public static Bitmap loadThumbnail(Context context, int i, int i2, int i3) throws IOException {
        int sampleSize = getSampleSize(getImageDimensions(context, i), i2, i3);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i, options);
        if (decodeResource != null) {
            return decodeResource;
        }
        throw new IOException("Failed to decode image resource with id " + i);
    }

    public static void compressToFile(Bitmap bitmap, File file, Bitmap.CompressFormat compressFormat, int i) throws IOException {
        FileOutputStream fileOutputStream;
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        Condition.ensureNotNull(file, "The file may not be null");
        Condition.ensureNotNull(compressFormat, "The format may not be null");
        Condition.ensureAtLeast(i, 0, "The quality must be at least 0");
        Condition.ensureAtMaximum(i, 100, "The quality must be at maximum 100");
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                if (bitmap.compress(compressFormat, i, fileOutputStream)) {
                    StreamUtil.close(fileOutputStream);
                    return;
                }
                throw new IOException("Failed to compress bitmap to file \"" + file + "\" using format " + compressFormat + " and quality " + i);
            } catch (Throwable th) {
                th = th;
                StreamUtil.close(fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            StreamUtil.close(fileOutputStream);
            throw th;
        }
    }

    public static byte[] compressToByteArray(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        Condition.ensureNotNull(bitmap, "The bitmap may not be null");
        Condition.ensureNotNull(compressFormat, "The format may not be null");
        Condition.ensureAtLeast(i, 0, "The quality must be at least 0");
        Condition.ensureAtMaximum(i, 100, "The quality must be at maximum 100");
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                if (bitmap.compress(compressFormat, i, byteArrayOutputStream)) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    StreamUtil.close(byteArrayOutputStream);
                    return byteArray;
                }
                throw new IOException("Failed to compress bitmap to byte array using format " + compressFormat + " and quality " + i);
            } catch (Throwable th) {
                th = th;
                StreamUtil.close(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
            StreamUtil.close(byteArrayOutputStream);
            throw th;
        }
    }
}
