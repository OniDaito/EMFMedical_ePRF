package uk.co.section9.emfmedical.data;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Vector;

/**
 * Created by oni on 21/05/2016.
 */
@RunWith(AndroidJUnit4.class)
public class PRFTest extends AndroidTestCase {

    private PRFDatabase _db;
    private PRF _prf;

    @Before
    public void createDatabase(){
        _db = new PRFDatabase(InstrumentationRegistry.getTargetContext());
        _db.reset();
        _prf = new PRF();
    }

    @Test
    public void createPRF() {
        _db.writePRF(_prf);
        Vector<String> prf_list = _db.listPRFS();
        if (prf_list.size() != 1 ){
            assertEquals(false,true);
        }
        //Log.d("TEST",prf_list.get(0) + ", " + _prf.get_uuid());
        assertEquals(prf_list.get(0).equals(_prf.get_uuid()) , true);
    }

    @Test
    public void updateAndReadPRF() {
        _db.writePRF(_prf);
        Vector<String> prf_list = _db.listPRFS();
        PRF prf = _db.readPRF(prf_list.elementAt(0));

        prf.get_primary().set_airway('o');
        _db.updatePRF(prf);

        assertEquals(prf.get_uuid().equals(prf_list.elementAt(0)), true);
        assertEquals(prf.get_primary().get_airway() == 'o', true);
    }

    @Test
    public void deletePRF() {
        _db.writePRF(_prf);
        Vector<String> prf_list0 = _db.listPRFS();
        _db.deletePRF(_prf);
        Vector<String> prf_list1 = _db.listPRFS();
        
        assertEquals(prf_list0.size() == 1, true);
        assertEquals(prf_list1.size() == 0, true);
    }
}
