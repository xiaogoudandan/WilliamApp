package com.william_zhang;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by william_zhang on 2018/3/27.
 */
@Database(name = DbFlowData.DBNAME,version = DbFlowData.VERSION)
public class DbFlowData {
    public static final String DBNAME="DbFlowData";
    public static final int VERSION=1;
}
