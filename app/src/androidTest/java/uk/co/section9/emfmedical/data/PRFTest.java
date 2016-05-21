package uk.co.section9.emfmedical.data;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Vector;

/**
 * Created by oni on 21/05/2016.
 */
@RunWith(AndroidJUnit4.class)
public class PRFTest  extends AndroidTestCase {

    private PRFDatabase _db;

    @Before
    public void createDatabase(){
        _db = new PRFDatabase(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void createPRF() {
        PRF prf = new PRF();
        prf.dbNew(_db);
        Vector<String> prf_list = _db.listPRFS();
        assertEquals(prf_list.get(0).equals(prf.get_uuid()) , true);
    }
}
