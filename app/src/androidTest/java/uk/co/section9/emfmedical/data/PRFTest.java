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
        _db.reset();
    }

    @Test
    public void createPRF() {
        PRF prf = new PRF();
        _db.newPRF(prf);
        Vector<String> prf_list = _db.listPRFS();
        if (prf_list.size() == 0 ){
            assertEquals(false,true);
        }
        assertEquals(prf_list.get(0).equals(prf.get_uuid()) , true);
    }
}
