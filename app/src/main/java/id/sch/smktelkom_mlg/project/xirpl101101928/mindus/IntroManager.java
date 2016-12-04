package id.sch.smktelkom_mlg.project.xirpl101101928.mindus;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by RafidiaAR on 12/3/2016.
 */

public class IntroManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public IntroManager (Context context){
        this.context=context;
        pref=context.getSharedPreferences("first",0);
        editor = pref.edit();
    }

    public void setFirst(boolean isFirst)
    {
        editor.putBoolean("check",isFirst);
        editor.commit();
    }

    public boolean check()
    {
        return pref.getBoolean("check",true);
    }
}
