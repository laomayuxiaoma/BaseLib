package searchcut.airr.searchview.helper;

import java.util.ArrayList;
import java.util.List;

import searchcut.airr.searchview.model.SearchItem;
import searchcut.airr.searchview.model.SearchModelDto;


/**
 * @author wangfei
 * @date 2019/8/2.
 */
public class AssembleDataUtil {

    private static List<SearchItem> datas = new ArrayList<>();
    public static String HISTORY_RECORD = "0";

    public static List<SearchItem> getViews(List<SearchItem> list, SearchModelDto searchDataDto) {
        datas.clear();
        if (searchDataDto != null) {
            datas.add(new SearchItem(HISTORY_RECORD, searchDataDto));
        }
        if (list != null && 0 != list.size()) {
            datas.addAll(list);
        }
        return datas;
    }
}
