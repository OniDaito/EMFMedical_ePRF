package uk.co.section9.emfmedical.data;

import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by oni on 21/05/2016.
 */

@RunWith(AndroidJUnit4.class) // Possibly deprecated? Who knows or really cares
public class PRFDatabaseTest extends AndroidTestCase{

    private PRFDatabase _db;

    @Before
    public void createDatabase(){
        _db = new PRFDatabase(InstrumentationRegistry.getTargetContext());
        _db.reset();
    }

    @Test
    public void checkTablesAreCreated() {
        String tables = _db.listTables();
        Log.d("OUTPUT",tables);
        assertEquals(tables.contains("incident"), true);
        assertEquals(tables.contains("primarysurvey"), true);
        assertEquals(tables.contains("secondarysurvey"), true);
        assertEquals(tables.contains("notes"), true);
        assertEquals(tables.contains("discharge"), true);
        assertEquals(tables.contains("observations"), true);
        assertEquals(tables.contains("serious"), true);
        assertEquals(tables.contains("treatment"), true);
        assertEquals(tables.contains("prfs"), true);
    }

    @After
    public void resetDatabase(){
        _db.reset();
    }

}