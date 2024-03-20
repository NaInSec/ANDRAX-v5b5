package com.thecrackertechnology.dragonterminal.component.pm;

import com.thecrackertechnology.dragonterminal.component.pm.NeoPackageParser;
import com.thecrackertechnology.dragonterminal.frontend.component.NeoComponent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class PackageComponent implements NeoComponent {
    private boolean isRefreshing = false;
    private final Object lock = new Object();
    /* access modifiers changed from: private */
    public HashMap<String, NeoPackageInfo> neoPackages;
    /* access modifiers changed from: private */
    public boolean queryEnabled = true;

    public void onServiceDestroy() {
    }

    public void onServiceObtained() {
    }

    private NeoPackageInfo getPackageInfo(String str) {
        if (this.queryEnabled) {
            return this.neoPackages.get(str);
        }
        return null;
    }

    public HashMap<String, NeoPackageInfo> getPackages() {
        return this.queryEnabled ? this.neoPackages : new HashMap<>();
    }

    public int getPackageCount() {
        if (this.queryEnabled) {
            return this.neoPackages.size();
        }
        return -1;
    }

    public SourceManager getSourceManager() {
        return new SourceManager();
    }

    public void reloadPackages(File file, boolean z) throws IOException {
        synchronized (this.lock) {
            if (!this.isRefreshing) {
                this.isRefreshing = true;
                tryParsePackages(file, z);
                synchronized (this.lock) {
                    this.isRefreshing = false;
                }
            }
        }
    }

    public void clearPackages() {
        if (!this.isRefreshing) {
            this.neoPackages.clear();
        }
    }

    private void tryParsePackages(File file, final boolean z) throws IOException {
        NeoPackageParser neoPackageParser = new NeoPackageParser(new FileInputStream(file));
        neoPackageParser.setStateListener(new NeoPackageParser.ParseStateListener() {
            public void onStartParsePackage(String str, NeoPackageInfo neoPackageInfo) {
            }

            public void onStartState() {
                boolean unused = PackageComponent.this.queryEnabled = false;
                if (z) {
                    PackageComponent.this.neoPackages.clear();
                }
            }

            public void onEndState() {
                boolean unused = PackageComponent.this.queryEnabled = true;
                for (NeoPackageInfo access$200 : PackageComponent.this.neoPackages.values()) {
                    PackageComponent.this.resolveDepends(access$200);
                }
            }

            public NeoPackageInfo onCreatePackageInfo() {
                return new NeoPackageInfo();
            }

            public void onEndParsePackage(NeoPackageInfo neoPackageInfo) {
                PackageComponent.this.neoPackages.put(neoPackageInfo.getPackageName(), neoPackageInfo);
            }
        });
        neoPackageParser.parse();
    }

    /* access modifiers changed from: private */
    public void resolveDepends(NeoPackageInfo neoPackageInfo) {
        String dependenciesString = neoPackageInfo.getDependenciesString();
        if (dependenciesString != null) {
            String[] split = dependenciesString.split(",");
            NeoPackageInfo[] neoPackageInfoArr = new NeoPackageInfo[split.length];
            neoPackageInfo.setDependencies(neoPackageInfoArr);
            for (int i = 0; i < split.length; i++) {
                neoPackageInfoArr[i] = getPackageInfo(split[i].trim());
            }
        }
    }

    public void onServiceInit() {
        this.neoPackages = new HashMap<>();
    }
}
