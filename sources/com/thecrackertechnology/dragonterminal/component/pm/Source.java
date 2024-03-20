package com.thecrackertechnology.dragonterminal.component.pm;

import com.thecrackertechnology.dragonterminal.framework.database.annotation.ID;
import com.thecrackertechnology.dragonterminal.framework.database.annotation.Table;

@Table
public class Source {
    public boolean enabled;
    @ID(autoIncrement = true)
    private int id;
    public String repo;
    public String url;

    public Source() {
    }

    public Source(String str, String str2, boolean z) {
        this.url = str;
        this.repo = str2;
        this.enabled = z;
    }
}
