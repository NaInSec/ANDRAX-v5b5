package org.acra.collector;

import android.content.Context;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import android.util.SparseArray;
import com.thecrackertechnology.dragonterminal.component.colorscheme.NeoColorScheme;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaCodecListCollector extends BaseReportFieldCollector {
    private static final String[] AAC_TYPES = {"aac", "AAC"};
    private static final String[] AVC_TYPES = {"avc", "h264", "AVC", "H264"};
    private static final String COLOR_FORMAT_PREFIX = "COLOR_";
    private static final String[] H263_TYPES = {"h263", "H263"};
    private static final String[] MPEG4_TYPES = {"mp4", "mpeg4", "MP4", "MPEG4"};
    private final SparseArray<String> mAACProfileValues = new SparseArray<>();
    private final SparseArray<String> mAVCLevelValues = new SparseArray<>();
    private final SparseArray<String> mAVCProfileValues = new SparseArray<>();
    private final SparseArray<String> mColorFormatValues = new SparseArray<>();
    private final SparseArray<String> mH263LevelValues = new SparseArray<>();
    private final SparseArray<String> mH263ProfileValues = new SparseArray<>();
    private final SparseArray<String> mMPEG4LevelValues = new SparseArray<>();
    private final SparseArray<String> mMPEG4ProfileValues = new SparseArray<>();

    private enum CodecType {
        AVC,
        H263,
        MPEG4,
        AAC
    }

    public MediaCodecListCollector() {
        super(ReportField.MEDIA_CODEC_LIST, new ReportField[0]);
    }

    public Collector.Order getOrder() {
        return Collector.Order.LATE;
    }

    /* access modifiers changed from: package-private */
    public void collect(ReportField reportField, Context context, CoreConfiguration coreConfiguration, ReportBuilder reportBuilder, CrashReportData crashReportData) throws JSONException {
        crashReportData.put(ReportField.MEDIA_CODEC_LIST, collectMediaCodecList());
    }

    /* access modifiers changed from: package-private */
    public boolean shouldCollect(Context context, CoreConfiguration coreConfiguration, ReportField reportField, ReportBuilder reportBuilder) {
        return super.shouldCollect(context, coreConfiguration, reportField, reportBuilder) && Build.VERSION.SDK_INT >= 16;
    }

    private void prepare() {
        try {
            for (Field field : Class.forName("android.media.MediaCodecInfo$CodecCapabilities").getFields()) {
                if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.getName().startsWith(COLOR_FORMAT_PREFIX)) {
                    this.mColorFormatValues.put(field.getInt((Object) null), field.getName());
                }
            }
            for (Field field2 : Class.forName("android.media.MediaCodecInfo$CodecProfileLevel").getFields()) {
                if (Modifier.isStatic(field2.getModifiers()) && Modifier.isFinal(field2.getModifiers())) {
                    if (field2.getName().startsWith("AVCLevel")) {
                        this.mAVCLevelValues.put(field2.getInt((Object) null), field2.getName());
                    } else if (field2.getName().startsWith("AVCProfile")) {
                        this.mAVCProfileValues.put(field2.getInt((Object) null), field2.getName());
                    } else if (field2.getName().startsWith("H263Level")) {
                        this.mH263LevelValues.put(field2.getInt((Object) null), field2.getName());
                    } else if (field2.getName().startsWith("H263Profile")) {
                        this.mH263ProfileValues.put(field2.getInt((Object) null), field2.getName());
                    } else if (field2.getName().startsWith("MPEG4Level")) {
                        this.mMPEG4LevelValues.put(field2.getInt((Object) null), field2.getName());
                    } else if (field2.getName().startsWith("MPEG4Profile")) {
                        this.mMPEG4ProfileValues.put(field2.getInt((Object) null), field2.getName());
                    } else if (field2.getName().startsWith("AAC")) {
                        this.mAACProfileValues.put(field2.getInt((Object) null), field2.getName());
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | SecurityException unused) {
        }
    }

    private JSONObject collectMediaCodecList() throws JSONException {
        MediaCodecInfo[] mediaCodecInfoArr;
        prepare();
        if (Build.VERSION.SDK_INT < 21) {
            int codecCount = MediaCodecList.getCodecCount();
            mediaCodecInfoArr = new MediaCodecInfo[codecCount];
            for (int i = 0; i < codecCount; i++) {
                mediaCodecInfoArr[i] = MediaCodecList.getCodecInfoAt(i);
            }
        } else {
            mediaCodecInfoArr = new MediaCodecList(1).getCodecInfos();
        }
        JSONObject jSONObject = new JSONObject();
        for (int i2 = 0; i2 < mediaCodecInfoArr.length; i2++) {
            MediaCodecInfo mediaCodecInfo = mediaCodecInfoArr[i2];
            JSONObject jSONObject2 = new JSONObject();
            String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
            jSONObject2.put(NeoColorScheme.COLOR_META_NAME, mediaCodecInfo.getName()).put("isEncoder", mediaCodecInfo.isEncoder());
            JSONObject jSONObject3 = new JSONObject();
            for (String str : supportedTypes) {
                jSONObject3.put(str, collectCapabilitiesForType(mediaCodecInfo, str));
            }
            jSONObject2.put("supportedTypes", jSONObject3);
            jSONObject.put(String.valueOf(i2), jSONObject2);
        }
        return jSONObject;
    }

    private JSONObject collectCapabilitiesForType(MediaCodecInfo mediaCodecInfo, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
        int[] iArr = capabilitiesForType.colorFormats;
        int i = 0;
        if (iArr.length > 0) {
            JSONArray jSONArray = new JSONArray();
            for (int i2 : iArr) {
                jSONArray.put(this.mColorFormatValues.get(i2));
            }
            jSONObject.put("colorFormats", jSONArray);
        }
        CodecType identifyCodecType = identifyCodecType(mediaCodecInfo);
        MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = capabilitiesForType.profileLevels;
        if (codecProfileLevelArr.length > 0) {
            JSONArray jSONArray2 = new JSONArray();
            int length = codecProfileLevelArr.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                MediaCodecInfo.CodecProfileLevel codecProfileLevel = codecProfileLevelArr[i];
                int i3 = codecProfileLevel.profile;
                int i4 = codecProfileLevel.level;
                if (identifyCodecType == null) {
                    jSONArray2.put(i3 + 45 + i4);
                    break;
                }
                int i5 = AnonymousClass1.$SwitchMap$org$acra$collector$MediaCodecListCollector$CodecType[identifyCodecType.ordinal()];
                if (i5 == 1) {
                    jSONArray2.put(i3 + this.mAVCProfileValues.get(i3) + '-' + this.mAVCLevelValues.get(i4));
                } else if (i5 == 2) {
                    jSONArray2.put(this.mH263ProfileValues.get(i3) + '-' + this.mH263LevelValues.get(i4));
                } else if (i5 == 3) {
                    jSONArray2.put(this.mMPEG4ProfileValues.get(i3) + '-' + this.mMPEG4LevelValues.get(i4));
                } else if (i5 == 4) {
                    jSONArray2.put(this.mAACProfileValues.get(i3));
                }
                i++;
            }
            jSONObject.put("profileLevels", jSONArray2);
        }
        return jSONObject;
    }

    /* renamed from: org.acra.collector.MediaCodecListCollector$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$acra$collector$MediaCodecListCollector$CodecType = new int[CodecType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                org.acra.collector.MediaCodecListCollector$CodecType[] r0 = org.acra.collector.MediaCodecListCollector.CodecType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$acra$collector$MediaCodecListCollector$CodecType = r0
                int[] r0 = $SwitchMap$org$acra$collector$MediaCodecListCollector$CodecType     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.acra.collector.MediaCodecListCollector$CodecType r1 = org.acra.collector.MediaCodecListCollector.CodecType.AVC     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$acra$collector$MediaCodecListCollector$CodecType     // Catch:{ NoSuchFieldError -> 0x001f }
                org.acra.collector.MediaCodecListCollector$CodecType r1 = org.acra.collector.MediaCodecListCollector.CodecType.H263     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$org$acra$collector$MediaCodecListCollector$CodecType     // Catch:{ NoSuchFieldError -> 0x002a }
                org.acra.collector.MediaCodecListCollector$CodecType r1 = org.acra.collector.MediaCodecListCollector.CodecType.MPEG4     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$org$acra$collector$MediaCodecListCollector$CodecType     // Catch:{ NoSuchFieldError -> 0x0035 }
                org.acra.collector.MediaCodecListCollector$CodecType r1 = org.acra.collector.MediaCodecListCollector.CodecType.AAC     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.acra.collector.MediaCodecListCollector.AnonymousClass1.<clinit>():void");
        }
    }

    private CodecType identifyCodecType(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        for (String contains : AVC_TYPES) {
            if (name.contains(contains)) {
                return CodecType.AVC;
            }
        }
        for (String contains2 : H263_TYPES) {
            if (name.contains(contains2)) {
                return CodecType.H263;
            }
        }
        for (String contains3 : MPEG4_TYPES) {
            if (name.contains(contains3)) {
                return CodecType.MPEG4;
            }
        }
        for (String contains4 : AAC_TYPES) {
            if (name.contains(contains4)) {
                return CodecType.AAC;
            }
        }
        return null;
    }
}
