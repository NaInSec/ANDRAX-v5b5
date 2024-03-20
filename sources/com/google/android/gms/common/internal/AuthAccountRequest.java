package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class AuthAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthAccountRequest> CREATOR = new zaa();
    private final int zale;
    @Deprecated
    private final IBinder zanw;
    private final Scope[] zanx;
    private Integer zany;
    private Integer zanz;
    private Account zax;

    AuthAccountRequest(int i, IBinder iBinder, Scope[] scopeArr, Integer num, Integer num2, Account account) {
        this.zale = i;
        this.zanw = iBinder;
        this.zanx = scopeArr;
        this.zany = num;
        this.zanz = num2;
        this.zax = account;
    }

    @Deprecated
    public AuthAccountRequest(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        this(3, iAccountAccessor.asBinder(), (Scope[]) set.toArray(new Scope[set.size()]), (Integer) null, (Integer) null, (Account) null);
    }

    public AuthAccountRequest(Account account, Set<Scope> set) {
        this(3, (IBinder) null, (Scope[]) set.toArray(new Scope[set.size()]), (Integer) null, (Integer) null, (Account) Preconditions.checkNotNull(account));
    }

    public Account getAccount() {
        Account account = this.zax;
        if (account != null) {
            return account;
        }
        IBinder iBinder = this.zanw;
        if (iBinder != null) {
            return AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder));
        }
        return null;
    }

    public Set<Scope> getScopes() {
        return new HashSet(Arrays.asList(this.zanx));
    }

    public AuthAccountRequest setOauthPolicy(@Nullable Integer num) {
        this.zany = num;
        return this;
    }

    @Nullable
    public Integer getOauthPolicy() {
        return this.zany;
    }

    public AuthAccountRequest setPolicyAction(@Nullable Integer num) {
        this.zanz = num;
        return this;
    }

    @Nullable
    public Integer getPolicyAction() {
        return this.zanz;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zale);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zanw, false);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zanx, i, false);
        SafeParcelWriter.writeIntegerObject(parcel, 4, this.zany, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, this.zanz, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zax, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
