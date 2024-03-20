package com.thecrackertechnology.dragonterminal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.thecrackertechnology.dragonterminal.Settings;
import com.thecrackertechnology.dragonterminal.SettingsMenu;
import com.thecrackertechnology.dragonterminal.xorg.R;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

class SettingsMenuMisc extends SettingsMenu {
    SettingsMenuMisc() {
    }

    static class DownloadConfig extends SettingsMenu.Menu {
        DownloadConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.storage_question);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            long j;
            long j2 = 0;
            try {
                StatFs statFs = new StatFs(mainActivity.getFilesDir().getAbsolutePath());
                j = ((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                try {
                    StatFs statFs2 = new StatFs(Settings.SdcardAppPath.get().bestPath(mainActivity));
                    j2 = ((((long) statFs2.getAvailableBlocks()) * ((long) statFs2.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                j = 0;
            }
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.storage_phone, new Object[]{Long.valueOf(j)}), mainActivity.getResources().getString(R.string.storage_sd, new Object[]{Long.valueOf(j2)}), mainActivity.getResources().getString(R.string.storage_custom)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.storage_question));
            builder.setSingleChoiceItems(charSequenceArr, Globals.DownloadToSdcard ? 1 : 0, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String str;
                    dialogInterface.dismiss();
                    if (i == 2) {
                        DownloadConfig.showCustomDownloadDirConfig(mainActivity);
                        return;
                    }
                    Globals.DownloadToSdcard = i != 0;
                    if (Globals.DownloadToSdcard) {
                        str = Settings.SdcardAppPath.get().bestPath(mainActivity);
                    } else {
                        str = mainActivity.getFilesDir().getAbsolutePath();
                    }
                    Globals.DataDir = str;
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }

        static void showCustomDownloadDirConfig(final MainActivity mainActivity) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.storage_custom));
            final EditText editText = new EditText(mainActivity);
            editText.setFocusableInTouchMode(true);
            editText.setFocusable(true);
            editText.setText(Globals.DataDir);
            builder.setView(editText);
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.DataDir = editText.getText().toString();
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class OptionalDownloadConfig extends SettingsMenu.Menu {
        boolean firstStart;

        OptionalDownloadConfig() {
            this.firstStart = false;
            this.firstStart = true;
        }

        OptionalDownloadConfig(boolean z) {
            this.firstStart = false;
            this.firstStart = z;
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.downloads);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            String[] strArr = Globals.DataDownloadUrl;
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.downloads));
            final int[] iArr = new int[strArr.length];
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i = 0;
            while (true) {
                boolean z = true;
                if (i >= strArr.length) {
                    break;
                }
                String str = new String(strArr[i].split("[|]")[0]);
                if (str.indexOf("!") == 0) {
                    str = str.substring(1);
                } else {
                    z = false;
                }
                if (str.toString().indexOf("!") != 0) {
                    iArr[arrayList.size()] = i;
                    arrayList.add(str);
                    arrayList2.add(Boolean.valueOf(z));
                }
                i++;
            }
            if (Globals.OptionalDataDownload == null || Globals.OptionalDataDownload.length != strArr.length) {
                Globals.OptionalDataDownload = new boolean[strArr.length];
                boolean z2 = true;
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    if (strArr[i2].indexOf("!") == 0) {
                        Globals.OptionalDataDownload[i2] = true;
                        z2 = false;
                    }
                }
                if (z2) {
                    Globals.OptionalDataDownload[0] = true;
                }
            }
            if (arrayList2.size() <= 0) {
                SettingsMenu.goBack(mainActivity);
                return;
            }
            boolean[] zArr = new boolean[arrayList2.size()];
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                zArr[i3] = ((Boolean) arrayList2.get(i3)).booleanValue();
            }
            builder.setMultiChoiceItems((CharSequence[]) arrayList.toArray(new CharSequence[0]), zArr, new DialogInterface.OnMultiChoiceClickListener() {
                public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                    Globals.OptionalDataDownload[iArr[i]] = z;
                }
            });
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            if (this.firstStart) {
                builder.setNegativeButton(mainActivity.getResources().getString(R.string.show_more_options), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        SettingsMenu.menuStack.clear();
                        new SettingsMenu.MainMenu().run(mainActivity);
                    }
                });
            }
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class AudioConfig extends SettingsMenu.Menu {
        AudioConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.audiobuf_question);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.audiobuf_verysmall), mainActivity.getResources().getString(R.string.audiobuf_small), mainActivity.getResources().getString(R.string.audiobuf_medium), mainActivity.getResources().getString(R.string.audiobuf_large)};
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.audiobuf_question);
            builder.setSingleChoiceItems(charSequenceArr, Globals.AudioBufferConfig, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.AudioBufferConfig = i;
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class VideoSettingsConfig extends SettingsMenu.Menu {
        static int debugMenuShowCount;

        VideoSettingsConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.video);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            boolean z = true;
            debugMenuShowCount++;
            CharSequence[] charSequenceArr = {mainActivity.getResources().getString(R.string.mouse_keepaspectratio), mainActivity.getResources().getString(R.string.video_smooth), mainActivity.getResources().getString(R.string.video_immersive), mainActivity.getResources().getString(R.string.video_orientation_autodetect), mainActivity.getResources().getString(R.string.video_orientation_vertical), mainActivity.getResources().getString(R.string.video_bpp_24), mainActivity.getResources().getString(R.string.tv_borders)};
            boolean[] zArr = new boolean[7];
            zArr[0] = Globals.KeepAspectRatio;
            zArr[1] = Globals.VideoLinearFilter;
            zArr[2] = Globals.ImmersiveMode;
            zArr[3] = Globals.AutoDetectOrientation;
            zArr[4] = !Globals.HorizontalOrientation;
            zArr[5] = Globals.VideoDepthBpp == 24;
            zArr[6] = Globals.TvBorders;
            if (Globals.SwVideoMode && !Globals.CompatibilityHacksVideo) {
                CharSequence[] charSequenceArr2 = {mainActivity.getResources().getString(R.string.mouse_keepaspectratio), mainActivity.getResources().getString(R.string.video_smooth), mainActivity.getResources().getString(R.string.video_immersive), mainActivity.getResources().getString(R.string.video_orientation_autodetect), mainActivity.getResources().getString(R.string.video_orientation_vertical), mainActivity.getResources().getString(R.string.video_bpp_24), mainActivity.getResources().getString(R.string.tv_borders), mainActivity.getResources().getString(R.string.video_separatethread)};
                boolean[] zArr2 = new boolean[8];
                zArr2[0] = Globals.KeepAspectRatio;
                zArr2[1] = Globals.VideoLinearFilter;
                zArr2[2] = Globals.ImmersiveMode;
                zArr2[3] = Globals.AutoDetectOrientation;
                zArr2[4] = !Globals.HorizontalOrientation;
                if (Globals.VideoDepthBpp != 24) {
                    z = false;
                }
                zArr2[5] = z;
                zArr2[6] = Globals.TvBorders;
                zArr2[7] = Globals.MultiThreadedVideo;
                CharSequence[] charSequenceArr3 = charSequenceArr2;
                zArr = zArr2;
                charSequenceArr = charSequenceArr3;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.video));
            builder.setMultiChoiceItems(charSequenceArr, zArr, new DialogInterface.OnMultiChoiceClickListener() {
                public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                    if (i == 0) {
                        Globals.KeepAspectRatio = z;
                    }
                    if (i == 1) {
                        Globals.VideoLinearFilter = z;
                    }
                    if (i == 2) {
                        Globals.ImmersiveMode = z;
                    }
                    if (i == 3) {
                        Globals.AutoDetectOrientation = z;
                    }
                    if (i == 4) {
                        Globals.HorizontalOrientation = !z;
                    }
                    if (i == 5) {
                        Globals.VideoDepthBpp = z ? 24 : 16;
                    }
                    if (i == 6) {
                        Globals.TvBorders = z;
                    }
                    if (i == 7) {
                        Globals.MultiThreadedVideo = z;
                    }
                }
            });
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class ShowReadme extends SettingsMenu.Menu {
        /* access modifiers changed from: package-private */
        public boolean enabled() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return "Readme";
        }

        ShowReadme() {
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            String[] split = Globals.ReadmeText.split("\\^");
            String str = new String(Locale.getDefault().getLanguage()) + ":";
            if (mainActivity.isRunningOnOUYA()) {
                str = "tv:";
            }
            String str2 = split[0];
            String str3 = "";
            final String str4 = str3;
            String str5 = str2;
            for (String str6 : split) {
                if (str6.startsWith(str)) {
                    str5 = str6.substring(str.length());
                }
                if (str6.startsWith("button:")) {
                    str3 = str6.substring(7);
                    if (str3.indexOf(":") != -1) {
                        str4 = str3.substring(str3.indexOf(":") + 1);
                        str3 = str3.substring(0, str3.indexOf(":"));
                    }
                }
            }
            String trim = str5.trim();
            if (trim.length() <= 2) {
                SettingsMenu.goBack(mainActivity);
                return;
            }
            TextView textView = new TextView(mainActivity);
            textView.setMaxLines(100);
            textView.setText(trim);
            textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            textView.setPadding(0, 5, 0, 20);
            textView.setTextSize(20.0f);
            textView.setGravity(17);
            textView.setFocusable(false);
            textView.setFocusableInTouchMode(false);
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            ScrollView scrollView = new ScrollView(mainActivity);
            scrollView.setFocusable(false);
            scrollView.setFocusableInTouchMode(false);
            scrollView.addView(textView, new FrameLayout.LayoutParams(-2, -2));
            Button button = new Button(mainActivity);
            final AlertDialog[] alertDialogArr = new AlertDialog[1];
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    alertDialogArr[0].cancel();
                }
            });
            button.setText(R.string.ok);
            LinearLayout linearLayout = new LinearLayout(mainActivity);
            linearLayout.setOrientation(1);
            linearLayout.addView(scrollView);
            linearLayout.addView(button);
            if (str3.length() > 0) {
                Button button2 = new Button(mainActivity);
                button2.setText(str3);
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (str4.length() > 0) {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setData(Uri.parse(str4));
                            mainActivity.startActivity(intent);
                        }
                        alertDialogArr[0].cancel();
                        System.exit(0);
                    }
                });
                linearLayout.addView(button2);
            }
            builder.setView(linearLayout);
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            alertDialogArr[0] = create;
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class GyroscopeCalibration extends SettingsMenu.Menu {
        /* access modifiers changed from: package-private */
        public boolean enabled() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return "";
        }

        GyroscopeCalibration() {
        }

        /* access modifiers changed from: package-private */
        public void run(MainActivity mainActivity) {
            SettingsMenu.goBack(mainActivity);
        }
    }

    static class CommandlineConfig extends SettingsMenu.Menu {
        CommandlineConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.storage_commandline);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.storage_commandline));
            final EditText editText = new EditText(mainActivity);
            editText.setFocusableInTouchMode(true);
            editText.setFocusable(true);
            if (Globals.CommandLine.length() == 0) {
                Globals.CommandLine = "SDL_app";
            }
            if (Globals.CommandLine.indexOf(StringUtils.SPACE) == -1) {
                Globals.CommandLine += StringUtils.SPACE;
            }
            editText.setText(Globals.CommandLine.substring(Globals.CommandLine.indexOf(StringUtils.SPACE)).replace(StringUtils.SPACE, StringUtils.LF).replace("\t", StringUtils.SPACE));
            editText.setInputType(655361);
            editText.setMinLines(2);
            builder.setView(editText);
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Globals.CommandLine = "SDL_app";
                    String[] split = editText.getText().toString().split(StringUtils.LF);
                    int length = split.length;
                    int i2 = 0;
                    boolean z = true;
                    while (i2 < length) {
                        String str = split[i2];
                        Globals.CommandLine += StringUtils.SPACE;
                        if (z) {
                            Globals.CommandLine += str;
                        } else {
                            Globals.CommandLine += str.replace(StringUtils.SPACE, "\t");
                        }
                        i2++;
                        z = false;
                    }
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }

    static class ResetToDefaultsConfig extends SettingsMenu.Menu {
        /* access modifiers changed from: package-private */
        public boolean enabled() {
            return true;
        }

        ResetToDefaultsConfig() {
        }

        /* access modifiers changed from: package-private */
        public String title(MainActivity mainActivity) {
            return mainActivity.getResources().getString(R.string.reset_config);
        }

        /* access modifiers changed from: package-private */
        public void run(final MainActivity mainActivity) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getResources().getString(R.string.reset_config_ask));
            builder.setMessage(mainActivity.getResources().getString(R.string.reset_config_ask));
            builder.setPositiveButton(mainActivity.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Settings.DeleteSdlConfigOnUpgradeAndRestart(mainActivity);
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setNegativeButton(mainActivity.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    SettingsMenu.goBack(mainActivity);
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SettingsMenu.goBack(mainActivity);
                }
            });
            AlertDialog create = builder.create();
            create.setOwnerActivity(mainActivity);
            create.show();
        }
    }
}
