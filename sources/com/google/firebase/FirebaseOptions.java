package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class FirebaseOptions {
    /* access modifiers changed from: private */
    public final String zza;
    /* access modifiers changed from: private */
    public final String zzb;
    /* access modifiers changed from: private */
    public final String zzc;
    /* access modifiers changed from: private */
    public final String zzd;
    /* access modifiers changed from: private */
    public final String zze;
    /* access modifiers changed from: private */
    public final String zzf;
    /* access modifiers changed from: private */
    public final String zzg;

    /* synthetic */ FirebaseOptions(String str, String str2, String str3, String str4, String str5, String str6, String str7, byte b) {
        this(str, str2, str3, str4, str5, str6, str7);
    }

    /* compiled from: com.google.firebase:firebase-common@@16.0.2 */
    public static final class Builder {
        private String zza;
        private String zzb;
        private String zzc;
        private String zzd;
        private String zze;
        private String zzf;
        private String zzg;

        public Builder() {
        }

        public Builder(FirebaseOptions firebaseOptions) {
            this.zzb = firebaseOptions.zzb;
            this.zza = firebaseOptions.zza;
            this.zzc = firebaseOptions.zzc;
            this.zzd = firebaseOptions.zzd;
            this.zze = firebaseOptions.zze;
            this.zzf = firebaseOptions.zzf;
            this.zzg = firebaseOptions.zzg;
        }

        public final Builder setApiKey(String str) {
            this.zza = Preconditions.checkNotEmpty(str, "ApiKey must be set.");
            return this;
        }

        public final Builder setApplicationId(String str) {
            this.zzb = Preconditions.checkNotEmpty(str, "ApplicationId must be set.");
            return this;
        }

        public final Builder setDatabaseUrl(String str) {
            this.zzc = str;
            return this;
        }

        public final Builder setGaTrackingId(String str) {
            this.zzd = str;
            return this;
        }

        public final Builder setGcmSenderId(String str) {
            this.zze = str;
            return this;
        }

        public final Builder setStorageBucket(String str) {
            this.zzf = str;
            return this;
        }

        public final Builder setProjectId(String str) {
            this.zzg = str;
            return this;
        }

        public final FirebaseOptions build() {
            return new FirebaseOptions(this.zzb, this.zza, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, (byte) 0);
        }
    }

    private FirebaseOptions(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(str), "ApplicationId must be set.");
        this.zzb = str;
        this.zza = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = str6;
        this.zzg = str7;
    }

    public static FirebaseOptions fromResource(Context context) {
        StringResourceValueReader stringResourceValueReader = new StringResourceValueReader(context);
        String string = stringResourceValueReader.getString("google_app_id");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new FirebaseOptions(string, stringResourceValueReader.getString("google_api_key"), stringResourceValueReader.getString("firebase_database_url"), stringResourceValueReader.getString("ga_trackingId"), stringResourceValueReader.getString("gcm_defaultSenderId"), stringResourceValueReader.getString("google_storage_bucket"), stringResourceValueReader.getString("project_id"));
    }

    public final String getApiKey() {
        return this.zza;
    }

    public final String getApplicationId() {
        return this.zzb;
    }

    public final String getDatabaseUrl() {
        return this.zzc;
    }

    public final String getGaTrackingId() {
        return this.zzd;
    }

    public final String getGcmSenderId() {
        return this.zze;
    }

    public final String getStorageBucket() {
        return this.zzf;
    }

    public final String getProjectId() {
        return this.zzg;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
        if (!Objects.equal(this.zzb, firebaseOptions.zzb) || !Objects.equal(this.zza, firebaseOptions.zza) || !Objects.equal(this.zzc, firebaseOptions.zzc) || !Objects.equal(this.zzd, firebaseOptions.zzd) || !Objects.equal(this.zze, firebaseOptions.zze) || !Objects.equal(this.zzf, firebaseOptions.zzf) || !Objects.equal(this.zzg, firebaseOptions.zzg)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzb, this.zza, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.zzb).add("apiKey", this.zza).add("databaseUrl", this.zzc).add("gcmSenderId", this.zze).add("storageBucket", this.zzf).add("projectId", this.zzg).toString();
    }
}
