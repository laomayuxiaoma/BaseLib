package searchcut.airr.searchview.model;

import android.view.View;

import java.util.List;

import searchcut.airr.searchview.icallback.ICallBack;

/**
 * @author wangfei
 * @date 2019/7/24.
 */
public class SearchDataDto extends SearchModelDto {

    private String title;
    private List<String> data;

    private View.OnClickListener onClickListener = null;
    private ICallBack onCallBackListener = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ICallBack getOnCallBackListener() {
        return onCallBackListener;
    }

    public void setOnCallBackListener(ICallBack onCallBackListener) {
        this.onCallBackListener = onCallBackListener;
    }

}
