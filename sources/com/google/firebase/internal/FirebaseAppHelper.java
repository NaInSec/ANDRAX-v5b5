package com.google.firebase.internal;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.GetTokenResult;

@Deprecated
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class FirebaseAppHelper {
    public static Task<GetTokenResult> getToken(FirebaseApp firebaseApp, boolean z) {
        return firebaseApp.getToken(z);
    }

    public static void addIdTokenListener(FirebaseApp firebaseApp, FirebaseApp.IdTokenListener idTokenListener) {
        firebaseApp.addIdTokenListener(idTokenListener);
    }

    public static void removeIdTokenListener(FirebaseApp firebaseApp, FirebaseApp.IdTokenListener idTokenListener) {
        firebaseApp.removeIdTokenListener(idTokenListener);
    }

    public static String getUid(FirebaseApp firebaseApp) throws FirebaseApiNotAvailableException {
        return firebaseApp.getUid();
    }
}
