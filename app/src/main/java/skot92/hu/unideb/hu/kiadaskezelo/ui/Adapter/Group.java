package skot92.hu.unideb.hu.kiadaskezelo.ui.Adapter;

/**
 * Created by skot9 on 2015. 12. 20..
 */
import java.util.ArrayList;
import java.util.List;

public class Group {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }

}